package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class Organ {
    private OrganType slot;
    private int max;
    private List<Organ> required;
    private List<Organ> incompatible;

    public Organ(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans){
        this.slot = type;
        this.max = max;
        this.required = Arrays.stream(requiredOrgans).toList();
        this.incompatible = Arrays.stream(incompatibleOrgans).toList();
    }

    public OrganType getType() {
        return slot;
    }

    public int getMax() {
        return max;
    }

    public List<Organ> getRequired() {
        return required;
    }

    public List<Organ> getIncompatible() {
        return incompatible;
    }

    public boolean isRequired(Organ organ) {
        return required.contains(organ);
    }

    public Component getTooltip() {
        ResourceLocation registryName = OrganRegistry.getRegistryName(this);
        return Component.translatable(registryName.getNamespace() + ".organ." + registryName.getPath() + ".tooltip");
    }
}