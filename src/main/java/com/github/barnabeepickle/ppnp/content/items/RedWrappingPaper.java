package com.github.barnabeepickle.ppnp.content.items;

import com.github.barnabeepickle.ppnp.content.ModCreativeTabs;
import com.github.barnabeepickle.ppnp.content.items.base.WrappingPaperItem;

import javax.annotation.Nonnull;

public class RedWrappingPaper extends WrappingPaperItem {
    public RedWrappingPaper() {
        this.setTranslationKey(name);
        this.setCreativeTab(ModCreativeTabs.primaryCreativeTab);
    }

    @Nonnull
    private final String name = "red_" + super.getName();

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
