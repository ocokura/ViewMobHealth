package net.ocoserver;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class getVariousInfo {

    public static void getMobHealth(PlayerEntity player, float maxDistance, World world) {
        Vec3d eyePos = player.getEyePos();
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endPos = eyePos.add(lookVec.multiply(maxDistance));

        Box box = player.getBoundingBox()
                .stretch(lookVec.multiply(maxDistance))
                .expand(1.0D);

        Predicate<Entity> predicate = (Entity entity) -> entity instanceof LivingEntity && entity.isAlive();

        EntityHitResult hitResult = ProjectileUtil.getEntityCollision(world, player, eyePos, endPos, box,
                predicate, maxDistance);

        if (hitResult != null) {
            LivingEntity targetEntity = (LivingEntity) hitResult.getEntity();
            float entityHealth = targetEntity.getHealth();
            float entityMaxHealth = targetEntity.getMaxHealth();
            MobHealth.setValue(targetEntity, entityHealth, entityMaxHealth);
        }
    }
}
