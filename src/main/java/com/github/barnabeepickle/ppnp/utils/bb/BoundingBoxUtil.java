package com.github.barnabeepickle.ppnp.utils.bb;

import net.minecraft.util.math.AxisAlignedBB;
import org.joml.Matrix3d;
import org.joml.Vector3d;

public class BoundingBoxUtil {
    public static Vector3d rotHack(Vector3d vector, double angle) {
        angle = Math.toRadians(angle);

        // identity matrix
        Matrix3d rotMat = new Matrix3d(1,0,0, 0,1,0, 0,0,1);
        rotMat.rotateY(angle); // rotate on the y axis
        rotMat.transform(vector);

        vector.x = Math.round(vector.x() * 10000000D)/10000000D;
        vector.z = Math.round(vector.z() * 10000000D)/10000000D;

        return vector;
    }

    public static AxisAlignedBB rotHackAABB(AxisAlignedBB aabb) {
        Vector3d minVec = rotHack(new Vector3d(aabb.minX, aabb.minY, aabb.minZ), 90);
        Vector3d maxVec = rotHack(new Vector3d(aabb.maxX, aabb.maxY, aabb.maxZ), 90);
        if (minVec.x() < 0) {
            minVec.x = Math.abs(minVec.x() + 1);
            maxVec.x = Math.abs(maxVec.x() + 1);
        }
        if (minVec.z() < 0) {
            minVec.z = Math.abs(minVec.z() + 1);
            maxVec.z = Math.abs(maxVec.z() + 1);
        }

        return new AxisAlignedBB(minVec.x, minVec.y, minVec.z, maxVec.x, maxVec.y, maxVec.z);
    }
}
