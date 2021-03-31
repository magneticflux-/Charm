package svenhjol.charm.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import svenhjol.charm.base.handler.ModuleHandler;
import svenhjol.charm.module.Bronze;

import java.util.Random;

public class TinScrapLootFunction extends ConditionalLootFunction {

    public TinScrapLootFunction(LootCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        if (!ModuleHandler.enabled("charm:bronze"))
            return stack;

        return tryCreateItem(stack, context);
    }

    private ItemStack tryCreateItem(ItemStack stack, LootContext context) {
        Random random = context.getRandom();
        return new ItemStack(Bronze.TIN_SCRAP, random.nextInt(24) + 4);
    }

    @Override
    public LootFunctionType getType() {
        return Bronze.TIN_SCRAP_LOOT_FUNCTION;
    }

    public static class Serializer extends ConditionalLootFunction.Serializer<TinScrapLootFunction> {
        @Override
        public TinScrapLootFunction fromJson(JsonObject json, JsonDeserializationContext context, LootCondition[] conditions) {
            return new TinScrapLootFunction(conditions);
        }
    }
}
