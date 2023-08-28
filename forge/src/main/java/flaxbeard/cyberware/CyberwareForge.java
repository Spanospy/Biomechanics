package flaxbeard.cyberware;

import flaxbeard.cyberware.client.CyberwareClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(Cyberware.MODID)
public class CyberwareForge {
    public CyberwareForge() {
        Cyberware.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> CyberwareClient::init);
    }
}
