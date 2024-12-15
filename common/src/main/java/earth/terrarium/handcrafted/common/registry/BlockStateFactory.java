package earth.terrarium.handcrafted.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockStateFactory<T extends BlockEntity> {
    T create(BlockPos var1, BlockState var2);
}
