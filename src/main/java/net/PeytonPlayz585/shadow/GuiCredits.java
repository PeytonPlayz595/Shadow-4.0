package net.PeytonPlayz585.shadow;

import java.util.ArrayList;
import java.io.IOException;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.gui.GuiScreen;

public class GuiCredits extends GuiScreen {

    private ArrayList<String> credits = new ArrayList<String>();

	private int mousey = 0;

    private int scrollPosition = 0;
    private static final int visibleLines = 21;

    private int dragstart = -1;
	private int dragstartI = -1;

    private static final ResourceLocation beaconx = new ResourceLocation("textures/gui/container/beacon.png");
    private static final ResourceLocation creditsBck = new ResourceLocation("textures/gui/demo_bg.png");
    
    String fileLocation;
    GuiScreen parentScreen;
    
    public GuiCredits(GuiScreen screen, String location) {
    	this.fileLocation = location;
    	this.parentScreen = screen;
    }

    public void initGui() {
        if(this.credits.isEmpty()) {
            String file = EagRuntime.getResourceString(fileLocation);
            String[] lines = file.split("\n");
            for(String s : lines) {
                String s2 = s.trim();
                if(s2.isEmpty()) {
                    this.credits.add("");
                }else {
                    String[] words = s2.split(" ");
                    String currentLine = "   ";
                    for(String s3 : words) {
                        String cCurrentLine = currentLine + s3 + " ";
                        if(this.mc.fontRendererObj.getStringWidth(cCurrentLine) < width) {
                            currentLine = cCurrentLine;
                        }else {
                            this.credits.add(currentLine);
                            currentLine = s3 + " ";
                        }
                    }
                    this.credits.add(currentLine);
                }
            }
        }
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        if(par3 == 0) {
            int x = (width - 345) / 2;
            int y = (height - 230) / 2;
            if(par1 >= (x + 323) && par1 <= (x + 323 + 13) && par2 >= (y + 7) && par2 <= (y + 7 + 13)) {
                mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
                this.mc.displayGuiScreen(parentScreen);
            }
            int trackHeight = 193;
            int offset = trackHeight * scrollPosition / this.credits.size();
            if(par1 >= (x + 326) && par1 <= (x + 334) && par2 >= (y + 27 + offset) && par2 <= (y + 27 + offset + (visibleLines * trackHeight / this.credits.size()) + 1)) {
                dragstart = par2;
                dragstartI = scrollPosition;
            }
        }
    }

    public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(0, 0, par3);
        this.drawDefaultBackground();
		//this.drawGradientRect(0, 0, width, height, -1072689136, -804253680);
		//this.drawGradientRect(0, 0, width, height, 0xFFFF0000, 0xFF00FF00);
		mousey = par2;
		int x = (width - 345) / 2;
		int y = (height - 230) / 2;
        this.mc.getTextureManager().bindTexture(creditsBck);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0.0f);
		GlStateManager.scale(1.39f, 1.39f, 1.39f);
		this.drawTexturedModalRect(0, 0, 0, 0, 248, 166);
		GlStateManager.popMatrix();
        this.mc.getTextureManager().bindTexture(beaconx);
		this.drawTexturedModalRect(x + 323, y + 7, 114, 223, 13, 13);
		int lines = this.credits.size();
		if(scrollPosition < 0) scrollPosition = 0;
		if(scrollPosition + visibleLines > lines) scrollPosition = lines - visibleLines;
		for(int i = 0; i < visibleLines; ++i) {
			this.mc.fontRendererObj.drawString(this.credits.get(scrollPosition + i), x + 10, y + 10 + (i * 10), 0x404060);
		}
		int trackHeight = 193;
		int offset = trackHeight * scrollPosition / lines;
		drawRect(x + 326, y + 27, x + 334, y + 220, 0x33000020);
		drawRect(x + 326, y + 27 + offset, x + 334, y + 27 + (visibleLines * trackHeight / lines) + offset + 1, 0x66000000);
	}

    public void updateScreen() {
        if(Mouse.isButtonDown(0) && dragstart > 0) {
			int trackHeight = 193;
			scrollPosition = (mousey - dragstart) * this.credits.size() / trackHeight + dragstartI;
			if(scrollPosition < 0) scrollPosition = 0;
			if(scrollPosition + visibleLines > this.credits.size()) scrollPosition = this.credits.size() - visibleLines;
		} else {
			dragstart = -1;
		}
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int var1 = Mouse.getEventDWheel();
		if(var1 < 0) {
			scrollPosition += 3;
		}
		if(var1 > 0) {
			scrollPosition -= 3;
		}
    }
}