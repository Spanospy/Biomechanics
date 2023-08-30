package flaxbeard.cyberware.common.block;

import flaxbeard.cyberware.common.block.entity.SurgeryMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class SurgeryMachineBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<SurgeryMachinePart> PART = EnumProperty.create("part", SurgeryMachinePart.class);
    private static final VoxelShape BOTTOM_PANE = Block.box(0, 0, 0, 16, 1, 16);
    private static final VoxelShape NORTH_PANE = Block.box(0, 0, 0, 16, 16, 1);
    private static final VoxelShape SOUTH_PANE = Block.box(0, 0, 15, 16, 16, 16);
    private static final VoxelShape EAST_PANE = Block.box(15, 0, 0, 16, 16, 16);
    private static final VoxelShape WEST_PANE = Block.box(0, 0, 0, 1, 16, 16);

    private static final VoxelShape FULL_CUBE = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape CLOSED_MIDDLE = Shapes.or(NORTH_PANE, SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape NORTH_MIDDLE = Shapes.or(SOUTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape SOUTH_MIDDLE = Shapes.or(NORTH_PANE, EAST_PANE, WEST_PANE);
    private static final VoxelShape EAST_MIDDLE = Shapes.or(NORTH_PANE, SOUTH_PANE, WEST_PANE);
    private static final VoxelShape WEST_MIDDLE = Shapes.or(NORTH_PANE, SOUTH_PANE, EAST_PANE);
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
            return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(PART, SurgeryMachinePart.BOTTOM).setValue(OPEN, false);
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (!level.isClientSide){
            BlockPos blockPosMid = blockPos.above();
            BlockPos blockPosTop = blockPosMid.above();
            level.setBlockAndUpdate(blockPosMid, blockState.setValue(PART, SurgeryMachinePart.MIDDLE).setValue(FACING, blockState.getValue(FACING)).setValue(OPEN, false));
            level.setBlockAndUpdate(blockPosTop, blockState.setValue(PART, SurgeryMachinePart.TOP).setValue(FACING, blockState.getValue(FACING)).setValue(OPEN, false));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, PART);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        boolean open = blockState.getValue(OPEN);
        List<BlockPos> other2 = new ArrayList<>();
        switch (blockState.getValue(PART)){
            case BOTTOM:
                other2.add(blockPos.above());
                other2.add(blockPos.above().above());
                playSoundCloseAndOpenSound(blockPos, level);
                break;
            case MIDDLE:
                other2.add(blockPos.below());
                other2.add(blockPos.above());
                playSoundCloseAndOpenSound(blockPos.below(), level);
                break;
            case TOP:
                //will be added later
                return InteractionResult.sidedSuccess(level.isClientSide);
        }
        for (BlockPos pos : other2){
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof SurgeryMachineBlock){
                level.setBlockAndUpdate(pos, state.setValue(OPEN, !open));
            }
        }
        level.setBlockAndUpdate(blockPos, blockState.setValue(OPEN, !open));

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public void playSoundCloseAndOpenSound(BlockPos blockPos, Level level){
        boolean open = level.getBlockState(blockPos).getValue(OPEN);
        level.playSound(null, blockPos, open ? SoundEvents.IRON_DOOR_CLOSE : SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1, 1);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        List<BlockPos> other2 = new ArrayList<>();
        switch (blockState.getValue(PART)){
            case BOTTOM:
                other2.add(blockPos.above());
                other2.add(blockPos.above().above());
                break;
            case MIDDLE:
                other2.add(blockPos.below());
                other2.add(blockPos.above());
                break;
            case TOP:
                other2.add(blockPos.below());
                other2.add(blockPos.below().below());
                break;
        }
        for (BlockPos pos : other2){
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof SurgeryMachineBlock){
                level.destroyBlock(pos, false);
            }
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
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

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        if (blockState.getValue(PART) == SurgeryMachinePart.TOP)
            return new SurgeryMachineBlockEntity(blockPos, blockState);
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
