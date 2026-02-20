package com.github.barnabeepickle.ppnp.content.items;

import com.github.barnabeepickle.ppnp.content.ModCreativeTabs;
import com.github.barnabeepickle.ppnp.content.items.base.ModItem;

import javax.annotation.Nonnull;

public class RedWrappingPaper extends ModItem {
    public RedWrappingPaper() {
        this.setTranslationKey(name);
        this.setCreativeTab(ModCreativeTabs.primaryCreativeTab);
    }

    @Nonnull
    private static final String name = "red_wrapping_paper";

    @Override
    public String getName() {
        return name;
    }
}
