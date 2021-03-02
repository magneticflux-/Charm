package svenhjol.charm.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmArmorItem;

public class BronzeBootsItem extends CharmArmorItem {
    private final CharmModule module;

    public BronzeBootsItem(CharmModule module) {
        super(BronzeArmorMaterial.INSTANCE, EquipmentSlot.FEET, (new Settings()).group(ItemGroup.COMBAT));
        this.register(module, "bronze_boots");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
