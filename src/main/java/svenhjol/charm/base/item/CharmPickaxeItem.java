package svenhjol.charm.base.item;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public abstract class CharmPickaxeItem extends PickaxeItem implements ICharmItem {
    public CharmPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
