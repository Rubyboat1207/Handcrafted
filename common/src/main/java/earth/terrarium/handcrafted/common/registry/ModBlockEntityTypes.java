package earth.terrarium.handcrafted.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.blockentities.ContainerBlockEntity;
import earth.terrarium.handcrafted.common.blockentities.OvenBlockEntity;
import earth.terrarium.handcrafted.common.blocks.crockery.CrockeryBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntityTypes {
    public static BlockEntityTypeRegisterer blockEntityTypeRegisterer;
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Handcrafted.MOD_ID);

    public static final RegistryEntry<BlockEntityType<OvenBlockEntity>> OVEN = BLOCK_ENTITY_TYPES.register("oven", () -> createBlockEntityType(OvenBlockEntity::new, ModBlocks.OVEN.get()));
    public static final RegistryEntry<BlockEntityType<CrockeryBlockEntity>> CROCKERY = BLOCK_ENTITY_TYPES.register("crockery", () -> createBlockEntityType(CrockeryBlockEntity::new, ModBlocks.CROCKERY_COMBOS));
    public static final RegistryEntry<BlockEntityType<ContainerBlockEntity>> CONTAINER;

    static {
        List<RegistryEntry<Block>> entries = new ArrayList<>();

        entries.addAll(ModBlocks.COUNTERS.getEntries());
        entries.addAll(ModBlocks.CUPBOARDS.getEntries());
        entries.addAll(ModBlocks.DESKS.getEntries());
        entries.addAll(ModBlocks.DRAWERS.getEntries());
        entries.addAll(ModBlocks.NIGHTSTANDS.getEntries());
        entries.addAll(ModBlocks.SHELVES.getEntries());
        entries.addAll(ModBlocks.SIDE_TABLES.getEntries());

        CONTAINER = BLOCK_ENTITY_TYPES.register("container", () -> createBlockEntityType(ContainerBlockEntity::new, entries.stream().map(RegistryEntry::get).toArray(Block[]::new)));
    }

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityType.BlockEntitySupplier<E> factory, Block... blocks) {
        return blockEntityTypeRegisterer.createBlockEntityType(factory::create, blocks);
    }

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityType.BlockEntitySupplier<E> factory, ResourcefulRegistry<Block> registry) {
        return blockEntityTypeRegisterer.createBlockEntityType(factory::create, registry);
    }
}
