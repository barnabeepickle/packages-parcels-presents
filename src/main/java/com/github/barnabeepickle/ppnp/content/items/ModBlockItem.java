package com.github.barnabeepickle.ppnp.content.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public class ModBlockItem extends ItemBlock {
    public ModBlockItem(Block block) {
        super(block);
        this.setCreativeTab(primaryCreativeTab);
    }

    public ModBlockItem(Block block, CreativeTabs creativeTab) {
        super(block);
        this.setCreativeTab(creativeTab);
    }
}
