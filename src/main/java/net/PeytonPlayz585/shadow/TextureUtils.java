package net.PeytonPlayz585.shadow;

import java.io.IOException;
import java.io.InputStream;

import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.DataView;

import net.lax1dude.eaglercraft.v1_8.internal.EnumPlatformOS;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

public class TextureUtils {
	
	public static EaglerTextureAtlasSprite iconGrassTop;
    public static EaglerTextureAtlasSprite iconGrassSide;
    public static EaglerTextureAtlasSprite iconGrassSideOverlay;
    public static EaglerTextureAtlasSprite iconSnow;
    public static EaglerTextureAtlasSprite iconGrassSideSnowed;
    public static EaglerTextureAtlasSprite iconMyceliumSide;
    public static EaglerTextureAtlasSprite iconMyceliumTop;
    public static EaglerTextureAtlasSprite iconWaterStill;
    public static EaglerTextureAtlasSprite iconWaterFlow;
    public static EaglerTextureAtlasSprite iconLavaStill;
    public static EaglerTextureAtlasSprite iconLavaFlow;
    public static EaglerTextureAtlasSprite iconPortal;
    public static EaglerTextureAtlasSprite iconFireLayer0;
    public static EaglerTextureAtlasSprite iconFireLayer1;
    public static EaglerTextureAtlasSprite iconGlass;
    public static EaglerTextureAtlasSprite iconGlassPaneTop;
    public static EaglerTextureAtlasSprite iconCompass;
    public static EaglerTextureAtlasSprite iconClock;
    
    public static void update() {
        TextureMap texturemap = getTextureMapBlocks();

        if (texturemap != null) {
            String s = "minecraft:blocks/";
            iconGrassTop = texturemap.getSpriteSafe(s + "grass_top");
            iconGrassSide = texturemap.getSpriteSafe(s + "grass_side");
            iconGrassSideOverlay = texturemap.getSpriteSafe(s + "grass_side_overlay");
            iconSnow = texturemap.getSpriteSafe(s + "snow");
            iconGrassSideSnowed = texturemap.getSpriteSafe(s + "grass_side_snowed");
            iconMyceliumSide = texturemap.getSpriteSafe(s + "mycelium_side");
            iconMyceliumTop = texturemap.getSpriteSafe(s + "mycelium_top");
            iconWaterStill = texturemap.getSpriteSafe(s + "water_still");
            iconWaterFlow = texturemap.getSpriteSafe(s + "water_flow");
            iconLavaStill = texturemap.getSpriteSafe(s + "lava_still");
            iconLavaFlow = texturemap.getSpriteSafe(s + "lava_flow");
            iconFireLayer0 = texturemap.getSpriteSafe(s + "fire_layer_0");
            iconFireLayer1 = texturemap.getSpriteSafe(s + "fire_layer_1");
            iconPortal = texturemap.getSpriteSafe(s + "portal");
            iconGlass = texturemap.getSpriteSafe(s + "glass");
            iconGlassPaneTop = texturemap.getSpriteSafe(s + "glass_pane_top");
            String s1 = "minecraft:items/";
            iconCompass = texturemap.getSpriteSafe(s1 + "compass");
            iconClock = texturemap.getSpriteSafe(s1 + "clock");
        }
    }
    
    public static TextureMap getTextureMapBlocks() {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }
    
    public static void registerResourceListener() {
        IResourceManager iresourcemanager = Config.getResourceManager();

        if (iresourcemanager instanceof IReloadableResourceManager) {
            IReloadableResourceManager ireloadableresourcemanager = (IReloadableResourceManager)iresourcemanager;
            IResourceManagerReloadListener iresourcemanagerreloadlistener = new IResourceManagerReloadListener() {
                public void onResourceManagerReload(IResourceManager resourceManager) {
                    TextureUtils.resourcesReloaded(resourceManager);
                }
            };
            ireloadableresourcemanager.registerReloadListener(iresourcemanagerreloadlistener);
        }
    }
    
    public static void resourcesReloaded(IResourceManager p_resourcesReloaded_0_) {
        if (getTextureMapBlocks() != null) {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            update();
            BetterGrass.update();
            BetterSnow.update();
            CustomSky.update();
            //SmartLeaves.updateLeavesModels();
            Config.getTextureManager().tick();
        }
    }
    
    public static void applyAnisotropicLevel() {
    	float f1 = (float)Config.getAnisotropicFilterLevel();
    	if(PlatformRuntime.isDebugRuntime()) {
    		f1 = Math.min(f1, 1.0f);
    	} else {
    		f1 = Math.min(f1, 16.0f);
    	}
    	EaglercraftGPU.glTexParameterf(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_MAX_ANISOTROPY, f1);
    	EaglercraftGPU.glTexParameteri(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_MIN_FILTER, RealOpenGLEnums.GL_NEAREST);
    }
    
    public static void bindTexture(int p_bindTexture_0_) {
        GlStateManager.bindTexture(p_bindTexture_0_);
    }
    
    public static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
        String s = "assets/minecraft/";

        if (p_fixResourcePath_0_.startsWith(s)) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(s.length());
            return p_fixResourcePath_0_;
        } else if (p_fixResourcePath_0_.startsWith("./")) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);

            if (!p_fixResourcePath_1_.endsWith("/")) {
                p_fixResourcePath_1_ = p_fixResourcePath_1_ + "/";
            }

            p_fixResourcePath_0_ = p_fixResourcePath_1_ + p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        } else {
            if (p_fixResourcePath_0_.startsWith("/~")) {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(1);
            }

            String s1 = "mcpatcher/";

            if (p_fixResourcePath_0_.startsWith("~/")) {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_;
                return p_fixResourcePath_0_;
            } else if (p_fixResourcePath_0_.startsWith("/")) {
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_.substring(1);
                return p_fixResourcePath_0_;
            } else {
                return p_fixResourcePath_0_;
            }
        }
    }
    
    public static String getBasePath(String p_getBasePath_0_) {
        int i = p_getBasePath_0_.lastIndexOf(47);
        return i < 0 ? "" : p_getBasePath_0_.substring(0, i);
    }
    
    public static ITextureObject getTexture(ResourceLocation p_getTexture_0_) {
        TextureManager textureManager = Config.getTextureManager();
        ITextureObject itextureobject = textureManager.getTexture(p_getTexture_0_);
    
        if (itextureobject != null) {
            return itextureobject;
        } else if (!Config.hasResource(p_getTexture_0_)) {
            return null;
        } else {
            for (ResourcePackRepository.Entry resourcePackEntry : Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries()) {
                IResourcePack resourcePack = resourcePackEntry.getResourcePack();
                try {
                    InputStream resourceStream = resourcePack.getInputStream(p_getTexture_0_);
                    if (resourceStream != null) {
                        itextureobject = new SimpleTexture(p_getTexture_0_);
                        textureManager.loadTexture(p_getTexture_0_, itextureobject);
                        return itextureobject;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    
            return null;
        }
    }
}
