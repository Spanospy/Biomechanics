package flaxbeard.cyberware;

import net.fabricmc.api.ModInitializer;

public class CyberwareFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		Cyberware.init();
	}
}
