package flaxbeard.cyberware.common.data.organ;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.api.playerdata.PlayerOrgansData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.DATA_FOLDER;

public class DefaultDataReloadListener extends SimplePreparableReloadListener<List<ResourceLocation>> {
    @Override
    protected List<ResourceLocation> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        PlayerOrgansData.DEFAULTS.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(DATA_FOLDER, file -> file.getPath().endsWith("defaults.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()){
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                PlayerOrgansData.TOLERANCE = root.get("tolerance").getAsFloat();

                root.get("organs").getAsJsonArray().forEach(jsonElement -> {
                    PlayerOrgansData.DEFAULTS.add(new ResourceLocation(jsonElement.getAsString()));
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return PlayerOrgansData.DEFAULTS;
    }

    @Override
    protected void apply(List<ResourceLocation> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
