package com.github.barnabeepickle.ppnp.content.blocks;

import com.github.barnabeepickle.ppnp.content.blocks.base.PresentBlock;
import jakarta.annotation.Nonnull;
import net.minecraft.block.material.MapColor;

public class RedPresent extends PresentBlock {
    public RedPresent() {
        super(MapColor.RED);
        this.setTranslationKey(name);
    }

    @Nonnull
    private static final String name = "red_present";

    public String getName() {
        return name;
    }
}
