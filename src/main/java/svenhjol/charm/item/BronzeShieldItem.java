package svenhjol.charm.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShieldItem;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.ICharmItem;

public class BronzeShieldItem extends ShieldItem implements ICharmItem {
    private final CharmModule module;

    public BronzeShieldItem(CharmModule module) {
        super((new Item.Settings()).maxDamage(990).group(ItemGroup.COMBAT));
        this.register(module, "bronze_shield");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
