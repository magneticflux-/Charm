package svenhjol.charm.module.potion_of_recall;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import svenhjol.charm.module.CharmModule;
import svenhjol.charm.potion.CharmPotion;

public class LongRecallPotion extends CharmPotion {
    public LongRecallPotion(CharmModule module) {
        super(module, "long_recall", "recall", new MobEffectInstance(PotionOfRecall.RECALL_EFFECT, 9600));
        registerRecipe(PotionOfRecall.RECALL_POTION, Items.REDSTONE);
    }
}
