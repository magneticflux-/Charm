package svenhjol.charm.module;

import net.minecraft.item.ShieldItem;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.client.BronzeClient;
import svenhjol.charm.item.BronzeIngotItem;
import svenhjol.charm.item.BronzeShieldItem;
import svenhjol.charm.item.TinScrapItem;

@Module(mod = Charm.MOD_ID, client = BronzeClient.class, description = "Bronze tier tools and decoration.")
public class Bronze extends CharmModule {
    public static TinScrapItem TIN_SCRAP;
    public static BronzeIngotItem BRONZE_INGOT;
    public static ShieldItem BRONZE_SHIELD;

    @Override
    public void register() {
        TIN_SCRAP = new TinScrapItem(this);
        BRONZE_INGOT = new BronzeIngotItem(this);
        BRONZE_SHIELD = new BronzeShieldItem(this);
    }
}
