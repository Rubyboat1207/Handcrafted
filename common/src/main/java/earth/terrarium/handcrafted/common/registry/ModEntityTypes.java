package earth.terrarium.handcrafted.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.entities.FancyPainting;
import earth.terrarium.handcrafted.common.entities.Seat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, Handcrafted.MOD_ID);

    public static final RegistryEntry<EntityType<Seat>> SEAT = ENTITY_TYPES.register("seat", () ->
        EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC)
            .sized(1, 1)
            .noSave()
            .fireImmune()
            .noSummon()
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "seat"))));

    public static final RegistryEntry<EntityType<FancyPainting>> FANCY_PAINTING = ENTITY_TYPES.register("fancy_painting", () ->
        EntityType.Builder.<FancyPainting>of(FancyPainting::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .clientTrackingRange(10)
            .updateInterval(Integer.MAX_VALUE)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "fancy_painting"))));
}