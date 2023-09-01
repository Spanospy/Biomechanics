package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.common.organ.OrganSlots;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.InputStreamReader;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class OrganSlotsReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, OrganSlots.OrganSlot>> {
    @Override
    protected Map<ResourceLocation, OrganSlots.OrganSlot> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganSlots.ORGAN_SLOTS.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(MODID, file -> file.getPath().endsWith("organ_slots.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()){
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                if (root.has("override") && root.get("override").getAsBoolean()){
                    OrganSlots.ORGAN_SLOTS.clear();
                }

                root.get("values").getAsJsonArray().forEach(jsonElement -> {
                    OrganSlots.register(new ResourceLocation(jsonElement.getAsString()));
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return OrganSlots.ORGAN_SLOTS;
    }

    @Override
    protected void apply(Map<ResourceLocation, OrganSlots.OrganSlot> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
