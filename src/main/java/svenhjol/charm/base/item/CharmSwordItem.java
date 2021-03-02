package svenhjol.charm.base.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public abstract class CharmSwordItem extends SwordItem implements ICharmItem {
    public CharmSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
