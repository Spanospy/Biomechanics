package flaxbeard.cyberware;

import flaxbeard.cyberware.client.CyberwareClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Cyberware.MODID, dist = Dist.CLIENT)
public class CyberwareNeoForgeClient {
    public CyberwareNeoForgeClient(IEventBus modBus) {
        CyberwareClient.init();
    }
}
