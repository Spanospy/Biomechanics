package flaxbeard.cyberware.common.block.entity;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.common.block.CWBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWBlockEntities {
    public static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = MANAGER.get().get(Registries.BLOCK_ENTITY_TYPE);
    public static final RegistrySupplier<BlockEntityType<SurgeryMachineBlockEntity>> SURGERY_MACHINE = BLOCK_ENTITY_TYPES.register(
            new ResourceLocation(MODID, "surgery_machine"),
            () -> BlockEntityType.Builder.of(SurgeryMachineBlockEntity::new, CWBlocks.SURGERY_MACHINE.get()).build(null)
    );
}
