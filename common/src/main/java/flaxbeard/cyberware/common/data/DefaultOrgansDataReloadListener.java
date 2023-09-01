package flaxbeard.cyberware.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import flaxbeard.cyberware.common.organ.Organs.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.playerdata.PlayerOrgansData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class DefaultOrgansDataReloadListener extends SimplePreparableReloadListener<List<Organ>> {
    @Override
    protected List<Organ> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        PlayerOrgansData.DEFAULTS.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(MODID, file -> file.getPath().endsWith("default_organs.json"));
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()){
            try {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(entry.getValue().open())).getAsJsonObject();

                if (root.has("override") && root.get("override").getAsBoolean()){
                    PlayerOrgansData.DEFAULTS.clear();
                }

                root.get("values").getAsJsonArray().forEach(jsonElement -> {
                    Organ organ = Organs.get(new ResourceLocation(jsonElement.getAsString()));
                    PlayerOrgansData.DEFAULTS.add(organ);
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return PlayerOrgansData.DEFAULTS;
    }

    @Override
    protected void apply(List<Organ> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {}
}
