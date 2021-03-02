package svenhjol.charm.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmArmorItem;

public class BronzeLeggingsItem extends CharmArmorItem {
    private final CharmModule module;

    public BronzeLeggingsItem(CharmModule module) {
        super(BronzeArmorMaterial.INSTANCE, EquipmentSlot.LEGS, (new Settings()).group(ItemGroup.COMBAT));
        this.register(module, "bronze_leggings");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
