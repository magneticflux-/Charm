package svenhjol.charm.module;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolItem;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.client.BronzeClient;
import svenhjol.charm.item.*;

@Module(mod = Charm.MOD_ID, client = BronzeClient.class)
public class Bronze extends CharmModule {
    public static BronzeIngot BRONZE_INGOT;
    public static ToolItem BRONZE_AXE;
    public static ToolItem BRONZE_HOE;
    public static ToolItem BRONZE_PICKAXE;
    public static ToolItem BRONZE_SHOVEL;
    public static ToolItem BRONZE_SWORD;
    public static ArmorItem BRONZE_HELMET;
    public static ArmorItem BRONZE_CHESTPLATE;
    public static ArmorItem BRONZE_LEGGINGS;
    public static ArmorItem BRONZE_BOOTS;
    public static ShieldItem BRONZE_SHIELD;

    @Override
    public void register() {
        BRONZE_INGOT = new BronzeIngot(this);
        BRONZE_AXE = new BronzeAxeItem(this);
        BRONZE_HOE = new BronzeHoeItem(this);
        BRONZE_PICKAXE = new BronzePickaxeItem(this);
        BRONZE_SHOVEL = new BronzeShovelItem(this);
        BRONZE_SWORD = new BronzeSwordItem(this);
        BRONZE_HELMET = new BronzeHelmetItem(this);
        BRONZE_CHESTPLATE = new BronzeChestplateItem(this);
        BRONZE_LEGGINGS = new BronzeLeggingsItem(this);
        BRONZE_BOOTS = new BronzeBootsItem(this);
        BRONZE_SHIELD = new BronzeShieldItem(this);
    }
}
