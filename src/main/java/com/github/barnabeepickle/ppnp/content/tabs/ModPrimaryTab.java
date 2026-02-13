package com.github.barnabeepickle.ppnp.content.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.github.barnabeepickle.ppnp.events.ModItems.ITEM_RED_PRESENT;

public class ModPrimaryTab extends CreativeTabs {
    public ModPrimaryTab() {
        super("primary");
    }

    @Override
    public @Nonnull ItemStack createIcon() {
        return ITEM_RED_PRESENT.getDefaultInstance();
    }
}
