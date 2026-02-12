package com.github.barnabeepickle.ppnp.content.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ModBlockItem extends ItemBlock {
    public ModBlockItem(Block block) {
        super(block);
    }

    public ModBlockItem(Block block, int stackSize) {
        super(block);
        this.setMaxStackSize(stackSize);
    }
}
