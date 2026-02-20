package com.github.barnabeepickle.ppnp.content.items.base;

import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public class WrappingPaperItem extends ModItem {
    public WrappingPaperItem(String prefix) {
        this(prefix, primaryCreativeTab);
    }

    public WrappingPaperItem(String prefix, CreativeTabs creativeTab) {
        this.name = prefix + "_wrapping_paper";
        this.setCreativeTab(creativeTab);
        this.setTranslationKey(name);
    }

    @Nonnull
    private final String name;

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
