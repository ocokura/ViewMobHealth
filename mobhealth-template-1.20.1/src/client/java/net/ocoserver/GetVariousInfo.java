package net.ocoserver;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class GetVariousInfo {

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
                HUD.status = false;
                continue;
            }

            double dot = lookVec.normalize().dotProduct(toMob.normalize());

            if (!mob.isAlive()) {
                HUD.status = false;
                continue;
            }

            //条件を満たしたらモブを対象から外す (Some comments are in Japanese to facilitate development)
            if (dot < 0.9) {
                HUD.status = false;
                continue;
            }

            if (distance < closestDist) {
                closestTarget = mob;
                closestDist = distance;
            } else { HUD.status = false; }
        }

        if (closestTarget != null) {
            Text entityName = closestTarget.getType().getName();
            float entityHealth = closestTarget.getHealth();
            float entityMaxHealth = closestTarget.getMaxHealth();
            HUD.setData(entityName, entityHealth, entityMaxHealth);
            HUD.status = true;
        }
    }
}
