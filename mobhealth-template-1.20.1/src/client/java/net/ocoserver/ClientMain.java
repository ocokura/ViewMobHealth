package net.ocoserver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientMain implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		HudRenderCallback.EVENT.register((context, tickDelta) -> {
			HUD.drawHud(context);
		});

		System.out.println("client side success");
		runMain();
	}

	public void runMain() {

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;
			World world = client.world;
			if (player != null && world != null) {
				GetVariousInfo.getMobHealth(player, world, 4.0F);
			}
		});
	}
}