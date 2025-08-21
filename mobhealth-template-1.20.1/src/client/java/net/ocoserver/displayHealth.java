package net.ocoserver;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.ocoserver.MobHealth;

public class displayHealth {

    public static void displayHealth(float entityHealth, float entityMaxHealth) {
        LivingEntity targetEntity = MobHealth.getTargetEntity();
        targetEntity.setCustomName(Text.of("HP:"+MobHealth.getEntityHealth()+"/"+MobHealth.getEntityMaxHealth()));
        targetEntity.setCustomNameVisible(true);
    }
}
