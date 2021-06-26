package svenhjol.charm.mixin.respawn_in_nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm.handler.ModuleHandler;
import svenhjol.charm.module.respawn_in_nether.RespawnInNether;

import java.util.Optional;

@Mixin(Player.class)
public class FindSpaceForPlayer {
    @Inject(
        method = "findRespawnPositionAndUseSpawnBlock",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void hookFindRespawnPosition(ServerLevel level, BlockPos pos, float f, boolean bl, boolean bl2, CallbackInfoReturnable<Optional<Vec3>> cir) {
        if (ModuleHandler.enabled(RespawnInNether.class)) {
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            if (block == Blocks.RESPAWN_ANCHOR && state.getValue(RespawnAnchorBlock.CHARGE) > 0) {
                return;
            }

            if (level.dimension() == Level.NETHER
                && !(level.getBlockState(pos).getBlock() instanceof RespawnAnchorBlock)
                && RespawnInNether.respawnPosition.contains(pos)) {

                BlockPos portal = level.findNearestMapFeature(StructureFeature.RUINED_PORTAL, pos, 100, true);
                if (portal != null) {
                    boolean found = false;
                    int tries = 0;
                    BlockPos.MutableBlockPos spawn = new BlockPos.MutableBlockPos(portal.getX(), 48, portal.getZ());

                    while (!found && tries < 5) {

                        do {
                            BlockState spawnState = level.getBlockState(spawn);
                            BlockState below = level.getBlockState(spawn.below());
                            if (spawnState.isAir() && below.isFaceSturdy(level, spawn.below(), Direction.UP)) {
                                found = true;
                                break;
                            }

                            spawn.move(Direction.UP, 1);
                        } while (spawn.getY() < 96);

                        ++tries;
                    }

                    if (!found)
                        spawn.setY(64);

                    level.setBlock(spawn, Blocks.AIR.defaultBlockState(), 2);
                    level.setBlock(spawn.above(), Blocks.AIR.defaultBlockState(), 2);
                    level.setBlock(spawn.below(), Blocks.NETHERRACK.defaultBlockState(), 2);

                    RespawnInNether.respawnPosition.remove(pos);
                    cir.setReturnValue(Optional.of(new Vec3(spawn.getX() + 0.5D, spawn.getY() + 0.1D, spawn.getZ() + 0.5D)));
                }
            }
        }
    }
}
