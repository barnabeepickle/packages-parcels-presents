package com.github.barnabeepickle.ppnp.content.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class ModBlock extends Block {
    public ModBlock(Material material, MapColor color) {
        super(material, color);
    }

    public abstract String getName();
}
