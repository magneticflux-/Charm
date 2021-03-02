package svenhjol.charm.item;

import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmHoeItem;

public class BronzeHoeItem extends CharmHoeItem {
    private final CharmModule module;

    public BronzeHoeItem(CharmModule module) {
        super(BronzeToolMaterial.INSTANCE, -2, -1.0F, (new Settings()).group(ItemGroup.TOOLS));
        this.register(module, "bronze_hoe");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
