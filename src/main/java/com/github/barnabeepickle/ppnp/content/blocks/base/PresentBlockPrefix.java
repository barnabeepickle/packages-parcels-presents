package com.github.barnabeepickle.ppnp.content.blocks.base;

import com.github.barnabeepickle.ppnp.utils.ColorUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;

import javax.annotation.Nonnull;

import static com.github.barnabeepickle.ppnp.content.ModCreativeTabs.primaryCreativeTab;

public class PresentBlockPrefix extends PresentBlock {
    public PresentBlockPrefix(EnumDyeColor dye) {
        this(dye, primaryCreativeTab);
    }

    public PresentBlockPrefix(EnumDyeColor dye, CreativeTabs creativeTab) {
        this(dye, dye.getDyeColorName(), creativeTab);
    }

    public PresentBlockPrefix(EnumDyeColor dye, String prefix) {
        this(dye, prefix, primaryCreativeTab);
    }

    public PresentBlockPrefix(EnumDyeColor dye, String prefix, CreativeTabs creativeTab) {
        super(ColorUtil.dyeColorToMapColor(dye), creativeTab);
        this.dyeColor = dye;
        this.name = prefix + "_present";
        this.setTranslationKey(this.name);
    }

    private final EnumDyeColor dyeColor;

    public EnumDyeColor getDyeColor() {
        return this.dyeColor;
    }

    @Nonnull
    private final String name;

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
