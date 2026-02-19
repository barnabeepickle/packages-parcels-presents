package com.github.barnabeepickle.ppnp.content.items;

import com.github.barnabeepickle.ppnp.content.ModCreativeTabs;

import javax.annotation.Nonnull;

public class CardboardItem extends ModItem {
    public CardboardItem() {
        this.setTranslationKey(name);
        this.setCreativeTab(ModCreativeTabs.primaryCreativeTab);
    }

    @Nonnull
    private static final String name = "cardboard";

    @Override
    public String getName() {
        return name;
    }
}
