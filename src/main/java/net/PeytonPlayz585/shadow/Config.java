package net.PeytonPlayz585.shadow;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.EaglerDeferredConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class Config {
	
	public static GameSettings gameSettings = null;
	private static Thread minecraftThread = null;
	private static final Logger LOGGER = LogManager.getLogger();
	private static DefaultResourcePack defaultResourcePackLazy = null;
	public static boolean zoomMode = false;
	private static int antialiasingLevel = 0;
	public static boolean waterOpacityChanged = false;
	
	public static void initGameSettings(GameSettings p_initGameSettings_0_) {	
		if (gameSettings == null)	{
			gameSettings = p_initGameSettings_0_;
		}
	}
	
	public static void initDisplay() {
       antialiasingLevel = gameSettings.ofAaLevel;
        minecraftThread = Thread.currentThread();
        updateThreadPriorities();
    }
	
	public static float getAmbientOcclusionLevel() {
		return gameSettings.ofAoLevel;
	}
	
	public static boolean isFogFancy() {
        return gameSettings.ofFogType == 2;
    }

    public static boolean isFogFast() {
        return gameSettings.ofFogType == 1;
    }

    public static boolean isFogOff() {
        return gameSettings.ofFogType == 3;
    }

    public static float getFogStart() {
        return gameSettings.ofFogStart;
    }
    
    public static boolean isSmoothWorld() {
        return gameSettings.ofSmoothWorld;
    }
    
    public static int getUpdatesPerFrame() {
        return gameSettings.ofChunkUpdates;
    }
    
    public static boolean isWeatherEnabled() {
        return gameSettings.ofWeather;
    }
    
    public static boolean isTimeDayOnly() {
        return gameSettings.ofTime == 1;
    }

    public static boolean isTimeDefault() {
        return gameSettings.ofTime == 0;
    }

    public static boolean isTimeNightOnly() {
        return gameSettings.ofTime == 2;
    }
    
    public static boolean isAnimatedWater() {
        return gameSettings.ofAnimatedWater != 2;
    }

    public static boolean isGeneratedWater() {
        return gameSettings.ofAnimatedWater == 1;
    }

    public static boolean isAnimatedPortal() {
        return gameSettings.ofAnimatedPortal;
    }

    public static boolean isAnimatedLava() {
        return gameSettings.ofAnimatedLava != 2;
    }

    public static boolean isGeneratedLava() {
        return gameSettings.ofAnimatedLava == 1;
    }

    public static boolean isAnimatedFire() {
        return gameSettings.ofAnimatedFire;
    }

    public static boolean isAnimatedRedstone() {
        return gameSettings.ofAnimatedRedstone;
    }

    public static boolean isAnimatedExplosion() {
        return gameSettings.ofAnimatedExplosion;
    }

    public static boolean isAnimatedFlame() {
        return gameSettings.ofAnimatedFlame;
    }

    public static boolean isAnimatedSmoke() {
        return gameSettings.ofAnimatedSmoke;
    }

    public static boolean isVoidParticles() {
        return gameSettings.ofVoidParticles;
    }

    public static boolean isWaterParticles() {
        return gameSettings.ofWaterParticles;
    }

    public static boolean isRainSplash() {
        return gameSettings.ofRainSplash;
    }

    public static boolean isPortalParticles() {
        return gameSettings.ofPortalParticles;
    }

    public static boolean isPotionParticles() {
        return gameSettings.ofPotionParticles;
    }

    public static boolean isFireworkParticles() {
        return gameSettings.ofFireworkParticles;
    }
    
    public static boolean isAnimatedTerrain() {
        return gameSettings.ofAnimatedTerrain;
    }

    public static boolean isAnimatedTextures() {
        return gameSettings.ofAnimatedTextures;
    }
    
    public static boolean isDrippingWaterLava() {
        return gameSettings.ofDrippingWaterLava;
    }
    
    public static boolean isTreesFancy() {
        return gameSettings.ofTrees == 0 ? gameSettings.fancyGraphics : gameSettings.ofTrees != 1;
    }

    //public static boolean isTreesSmart() {
        //return gameSettings.ofTrees == 4;
    //}

    public static boolean isCullFacesLeaves() {
        return gameSettings.ofTrees == 0 ? !gameSettings.fancyGraphics : gameSettings.ofTrees == 4;
    }
    
    public static boolean isRainFancy() {
        return gameSettings.ofRain == 0 ? gameSettings.fancyGraphics : gameSettings.ofRain == 2;
    }

    public static boolean isRainOff() {
        return gameSettings.ofRain == 3;
    }
    
    public static boolean isSkyEnabled() {
        return gameSettings.ofSky;
    }
    
    public static boolean isSunMoonEnabled() {
        return gameSettings.ofSunMoon;
    }
    
    public static boolean isStarsEnabled() {
        return gameSettings.ofStars;
    }
    
    public static boolean isDynamicFov() {
        return gameSettings.ofDynamicFov;
    }
    
    public static boolean isVignetteEnabled() {
        return gameSettings.ofVignette == 0 ? gameSettings.fancyGraphics : gameSettings.ofVignette == 2;
    }
    
    public static boolean isTranslucentBlocksFancy() {
        return gameSettings.ofTranslucentBlocks == 0 ? gameSettings.fancyGraphics : gameSettings.ofTranslucentBlocks == 2;
    }
    
    public static boolean isDroppedItemsFancy() {
        return gameSettings.ofDroppedItems == 0 ? gameSettings.fancyGraphics : gameSettings.ofDroppedItems == 2;
    }
    
    public static boolean isShowCapes() {
        return gameSettings.ofShowCapes;
    }
    
    public static int getMipmapType() {
        switch (gameSettings.ofMipmapType) {
            case 0:
                return 9986;
            case 1:
                return 9986;
            case 2:
                if (isMultiTexture()) {
                    return 9985;
                }
                return 9986;
            case 3:
                if (isMultiTexture()) {
                    return 9987;
                }
                return 9986;
            default:
                return 9986;
        }
    }
    
    public static int getAnisotropicFilterLevel() {
        return gameSettings.ofAfLevel;
    }

    public static boolean isAnisotropicFiltering() {
        return getAnisotropicFilterLevel() > 1;
    }

    public static int getAntialiasingLevel() {
        return antialiasingLevel;
    }

    public static boolean isAntialiasing() {
        return ((gameSettings.fxaa == 0 && gameSettings.fancyGraphics) || gameSettings.fxaa == 1);
    }

    public static boolean isMultiTexture() {
        return getAnisotropicFilterLevel() > 1 ? true : false;
    }
    
    public static boolean isClearWater() {
        return gameSettings.ofClearWater;
    }
    
    public static boolean isBetterGrass() {
        return gameSettings.ofBetterGrass != 3;
    }

    public static boolean isBetterGrassFancy() {
        return gameSettings.ofBetterGrass == 2;
    }
    
    public static boolean isBetterSnow() {
        return gameSettings.ofBetterSnow;
    }
    
    public static boolean isCustomSky() {
        return gameSettings.ofCustomSky;
    }
    
    public static boolean isCustomFonts() {
        return gameSettings.ofCustomFonts;
    }
    
    public static boolean isDynamicLights() {
        return gameSettings.ofDynamicLights != 3;
    }

    public static boolean isDynamicLightsFast() {
        return gameSettings.ofDynamicLights == 1;
    }

    public static boolean isDynamicHandLight() {
    	EaglerDeferredConfig conf = gameSettings.deferredShaderConf;
    	if(isShaders()) {
    		return conf.dynamicLights;
    	}
        return !isDynamicLights() ? false : true;
    }
	
	public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
        return p_limit_0_ < p_limit_2_ ? p_limit_2_ : (p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_);
    }

    public static float limitTo1(float p_limitTo1_0_) {
        return p_limitTo1_0_ < 0.0F ? 0.0F : (p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_);
    }
    
    public static boolean isSingleProcessor() {
        return PlatformRuntime.getAvailableProcessors() <= 1;
    }
    
    public static void updateThreadPriorities() {
        if (isSingleProcessor()) {
            if (isSmoothWorld()) {
                minecraftThread.setPriority(10);
            } else {
                minecraftThread.setPriority(5);
            }
        } else {
            minecraftThread.setPriority(10);
        }
    }
    
    public static boolean isMinecraftThread() {
        return Thread.currentThread() == minecraftThread;
    }
    
    public static IResourceManager getResourceManager() {
        return Minecraft.getMinecraft().getResourceManager();
    }
    
    public static TextureManager getTextureManager() {
        return Minecraft.getMinecraft().getTextureManager();
    }
    
    public static void dbg(String p_dbg_0_) {
        LOGGER.info("[Shadow Client] " + p_dbg_0_);
    }

    public static void warn(String p_warn_0_) {
        LOGGER.warn("[Shadow Client] " + p_warn_0_);
    }

    public static void error(String p_error_0_) {
        LOGGER.error("[Shadow Client] " + p_error_0_);
    }

    public static void log(String p_log_0_) {
        dbg(p_log_0_);
    }
    
    public static String arrayToString(Object[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                Object object = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(object));
            }

            return stringbuffer.toString();
        }
    }
    
    public static ModelManager getModelManager() {
        return Minecraft.getMinecraft().getRenderItem().modelManager;
    }
    
    public static IResourcePack getDefiningResourcePack(ResourceLocation p_getDefiningResourcePack_0_) {
        ResourcePackRepository resourcepackrepository = Minecraft.getMinecraft().getResourcePackRepository();
        IResourcePack iresourcepack = resourcepackrepository.getResourcePackInstance();

        if (iresourcepack != null && iresourcepack.resourceExists(p_getDefiningResourcePack_0_)) {
            return iresourcepack;
        } else {
            List<ResourcePackRepository.Entry> list = (List<ResourcePackRepository.Entry>)resourcepackrepository.repositoryEntries;

            if (list != null) {
                for (int i = list.size() - 1; i >= 0; --i) {
                    ResourcePackRepository.Entry resourcepackrepository$entry = (ResourcePackRepository.Entry)list.get(i);
                    IResourcePack iresourcepack1 = resourcepackrepository$entry.getResourcePack();

                    if (iresourcepack1.resourceExists(p_getDefiningResourcePack_0_)) {
                        return iresourcepack1;
                    }
                }
            }

            return getDefaultResourcePack().resourceExists(p_getDefiningResourcePack_0_) ? getDefaultResourcePack() : null;
        }
    }
    
    public static DefaultResourcePack getDefaultResourcePack() {
        if (defaultResourcePackLazy == null) {
            Minecraft minecraft = Minecraft.getMinecraft();
            defaultResourcePackLazy = (DefaultResourcePack)Minecraft.getMinecraft().mcDefaultResourcePack;

            if (defaultResourcePackLazy == null) {
                ResourcePackRepository resourcepackrepository = minecraft.getResourcePackRepository();

                if (resourcepackrepository != null) {
                    defaultResourcePackLazy = (DefaultResourcePack)resourcepackrepository.rprDefaultResourcePack;
                }
            }
        }

        return defaultResourcePackLazy;
    }
    
    public static void showGuiMessage(String p_showGuiMessage_0_, String p_showGuiMessage_1_) {
        GuiMessage guimessage = new GuiMessage(Minecraft.getMinecraft().currentScreen, p_showGuiMessage_0_, p_showGuiMessage_1_);
        Minecraft.getMinecraft().displayGuiScreen(guimessage);
    }

	public static boolean isShaders() {
		return gameSettings.shaders;
	}
	
	public static GameSettings getGameSettings() {
        return gameSettings;
    }
	
	public static boolean hasResource(ResourceLocation p_hasResource_0_) {
		for (IResourcePack resourcePack : getResourcePacks()) {
            if (resourcePack.resourceExists(p_hasResource_0_)) {
                return true;
            }
        }

        return false;
	}
	
	public static IResourcePack[] getResourcePacks() {
        ResourcePackRepository resourcepackrepository = Minecraft.getMinecraft().getResourcePackRepository();
        List list = resourcepackrepository.getRepositoryEntries();
        List list1 = new ArrayList();

        for (Object resourcepackrepository$entry : list) {
            list1.add(((ResourcePackRepository.Entry) resourcepackrepository$entry).getResourcePack());
        }

        if (resourcepackrepository.getResourcePackInstance() != null) {
            list1.add(resourcepackrepository.getResourcePackInstance());
        }

        IResourcePack[] airesourcepack = (IResourcePack[])((IResourcePack[])list1.toArray(new IResourcePack[list1.size()]));
        return airesourcepack;
    }
	
	public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_) {
        if (p_addObjectToArray_0_ == null) {
            throw new NullPointerException("The given array is NULL");
        } else {
            int i = p_addObjectToArray_0_.length;
            int j = i + 1;
            Object[] aobject = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), j));
            System.arraycopy(p_addObjectToArray_0_, 0, aobject, 0, i);
            aobject[i] = p_addObjectToArray_1_;
            return aobject;
        }
    }

    public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_, int p_addObjectToArray_2_) {
        List list = new ArrayList(Arrays.asList(p_addObjectToArray_0_));
        list.add(p_addObjectToArray_2_, p_addObjectToArray_1_);
        Object[] aobject = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), list.size()));
        return list.toArray(aobject);
    }
    
    public static int parseInt(String p_parseInt_0_, int p_parseInt_1_) {
        try {
            if (p_parseInt_0_ == null) {
                return p_parseInt_1_;
            } else {
                p_parseInt_0_ = p_parseInt_0_.trim();
                return Integer.parseInt(p_parseInt_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseInt_1_;
        }
    }

    public static float parseFloat(String p_parseFloat_0_, float p_parseFloat_1_) {
        try {
            if (p_parseFloat_0_ == null) {
                return p_parseFloat_1_;
            } else {
                p_parseFloat_0_ = p_parseFloat_0_.trim();
                return Float.parseFloat(p_parseFloat_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseFloat_1_;
        }
    }

    public static boolean parseBoolean(String p_parseBoolean_0_, boolean p_parseBoolean_1_) {
        try {
            if (p_parseBoolean_0_ == null) {
                return p_parseBoolean_1_;
            } else {
                p_parseBoolean_0_ = p_parseBoolean_0_.trim();
                return Boolean.parseBoolean(p_parseBoolean_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseBoolean_1_;
        }
    }

    public static String[] tokenize(String p_tokenize_0_, String p_tokenize_1_) {
        StringTokenizer stringtokenizer = new StringTokenizer(p_tokenize_0_, p_tokenize_1_);
        List list = new ArrayList();

        while (stringtokenizer.hasMoreTokens()) {
            String s = stringtokenizer.nextToken();
            list.add(s);
        }

        String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
        return astring;
    }
    
    public static InputStream getResourceStream(ResourceLocation p_getResourceStream_1_) throws IOException {
        IResourceManager p_getResourceStream_0_ = Minecraft.getMinecraft().getResourceManager();
    
        IResource defaultResource = p_getResourceStream_0_.getResource(p_getResourceStream_1_);
        if (defaultResource != null) {
            return defaultResource.getInputStream();
        }
    
        Set<String> resourceDomains = p_getResourceStream_0_.getResourceDomains();
        for (String resourceDomain : resourceDomains) {
            IResource resource = p_getResourceStream_0_.getResource(new ResourceLocation(resourceDomain, p_getResourceStream_1_.getResourcePath()));
            if (resource != null) {
                return resource.getInputStream();
            }
        }
    
        return null;
    }
    
    public static int[] addIntToArray(int[] p_addIntToArray_0_, int p_addIntToArray_1_) {
        return addIntsToArray(p_addIntToArray_0_, new int[] {p_addIntToArray_1_});
    }

    public static int[] addIntsToArray(int[] p_addIntsToArray_0_, int[] p_addIntsToArray_1_) {
        if (p_addIntsToArray_0_ != null && p_addIntsToArray_1_ != null) {
            int i = p_addIntsToArray_0_.length;
            int j = i + p_addIntsToArray_1_.length;
            int[] aint = new int[j];
            System.arraycopy(p_addIntsToArray_0_, 0, aint, 0, i);

            for (int k = 0; k < p_addIntsToArray_1_.length; ++k) {
                aint[k + i] = p_addIntsToArray_1_[k];
            }

            return aint;
        } else {
            throw new NullPointerException("The given array is NULL");
        }
    }
    
    public static String arrayToString(int[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                int j = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(j));
            }

            return stringbuffer.toString();
        }
    }
}
