package com.sihenzhang.crockpot.integration.jei.gui.requirement;

import com.sihenzhang.crockpot.base.FoodCategory;
import com.sihenzhang.crockpot.integration.jei.FoodValuesDefinitionCache;
import com.sihenzhang.crockpot.recipe.cooking.requirement.RequirementCategoryMaxExclusive;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class DrawableRequirementCategoryMaxExclusive extends AbstractDrawableRequirement<RequirementCategoryMaxExclusive> {
    public DrawableRequirementCategoryMaxExclusive(RequirementCategoryMaxExclusive requirement) {
        super(requirement, Component.translatable("integration.crockpot.jei.crock_pot_cooking.requirement.lt", requirement.getMax()));
    }

    @Override
    public int getWidth() {
        return 23 + Minecraft.getInstance().font.width(description);
    }

    @Override
    public int getHeight() {
        return 22;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        super.draw(guiGraphics, xOffset, yOffset);
        guiGraphics.drawString(Minecraft.getInstance().font, description, xOffset + 20, yOffset + 7, 0, false);
    }

    @Override
    public List<ItemStack> getInvisibleInputs() {
        return List.copyOf(FoodValuesDefinitionCache.getMatchedItems(requirement.getCategory()));
    }

    @Override
    public List<GuiItemStacksInfo> getGuiItemStacksInfos(int xOffset, int yOffset) {
        return List.of(new GuiItemStacksInfo(List.of(FoodCategory.getItemStack(requirement.getCategory())), xOffset + 3, yOffset + 3));
    }
}
