package net.PeytonPlayz585.shadow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ArmorGui {
    public static void draw() {
    	if(Minecraft.getMinecraft().gameSettings.potionGUI) {
    		renderPotEffects();
    	}
    	if(Minecraft.getMinecraft().gameSettings.armorgui) {
    		renderArmorDura();
    	}
    	if(Minecraft.getMinecraft().gameSettings.cps) {
    		renderCPS();
    	}
    	if(Minecraft.getMinecraft().gameSettings.keyStrokes) {
    		renderKeyStrokes();
    	}
    }

    public static void renderArmorDura() {
        GlStateManager.enableLighting();
        ItemStack boots = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[0];
        ItemStack legs = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[1];
        ItemStack chest = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2];
        ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3];
        ItemStack hand = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
        
        if (helmet != null) {
            ItemStack displayhelmet = helmet.copy();
            displayhelmet.stackSize = 1;
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(displayhelmet, 5 + 3, 15 + 2);
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displayhelmet, 5 + 3, 15 + 2, "");
        }
        
        if (chest != null) {
            ItemStack displaychest = chest.copy();
            displaychest.stackSize = 1;
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(displaychest, 5 + 3, 15 + 18);
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displaychest, 5 + 3, 15 + 18, "");
        }
        
        if (legs != null) {
            ItemStack displaylegs = legs.copy();
            displaylegs.stackSize = 1;
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(displaylegs, 5 + 3, 10 + 38);
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displaylegs, 5 + 3, 15 + 38, "");
        }
        
        if (boots != null) {
            ItemStack displayboots = boots.copy();
            displayboots.stackSize = 1;
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(displayboots, 5 + 3, 15 + 50);
            Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displayboots, 5 + 3, 15 + 50, "");
        }
        
        RenderHelper.enableGUIStandardItemLighting();
        
        if (hand != null) {
        	ItemStack displayhand = hand.copy();
    		float f = (float) displayhand.animationsToGo - Minecraft.getMinecraft().getPartialTicks();
    		if (f > 0.0F) {
    			GlStateManager.pushMatrix();
    			float f1 = 1.0F + f / 5.0F;
    			GlStateManager.translate((float) ((5 + 3) + 8), (float) ((15 + 66) + 12), 0.0F);
    			GlStateManager.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
    			GlStateManager.translate((float) (-((5 + 3) + 8)), (float) (-((15 + 66) + 12)), 0.0F);
    		}

    		Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(displayhand, 5 + 3, 15 + 66);
    		if (f > 0.0F) {
    			GlStateManager.popMatrix();
    		}

    		Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlays(Minecraft.getMinecraft().fontRendererObj, displayhand, 5 + 3, 15 + 66);
        }
        
        RenderHelper.disableStandardItemLighting();
    }
    
    public static void renderPotEffects() {
        int i = 80;
        int i2 = 16;
        Collection <PotionEffect> collection = Minecraft.getMinecraft().thePlayer.getActivePotionEffects();
        if (!collection.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.enableAlpha();
            int l = 33;
            if (collection.size() > 5) l = 132 / (collection.size() - 1);
            for (PotionEffect potioneffect: Minecraft.getMinecraft().thePlayer.getActivePotionEffects()) {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                if (potion.hasStatusIcon()) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int i3 = potion.getStatusIconIndex();
                    GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());
                    guiIngame.drawTexturedModalRect(4 + 21 - 20, 110 + i2 - 14, 0 + i3 % 8 * 18, 198 + i3 / 8 * 18, 18, 18);
                }
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.2", new Object[0]);
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.3", new Object[0]);
                } else if (potioneffect.getAmplifier() == 3) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.4", new Object[0]);
                }
                Minecraft.getMinecraft().fontRendererObj.drawString(s1, (4 + 21), (110 + i2 - 14), -1);
                String s2 = Potion.getDurationString(potioneffect);
                Minecraft.getMinecraft().fontRendererObj.drawString(s2, (4 + 21), (110 + i2 + 10 - 14), -1);
                i2 += l;
            }
        }
    }

    public static boolean wasPressed;
    public static long lastPressed;
    private static List <Long> clicks = new ArrayList < > ();

    public static void renderCPS() {
        boolean pressed = Mouse.isButtonDown(0) || Mouse.isButtonDown(1);

        if (pressed != wasPressed) {
            lastPressed = System.currentTimeMillis();
            wasPressed = pressed;
            if (pressed) {
                clicks.add(lastPressed);
            }
        }

        final long time = System.currentTimeMillis();
        FuncUtils.removeIf(clicks, sinceLast -> sinceLast + 1000 < time);

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        Minecraft.getMinecraft().fontRendererObj.drawString("[CPS: " + clicks.size() + "]", res.getScaledWidth() / 2 - 17, 5 + 2, -1);
    }

    public static void renderKeyStrokes() {
    }
    
    private static void renderHotbarItem(int xPos, int yPos, float partialTicks, ItemStack stack) {
		ItemStack itemstack = stack;
		if (itemstack != null) {
			float f = (float) itemstack.animationsToGo - partialTicks;
			if (f > 0.0F) {
				GlStateManager.pushMatrix();
				float f1 = 1.0F + f / 5.0F;
				GlStateManager.translate((float) (xPos + 8), (float) (yPos + 12), 0.0F);
				GlStateManager.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
				GlStateManager.translate((float) (-(xPos + 8)), (float) (-(yPos + 12)), 0.0F);
			}

			Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemAndEffectIntoGUI(itemstack, xPos, yPos);
			if (f > 0.0F) {
				GlStateManager.popMatrix();
			}

			Minecraft.getMinecraft().ingameGUI.itemRenderer.renderItemOverlays(Minecraft.getMinecraft().fontRendererObj, itemstack, xPos, yPos);
		}
	}
}