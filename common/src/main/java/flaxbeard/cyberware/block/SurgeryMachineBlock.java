package flaxbeard.cyberware.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SurgeryMachineBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<SurgeryMachinePart> PART = EnumProperty.create("part", SurgeryMachinePart.class);

    private static final VoxelShape TOP_PANE = Block.box(0, 15, 0, 16, 16, 16);
    private static final VoxelShape BOTTOM_PANE = Block.box(0, 0, 0, 16, 1, 16);
    private static final VoxelShape NORTH_PANE = Block.box(0, 0, 0, 16, 16, 1);
    private static final VoxelShape SOUTH_PANE = Block.box(0, 0, 15, 16, 16, 16);
    private static final VoxelShape EAST_PANE = Block.box(15, 0, 0, 16, 16, 16);
    private static final VoxelShape WEST_PANE = Block.box(0, 0, 0, 1, 16, 16);

    private static final VoxelShape FULL_CUBE = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape CLOSED_MIDDLE = Shapes.or(TOP_PANE, NORTH_PANE, SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape NORTH_MIDDLE = Shapes.or(TOP_PANE, SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape SOUTH_MIDDLE = Shapes.or(TOP_PANE, NORTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape EAST_MIDDLE = Shapes.or(TOP_PANE, NORTH_PANE, SOUTH_PANE, WEST_PANE);
    private static final VoxelShape WEST_MIDDLE = Shapes.or(TOP_PANE, NORTH_PANE, SOUTH_PANE, EAST_PANE);
    private static final VoxelShape CLOSED_BOTTOM = Shapes.or(BOTTOM_PANE, NORTH_PANE, SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape NORTH_BOTTOM = Shapes.or(BOTTOM_PANE, SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape SOUTH_BOTTOM = Shapes.or(BOTTOM_PANE, NORTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape EAST_BOTTOM = Shapes.or(BOTTOM_PANE, NORTH_PANE, SOUTH_PANE, WEST_PANE);
    private static final VoxelShape WEST_BOTTOM = Shapes.or(BOTTOM_PANE, NORTH_PANE, SOUTH_PANE, EAST_PANE);

    public SurgeryMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Level level = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockState blockStateMid = level.getBlockState(blockPos.above());
        BlockState blockStateTop = level.getBlockState(blockPos.above().above());
        if (blockStateMid.canBeReplaced(blockPlaceContext) && blockStateTop.canBeReplaced(blockPlaceContext))
            return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection()).setValue(PART, SurgeryMachinePart.BOTTOM);
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (!level.isClientSide){
            BlockPos blockPosMid = blockPos.above();
            BlockPos blockPosTop = blockPosMid.above();
            level.setBlockAndUpdate(blockPosMid, blockState.setValue(PART, SurgeryMachinePart.MIDDLE).setValue(FACING, blockState.getValue(FACING)));
            level.setBlockAndUpdate(blockPosTop, blockState.setValue(PART, SurgeryMachinePart.TOP).setValue(FACING, blockState.getValue(FACING)));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, PART);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (blockState.getValue(PART) != SurgeryMachinePart.TOP) {
            if (blockState.getValue(PART) == SurgeryMachinePart.BOTTOM){
                boolean open = blockState.getValue(OPEN);
                BlockPos blockPosMid = blockPos.above();
                BlockState blockStateMid = level.getBlockState(blockPosMid);
                BlockState blockStateTop = level.getBlockState(blockPosMid.above());
                blockStateMid.setValue(OPEN, !open);
                blockStateTop.setValue(OPEN, !open);
                blockState.setValue(OPEN, !open);
                level.setBlockAndUpdate(blockPosMid, blockStateMid);
                level.setBlockAndUpdate(blockPosMid.above(), blockStateTop);
                level.setBlockAndUpdate(blockPos, blockState);
            }else {
                BlockPos blockPosBottom = blockPos.below();
                BlockState blockStateBottom = level.getBlockState(blockPosBottom);
                BlockState blockStateMid = level.getBlockState(blockPos);
                BlockState blockStateTop = level.getBlockState(blockPos.above());
                blockStateBottom.setValue(OPEN, !blockStateBottom.getValue(OPEN));
                blockStateMid.setValue(OPEN, !blockStateMid.getValue(OPEN));
                blockStateTop.setValue(OPEN, !blockStateTop.getValue(OPEN));
                level.setBlockAndUpdate(blockPosBottom, blockStateBottom);
                level.setBlockAndUpdate(blockPos, blockStateMid);
                level.setBlockAndUpdate(blockPos.above(), blockStateTop);
            }
        }
        else {}

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        boolean open = blockState.getValue(OPEN);
        SurgeryMachinePart part = blockState.getValue(PART);
        if (!open){
            switch (part){
                case BOTTOM:
                    return CLOSED_BOTTOM;
                case MIDDLE:
                    return CLOSED_MIDDLE;
            }
        }
        Direction directionProperty = blockState.getValue(FACING);
        if (part == SurgeryMachinePart.MIDDLE){
            switch (directionProperty) {
                case NORTH:
                    return NORTH_MIDDLE;
                case SOUTH:
                    return SOUTH_MIDDLE;
                case EAST:
                    return EAST_MIDDLE;
                case WEST:
                    return WEST_MIDDLE;
            }
        }else if (part == SurgeryMachinePart.BOTTOM){
            switch (directionProperty) {
                case NORTH:
                    return NORTH_BOTTOM;
                case SOUTH:
                    return SOUTH_BOTTOM;
                case EAST:
                    return EAST_BOTTOM;
                case WEST:
                    return WEST_BOTTOM;
            }
        }

        return FULL_CUBE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    public enum SurgeryMachinePart implements StringRepresentable {
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

        String id;
        SurgeryMachinePart(String id) {
            this.id = id;
        }
        @Override
        public String getSerializedName() {
            return id;
        }
    }
}
