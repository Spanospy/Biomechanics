package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.common.organ.OrganSlots;
import flaxbeard.cyberware.common.organ.OrganTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.InputStreamReader;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class OrganTypesReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, OrganTypes.OrganType>> {
    @Override
    protected Map<ResourceLocation, OrganTypes.OrganType> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganTypes.ORGAN_TYPES.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(MODID, file -> file.getPath().endsWith("organ_types.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()){
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                if (root.has("override") && root.get("override").getAsBoolean()){
                    OrganTypes.ORGAN_TYPES.clear();
                }

                root.get("values").getAsJsonArray().forEach(jsonElement -> {
                    JsonObject object = jsonElement.getAsJsonObject();
                    OrganSlots.OrganSlot slot = OrganSlots.get(new ResourceLocation(object.get("slot").getAsString()));
                    OrganTypes.register(new ResourceLocation(object.get("id").getAsString()), slot);
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return OrganTypes.ORGAN_TYPES;
    }

    @Override
    protected void apply(Map<ResourceLocation, OrganTypes.OrganType> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
