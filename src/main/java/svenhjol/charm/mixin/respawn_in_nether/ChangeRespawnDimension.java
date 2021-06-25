package svenhjol.charm.mixin.respawn_in_nether;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import svenhjol.charm.handler.ModuleHandler;
import svenhjol.charm.module.respawn_in_nether.RespawnInNether;

import java.util.Random;

@Mixin(ServerGamePacketListenerImpl.class)
public class ChangeRespawnDimension {
    @Redirect(
        method = "handleClientCommand",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/players/PlayerList;respawn(Lnet/minecraft/server/level/ServerPlayer;Z)Lnet/minecraft/server/level/ServerPlayer;",
            ordinal = 1
        )
    )
    private ServerPlayer hookHandleClientCommand(PlayerList playerList, ServerPlayer serverPlayer, boolean bl) {
        if (ModuleHandler.enabled(RespawnInNether.class)) {
            Random random = new Random();
            BlockPos pos = new BlockPos(random.nextInt(5000) - 2500, 0, random.nextInt(5000) - 2500);

            serverPlayer.setRespawnPosition(Level.NETHER, pos, 90.0F, true, false);
            RespawnInNether.respawnPosition.add(pos);
        }

        // vanilla call
        return playerList.respawn(serverPlayer, bl);
    }
}
