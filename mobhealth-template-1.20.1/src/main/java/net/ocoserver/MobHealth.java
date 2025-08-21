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

import java.util.function.Predicate;

public class MobHealth {

    private static float entityHealth;
    private static float entityMaxHealth;

    public static void setValue(float entityHealth, float entityMaxHealth) {
        MobHealth.entityHealth = entityHealth;
        MobHealth.entityMaxHealth = entityMaxHealth;
    }
    
    }



