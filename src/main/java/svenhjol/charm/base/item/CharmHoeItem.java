package svenhjol.charm.base.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public abstract class CharmHoeItem extends HoeItem implements ICharmItem {
    public CharmHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
