package earth.terrarium.handcrafted.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface BlockEntityTypeRegisterer {
    <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockStateFactory<E> factory, Block... blocks);
    <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockStateFactory<E> factory, ResourcefulRegistry<Block> registry);
}
