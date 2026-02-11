package com.github.barnabeepickle.basketbottlebox.content.blocks;

import com.github.barnabeepickle.basketbottlebox.content.blocks.base.PresentBlock;
import jakarta.annotation.Nonnull;
import net.minecraft.block.material.MapColor;

public class RedPresent extends PresentBlock {
    public RedPresent() {
        super(MapColor.RED);
        this.setTranslationKey(name);
    }

    @Nonnull
    private static final String name = "red_present_block";

    public String getName() {
        return name;
    }
}
