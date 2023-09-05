package flaxbeard.cyberware.common.data.organ;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.api.OrganAction;
import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.registry.OrganSlotRegistry;
import flaxbeard.cyberware.api.registry.OrganTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.InputStreamReader;
import java.util.Map;

import static flaxbeard.cyberware.common.data.CWDataReloadListeners.ORGANS_FOLDER;

public class OrganTypesReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, OrganType>> {
    public static final String ORGAN_TYPES_FOLDER = ORGANS_FOLDER + "/organ_types";
    @Override
    protected Map<ResourceLocation, OrganType> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganTypeRegistry.ORGAN_TYPES.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(
                ORGAN_TYPES_FOLDER,
                file -> file.getPath().endsWith(".json")
        );

        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
            try {
                JsonObject root =  JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                OrganTypeRegistry.register(
                        new ResourceLocation(root.get("id").getAsString()),
                        () -> new OrganType(
                                OrganSlotRegistry.get(new ResourceLocation(root.get("slots").getAsString())),
                                root.get("only_one").getAsBoolean(),
                                OrganAction.fromJson(root.getAsJsonArray("tick_with")),
                                OrganAction.fromJson(root.getAsJsonArray("tick_without"))
                        )
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return OrganTypeRegistry.ORGAN_TYPES;
    }

    @Override
    protected void apply(Map<ResourceLocation, OrganType> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }
}
