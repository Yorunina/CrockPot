package com.sihenzhang.crockpot.recipe;

import com.google.gson.*;
import com.sihenzhang.crockpot.utils.JsonUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.MathHelper;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;

public class PiglinBarteringRecipe implements Predicate<ItemStack> {
    private static final Random RAND = new Random();
    private final Ingredient input;
    private final List<WeightedItem> weightedOutputs;

    public PiglinBarteringRecipe(Ingredient input, List<WeightedItem> weightedOutputs) {
        this.input = input;
        this.weightedOutputs = new ArrayList<>();
        weightedOutputs.forEach(weightedItem -> {
            WeightedItem dummy = null;
            Iterator<WeightedItem> iterator = this.weightedOutputs.iterator();
            while (iterator.hasNext()) {
                WeightedItem e = iterator.next();
                if (e.equals(weightedItem)) {
                    dummy = new WeightedItem(e.item, e.min, e.max, e.weight + weightedItem.weight);
                    iterator.remove();
                }
            }
            if (dummy != null) {
                this.weightedOutputs.add(dummy);
            } else {
                this.weightedOutputs.add(weightedItem);
            }
        });
    }

    public Ingredient getInput() {
        return this.input;
    }

    public List<WeightedItem> getWeightedOutputs() {
        return this.weightedOutputs;
    }

    public ItemStack createOutput() {
        WeightedItem weightedItem = WeightedRandom.getRandomItem(RAND, this.weightedOutputs);
        if (weightedItem.isRanged()) {
            return new ItemStack(weightedItem.item, MathHelper.nextInt(RAND, weightedItem.min, weightedItem.max));
        } else {
            return new ItemStack(weightedItem.item, weightedItem.min);
        }
    }

    @Override
    public boolean test(ItemStack stack) {
        return this.input.test(stack);
    }

    public static class Serializer implements JsonDeserializer<PiglinBarteringRecipe>, JsonSerializer<PiglinBarteringRecipe> {
        @Override
        public PiglinBarteringRecipe deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Ingredient input = JsonUtils.getIngredient(object, "input");
            List<WeightedItem> weightedOutputs = new ArrayList<>();
            JsonArray outputs = JSONUtils.getAsJsonArray(object, "outputs");
            for (JsonElement output : outputs) {
                WeightedItem weightedItem = JsonUtils.getWeightedItem(output, "output");
                if (weightedItem != null) {
                    weightedOutputs.add(weightedItem);
                }
            }
            return new PiglinBarteringRecipe(input, weightedOutputs);
        }

        @Override
        public JsonElement serialize(PiglinBarteringRecipe src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.add("input", src.input.toJson());
            JsonArray array = new JsonArray();
            src.weightedOutputs.forEach(e -> {
                JsonObject weightedItem = new JsonObject();
                weightedItem.addProperty("item", Objects.requireNonNull(e.item.getRegistryName()).toString());
                if (e.isRanged()) {
                    JsonObject count = new JsonObject();
                    count.addProperty("min", e.min);
                    count.addProperty("max", e.max);
                    weightedItem.add("count", count);
                } else {
                    weightedItem.addProperty("count", e.min);
                }
                weightedItem.addProperty("weight", e.weight);
                array.add(weightedItem);
            });
            object.add("outputs", array);
            return object;
        }
    }
}
