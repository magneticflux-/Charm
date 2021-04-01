package svenhjol.charm.module;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.handler.RegistryHandler;
import svenhjol.charm.base.iface.Module;
import svenhjol.charm.item.BronzeIngotItem;
import svenhjol.charm.item.TinScrapItem;
import svenhjol.charm.loot.TinScrapLootFunction;

@Module(mod = Charm.MOD_ID, description = "Bronze tools and decoration.")
public class Bronze extends CharmModule {
    public static final Identifier TIN_SCRAP_LOOT_ID = new Identifier(Charm.MOD_ID, "tin_scrap");
    public static LootFunctionType TIN_SCRAP_LOOT_FUNCTION;

    public static TinScrapItem TIN_SCRAP;
    public static BronzeIngotItem BRONZE_INGOT;

    @Override
    public void register() {
        TIN_SCRAP = new TinScrapItem(this);
        BRONZE_INGOT = new BronzeIngotItem(this);
        TIN_SCRAP_LOOT_FUNCTION = RegistryHandler.lootFunctionType(TIN_SCRAP_LOOT_ID, new LootFunctionType(new TinScrapLootFunction.Serializer()));
    }

    @Override
    public void init() {
        LootTableLoadingCallback.EVENT.register(this::handleLootTables);
    }

    private void handleLootTables(ResourceManager resourceManager, LootManager lootManager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
            FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(Items.AIR)
                    .weight(1)
                    .apply(() -> new TinScrapLootFunction(new LootCondition[0])));

            supplier.pool(builder);
        }
    }
}
