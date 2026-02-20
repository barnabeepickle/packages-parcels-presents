package com.github.barnabeepickle.ppnp.content.items;

import com.github.barnabeepickle.ppnp.content.ModCreativeTabs;

import javax.annotation.Nonnull;

public class CardboardBoxItem extends CardboardItem {
    public CardboardBoxItem() {
        this.setTranslationKey(name);
        this.setCreativeTab(ModCreativeTabs.primaryCreativeTab);
    }

    @Nonnull
    private static final String name = "cardboard_box";

    @Override
    public String getName() {
        return name;
    }
}
