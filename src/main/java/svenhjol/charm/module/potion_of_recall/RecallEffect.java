package svenhjol.charm.module.potion_of_recall;

import net.minecraft.world.effect.MobEffectCategory;
import svenhjol.charm.module.CharmModule;
import svenhjol.charm.potion.CharmStatusEffect;

public class RecallEffect extends CharmStatusEffect {
    protected RecallEffect(CharmModule module) {
        super(module, "recall", MobEffectCategory.BENEFICIAL, 0xEF20FF);
    }
}
