package svenhjol.charm.module;

import net.minecraft.util.Identifier;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.handler.ModuleHandler;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.block.BronzeLanternBlock;
import svenhjol.charm.block.GoldLanternBlock;
import svenhjol.charm.client.LanternsClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Module(mod = Charm.MOD_ID, client = LanternsClient.class, description = "Gold version of the vanilla lanterns.")
public class Lanterns extends CharmModule {
    public static GoldLanternBlock GOLD_LANTERN;
    public static GoldLanternBlock GOLD_SOUL_LANTERN;
    public static BronzeLanternBlock BRONZE_LANTERN;
    public static BronzeLanternBlock BRONZE_SOUL_LANTERN;

    @Override
    public void register() {
        GOLD_LANTERN = new GoldLanternBlock(this, "gold_lantern");
        GOLD_SOUL_LANTERN = new GoldLanternBlock(this, "gold_soul_lantern");
        BRONZE_LANTERN = new BronzeLanternBlock(this, "bronze_lantern");
        BRONZE_SOUL_LANTERN = new BronzeLanternBlock(this, "bronze_soul_lantern");
    }

    @Override
    public List<Identifier> getRecipesToRemove() {
        List<Identifier> remove = new ArrayList<>();

        if (!ModuleHandler.enabled("charm:bronze_nuggets")) {
            remove.addAll(Arrays.asList(
                new Identifier(Charm.MOD_ID, "lanterns/bronze_lantern"),
                new Identifier(Charm.MOD_ID, "lanterns/bronze_soul_lantern")
            ));
        }

        return remove;
    }
}
