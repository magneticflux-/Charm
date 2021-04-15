package svenhjol.charm.mixin.accessor;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.GoatBrain;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GoatBrain.class)
public interface GoatBrainAccessor {
    @Accessor("field_33500")
    static void setTargetPredicate(TargetPredicate targetPredicate) {
        throw new IllegalStateException();
    }

    @Accessor("field_33499")
    static void setRamTicks(UniformIntProvider uniformIntProvider) {
        throw new IllegalStateException();
    }
}
