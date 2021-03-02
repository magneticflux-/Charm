package svenhjol.charm.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmShovelItem;

public class BronzeShovelItem extends CharmShovelItem {
    private final CharmModule module;

    public BronzeShovelItem(CharmModule module) {
        super(BronzeToolMaterial.INSTANCE, 1.5F, -3.0F, (new Item.Settings()).group(ItemGroup.TOOLS));
        this.register(module, "bronze_shovel");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
