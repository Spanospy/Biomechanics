package flaxbeard.cyberware.api;

import net.minecraft.resources.ResourceLocation;

public class OrganSlotType {
    public ResourceLocation icon;
    public OrganSlotType(ResourceLocation resourceLocation){
        this.icon = new ResourceLocation(
                resourceLocation.getNamespace(), "textures/gui/organ_slots/" + resourceLocation.getPath() + ".png"
        );
    }
}
