package com.github.barnabeepickle.ppnp.content.blocks.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public class PresentBlockPrefix extends PresentBlock {
    public PresentBlockPrefix(String prefix, MapColor mapColor) {
        this(prefix, mapColor, primaryCreativeTab);
    }
    public PresentBlockPrefix(String prefix, MapColor mapColor, CreativeTabs creativeTab) {
        super(mapColor, creativeTab);
        this.name = prefix + "_present";
    }

    @Nonnull
    private final String name;

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
