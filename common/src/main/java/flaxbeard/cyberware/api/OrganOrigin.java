package flaxbeard.cyberware.api;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class OrganOrigin {
    public final ResourceLocation id;

    public OrganOrigin(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Component getLocalizedName() {
        String string = "cyberware.organ_origin." + id.getNamespace() + "." + id.getPath();
        return Component.translatable(string);
    }
}
