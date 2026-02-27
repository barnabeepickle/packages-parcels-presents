package com.github.barnabeepickle.ppnp.utils.bb;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3d;

import java.util.List;

public class BlockCollisionUtil {
    public static void addBoundingBox(
            AxisAlignedBB entityBoundingBox,
            List<AxisAlignedBB> collidingBoxes,
            BlockPos pos,
            // min cords
            double minX,
            double minY,
            double minZ,
            // max cords
            double maxX,
            double maxY,
            double maxZ
    ) {
        AxisAlignedBB boundingBox = new AxisAlignedBB(
                // min cords
                pos.getX() + minX, // minX
                pos.getY() + minY, // minY
                pos.getZ() + minZ, // minZ
                // max cords
                pos.getX() + maxX, // maxX
                pos.getY() + maxY, // maxY
                pos.getZ() + maxZ  // maxZ
        );
        if (entityBoundingBox.intersects(boundingBox)) {
            collidingBoxes.add(boundingBox);
        }
    }

    public static void addBoundingBox(
            AxisAlignedBB entityBoundingBox,
            List<AxisAlignedBB> collidingBoxes,
            BlockPos pos,
            AxisAlignedBB aabb
    ) {
        AxisAlignedBB boundingBox = new AxisAlignedBB(
                // min cords
                pos.getX() + aabb.minX, // minX
                pos.getY() + aabb.minY, // minY
                pos.getZ() + aabb.minZ, // minZ
                // max cords
                pos.getX() + aabb.maxX, // maxX
                pos.getY() + aabb.maxY, // maxY
                pos.getZ() + aabb.maxZ  // maxZ
        );
        if (entityBoundingBox.intersects(boundingBox)) {
            collidingBoxes.add(boundingBox);
        }
    }

    public static void addBoundingBox16(
            AxisAlignedBB entityBoundingBox,
            List<AxisAlignedBB> collidingBoxes,
            BlockPos pos,
            // min cords
            double minX,
            double minY,
            double minZ,
            // max cords
            double maxX,
            double maxY,
            double maxZ
    ) {
        AxisAlignedBB boundingBox = new AxisAlignedBB(
                // min cords
                pos.getX() + 1.0D/16 * minX, // minX
                pos.getY() + 1.0D/16 * minY, // minY
                pos.getZ() + 1.0D/16 * minZ, // minZ
                // max cords
                pos.getX() + 1.0D/16 * maxX, // maxX
                pos.getY() + 1.0D/16 * maxY, // maxY
                pos.getZ() + 1.0D/16 * maxZ  // maxZ
        );
        if (entityBoundingBox.intersects(boundingBox)) {
            collidingBoxes.add(boundingBox);
        }
    }

    public static void addBoundingBox(
            AxisAlignedBB entityBoundingBox,
            List<AxisAlignedBB> collidingBoxes,
            BlockPos pos,
            Vector3d minVector,
            Vector3d maxVector
    ) {
        AxisAlignedBB boundingBox = new AxisAlignedBB(
                // min cords
                pos.getX() + minVector.x, // minX
                pos.getY() + minVector.y, // minY
                pos.getZ() + minVector.z, // minZ
                // max cords
                pos.getX() + maxVector.x, // maxX
                pos.getY() + maxVector.y, // maxY
                pos.getZ() + maxVector.z // maxZ
        );
        if (entityBoundingBox.intersects(boundingBox)) {
            collidingBoxes.add(boundingBox);
        }
    }
}
