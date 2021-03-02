package svenhjol.charm.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmArmorItem;

public class BronzeChestplateItem extends CharmArmorItem {
    private final CharmModule module;

    public BronzeChestplateItem(CharmModule module) {
        super(BronzeArmorMaterial.INSTANCE, EquipmentSlot.CHEST, (new Settings()).group(ItemGroup.COMBAT));
        this.register(module, "bronze_chestplate");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
