package flaxbeard.cyberware.common.data;

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
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.DATA_FOLDER;

public class CWDataReloadListener extends SimplePreparableReloadListener<Object> {
    @Override
    protected Object prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        {
            OrganOriginRegistry.ORGAN_ORGINS.clear();
            Map<ResourceLocation, Resource> map = resourceManager.listResources(DATA_FOLDER, file -> file.getPath().endsWith("organ_origins.json"));
            for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
                try {
                    JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();
                    root.getAsJsonArray("values").forEach(jsonElement -> {
                        String jsonObject = jsonElement.getAsString();
                        ResourceLocation resourceLocation = new ResourceLocation(jsonObject);
                        OrganOriginRegistry.register(resourceLocation);
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        {
            OrganSlotRegistry.ORGAN_SLOTS.clear();
        }
        {
            PlayerOrgansData.DEFAULTS.clear();
            Map<ResourceLocation, Resource> map = resourceManager.listResources(DATA_FOLDER, file -> file.getPath().endsWith("defaults.json"));
            for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
                try {
                    JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();
                    PlayerOrgansData.setDefaultFromJson(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    protected void apply(Object object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
