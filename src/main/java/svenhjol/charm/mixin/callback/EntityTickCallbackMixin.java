package svenhjol.charm.mixin.callback;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.event.EntityTickCallback;

@Mixin(LivingEntity.class)
public class EntityTickCallbackMixin {

    /**
     * Fires the {@link EntityTickCallback} event.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void hookTick(CallbackInfo ci) {
        EntityTickCallback.EVENT.invoker().interact((LivingEntity)(Object)this);
    }
}
