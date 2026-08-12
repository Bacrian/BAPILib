package imbacrian.bapilib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import org.lwjgl.opengl.GL11;

public class PlayerDoll {
	/*
	 * PlayerDoll: Creates a custom render of the player on screen. Like on the inventory menu.
	 */
    public static void renderPlayerDoll(int dollX, int dollY, int mx, int my, float partialTick, float size) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) {
            return;
        }

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(32826); // GL_RESCALE_NORMAL
		GL11.glEnable(2903);  // GL_COLOR_MATERIAL
		GL11.glEnable(2929);  // GL_DEPTH_TEST
		GL11.glPushMatrix();
		GL11.glTranslatef(dollX, dollY, 50.0F);

		float scale = size;
		GL11.glScalef(-scale, scale, scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

		float yBodyRot = mc.thePlayer.yBodyRot;
		float yBodyRotO = mc.thePlayer.yBodyRotO;
		float yRot = mc.thePlayer.yRot;
		float yRotO = mc.thePlayer.yRotO;
		float xRot = mc.thePlayer.xRot;
		float xRotO = mc.thePlayer.xRotO;

		float eyesX = dollX - mx;
		float eyesY = dollY - 75 - my;

		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		Lighting.enableLight();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan(eyesY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);

		mc.thePlayer.yBodyRotO = mc.thePlayer.yBodyRot = (float) Math.atan(eyesX / 40.0F) * 20.0F;
		mc.thePlayer.yRotO = mc.thePlayer.yRot = (float) Math.atan(eyesX / 40.0F) * 40.0F;
		mc.thePlayer.xRotO = mc.thePlayer.xRot = -((float) Math.atan(eyesY / 40.0F)) * 20.0F;

		GL11.glTranslatef(0.0F, mc.thePlayer.heightOffset, 0.0F);

		EntityRenderDispatcher.instance.viewLerpYaw = 180.0F;
		EntityRenderer.renderShadows = false;
		mc.thePlayer.entityBrightness = 1.0F; // equivalente 7.3 a GLRenderer.setLightmapCoord2i(15,15)

		EntityRenderDispatcher.instance.renderEntityWithPosYaw(Tessellator.instance, mc.thePlayer, 0.0, 0.0, 0.0, 0.0F, partialTick);

		mc.thePlayer.entityBrightness = 0.0F;
		EntityRenderer.renderShadows = true;
		mc.thePlayer.yBodyRot = yBodyRot;
		mc.thePlayer.yBodyRotO = yBodyRotO;
		mc.thePlayer.yRot = yRot;
		mc.thePlayer.yRotO = yRotO;
		mc.thePlayer.xRot = xRot;
		mc.thePlayer.xRotO = xRotO;

		GL11.glPopMatrix();
		Lighting.disable();
		GL11.glDisable(32826);
    }
}
