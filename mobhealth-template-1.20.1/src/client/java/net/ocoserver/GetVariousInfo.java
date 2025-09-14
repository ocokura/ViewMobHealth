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
            double dot = lookVec.normalize().dotProduct(toMob.normalize());

            if (distance > maxDistance || !mob.isAlive() || dot < 0.9 || distance > closestDist) {
                TargetMobData mobDataNone = new TargetMobData();
                HUD.setStandardData(mobDataNone);
                continue;
            }
            closestTarget = mob;
            closestDist = distance;
        }

        if (closestTarget != null) {
            float entityHealth = closestTarget.getHealth();
            float entityMaxHealth = closestTarget.getMaxHealth();

            if (closestTarget instanceof PlayerEntity targetPlayer) {
                Text playerName = targetPlayer.getName();
                TargetMobData playerData = new TargetMobData(playerName, entityHealth, entityMaxHealth, true);
                HUD.setStandardData(playerData);
            } else {
                Text entityName = closestTarget.getType().getName();
                TargetMobData targetData = new TargetMobData(entityName, entityHealth, entityMaxHealth, true);
                HUD.setStandardData(targetData);
            }
        }
    }
}