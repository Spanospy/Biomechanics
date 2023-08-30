package flaxbeard.cyberware.common.block;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWBlocks {
    public static final Registrar<Block> BLOCKS = MANAGER.get().get(Registries.BLOCK);

    public static final RegistrySupplier<Block> SURGERY_MACHINE = BLOCKS.register(
            new ResourceLocation(MODID, "surgery_machine"),
            () -> new SurgeryMachineBlock(Block.Properties.copy(Blocks.IRON_BLOCK))
    );
}