package com.sihenzhang.crockpot.integration.jei;

import com.sihenzhang.crockpot.base.FoodCategory;
import com.sihenzhang.crockpot.recipe.FoodValuesDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class FoodValuesDefinitionCache {
    private static Map<FoodCategory, Set<ItemStack>> CACHE;

    private FoodValuesDefinitionCache() {
    }

    public static void regenerate(Level level) {
        CACHE = null;
        var map = new EnumMap<FoodCategory, Set<ItemStack>>(FoodCategory.class);
        for (var category : FoodCategory.values()) {
            map.put(category, FoodValuesDefinition.getMatchedItems(category, level));
        }
        CACHE = Map.copyOf(map);
    }

    public static Set<ItemStack> getMatchedItems(FoodCategory category) {
        return CACHE == null ? Set.of() : CACHE.get(category);
    }
}
