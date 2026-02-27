package com.github.barnabeepickle.ppnp.utils.bb;

import net.minecraft.util.math.AxisAlignedBB;

public class BoundingBox16 extends AxisAlignedBB {
    public BoundingBox16(
            double minX,
            double minY,
            double minZ,
            double maxX,
            double maxY,
            double maxZ
    ) {
        super(
                1.0D/16 * minX,
                1.0D/16 * minY,
                1.0D/16 * minZ,
                1.0D/16 * maxX,
                1.0D/16 * maxY,
                1.0D/16 * maxZ
        );
    }
}
