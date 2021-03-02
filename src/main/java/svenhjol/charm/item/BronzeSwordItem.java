package svenhjol.charm.item;

import net.minecraft.item.ItemGroup;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.item.CharmSwordItem;

public class BronzeSwordItem extends CharmSwordItem {
    private final CharmModule module;

    public BronzeSwordItem(CharmModule module) {
        super(BronzeToolMaterial.INSTANCE, 3, -2.4F, (new Settings()).group(ItemGroup.COMBAT));
        this.register(module, "bronze_sword");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
