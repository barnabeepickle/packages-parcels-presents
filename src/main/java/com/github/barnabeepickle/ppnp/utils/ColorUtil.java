package com.github.barnabeepickle.ppnp.utils;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("unused")
public class ColorUtil {
    private static final int ALPHA = 255 << 24;

    /**
     * Clamps and converts RGB color to decimal color
     * @param r red (0 - 255)
     * @param g green (0 - 255)
     * @param b blue (0 - 255)
     * @return supplied RGB color in Decimal
     */
    public static int getColor(int r, int g, int b) {
        r = MathHelper.clamp(r, 0, 255);
        g = MathHelper.clamp(g, 0, 255);
        b = MathHelper.clamp(b, 0, 255);
        return color(r, g, b);
    }

    /**
     * Convert RGB color to decimal color with no safe guards, for safe guards use ColorUtil.getColor(r, g, b)
     * @param r red
     * @param g green
     * @param b blue
     * @return supplied RGB color in Decimal
     */
    public static int color(int r, int g, int b) {
        return ALPHA | (r << 16) | (g << 8) | b;
    }

    public static MapColor dyeColorToMapColor(EnumDyeColor dye) {
        return switch (dye) {
            case WHITE -> MapColor.SNOW;
            case SILVER -> MapColor.SILVER;
            case GRAY -> MapColor.GRAY;
            case BLACK -> MapColor.BLACK;
            case BROWN -> MapColor.BROWN;
            case RED -> MapColor.RED;
            case ORANGE -> MapColor.ADOBE;
            case YELLOW -> MapColor.YELLOW;
            case LIME -> MapColor.LIME;
            case GREEN -> MapColor.GREEN;
            case CYAN -> MapColor.CYAN;
            case LIGHT_BLUE -> MapColor.LIGHT_BLUE;
            case BLUE -> MapColor.BLUE;
            case PURPLE -> MapColor.PURPLE;
            case MAGENTA -> MapColor.MAGENTA;
            case PINK -> MapColor.PINK;
        };
    }
}