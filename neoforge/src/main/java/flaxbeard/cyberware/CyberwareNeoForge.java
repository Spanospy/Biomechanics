package flaxbeard.cyberware;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Cyberware.MODID)
public class CyberwareNeoForge {
    public CyberwareNeoForge(IEventBus modBus) {
        Cyberware.init();
    }
}
