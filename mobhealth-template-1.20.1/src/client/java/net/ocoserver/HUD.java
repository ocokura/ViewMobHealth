package net.ocoserver;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.awt.*;

public class HUD {

    private static Text entityName;
    private static float entityHealth;
    private static float entityMaxHealth;

    public static boolean status = false;

    public static void setData(Text entityName, float entityHealth, float entityMaxHealth) {
        HUD.entityName = entityName;
        HUD.entityHealth = entityHealth;
        HUD.entityMaxHealth = entityMaxHealth;
    }

    public static void drawHud(DrawContext context) {
        if (!status) return;

        int entityNameTextWidth = MinecraftClient.getInstance().textRenderer.getWidth(entityName.getString());
        int entityNameStartX = (80 - entityNameTextWidth) / 2;

        Text healthString = Text.literal(String.format("%.1f / %.1f", entityHealth, entityMaxHealth));
        int entityHealthTextWidth = MinecraftClient.getInstance().textRenderer.getWidth(healthString.getString());
        int entityHealthStartX = ((80 - entityHealthTextWidth) / 2);

        context.fill(0,0,80,8, new Color(44, 44, 44).getRGB());
        context.drawText(MinecraftClient.getInstance().textRenderer, entityName, entityNameStartX, 0, new Color(255, 255, 255).getRGB(), true);
        context.fillGradient(0,8,80,40, new Color(84, 84, 84).getRGB(), new Color(35, 35, 35).getRGB());
        context.drawText(MinecraftClient.getInstance().textRenderer, healthString, entityHealthStartX, 20, new Color(255, 255, 255).getRGB(), true);


        float ratio = 70.0f / entityMaxHealth;
        int finishX = (int) (ratio * entityHealth);
        context.fillGradient(5, 20, 5 + finishX, 28, new Color(26, 255, 0).getRGB(), new Color(14, 121, 0).getRGB());
        context.fillGradient(5 + finishX, 20, 75, 28, new Color(255, 0, 0).getRGB(), new Color(101, 0, 0).getRGB());
    }
}

