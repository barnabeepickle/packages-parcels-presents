package com.github.barnabeepickle.ppnp.content.items.base;

import com.github.barnabeepickle.ppnp.utils.IModContent;
import net.minecraft.item.Item;

public abstract class ModItem extends Item implements IModContent {
    public abstract String getName();
}
