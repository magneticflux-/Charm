package svenhjol.charm.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm.event.AddEntityCallback;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(
        method = "addEntity",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/server/world/ServerWorld.getChunk(IILnet/minecraft/world/chunk/ChunkStatus;Z)Lnet/minecraft/world/chunk/Chunk;"
        )
    )
    private void hookAddEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        AddEntityCallback.EVENT.invoker().interact(entity);
    }

    @Inject(
        method = "loadEntity",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/server/world/ServerWorld.loadEntityUnchecked(Lnet/minecraft/entity/Entity;)V"
        )
    )
    private void hookLoadEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        AddEntityCallback.EVENT.invoker().interact(entity);
    }
}
