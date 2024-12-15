package earth.terrarium.handcrafted.common.entities;

import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.registry.ModEntityTypes;
import earth.terrarium.handcrafted.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FancyPainting extends Painting {

    public FancyPainting(EntityType<? extends Painting> type, Level level) {
        super(type, level);
    }

    public FancyPainting(Level level, BlockPos pos) {
        super(ModEntityTypes.FANCY_PAINTING.get(), level);
        this.pos = pos;
    }

    @Nullable
    @Override
    public ItemEntity spawnAtLocation(ServerLevel serverLevel, ItemLike itemLike) {
        return super.spawnAtLocation(serverLevel, itemLike);
    }

    @Override
    public ItemStack getPickResult() {
        return ModItems.FANCY_PAINTING.get().getDefaultInstance();
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        PaintingVariant variant = this.getVariant().value();
        compound.putString("variant", variant.assetId().getPath());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("variant", CompoundTag.TAG_STRING)) {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, compound.getString("variant"));
            registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT)
                .get(ResourceKey.create(Registries.PAINTING_VARIANT, location))
                .ifPresent(this::setVariant);
        }
    }
}