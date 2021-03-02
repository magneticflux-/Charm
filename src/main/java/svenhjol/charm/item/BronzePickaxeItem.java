package svenhjol.charm.item;

import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmPickaxeItem;

public class BronzePickaxeItem extends CharmPickaxeItem {
    private final CharmModule module;

    public BronzePickaxeItem(CharmModule module) {
        super(BronzeToolMaterial.INSTANCE, 1, -2.8F, (new Settings()).group(ItemGroup.TOOLS));
        this.register(module, "bronze_pickaxe");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
