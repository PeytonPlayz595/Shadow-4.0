package net.PeytonPlayz585.shadow;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ImageButton extends MainButton {

    protected ResourceLocation image;

    public ImageButton(int id, int x, int y, String buttonText, ResourceLocation image) {
        this(id, x, y, 17, 17, buttonText);
        this.image = image;
    }

    public ImageButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }
	
    @Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        boolean hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		if (hovered) {
			this.hovered = true;
			if (hoverFade < 40) hoverFade += 10;

			drawHoverEffect();
		} else {
			this.hovered = false;
			if (hoverFade > 0) hoverFade -= 10;
		}
		
		drawRoundedRect(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 2, new Color(30, 30, 30, 60));
		drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 2, new Color(255, 255, 255, 38 + hoverFade));
		
		drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 2, 3, new Color(255, 255, 255, 30).getRGB());
		
		int color = new Color(232, 232, 232, 183).getRGB();
		float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
		GlStateManager.color(f2, f3, f4, f1);
		
		boolean isAlpha = GlStateManager.isAlpha();
		boolean isBlend = GlStateManager.isBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();

		Minecraft.getMinecraft().getTextureManager().bindTexture(image);
		Gui.drawModalRectWithCustomSizedTexture(this.xPosition + 4, this.yPosition + 4, 0, 0, 9, 9, 9, 9);

		if(!isBlend) {
			GlStateManager.disableBlend();
		}

		if(!isAlpha) {
			GlStateManager.disableAlpha();
		}
	}

    protected void drawHoverEffect() {
		FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
		int w = (int) (font.getStringWidth(this.displayString) * 0.9F);
		drawRoundedRect(this.xPosition + (this.width - w) / 2, this.yPosition - 12, w, 7, 2, new Color(0, 0, 0, 126));
		this.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition - 11, new Color(255, 255, 255, 135).getRGB());
	}
}