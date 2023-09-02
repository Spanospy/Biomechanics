package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.api.OrganSlot;
import flaxbeard.cyberware.api.registry.OrganSlotRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.ORGANS_FOLDER;

public class OrganSlotsReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, OrganSlot>> {
    @Override
    protected @NotNull Map<ResourceLocation, OrganSlot> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganSlotRegistry.ORGAN_SLOTS.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(ORGANS_FOLDER, file -> file.getPath().endsWith("slots.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                root.getAsJsonArray("values").forEach(jsonElement ->
                        OrganSlotRegistry.register(new ResourceLocation(jsonElement.getAsString()))
                );
            } catch (IOException e) {
            throw new RuntimeException(e);
            }
        }

        return OrganSlotRegistry.ORGAN_SLOTS;
    }

    @Override
    protected void apply(Map<ResourceLocation, OrganSlot> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
