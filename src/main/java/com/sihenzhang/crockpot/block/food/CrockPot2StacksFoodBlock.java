package com.sihenzhang.crockpot.block.food;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CrockPot2StacksFoodBlock extends AbstractStackableFoodBlock {
    public static final IntegerProperty STACKS = IntegerProperty.create("stacks", 1, 2);

    public CrockPot2StacksFoodBlock() {
        super();
    }

    public CrockPot2StacksFoodBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxStacks() {
        return 2;
    }

    @Override
    public IntegerProperty getStacksProperty() {
        return STACKS;
    }
}
