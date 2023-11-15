package flaxbeard.cyberware.common;

import flaxbeard.cyberware.Cyberware;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class CWDamageTypes {
    public static final ResourceKey<DamageType> NO_HEART = get("no_heart");
    public static final ResourceKey<DamageType> CYBER_REJECTION = get("cyber_rejection");

    public static ResourceKey<DamageType> get(String s){
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Cyberware.MODID, s));
    }

    public static DamageSource of(Level level, ResourceKey<DamageType> key){
        return new DamageSource(level.registryAccess().registry(Registries.DAMAGE_TYPE).orElseThrow().getHolderOrThrow(key));
    }
}