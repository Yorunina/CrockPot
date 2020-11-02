package com.sihenzhang.crockpot.item;

import com.sihenzhang.crockpot.CrockPot;
import com.sihenzhang.crockpot.item.food.FoodUseDuration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrockPotAlwaysEdibleItemFood extends Item {
    private final int useDuration;
    private final boolean isDrink;

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, int useDuration, boolean isDrink) {
        super(new Properties().group(CrockPot.ITEM_GROUP).food(new Food.Builder().hunger(hunger).saturation(saturation).setAlwaysEdible().build()));
        this.useDuration = useDuration;
        this.isDrink = isDrink;
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, FoodUseDuration useDuration, boolean isDrink) {
        this(hunger, saturation, useDuration.val, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, int useDuration) {
        this(hunger, saturation, useDuration, false);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, FoodUseDuration useDuration) {
        this(hunger, saturation, useDuration.val);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, boolean isDrink) {
        this(hunger, saturation, FoodUseDuration.NORMAL, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation) {
        this(hunger, saturation, FoodUseDuration.NORMAL, false);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect, int useDuration, boolean isDrink) {
        super(new Properties().group(CrockPot.ITEM_GROUP).food(new Food.Builder().hunger(hunger).saturation(saturation).effect(effect, 1.0F).setAlwaysEdible().build()));
        this.useDuration = useDuration;
        this.isDrink = isDrink;
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect, FoodUseDuration useDuration, boolean isDrink) {
        this(hunger, saturation, effect, useDuration.val, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect, int useDuration) {
        this(hunger, saturation, effect, useDuration, false);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect, FoodUseDuration useDuration) {
        this(hunger, saturation, effect, useDuration.val);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect, boolean isDrink) {
        this(hunger, saturation, effect, FoodUseDuration.NORMAL, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect) {
        this(hunger, saturation, effect, FoodUseDuration.NORMAL, false);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2, int useDuration, boolean isDrink) {
        super(new Properties().group(CrockPot.ITEM_GROUP).food(new Food.Builder().hunger(hunger).saturation(saturation).effect(effect1, 1.0F).effect(effect2, 1.0F).setAlwaysEdible().build()));
        this.useDuration = useDuration;
        this.isDrink = isDrink;
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2, FoodUseDuration useDuration, boolean isDrink) {
        this(hunger, saturation, effect1, effect2, useDuration.val, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2, int useDuration) {
        this(hunger, saturation, effect1, effect2, useDuration, false);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2, FoodUseDuration useDuration) {
        this(hunger, saturation, effect1, effect2, useDuration.val);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2, boolean isDrink) {
        this(hunger, saturation, effect1, effect2, FoodUseDuration.NORMAL, isDrink);
    }

    public CrockPotAlwaysEdibleItemFood(int hunger, float saturation, Supplier<EffectInstance> effect1, Supplier<EffectInstance> effect2) {
        this(hunger, saturation, effect1, effect2, FoodUseDuration.NORMAL, false);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.useDuration;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (isDrink) {
            return UseAction.DRINK;
        } else {
            return super.getUseAction(stack);
        }
    }

    @Override
    public SoundEvent getEatSound() {
        if (isDrink) {
            return SoundEvents.ENTITY_GENERIC_DRINK;
        } else {
            return super.getEatSound();
        }
    }
}
