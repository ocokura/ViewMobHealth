package net.ocoserver;

import net.minecraft.text.Text;

public class TargetMobData {

    private Text entityName;
    private float entityHealth;
    private float entityMaxHealth;
    private boolean showHealth = false;

    public TargetMobData(Text playerName, float entityHealth, float entityMaxHealth, boolean showHealth) {
        this.entityName = playerName;
        this.entityHealth = entityHealth;
        this.entityMaxHealth = entityMaxHealth;
        this.showHealth = showHealth;
    }

    public TargetMobData() {}

    public Text getEntityName() {return this.entityName;}
    public float getEntityHealth() {return this.entityHealth;}
    public float getEntityMaxHealth() {return this.entityMaxHealth;}
    public boolean getShowHealth() {return this.showHealth;}
}
