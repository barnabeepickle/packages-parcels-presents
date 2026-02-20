package com.github.barnabeepickle.ppnp.content.blocks;

import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import net.minecraft.block.material.MapColor;

import javax.annotation.Nonnull;

public class PurplePresent extends PresentBlock {
    public PurplePresent() {
        super(MapColor.PURPLE);
        this.setTranslationKey(name);
    }

    @Nonnull
    private static final String name = "purple_present";

    @Override
    public String getName() {
        return name;
    }
}
