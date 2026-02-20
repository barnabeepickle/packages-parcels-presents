package com.github.barnabeepickle.ppnp.content.blocks.base;

import com.github.barnabeepickle.ppnp.utils.IModContent;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class ModBlock extends Block implements IModContent {
    public ModBlock(Material material, MapColor color) {
        super(material, color);
    }

    public abstract String getName();
}
