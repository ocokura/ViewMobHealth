package net.ocoserver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		System.out.println("client side success");
		runMain();
	}

	public void runMain() {

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;
			World world = client.world;
			if (player != null && world != null) {
				getVariousInfo.getMobHealth(player, world, 4.0F);
				DisplayHealth.displayHealth();
				DisplayHealth.unSee();
			} else {
				System.err.println("The player or world variable is null");
			}

		});
	}
}