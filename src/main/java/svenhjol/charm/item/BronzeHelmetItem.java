package svenhjol.charm.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmArmorItem;

public class BronzeHelmetItem extends CharmArmorItem {
    private final CharmModule module;

    public BronzeHelmetItem(CharmModule module) {
        super(BronzeArmorMaterial.INSTANCE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
        this.register(module, "bronze_helmet");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
