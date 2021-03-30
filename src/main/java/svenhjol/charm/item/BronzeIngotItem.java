package svenhjol.charm.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmItem;

public class BronzeIngotItem extends CharmItem {
    public BronzeIngotItem(CharmModule module) {
        super(module, "bronze_ingot", (new Item.Settings()).group(ItemGroup.MATERIALS));
    }
}
