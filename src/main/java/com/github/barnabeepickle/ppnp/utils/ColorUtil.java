package com.github.barnabeepickle.ppnp.utils;

import net.minecraft.util.math.MathHelper;

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
}