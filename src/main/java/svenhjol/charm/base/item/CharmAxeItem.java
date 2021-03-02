package svenhjol.charm.base.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public abstract class CharmAxeItem extends AxeItem implements ICharmItem {
    public CharmAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
