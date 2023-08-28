package flaxbeard.cyberware.common;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWDamageTypes {
    public static final Registrar<DamageType> DAMAGE_TYPES = MANAGER.get().get(Registries.DAMAGE_TYPE);
    public static final RegistrySupplier<DamageType> NO_HEART = DAMAGE_TYPES.register(
            new ResourceLocation(MODID, "no_heart"), () -> new DamageType("no_heart", 0f)
    );
    public static final RegistrySupplier<DamageType> NO_POWER_HEART = DAMAGE_TYPES.register(
            new ResourceLocation(MODID, "no_power_heart"), () -> new DamageType("no_power_heart", 0f)
    );
}
