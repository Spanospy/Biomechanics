package flaxbeard.cyberware.common.data;

import dev.architectury.registry.ReloadListenerRegistry;
import net.minecraft.server.packs.PackType;

public class CWDataReloadListeners {
    public static final String DATA_FOLDER = "organs";
    public static void register() {
        ReloadListenerRegistry.register(PackType.SERVER_DATA, new DefaultOrgansDataReloadListener());
        ReloadListenerRegistry.register(PackType.SERVER_DATA, new DefaultToleranceReloadListener());
    }
}
