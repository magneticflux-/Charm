package svenhjol.charm.module;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.mixin.accessor.GoatBrainAccessor;

@Module(mod = Charm.MOD_ID)
public class GoatImprovements extends CharmModule {
    @Override
    public void init() {
        GoatBrainAccessor.setTargetPredicate((new TargetPredicate()).setPredicate((livingEntity)
            -> !livingEntity.getType().equals(EntityType.GOAT) && !livingEntity.getType().equals(EntityType.ARMOR_STAND)));
    }
}
