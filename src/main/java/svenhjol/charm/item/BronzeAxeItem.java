package svenhjol.charm.item;

import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmAxeItem;

public class BronzeAxeItem extends CharmAxeItem {
    private final CharmModule module;

    public BronzeAxeItem(CharmModule module) {
        super(BronzeToolMaterial.INSTANCE, 6.0F, -3.1F, (new Settings()).group(ItemGroup.TOOLS));
        this.register(module, "bronze_axe");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
