package net.ocoserver;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class MobHealth {

    private static LivingEntity targetEntity;
    private static float entityHealth;
    private static float entityMaxHealth;
    private static LivingEntity mob;

    public static void setValue(LivingEntity targetEntity, float entityHealth, float entityMaxHealth) {
        MobHealth.entityHealth = entityHealth;
        MobHealth.entityMaxHealth = entityMaxHealth;
        MobHealth.targetEntity = targetEntity;
    }

    public static void setMob(LivingEntity mob) {
        MobHealth.mob = mob;
    }

    public static LivingEntity getTargetEntity() {return targetEntity;}
    public static float getEntityHealth() {return entityHealth;}
    public static float getEntityMaxHealth() {return entityMaxHealth;}
    public static LivingEntity getMob() {return mob;}

    }



