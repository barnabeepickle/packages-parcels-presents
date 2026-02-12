package com.github.barnabeepickle.ppnp.content.tabs;

import com.github.barnabeepickle.ppnp.Tags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModPrimaryTab extends CreativeTabs {
    public ModPrimaryTab() {
        super(Tags.MODID + ".primary");
    }

    @Override
    public ItemStack createIcon() {
        return null; // TODO
    }
}
