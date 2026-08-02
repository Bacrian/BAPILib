package imbacrian.bapilib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.Shaders;
import net.minecraft.client.render.renderer.State;

public class PlayerDoll {
	/*
	 * PlayerDoll: Creates a custom render of the player on screen. Like on the inventory menu.
	 */
    public static void renderPlayerDoll(int dollX, int dollY, int mx, int my, float partialTick, float size) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) {
            return;
        }

		GLRenderer.pushFrame();
		GLRenderer.setShader(Shaders.WORLD);
		GLRenderer.getFogState().disable();
		GLRenderer.enableState(State.DEPTH_TEST);
		GLRenderer.modelM4f().translate(dollX,dollY,50.0F);

		float scale = size;
		GLRenderer.modelM4f().scale(-scale,scale,scale);
		GLRenderer.modelM4f().rotateZ(org.joml.Math.toRadians(180.0F));

		float yBodyRot = mc.thePlayer.yBodyRot;
		float yBodyRotO = mc.thePlayer.yBodyRotO;
		float yRot = mc.thePlayer.yRot;
		float yRotO = mc.thePlayer.yRotO;
		float xRot = mc.thePlayer.xRot;
		float xRotO = mc.thePlayer.xRotO;

		float eyesX = dollX - mx;
		float eyesY = dollY - 75 - my;

		GLRenderer.modelM4f().rotateY(org.joml.Math.toRadians(135.0F));
		Lighting.enableLight();
		GLRenderer.setLightMapStrength(0.0F);
		GLRenderer.modelM4f().rotateY(org.joml.Math.toRadians(-135.0F));
		GLRenderer.modelM4f().rotateX(org.joml.Math.toRadians(-((float)Math.atan(eyesY / 40.0F)) * 20.0F));

		mc.thePlayer.yBodyRotO = mc.thePlayer.yBodyRot = (float)Math.atan(eyesX / 40.0F) * 20.0F;
		mc.thePlayer.yRotO = mc.thePlayer.yRot = (float)Math.atan(eyesX / 40.0F) * 40.0F;
		mc.thePlayer.xRotO = mc.thePlayer.xRot = -((float)Math.atan(eyesY / 40.0F)) * 20.0F;

		GLRenderer.modelM4f().translate(0.0F,mc.thePlayer.heightOffset, 0.0F);
		EntityRenderer.renderShadows = false;
		GLRenderer.globalGetNormalTransformMatrix().scale(1.0F, -1.0F, 1.0F);
		GLRenderer.setLightmapCoord2i(15, 15);

		EntityRendererDispatcher.instance.renderEntityPreviewWithPosYaw(GLRenderer.getTessellator(), mc.thePlayer, 0.0, 0.0, 0.0, 0.0F, partialTick);

		EntityRenderer.renderShadows = true;
		mc.thePlayer.yBodyRot = yBodyRot;
		mc.thePlayer.yBodyRotO = yBodyRotO;
		mc.thePlayer.yRot = yRot;
		mc.thePlayer.yRotO = yRotO;
		mc.thePlayer.xRot = xRot;
		mc.thePlayer.xRotO = xRotO;

		GLRenderer.popFrame();
		Lighting.disable();
	}
}
