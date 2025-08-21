package net.ocoserver;

import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

public class DisplayHealth {

    public static void displayHealth() {
        LivingEntity targetEntity = MobHealth.getTargetEntity();
        if (targetEntity != null) {
            targetEntity.setCustomName(Text.of("HP:"+MobHealth.getEntityHealth()+"/"+MobHealth.getEntityMaxHealth()));
            targetEntity.setCustomNameVisible(true);
        }
    }

    public static void unSee() {
        LivingEntity mob = MobHealth.getMob();
        if (mob != null) {
            mob.setCustomNameVisible(false);
        }
    }
}
