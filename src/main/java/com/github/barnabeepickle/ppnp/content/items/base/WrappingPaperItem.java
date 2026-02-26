package com.github.barnabeepickle.ppnp.content.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;

import javax.annotation.Nonnull;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public class WrappingPaperItem extends ModItem {
    public WrappingPaperItem(EnumDyeColor dye) {
        this(dye, dye.getDyeColorName());
    }

    public WrappingPaperItem(EnumDyeColor dye, String prefix) {
        this(dye, prefix, primaryCreativeTab);
    }

    public WrappingPaperItem(EnumDyeColor dye, CreativeTabs creativeTab) {
        this(dye, dye.getDyeColorName(), creativeTab);
    }

    public WrappingPaperItem(EnumDyeColor dye, String prefix, CreativeTabs creativeTab) {
        this.dyeColor = dye;
        this.name = prefix + "_wrapping_paper";
        this.setCreativeTab(creativeTab);
        this.setTranslationKey(this.name);
    }

    private final EnumDyeColor dyeColor;

    public EnumDyeColor getDyeColor() {
        return this.dyeColor;
    }

    @Nonnull
    private final String name;

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
