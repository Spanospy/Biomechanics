package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.api.playerdata.PlayerOrgansData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.InputStreamReader;
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.DATA_FOLDER;

public class DefaultToleranceReloadListener extends SimplePreparableReloadListener<Float> {
    @Override
    protected Float prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, Resource> map = resourceManager.listResources(DATA_FOLDER, file -> file.getPath().endsWith("default_tolerance.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();
                PlayerOrgansData.TOLERANCE = root.get("tolerance").getAsFloat();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return PlayerOrgansData.TOLERANCE;
    }

    @Override
    protected void apply(Float object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
