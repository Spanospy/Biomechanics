package flaxbeard.cyberware.common.data;

import dev.architectury.registry.ReloadListenerRegistry;
import flaxbeard.cyberware.common.data.organ.DefaultDataReloadListener;
import flaxbeard.cyberware.common.data.organ.OrganSlotsReloadListener;
import flaxbeard.cyberware.common.data.organ.OrganTypesReloadListener;
import flaxbeard.cyberware.common.data.organ.OrgansReloadListener;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;

public class CWDataReloadListeners {
    public static final String DATA_FOLDER = "cw";
    public static final String ORGANS_FOLDER = DATA_FOLDER + "/organs";

    public static void register() {
        register(new OrganSlotsReloadListener());
        register(new OrganTypesReloadListener());
        register(new DefaultDataReloadListener());
        register(new OrgansReloadListener());
    }

    public static void register(SimplePreparableReloadListener<?> listener) {
        ReloadListenerRegistry.register(PackType.SERVER_DATA, listener);
    }
}
