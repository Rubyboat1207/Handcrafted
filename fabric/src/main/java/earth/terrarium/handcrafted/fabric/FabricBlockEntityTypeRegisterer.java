package earth.terrarium.handcrafted.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.handcrafted.common.registry.BlockEntityTypeRegisterer;
import earth.terrarium.handcrafted.common.registry.BlockStateFactory;
import earth.terrarium.handcrafted.common.registry.ModBlockEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class FabricBlockEntityTypeRegisterer implements BlockEntityTypeRegisterer {
    static {
        ModBlockEntityTypes.blockEntityTypeRegisterer = new FabricBlockEntityTypeRegisterer();
    }
    @Override
    public <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockStateFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build(null);
    }

    @Override
    public <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockStateFactory<E> factory, ResourcefulRegistry<Block> registry) {
        return FabricBlockEntityTypeBuilder.create(factory::create, registry.stream().map(RegistryEntry::get).toArray(Block[]::new)).build(null);
    }
}
