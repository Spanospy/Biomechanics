package flaxbeard.cyberware.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SurgeryMachineBlockEntity extends BlockEntity {
    public SurgeryMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(CWBlockEntities.SURGERY_MACHINE.get(), blockPos, blockState);
    }
}
