package svenhjol.charm.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmItem;

public class TinScrapItem extends CharmItem {
    public TinScrapItem(CharmModule module) {
        super(module, "tin_scrap", (new Item.Settings()).group(ItemGroup.MATERIALS));
    }
}
