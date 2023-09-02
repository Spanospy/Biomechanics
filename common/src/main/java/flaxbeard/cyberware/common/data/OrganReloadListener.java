package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.organ.Organ;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import flaxbeard.cyberware.api.registry.OrganTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.ORGANS_FOLDER;

public class OrganReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, Organ>> {
    @Override
    protected Map<ResourceLocation, Organ> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganRegistry.ORGANS.clear();
        Map<ResourceLocation, Resource> map = resourceManager.listResources(
                ORGANS_FOLDER,
                file -> file.getPath().endsWith(".json") && !file.getPath().endsWith("slots.json") && !file.getPath().contains("types/")
        );

        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
            try {
                JsonObject root =  JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                OrganType organType = OrganTypeRegistry.get(new ResourceLocation(root.get("type").getAsString()));
                int max = root.get("max").getAsInt();
                ResourceLocation registryName = new ResourceLocation(root.get("id").getAsString());

                List<Organ> required = new ArrayList<>();
                root.getAsJsonArray("required").forEach(jsonElement ->
                        required.add(OrganRegistry.get(new ResourceLocation(jsonElement.getAsString())))
                );

                List<Organ> incompatible = new ArrayList<>();
                root.getAsJsonArray("incompatible").forEach(jsonElement ->
                        incompatible.add(OrganRegistry.get(new ResourceLocation(jsonElement.getAsString())))
                );

                OrganRegistry.register(registryName, () -> new Organ(organType, max, required.toArray(new Organ[0]), incompatible.toArray(new Organ[0])));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return OrganRegistry.ORGANS;
    }

    @Override
    protected void apply(Map<ResourceLocation, Organ> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
