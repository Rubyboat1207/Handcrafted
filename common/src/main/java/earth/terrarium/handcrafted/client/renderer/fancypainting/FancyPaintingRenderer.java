package earth.terrarium.handcrafted.client.renderer.fancypainting;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.entities.FancyPainting;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FancyPaintingRenderer extends EntityRenderer<FancyPainting, FancyPaintingRenderState> {
    private static final ResourceLocation FRAME_SMALL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/small_painting_frame.png");
    private static final ResourceLocation FRAME_MEDIUM_TEXTURE = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/medium_painting_frame.png");
    private static final ResourceLocation FRAME_LARGE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/large_painting_frame.png");
    private static final ResourceLocation FRAME_TALL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/tall_painting_frame.png");
    private static final ResourceLocation FRAME_WIDE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/wide_painting_frame.png");

    private final ModelPart small;
    private final ModelPart medium;
    private final ModelPart large;
    private final ModelPart tall;
    private final ModelPart wide;

    private final Map<PaintingVariant, ResourceLocation> textures = new HashMap<>();

    public FancyPaintingRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.small = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_SMALL).getChild("main");
        this.medium = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_MEDIUM).getChild("main");
        this.large = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_LARGE).getChild("main");
        this.tall = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_TALL).getChild("main");
        this.wide = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_WIDE).getChild("main");
    }

    @Override
    public @NotNull FancyPaintingRenderState createRenderState() {
        return new FancyPaintingRenderState();
    }

    @Override
    public void render(FancyPaintingRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer frameVertex = buffer.getBuffer(RenderType.entitySolid(getTextureLocation(state)));

        int width = state.variant.width() * 16;
        int height = state.variant.height() * 16;
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.scale(0.8f, 0.8f, 0.8f);
            poseStack.mulPose(Axis.YN.rotationDegrees(state.direction.toYRot()));
            poseStack.translate(0, 0.875f, 0.46125f);
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            getFrame(state.variant).render(poseStack, frameVertex, packedLight, OverlayTexture.NO_OVERLAY);

            ResourceLocation texture = this.textures.computeIfAbsent(state.variant, v -> ResourceLocation.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/" + v.assetId().getPath() + ".png"));
            VertexConsumer paintingVertex = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
            poseStack.translate(width / 2f / -16f, -0.125f + 0.5 * ((32 - height) / 16f), 0.46125);
            poseStack.scale(width / 16f, height / 16f, 1);
            renderPainting(poseStack.last(), paintingVertex, state.direction, packedLight);
        }
    }


    private ModelPart getFrame(PaintingVariant variant) {
        int width = variant.width();
        int height = variant.height();
        if (width == 1 && height == 1) return small;
        if (width == 2 && height == 2) return medium;
        if (width == 3 && height == 2) return large;
        if (width == 1 && height == 2) return tall;
        if (width == 2 && height == 1) return wide;
        throw new IllegalStateException("Unknown painting variant: " + variant);
    }

    private static void renderPainting(PoseStack.Pose pose, VertexConsumer consumer, Direction dir, int light) {
        Vec3i normal = dir.getUnitVec3i();
        consumer.addVertex(pose.pose(), 0, 0, 0).setColor(-1).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 0, 1, 0).setColor(-1).setUv(0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 1, 1, 0).setColor(-1).setUv(1, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 1, 0, 0).setColor(-1).setUv(1, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
    }

    public @NotNull ResourceLocation getTextureLocation(FancyPaintingRenderState entity) {
        int width = entity.variant.width();
        int height = entity.variant.height();
        if (width == 1 && height == 1) return FRAME_SMALL_TEXTURE;
        if (width == 2 && height == 2) return FRAME_MEDIUM_TEXTURE;
        if (width == 3 && height == 2) return FRAME_LARGE_TEXTURE;
        if (width == 1 && height == 2) return FRAME_TALL_TEXTURE;
        if (width == 2 && height == 1) return FRAME_WIDE_TEXTURE;
        throw new IllegalStateException("Unknown painting variant: " + entity.variant);
    }
}