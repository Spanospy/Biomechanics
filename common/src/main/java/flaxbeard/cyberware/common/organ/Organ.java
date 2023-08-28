package flaxbeard.cyberware.common.organ;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.List;

public abstract class Organ {
    public OrganSlots slot;
    public int maxUpgrades;
    public List<Organ> requiredOrgans;
    public List<Organ> incompatibleOrgans;
    public String name;
    public Item item;
    public Item salvagedItem;
    public Organ(OrganSlots slots, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans, String name){
        this.slot = slots;
        this.maxUpgrades = max;
        this.requiredOrgans = Arrays.stream(requiredOrgans).toList();
        this.incompatibleOrgans = Arrays.stream(incompatibleOrgans).toList();
        this.name = name;
        Organs.organMap.put(name, this);
    }
    public abstract void tick(Player player);
}
