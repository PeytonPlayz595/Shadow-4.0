package net.PeytonPlayz585.shadow;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;

public class MainButton extends GuiButton {

    public int hoverFade = 0;

    public MainButton(int id, int x, int y, String buttonText) {
        this(id, x, y, 132, 15, buttonText);
    }

    public MainButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }
	
    @Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		boolean hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		if (hovered) {
			if (hoverFade < 40) hoverFade += 10;
		} else {
			if (hoverFade > 0) hoverFade -= 10;
		}
		
		GlStateManager.color(1.0F,  1.0F,  1.0F);
		drawRoundedRect(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 2, new Color(30, 30, 30, 60));
		drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 2, new Color(255, 255, 255, 38 + hoverFade));
		
		drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 2, 3, new Color(255, 255, 255, 30).getRGB());

		FontRenderer fontrenderer = mc.fontRendererObj;
        this.drawCenteredString(fontrenderer, this.displayString, (int)(this.xPosition + this.width / 2 + 0.5F), (int)(this.yPosition + (this.height - 4) / 2 + 0.5F) - 1, new Color(30, 30, 30, 50).getRGB());
        this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, (this.yPosition + (this.height - 4) / 2) - 1, 10526880);
	}

    public static void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
    }

    public static void drawRoundedOutline(int x, int y, int x2, int y2, float radius, float width, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width) {
        int i = 18;
        int j = 90 / i;

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(RealOpenGLEnums.GL_SRC_ALPHA, RealOpenGLEnums.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(RealOpenGLEnums.GL_SRC_ALPHA, RealOpenGLEnums.GL_ONE_MINUS_SRC_ALPHA, RealOpenGLEnums.GL_ONE, RealOpenGLEnums.GL_ZERO);

        if (width != 1.0F) {
            EaglercraftGPU.glLineWidth(width);
        }

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x + radius, y, 0).endVertex();
        worldRenderer.pos(x2 - radius, y, 0).endVertex();
        tessellator.draw();

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x2, y + radius, 0).endVertex();
        worldRenderer.pos(x2, y2 - radius, 0).endVertex();
        tessellator.draw();

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x2 - radius, y2 - 0.1F, 0).endVertex();
        worldRenderer.pos(x + radius, y2 - 0.1F, 0).endVertex();
        tessellator.draw();

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x + 0.1F, y2 - radius, 0).endVertex();
        worldRenderer.pos(x + 0.1F, y + radius, 0).endVertex();
        tessellator.draw();

        float f1 = x2 - radius;
        float f2 = y + radius;
        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        for (int k = 0; k <= i; k++) {
            int m = 90 - k * j;
            worldRenderer.pos((float) (f1 + radius * ClientMathUtils.getRightAngle(m)), (float) (f2 - radius * ClientMathUtils.getAngle(m)), 0).endVertex();
        }
        tessellator.draw();

        f1 = x2 - radius;
        f2 = y2 - radius;
        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        for (int k = 0; k <= i; k++) {
            int m = k * j + 270;
            worldRenderer.pos((float) (f1 + radius * ClientMathUtils.getRightAngle(m)), (float) (f2 - radius * ClientMathUtils.getAngle(m)), 0).endVertex();
        }
        tessellator.draw();

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; k++) {
            int m = k * j + 90;
            worldRenderer.pos((float) (f1 + radius * ClientMathUtils.getRightAngle(m)), (float) (f2 + radius * ClientMathUtils.getAngle(m)), 0).endVertex();
        }
        tessellator.draw();

        worldRenderer.begin(Tessellator.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; k++) {
            int m = 270 - k * j;
            worldRenderer.pos((float) (f1 + radius * ClientMathUtils.getRightAngle(m)), (float) (f2 + radius * ClientMathUtils.getAngle(m)), 0).endVertex();
        }
        tessellator.draw();

        if (width != 1.0F) {
            EaglercraftGPU.glLineWidth(1.0F);
        }

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }
}