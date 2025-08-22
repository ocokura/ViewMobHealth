package net.ocoserver;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            if (distance > maxDistance) {
                mob.setCustomNameVisible(false);
                continue;
            }

            double dot = lookVec.normalize().dotProduct(toMob.normalize());

            //条件を満たしたらモブを対象から外す (Some comments are in Japanese to facilitate development)
            if (dot < 0.99) {
                mob.setCustomNameVisible(false);
                continue;
            }

            if (distance < closestDist) {
                closestTarget = mob;
                closestDist = distance;
            } else {
                mob.setCustomNameVisible(false);
            }
        }

        if (closestTarget != null) {
            float entityHealth = closestTarget.getHealth();
            float entityMaxHealth = closestTarget.getMaxHealth();
            closestTarget.setCustomName(Text.of("HP:"+ entityHealth+"/"+entityMaxHealth));
            closestTarget.setCustomNameVisible(true);
        }
    }
}
