package flaxbeard.cyberware;

import flaxbeard.cyberware.client.CyberwareClient;
import net.fabricmc.api.ClientModInitializer;

public class CyberwareFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CyberwareClient.init();
    }
}