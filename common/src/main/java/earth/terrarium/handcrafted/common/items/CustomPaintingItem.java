package earth.terrarium.handcrafted.common.items;

import earth.terrarium.handcrafted.common.entities.FancyPainting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomPaintingItem extends HangingEntityItem {
    private final TagKey<PaintingVariant> variants;

    public CustomPaintingItem(Properties settings, TagKey<PaintingVariant> variants) {
        super(EntityType.PAINTING, settings);
        this.variants = variants;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos pos2 = pos.relative(direction);
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        if (player != null && !mayPlace(player, direction, stack, pos2)) {
            return InteractionResult.FAIL;
        }

        Optional<FancyPainting> optionalPainting = create(level, pos2, direction);
        if (optionalPainting.isEmpty()) return InteractionResult.CONSUME;
        FancyPainting painting = optionalPainting.get();

        CustomData customData = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        if (!customData.isEmpty()) {
            EntityType.updateCustomEntityTag(level, player, painting, customData);
        }

        if (painting.survives()) {
            if (!level.isClientSide) {
                painting.playPlacementSound();
                level.gameEvent(player, GameEvent.ENTITY_PLACE, painting.position());
                level.addFreshEntity(painting);
            }

            stack.shrink(1);
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private Optional<FancyPainting> create(Level level, BlockPos pos, Direction direction) {
        FancyPainting painting = new FancyPainting(level, pos);
        List<Holder<PaintingVariant>> paintings = new ArrayList<>();
        level.registryAccess().get(Registries.PAINTING_VARIANT).get().value().getTagOrEmpty(variants).forEach(paintings::add);
        if (paintings.isEmpty()) return Optional.empty();

        painting.setDirection(direction);

        paintings.removeIf(holder -> {
            painting.setVariant(holder);
            return !painting.survives();
        });

        if (paintings.isEmpty()) return Optional.empty();

        int area = paintings.stream().mapToInt(CustomPaintingItem::variantArea).max().orElse(0);
        paintings.removeIf(holder -> variantArea(holder) < area);
        Optional<Holder<PaintingVariant>> optional = Util.getRandomSafe(paintings, painting.getRandom());
        if (optional.isEmpty()) return Optional.empty();

        painting.setVariant(optional.get());
        painting.setDirection(direction);
        return Optional.of(painting);
    }

    private static int variantArea(Holder<PaintingVariant> variant) {
        return variant.value().width() * variant.value().height();
    }
}
