package svenhjol.charm.base.item;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public abstract class CharmShovelItem extends ShovelItem implements ICharmItem {
    public CharmShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
