package flaxbeard.cyberware.common.effect;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWEffects {
    public static final Registrar<MobEffect> EFFECTS = MANAGER.get().get(Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> CYBER_REJECTION = EFFECTS.register(
            new ResourceLocation(MODID, "cyber_rejection"),
            CyberRejectionEffect::new
    );

    public static void register() {}
}