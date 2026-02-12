package com.github.barnabeepickle.ppnp.content.tabs;

import com.github.barnabeepickle.ppnp.Tags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static com.github.barnabeepickle.ppnp.events.ModItems.ITEM_RED_PRESENT;

public class ModPrimaryTab extends CreativeTabs {
    public ModPrimaryTab() {
        super(Tags.MODID + ".primary");
    }

    @Override
    public ItemStack createIcon() {
        return ITEM_RED_PRESENT.getDefaultInstance();
    }
}
