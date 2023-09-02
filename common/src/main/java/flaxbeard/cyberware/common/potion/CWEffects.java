package flaxbeard.cyberware.common.potion;


import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWEffects {
    public static Registrar<MobEffect> MOB_EFFECTS = MANAGER.get().get(Registries.MOB_EFFECT);

    public static RegistrySupplier<MobEffect> CYBER_REJECTION = MOB_EFFECTS.register(
            new ResourceLocation(MODID, "cyber_rejection"),
            CyberRejectionEffect::new
    );

    public static void register() {}
}
