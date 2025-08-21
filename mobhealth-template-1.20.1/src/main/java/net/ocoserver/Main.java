package net.ocoserver;

import com.sun.jna.platform.win32.OaIdl;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

	@Override
	public void onInitialize() {
		System.out.println("Modが起動");
	}
}