package svenhjol.charm.module;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Config;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.mixin.accessor.GoatBrainAccessor;

@Module(mod = Charm.MOD_ID, description = "Goats are cool.")
public class GoatImprovements extends CharmModule {
    @Config(name = "Overly aggressive goats", description = "If true, goats will ram nearby entities much more frequently.")
    public static boolean aggressive = false;

    @Override
    public void init() {
        GoatBrainAccessor.setTargetPredicate((new TargetPredicate()).setPredicate((livingEntity)
            -> !livingEntity.getType().equals(EntityType.GOAT) && !livingEntity.getType().equals(EntityType.ARMOR_STAND)));

        if (aggressive)
            GoatBrainAccessor.setRamTicks(UniformIntProvider.create(60, 120));
    }
}
