package flaxbeard.cyberware.common.data.organ;

import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class OrgansReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, Organ>> {
    public static final String ORGANS_FOLDER = OrganTypesReloadListener.ORGAN_TYPES_FOLDER + "/organs";
    @Override
    protected Map<ResourceLocation, Organ> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        OrganRegistry.ORGANS.clear();

        Map<ResourceLocation, Resource> map = resourceManager.listResources(
                ORGANS_FOLDER,
                file -> file.getPath().endsWith(".json")
        );
        for (Map.Entry<ResourceLocation, Resource> entry : map.entrySet()) {
            try {


                OrganRegistry.register(
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return OrganRegistry.ORGANS;
    }

    @Override
    protected void apply(Map<ResourceLocation, Organ> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }
}
