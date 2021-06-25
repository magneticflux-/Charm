package svenhjol.charm.module.potion_of_recall;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import svenhjol.charm.module.CharmModule;
import svenhjol.charm.potion.CharmPotion;

public class RecallPotion extends CharmPotion {
    public RecallPotion(CharmModule module) {
        super(module, "recall", new MobEffectInstance(PotionOfRecall.RECALL_EFFECT, 3600));
        registerRecipe(Potions.AWKWARD, Items.CHORUS_FLOWER);
    }
}
