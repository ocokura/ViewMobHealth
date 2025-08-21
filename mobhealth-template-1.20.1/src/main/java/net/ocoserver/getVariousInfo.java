package net.ocoserver;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.List;
import java.util.function.Predicate;

public class getVariousInfo {

    public static void getMobHealth(PlayerEntity player, World world, float maxDistance) {
        Vec3d eyePos = player.getEyePos();
        Vec3d lookVec = player.getRotationVec(1.0F);

        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class,
                player.getBoundingBox().expand(maxDistance),
                e -> e != player
        );

        LivingEntity closestTarget = null;
        double closestDist = Double.MAX_VALUE;

        for (LivingEntity mob : entities) {
            Vec3d mobPos = mob.getPos().add(0, mob.getHeight() * 0.5, 0);
            Vec3d toMob = mobPos.subtract(eyePos);
            double distance = toMob.length();

            if (distance > maxDistance) MobHealth.setMob(mob);

            double dot = lookVec.normalize().dotProduct(toMob.normalize());
            if (dot < 0.2) MobHealth.setMob(mob);

            if (distance < closestDist) {
                closestTarget = mob;
                closestDist = distance;
            } else {
                MobHealth.setMob(mob);
            }
        }

        if (closestTarget != null) {
            float entityHealth = closestTarget.getHealth();
            float entityMaxHealth = closestTarget.getMaxHealth();
            MobHealth.setValue(closestTarget, entityHealth, entityMaxHealth);
        }
    }
}
