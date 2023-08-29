package flaxbeard.cyberware.common;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWDamageTypes {
    public static final DamageType NO_HEART = new DamageType("no_heart", 0f);
    public static final DamageType NO_POWER_HEART = new DamageType("no_power_heart", 0f);

    public static void init() {}
}
