package com.virbound.magmania.client.blocks;

import com.sun.jna.platform.win32.WinBase;
import com.virbound.magmania.Magmania;
import com.virbound.magmania.blocks.MagmaticTableBlockEntity;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class MagmaticTableBlockEntityRenderer implements BlockEntityRenderer<MagmaticTableBlockEntity> {
    public MagmaticTableBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    public float bookRotation = 180;
    @Override
    public void render(MagmaticTableBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, 1f, 0.5f);
        matrices.scale(0.35f, 0.35f, 0.35f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(entity.getTime()/10f+tickDelta/10f));


        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLight(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLight(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
