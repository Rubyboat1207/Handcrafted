package earth.terrarium.handcrafted.datagen.provider.server.registry;

import earth.terrarium.handcrafted.Handcrafted;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.Optional;

public class ModPaintingVariantProvider {

    public static final ResourceKey<PaintingVariant> PRIDE_STEVE = create("pride_steve");
    public static final ResourceKey<PaintingVariant> BEACH_SUNRISE_MID = create("beach_sunrise_mid");
    public static final ResourceKey<PaintingVariant> APPLE = create("apple");
    public static final ResourceKey<PaintingVariant> SMALL_SAFARI_SUNSET = create("small_safari_sunset");
    public static final ResourceKey<PaintingVariant> MARIGOLD_MEADOWS = create("marigold_meadows");
    public static final ResourceKey<PaintingVariant> AD_ASTRA = create("ad_astra");
    public static final ResourceKey<PaintingVariant> LOVE_FLOWERS = create("love_flowers");

    public static final ResourceKey<PaintingVariant> MISTY_MOUNTAINS = create("misty_mountains");
    public static final ResourceKey<PaintingVariant> CORAL_DEPTHS = create("coral_depths");
    public static final ResourceKey<PaintingVariant> BROKEN_BIRCHES = create("broken_birches");
    public static final ResourceKey<PaintingVariant> MYSTERIOUS_MANGROVES = create("mysterious_mangroves");
    public static final ResourceKey<PaintingVariant> MY_MAN_DIORITE = create("my_man_diorite");
    public static final ResourceKey<PaintingVariant> COOKIES_ON_A_PLATE = create("cookies_on_a_plate");
    public static final ResourceKey<PaintingVariant> LAVA_PUDDLES = create("lava_puddles");
    public static final ResourceKey<PaintingVariant> THE_CAT_AND_THE_CUP = create("the_cat_and_the_cup");
    public static final ResourceKey<PaintingVariant> TERRARIUM = create("terrarium");

    public static final ResourceKey<PaintingVariant> GREEN_WOODS = create("green_woods");
    public static final ResourceKey<PaintingVariant> SAFARI_SUNSET = create("safari_sunset");
    public static final ResourceKey<PaintingVariant> ROCKY_BEACH = create("rocky_beach");

    public static final ResourceKey<PaintingVariant> MISTY_MOUNTAIN_LEFT_SIDEPANEL = create("misty_mountain_left_sidepanel");
    public static final ResourceKey<PaintingVariant> MISTY_MOUNTAIN_RIGHT_SIDEPANEL = create("misty_mountain_right_sidepanel");
    public static final ResourceKey<PaintingVariant> BROKEN_BIRCHES_LEFT_SIDEPANEL = create("broken_birches_left_sidepanel");
    public static final ResourceKey<PaintingVariant> BROKEN_BIRCHES_RIGHT_SIDEPANEL = create("broken_birches_right_sidepanel");
    public static final ResourceKey<PaintingVariant> FLOWER_IN_THE_DARKNESS = create("flower_in_the_darkness");

    public static final ResourceKey<PaintingVariant> DESERT_PLATEAU = create("desert_plateau");
    public static final ResourceKey<PaintingVariant> BEACH_SUNRISE_RIGHT = create("beach_sunrise_right");
    public static final ResourceKey<PaintingVariant> BEACH_SUNRISE_LEFT = create("beach_sunrise_left");
    public static final ResourceKey<PaintingVariant> NIAN_CAT = create("nian_cat");

    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, PRIDE_STEVE, 1, 1);
        register(context, BEACH_SUNRISE_MID, 1, 1);
        register(context, APPLE, 1, 1);
        register(context, SMALL_SAFARI_SUNSET, 1, 1);
        register(context, MARIGOLD_MEADOWS, 1, 1);
        register(context, AD_ASTRA, 1, 1);
        register(context, LOVE_FLOWERS, 1, 1);

        register(context, MISTY_MOUNTAINS, 2, 2);
        register(context, CORAL_DEPTHS, 2, 2);
        register(context, BROKEN_BIRCHES, 2, 2);
        register(context, MYSTERIOUS_MANGROVES, 2, 2);
        register(context, MY_MAN_DIORITE, 2, 2);
        register(context, COOKIES_ON_A_PLATE, 2, 2);
        register(context, LAVA_PUDDLES, 2, 2);
        register(context, THE_CAT_AND_THE_CUP, 2, 2);
        register(context, TERRARIUM, 2, 2);

        register(context, GREEN_WOODS, 3, 2);
        register(context, SAFARI_SUNSET, 3, 2);
        register(context, ROCKY_BEACH, 3, 2);

        register(context, MISTY_MOUNTAIN_LEFT_SIDEPANEL, 1, 2);
        register(context, MISTY_MOUNTAIN_RIGHT_SIDEPANEL, 1, 2);
        register(context, BROKEN_BIRCHES_LEFT_SIDEPANEL, 1, 2);
        register(context, BROKEN_BIRCHES_RIGHT_SIDEPANEL, 1, 2);
        register(context, FLOWER_IN_THE_DARKNESS, 1, 2);

        register(context, DESERT_PLATEAU, 2, 1);
        register(context, BEACH_SUNRISE_RIGHT, 2, 1);
        register(context, BEACH_SUNRISE_LEFT, 2, 1);
        register(context, NIAN_CAT, 2, 1);
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location(), Optional.empty(), Optional.empty()));
    }
}
