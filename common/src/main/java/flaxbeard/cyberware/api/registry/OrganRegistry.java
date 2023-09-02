package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.organ.Organ;
import flaxbeard.cyberware.common.item.CWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrganRegistry {
    private static Map<ResourceLocation, Supplier<Organ>> REGISTRY_ORGANS = new HashMap<>();
    public static Map<ResourceLocation, Organ> ORGANS = new HashMap<>();

    public static Organ get(ResourceLocation resourceLocation){
        return ORGANS.get(resourceLocation);
    }

    public static ResourceLocation getRegistryName(Organ organ){
        for (Map.Entry<ResourceLocation, Organ> entry : ORGANS.entrySet()) {
            if (entry.getValue().equals(organ)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static List<ItemStack> getOrganItems(){
        List<ItemStack> organItems = new ArrayList<>();
        for (Organ organ : ORGANS.values()) {
            organItems.add(getOrganItem(organ));
        }
        return organItems;
    }

    public static ItemStack getOrganItem(Organ organ){
        ResourceLocation resourceLocation = getRegistryName(organ);
        if (resourceLocation == null) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(CWItems.ORGAN.get());
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag == null) {
            compoundTag = new CompoundTag();
        }
        compoundTag.put("organ", StringTag.valueOf(resourceLocation.toString()));
        itemStack.setTag(compoundTag);
        return itemStack;
    }

    public static void register(){
        for (Map.Entry<ResourceLocation, Supplier<Organ>> entry : REGISTRY_ORGANS.entrySet()) {
            ORGANS.put(entry.getKey(), entry.getValue().get());
        }
    }

    public static void register(ResourceLocation resourceLocation, Supplier<Organ> organ){
        REGISTRY_ORGANS.put(resourceLocation, organ);
    }
}
