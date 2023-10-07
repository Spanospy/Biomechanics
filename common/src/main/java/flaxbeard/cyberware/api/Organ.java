package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.registry.CWRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organ {
    private OrganType slot;
    private int max;
    private List<Organ> required;
    private List<Organ> incompatible;
    private float toleranceCost;
    private PlayerOrganTick tick;

    public Organ(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans, PlayerOrganTick tick, float toleranceCost){
        this.slot = type;
        this.max = max;
        this.required = requiredOrgans == null ? new ArrayList<>() : Arrays.stream(requiredOrgans).toList();
        this.incompatible = incompatibleOrgans == null ? new ArrayList<>() : Arrays.stream(incompatibleOrgans).toList();
        this.tick = tick;
        this.toleranceCost = toleranceCost;
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

    public boolean isIncompatible(Organ organ) {
        return incompatible.contains(organ);
    }

    public void tick(Player player) {
        if (tick != null)
            tick.tick(player);
    }

    public Component getTooltip() {
        ResourceLocation registryName = CWRegistry.ORGANS.getKey(this);
        return Component.translatable(registryName.getNamespace() + ".organ." + registryName.getPath() + ".tooltip");
    }

    public float getToleranceCost() {
        return toleranceCost;
    }
}