package net.minecraft.client.settings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.lax1dude.eaglercraft.v1_8.sp.relay.RelayManager;
import org.json.JSONArray;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.PeytonPlayz585.shadow.ClearWater;
import net.PeytonPlayz585.shadow.Config;
import net.PeytonPlayz585.shadow.CustomColors;
import net.PeytonPlayz585.shadow.CustomSky;
import net.PeytonPlayz585.shadow.DynamicLights;
import net.PeytonPlayz585.shadow.GuiYesNo;
import net.PeytonPlayz585.shadow.GuiYesNoCallbackBufferQueue;
import net.PeytonPlayz585.shadow.GuiYesNoCallbackVisGraph;
import net.PeytonPlayz585.shadow.Lang;
import net.lax1dude.eaglercraft.v1_8.ArrayUtils;
import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.EaglerInputStream;
import net.lax1dude.eaglercraft.v1_8.EaglerOutputStream;
import net.lax1dude.eaglercraft.v1_8.EaglerZLIB;
import net.lax1dude.eaglercraft.v1_8.HString;
import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.EaglerDeferredConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2024 lax1dude, ayunami2000. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class GameSettings {
	private static final Logger logger = LogManager.getLogger();

	/**+
	 * GUI scale values
	 */
	private static final String[] GUISCALES = new String[] { "options.guiScale.auto", "options.guiScale.small",
			"options.guiScale.normal", "options.guiScale.large" };
	private static final String[] PARTICLES = new String[] { "options.particles.all", "options.particles.decreased",
			"options.particles.minimal" };
	private static final String[] AMBIENT_OCCLUSIONS = new String[] { "options.ao.off", "options.ao.min",
			"options.ao.max" };
	private static final String[] field_181149_aW = new String[] { "options.off", "options.graphics.fast",
			"options.graphics.fancy" };
	private static final int[] OF_TREES_VALUES = new int[] {0, 1, 4, 2};
	private static final int[] OF_DYNAMIC_LIGHTS = new int[] {3, 1, 2};
    private static final String[] KEYS_DYNAMIC_LIGHTS = new String[] {"options.off", "options.graphics.fast", "options.graphics.fancy"};
	public float mouseSensitivity = 0.5F;
	public boolean invertMouse;
	public int renderDistanceChunks = -1;
	public boolean viewBobbing = true;
	public boolean anaglyph;
	public boolean fboEnable = true;
	public int limitFramerate = 260;
	/**+
	 * Clouds flag
	 */
	public int clouds = 1;
	public boolean fancyGraphics = false;
	/**+
	 * Smooth Lighting
	 */
	public int ambientOcclusion = 0;
	public List<String> resourcePacks = Lists.newArrayList();
	public List<String> field_183018_l = Lists.newArrayList();
	public EntityPlayer.EnumChatVisibility chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
	public boolean chatColours = true;
	public boolean chatLinks = true;
	public boolean chatLinksPrompt = true;
	public float chatOpacity = 1.0F;
	public boolean snooperEnabled = true;
	public boolean enableVsync = true;
	public boolean allowBlockAlternatives = true;
	public boolean reducedDebugInfo = false;
	public boolean hideServerAddress;
	public boolean advancedItemTooltips;
	/**+
	 * Whether to pause when the game loses focus, toggled by F3+P
	 */
	public boolean pauseOnLostFocus = true;
	private final Set<EnumPlayerModelParts> setModelParts = Sets.newHashSet(EnumPlayerModelParts._VALUES);
	public boolean touchscreen;
	public int overrideWidth;
	public int overrideHeight;
	public boolean heldItemTooltips = true;
	public float chatScale = 1.0F;
	public float chatWidth = 1.0F;
	public float chatHeightUnfocused = 0.44366196F;
	public float chatHeightFocused = 1.0F;
	public boolean showInventoryAchievementHint = true;
	public int mipmapLevels = 4;
	private Map<SoundCategory, Float> mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
	public boolean field_181150_U = true;
	public boolean field_181151_V = true;
	public KeyBinding keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
	public KeyBinding keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
	public KeyBinding keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
	public KeyBinding keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
	public KeyBinding keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
	public KeyBinding keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
	public KeyBinding keyBindSprint = new KeyBinding("key.sprint", KeyboardConstants.KEY_R, "key.categories.movement");
	public KeyBinding keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
	public KeyBinding keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
	public KeyBinding keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
	public KeyBinding keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
	public KeyBinding keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
	public KeyBinding keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
	public KeyBinding keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
	public KeyBinding keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
	public KeyBinding keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
	public KeyBinding keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
	public KeyBinding keyBindSmoothCamera = new KeyBinding("key.smoothCamera", KeyboardConstants.KEY_M,
			"key.categories.misc");
	public KeyBinding keyBindZoomCamera = new KeyBinding("key.zoomCamera", KeyboardConstants.KEY_C,
			"key.categories.misc");
	public KeyBinding keyBindFunction = new KeyBinding("key.function", KeyboardConstants.KEY_F, "key.categories.misc");
	public KeyBinding keyBindClose = new KeyBinding("key.close", KeyboardConstants.KEY_GRAVE, "key.categories.misc");
	public KeyBinding[] keyBindsHotbar = new KeyBinding[] {
			new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"),
			new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"),
			new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"),
			new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"),
			new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"),
			new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"),
			new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"),
			new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"),
			new KeyBinding("key.hotbar.9", 10, "key.categories.inventory") };
	public KeyBinding[] keyBindings;
	protected Minecraft mc;
	public EnumDifficulty difficulty;
	public boolean hasCreatedDemoWorld;
	public int relayTimeout;
	public boolean hideJoinCode;
	public boolean hideGUI;
	public int thirdPersonView;
	public boolean showDebugInfo;
	public boolean showDebugProfilerChart;
	public boolean field_181657_aC;
	public String lastServer;
	public boolean smoothCamera;
	public boolean debugCamEnable;
	public float fovSetting;
	public float gammaSetting;
	public float saturation;
	/**+
	 * GUI scale
	 */
	public int guiScale = 3;
	public int particleSetting;
	public String language;
	public boolean forceUnicodeFont;
	public boolean hudFps = true;
	public boolean hudCoords = true;
	public boolean hudPlayer = false;
	public boolean hudWorld = false;
	public boolean hudStats = false;
	public boolean hud24h = false;
	public boolean chunkFix = true;
	public int fxaa = 0;
	public boolean shaders = false;
	public boolean shadersAODisable = false;
	public EaglerDeferredConfig deferredShaderConf = new EaglerDeferredConfig();
	public boolean enableUpdateSvc = true;
	
	public float ofAoLevel = 1.0F;
	public boolean useVBOs = false;
	public int ofFogType = 1;
    public float ofFogStart = 0.8F;
    public boolean ofFastMath = false;
    public boolean ofSmoothFps = false;
    public boolean ofSmoothWorld = Config.isSingleProcessor();
    public int ofChunkUpdates = 1;
    public boolean ofLagometer = false;
    public boolean ofWeather = true;
    public int ofTime = 0;
    public boolean ofProfiler = false;
    public int ofAutoSaveTicks = 4000;
    public int ofAnimatedWater = 0;
    public int ofAnimatedLava = 0;
    public boolean ofAnimatedFire = true;
    public boolean ofAnimatedPortal = true;
    public boolean ofAnimatedRedstone = true;
    public boolean ofAnimatedExplosion = true;
    public boolean ofAnimatedFlame = true;
    public boolean ofAnimatedSmoke = true;
    public boolean ofVoidParticles = true;
    public boolean ofWaterParticles = true;
    public boolean ofRainSplash = true;
    public boolean ofPortalParticles = true;
    public boolean ofPotionParticles = true;
    public boolean ofFireworkParticles = true;
    public boolean ofDrippingWaterLava = true;
    public boolean ofAnimatedTerrain = true;
    public boolean ofAnimatedTextures = true;
    public float ofCloudsHeight = 0.0F;
    public int ofTrees = 0;
    public int ofRain = 0;
    public boolean ofSky = true;
    public boolean ofStars = true;
    public boolean ofSunMoon = true;
    public boolean ofDynamicFov = true;
    public int ofVignette = 0;
    public int ofTranslucentBlocks = 0;
    public int ofDroppedItems = 0;
    public boolean ofShowCapes = true;
    public int ofMipmapType = 0;
    public int ofAaLevel = 0;
    public int ofAfLevel = 1;
    public boolean ofClearWater = false;
    public boolean ofRandomMobs = true;
    public int ofBetterGrass = 3;
    public boolean ofBetterSnow = false;
    public boolean ofCustomFonts = true;
    public boolean ofCustomSky = true;
    public boolean ofCustomItems = true;
    public int ofDynamicLights = 3;
    public boolean toggleSprint = false;
	public boolean toggleSprintEnabled = false;
	public boolean chunkBorders = false;
	public boolean experimentalVisGraph = false;
	public boolean experimentalBufferQueue = false;
	public GuiScreen lastGuiScreen;
	public boolean ofSwampColors = true;
	public boolean ofSmoothBiomes = true;
	public boolean ofCustomColors = true;

	public GameSettings(Minecraft mcIn) {
		this.keyBindings = (KeyBinding[]) ArrayUtils.addAll(new KeyBinding[] { this.keyBindAttack, this.keyBindUseItem,
				this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump,
				this.keyBindSneak, this.keyBindSprint, this.keyBindDrop, this.keyBindInventory, this.keyBindChat,
				this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot,
				this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindZoomCamera, this.keyBindFunction,
				this.keyBindClose }, this.keyBindsHotbar);
		this.difficulty = EnumDifficulty.NORMAL;
		this.relayTimeout = 4;
		this.hideJoinCode = false;
		this.lastServer = "";
		this.fovSetting = 70.0F;
		this.gammaSetting = 1.0F;
		this.language = EagRuntime.getConfiguration().getDefaultLocale();
		this.forceUnicodeFont = false;
		this.mc = mcIn;
		GameSettings.Options.RENDER_DISTANCE.setValueMax(18.0F);

		this.renderDistanceChunks = 4;
		this.loadOptions();
		Config.initGameSettings(this);
	}

	/**+
	 * Represents a key or mouse button as a string. Args: key
	 */
	public static String getKeyDisplayString(int parInt1) {
		return parInt1 < 0 ? I18n.format("key.mouseButton", new Object[] { Integer.valueOf(parInt1 + 101) })
				: (parInt1 < 256 ? Keyboard.getKeyName(parInt1)
						: HString.format("%c", new Object[] { Character.valueOf((char) (parInt1 - 256)) })
								.toUpperCase());
	}

	/**+
	 * Returns whether the specified key binding is currently being
	 * pressed.
	 */
	public static boolean isKeyDown(KeyBinding parKeyBinding) {
		return parKeyBinding.getKeyCode() == 0 ? false
				: (parKeyBinding.getKeyCode() < 0 ? Mouse.isButtonDown(parKeyBinding.getKeyCode() + 100)
						: Keyboard.isKeyDown(parKeyBinding.getKeyCode()));
	}

	/**+
	 * Sets a key binding and then saves all settings.
	 */
	public void setOptionKeyBinding(KeyBinding parKeyBinding, int parInt1) {
		parKeyBinding.setKeyCode(parInt1);
		this.saveOptions();
	}

	/**+
	 * If the specified option is controlled by a slider (float
	 * value), this will set the float value.
	 */
	public void setOptionFloatValue(GameSettings.Options parOptions, float parFloat1) {
		if (parOptions == GameSettings.Options.SENSITIVITY) {
			this.mouseSensitivity = parFloat1;
		}

		if (parOptions == GameSettings.Options.FOV) {
			this.fovSetting = parFloat1;
		}

		if (parOptions == GameSettings.Options.GAMMA) {
			this.gammaSetting = parFloat1;
		}

		if (parOptions == GameSettings.Options.FRAMERATE_LIMIT) {
			this.limitFramerate = (int) parFloat1;
		}

		if (parOptions == GameSettings.Options.CHAT_OPACITY) {
			this.chatOpacity = parFloat1;
			this.mc.ingameGUI.getChatGUI().refreshChat();
		}

		if (parOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED) {
			this.chatHeightFocused = parFloat1;
			this.mc.ingameGUI.getChatGUI().refreshChat();
		}

		if (parOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED) {
			this.chatHeightUnfocused = parFloat1;
			this.mc.ingameGUI.getChatGUI().refreshChat();
		}

		if (parOptions == GameSettings.Options.CHAT_WIDTH) {
			this.chatWidth = parFloat1;
			this.mc.ingameGUI.getChatGUI().refreshChat();
		}

		if (parOptions == GameSettings.Options.CHAT_SCALE) {
			this.chatScale = parFloat1;
			this.mc.ingameGUI.getChatGUI().refreshChat();
		}

		if (parOptions == GameSettings.Options.MIPMAP_LEVELS) {
			int i = this.mipmapLevels;
			this.mipmapLevels = (int) parFloat1;
			if ((float) i != parFloat1) {
				this.mc.getTextureMapBlocks().setMipmapLevels(this.mipmapLevels);
				this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
				this.mc.getTextureMapBlocks().setBlurMipmapDirect(false, this.mipmapLevels > 0);
				this.mc.scheduleResourcesRefresh();
			}
		}

		if (parOptions == GameSettings.Options.BLOCK_ALTERNATIVES) {
			this.allowBlockAlternatives = !this.allowBlockAlternatives;
			this.mc.renderGlobal.loadRenderers();
		}

		if (parOptions == GameSettings.Options.RENDER_DISTANCE) {
			this.renderDistanceChunks = (int) parFloat1;
			this.mc.renderGlobal.setDisplayListEntitiesDirty();
		}
		
		if (parOptions == GameSettings.Options.AO_LEVEL) {
            this.ofAoLevel = parFloat1;
            this.mc.renderGlobal.loadRenderers();
        }
		
		if (parOptions == GameSettings.Options.CLOUD_HEIGHT) {
            this.ofCloudsHeight = parFloat1;
        }
		
		if (parOptions == GameSettings.Options.MIPMAP_TYPE) {
            int l = (int)parFloat1;
            this.ofMipmapType = Config.limit(l, 0, 3);
            this.mc.refreshResources();
        }
		
		if (parOptions == GameSettings.Options.AA_LEVEL) {
            int i = (int)parFloat1;

            if (i > 0 && Config.isShaders()) {
                Config.showGuiMessage("Antialiasing is not compatible with Shaders.", "Please disable Shaders to enable this option.");
                return;
            }

            int[] aint = new int[] {0, 2, 4, 6, 8, 12, 16};
            this.ofAaLevel = 0;

            for (int j = 0; j < aint.length; ++j) {
                if (i >= aint[j]) {
                    this.ofAaLevel = aint[j];
                }
            }

            this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
        }

        if (parOptions == GameSettings.Options.AF_LEVEL) {
            int k = (int)parFloat1;

            if (k > 1 && Config.isShaders()) {
                Config.showGuiMessage("Anisotropic Filtering is not compatible with Shaders.", "Please disable Shaders to enable this option.");
                return;
            }

            for (this.ofAfLevel = 1; this.ofAfLevel * 2 <= k; this.ofAfLevel *= 2) {
                ;
            }

            this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
            this.mc.refreshResources();
        }
	}

	/**+
	 * For non-float options. Toggles the option on/off, or cycles
	 * through the list i.e. render distances.
	 */
	public void setOptionValue(GameSettings.Options parOptions, int parInt1) {
		if (parOptions == GameSettings.Options.INVERT_MOUSE) {
			this.invertMouse = !this.invertMouse;
		}

		if (parOptions == GameSettings.Options.GUI_SCALE) {
			this.guiScale = this.guiScale + parInt1 & 3;
		}

		if (parOptions == GameSettings.Options.PARTICLES) {
			this.particleSetting = (this.particleSetting + parInt1) % 3;
		}

		if (parOptions == GameSettings.Options.VIEW_BOBBING) {
			this.viewBobbing = !this.viewBobbing;
		}

		if (parOptions == GameSettings.Options.RENDER_CLOUDS) {
			this.clouds = (this.clouds + parInt1) % 3;
		}

		if (parOptions == GameSettings.Options.FORCE_UNICODE_FONT) {
			this.forceUnicodeFont = !this.forceUnicodeFont;
			this.mc.fontRendererObj
					.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
		}

		if (parOptions == GameSettings.Options.FBO_ENABLE) {
			this.fboEnable = !this.fboEnable;
		}

		if (parOptions == GameSettings.Options.ANAGLYPH) {
			this.anaglyph = !this.anaglyph;
			this.mc.loadingScreen.eaglerShow(I18n.format("resourcePack.load.refreshing"),
					I18n.format("resourcePack.load.pleaseWait"));
			this.mc.refreshResources();
		}

		if (parOptions == GameSettings.Options.GRAPHICS) {
			this.fancyGraphics = !this.fancyGraphics;
			this.mc.renderGlobal.loadRenderers();
		}

		if (parOptions == GameSettings.Options.AMBIENT_OCCLUSION) {
			this.ambientOcclusion = (this.ambientOcclusion + parInt1) % 3;
			this.mc.renderGlobal.loadRenderers();
		}

		if (parOptions == GameSettings.Options.CHAT_VISIBILITY) {
			this.chatVisibility = EntityPlayer.EnumChatVisibility
					.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + parInt1) % 3);
		}

		if (parOptions == GameSettings.Options.CHAT_COLOR) {
			this.chatColours = !this.chatColours;
		}

		if (parOptions == GameSettings.Options.CHAT_LINKS) {
			this.chatLinks = !this.chatLinks;
		}

		if (parOptions == GameSettings.Options.CHAT_LINKS_PROMPT) {
			this.chatLinksPrompt = !this.chatLinksPrompt;
		}

		if (parOptions == GameSettings.Options.SNOOPER_ENABLED) {
			this.snooperEnabled = !this.snooperEnabled;
		}

		if (parOptions == GameSettings.Options.TOUCHSCREEN) {
			this.touchscreen = !this.touchscreen;
		}

		if (parOptions == GameSettings.Options.BLOCK_ALTERNATIVES) {
			this.allowBlockAlternatives = !this.allowBlockAlternatives;
			this.mc.renderGlobal.loadRenderers();
		}

		if (parOptions == GameSettings.Options.REDUCED_DEBUG_INFO) {
			this.reducedDebugInfo = !this.reducedDebugInfo;
		}

		if (parOptions == GameSettings.Options.ENTITY_SHADOWS) {
			this.field_181151_V = !this.field_181151_V;
		}

		if (parOptions == GameSettings.Options.HUD_FPS) {
			this.hudFps = !this.hudFps;
		}

		if (parOptions == GameSettings.Options.HUD_COORDS) {
			this.hudCoords = !this.hudCoords;
		}

		if (parOptions == GameSettings.Options.HUD_PLAYER) {
			this.hudPlayer = !this.hudPlayer;
		}

		if (parOptions == GameSettings.Options.HUD_STATS) {
			this.hudStats = !this.hudStats;
		}

		if (parOptions == GameSettings.Options.HUD_WORLD) {
			this.hudWorld = !this.hudWorld;
		}

		if (parOptions == GameSettings.Options.HUD_24H) {
			this.hud24h = !this.hud24h;
		}

		if (parOptions == GameSettings.Options.CHUNK_FIX) {
			this.chunkFix = !this.chunkFix;
		}

		if (parOptions == GameSettings.Options.FXAA) {
			this.fxaa = (this.fxaa + parInt1) % 3;
		}

		if (parOptions == GameSettings.Options.FULLSCREEN) {
			this.mc.toggleFullscreen();
		}
		
		if(parOptions == GameSettings.Options.USE_VBO) {
			this.useVBOs = !this.useVBOs;
		}
		
		if (parOptions == GameSettings.Options.FOG_FANCY) {
            switch (this.ofFogType) {
                case 1:
                    this.ofFogType = 2;
                    break;
                case 2:
                    this.ofFogType = 3;
                    break;
                case 3:
                    this.ofFogType = 1;
                    break;
                default:
                    this.ofFogType = 1;
            }
		}

        if (parOptions == GameSettings.Options.FOG_START) {
            this.ofFogStart += 0.2F;

            if (this.ofFogStart > 0.81F) {
                this.ofFogStart = 0.2F;
            }
        }
        
        if (parOptions == GameSettings.Options.FAST_MATH) {
            this.ofFastMath = !this.ofFastMath;
            MathHelper.fastMath = this.ofFastMath;
        }
        
        if (parOptions == GameSettings.Options.SMOOTH_FPS) {
            ofSmoothFps = !ofSmoothFps;
        }
        
        if (parOptions == GameSettings.Options.SMOOTH_WORLD) {
            ofSmoothWorld = !ofSmoothWorld;
            Config.updateThreadPriorities();
        }
        
        if (parOptions == GameSettings.Options.CHUNK_UPDATES) {
            ++this.ofChunkUpdates;

            if (this.ofChunkUpdates > 5) {
                this.ofChunkUpdates = 1;
            }
        }
        
        if (parOptions == GameSettings.Options.LAGOMETER) {
            this.ofLagometer = !this.ofLagometer;
        }
        
        if (parOptions == GameSettings.Options.WEATHER) {
            ofWeather = !ofWeather;
        }
        
        if (parOptions == GameSettings.Options.TIME) {
            ++ofTime;

            if (ofTime > 2) {
                ofTime = 0;
            }
        }
        
        if (parOptions == GameSettings.Options.PROFILER) {
            this.ofProfiler = !this.ofProfiler;
        }
        
        if (parOptions == GameSettings.Options.AUTOSAVE_TICKS) {
            this.ofAutoSaveTicks *= 10;

            if (this.ofAutoSaveTicks > 40000) {
                this.ofAutoSaveTicks = 40;
            }
        }
        
        if (parOptions == GameSettings.Options.ANIMATED_WATER) {
            ++this.ofAnimatedWater;

            if (this.ofAnimatedWater == 1) {
                ++this.ofAnimatedWater;
            }

            if (this.ofAnimatedWater > 2) {
                this.ofAnimatedWater = 0;
            }
        }

        if (parOptions == GameSettings.Options.ANIMATED_LAVA) {
            ++this.ofAnimatedLava;

            if (this.ofAnimatedLava == 1) {
                ++this.ofAnimatedLava;
            }

            if (this.ofAnimatedLava > 2) {
                this.ofAnimatedLava = 0;
            }
        }

        if (parOptions == GameSettings.Options.ANIMATED_FIRE) {
            this.ofAnimatedFire = !this.ofAnimatedFire;
        }

        if (parOptions == GameSettings.Options.ANIMATED_PORTAL) {
            this.ofAnimatedPortal = !this.ofAnimatedPortal;
        }

        if (parOptions == GameSettings.Options.ANIMATED_REDSTONE) {
            this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
        }

        if (parOptions == GameSettings.Options.ANIMATED_EXPLOSION) {
            this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
        }

        if (parOptions == GameSettings.Options.ANIMATED_FLAME) {
            this.ofAnimatedFlame = !this.ofAnimatedFlame;
        }

        if (parOptions == GameSettings.Options.ANIMATED_SMOKE) {
            this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
        }

        if (parOptions  == GameSettings.Options.VOID_PARTICLES) {
            this.ofVoidParticles = !this.ofVoidParticles;
        }

        if (parOptions == GameSettings.Options.WATER_PARTICLES) {
            this.ofWaterParticles = !this.ofWaterParticles;
        }

        if (parOptions == GameSettings.Options.PORTAL_PARTICLES) {
            this.ofPortalParticles = !this.ofPortalParticles;
        }

        if (parOptions == GameSettings.Options.POTION_PARTICLES) {
            this.ofPotionParticles = !this.ofPotionParticles;
        }

        if (parOptions == GameSettings.Options.FIREWORK_PARTICLES) {
            this.ofFireworkParticles = !this.ofFireworkParticles;
        }

        if (parOptions == GameSettings.Options.DRIPPING_WATER_LAVA) {
            this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
        }

        if (parOptions == GameSettings.Options.ANIMATED_TERRAIN) {
            this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
        }

        if (parOptions == GameSettings.Options.ANIMATED_TEXTURES) {
            this.ofAnimatedTextures = !this.ofAnimatedTextures;
        }

        if (parOptions == GameSettings.Options.RAIN_SPLASH) {
            this.ofRainSplash = !this.ofRainSplash;
        }
        
        if (parOptions == GameSettings.Options.TREES) {
            this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
            //SmartLeaves.updateLeavesModels();
            //for(int i = 0; i < 5; i++) {
            	this.mc.renderGlobal.loadRenderers();
            	//Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(Minecraft.getMinecraft().theWorld);
            	//Minecraft.getMinecraft().theWorld.updateBlocks();
            	//if(Minecraft.getMinecraft().isIntegratedServerRunning()) {
            		//ClientPlatformSingleplayer.sendPacket(new IPCPacketData("iFuckingHateWebworkers", "updateBlocks".getBytes()));
            	//}
            //}
            //SmartLeaves.updateLeavesModels();
        }
        
        if (parOptions == GameSettings.Options.RAIN) {
            ++this.ofRain;

            if (this.ofRain > 3) {
                this.ofRain = 0;
            }
        }
        
        if (parOptions == GameSettings.Options.SKY) {
            this.ofSky = !this.ofSky;
        }
        
        if (parOptions == GameSettings.Options.STARS) {
            this.ofStars = !this.ofStars;
        }

        if (parOptions == GameSettings.Options.SUN_MOON) {
            this.ofSunMoon = !this.ofSunMoon;
        }
        
        if (parOptions == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
            this.heldItemTooltips = !this.heldItemTooltips;
        }
        
        if (parOptions == GameSettings.Options.DYNAMIC_FOV) {
            this.ofDynamicFov = !this.ofDynamicFov;
        }
        
        if (parOptions == GameSettings.Options.VIGNETTE) {
            ++this.ofVignette;

            if (this.ofVignette > 2) {
                this.ofVignette = 0;
            }
        }
        
        if (parOptions == GameSettings.Options.TRANSLUCENT_BLOCKS) {
            if (this.ofTranslucentBlocks == 0) {
                this.ofTranslucentBlocks = 1;
            } else if (this.ofTranslucentBlocks == 1) {
                this.ofTranslucentBlocks = 2;
            } else if (this.ofTranslucentBlocks == 2) {
                this.ofTranslucentBlocks = 0;
            } else {
                this.ofTranslucentBlocks = 0;
            }

            this.mc.renderGlobal.loadRenderers();
        }
        
        if (parOptions == GameSettings.Options.DROPPED_ITEMS) {
            ++this.ofDroppedItems;

            if (this.ofDroppedItems > 2) {
                this.ofDroppedItems = 0;
            }
        }
        
        if (parOptions == GameSettings.Options.SHOW_CAPES) {
            this.ofShowCapes = !this.ofShowCapes;
        }
        
        if (parOptions == GameSettings.Options.CLEAR_WATER) {
            this.ofClearWater = !this.ofClearWater;
            this.updateWaterOpacity();
        }
        
        if (parOptions == GameSettings.Options.RANDOM_MOBS) {
            this.ofRandomMobs = !this.ofRandomMobs;
            //RandomMobs.resetTextures();
        }
        
        if (parOptions == GameSettings.Options.BETTER_GRASS) {
            ++this.ofBetterGrass;

            if (this.ofBetterGrass > 3) {
                this.ofBetterGrass = 1;
            }

            this.mc.renderGlobal.loadRenderers();
        }
        
        if (parOptions == GameSettings.Options.BETTER_SNOW) {
            this.ofBetterSnow = !this.ofBetterSnow;
            this.mc.renderGlobal.loadRenderers();
        }
        
        if (parOptions == GameSettings.Options.CUSTOM_FONTS) {
            this.ofCustomFonts = !this.ofCustomFonts;
            this.mc.fontRendererObj.onResourceManagerReload(Config.getResourceManager());
            this.mc.standardGalacticFontRenderer.onResourceManagerReload(Config.getResourceManager());
        }
        
        if (parOptions == GameSettings.Options.CUSTOM_SKY) {
            this.ofCustomSky = !this.ofCustomSky;
            CustomSky.update();
        }
        
        if (parOptions == GameSettings.Options.DYNAMIC_LIGHTS) {
            this.ofDynamicLights = nextValue(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
            DynamicLights.removeLights(this.mc.renderGlobal);
        }
        
        if (parOptions == GameSettings.Options.TOGGLE_SPRINT) {
			this.toggleSprintEnabled = false;
			this.toggleSprint = !this.toggleSprint;
		}
        
        if(parOptions == GameSettings.Options.CHUNK_BORDERS) {
        	this.chunkBorders = !this.chunkBorders;
        }
        
        if(parOptions == GameSettings.Options.VIS_GRAPH) {
        	if(!this.experimentalVisGraph) {
        		lastGuiScreen = Minecraft.getMinecraft().currentScreen;
        		this.mc.displayGuiScreen(new GuiYesNo(new GuiYesNoCallbackVisGraph(), "This is an experimental feature, use with caution!", "Are you sure you want to enable it?", 0));
        	} else {
        		this.experimentalVisGraph = false;
        		this.mc.renderGlobal.loadRenderers();
        	}
        }
        
        if(parOptions == GameSettings.Options.BUFFER_QUEUE) {
        	if(!this.experimentalBufferQueue) {
        		lastGuiScreen = Minecraft.getMinecraft().currentScreen;
        		this.mc.displayGuiScreen(new GuiYesNo(new GuiYesNoCallbackBufferQueue(), "This is an experimental feature, use with caution!", "Are you sure you want to enable it?", 0));
        	} else {
        		this.experimentalBufferQueue = false;
        		this.mc.renderGlobal.loadRenderers();
        	}
        }
        
        if (parOptions == GameSettings.Options.CUSTOM_ITEMS) {
            this.ofCustomItems = !this.ofCustomItems;
            this.mc.refreshResources();
        }
        
        if (parOptions == GameSettings.Options.SWAMP_COLORS) {
            this.ofSwampColors = !this.ofSwampColors;
            CustomColors.updateUseDefaultGrassFoliageColors();
            this.mc.renderGlobal.loadRenderers();
        }
        
        if (parOptions == GameSettings.Options.SMOOTH_BIOMES) {
            this.ofSmoothBiomes = !this.ofSmoothBiomes;
            CustomColors.updateUseDefaultGrassFoliageColors();
            this.mc.renderGlobal.loadRenderers();
        }
        
        if (parOptions == GameSettings.Options.CUSTOM_COLORS) {
            this.ofCustomColors = !this.ofCustomColors;
            CustomColors.update();
            this.mc.renderGlobal.loadRenderers();
        }

		this.saveOptions();
	}

	public float getOptionFloatValue(GameSettings.Options parOptions) {
		return parOptions == GameSettings.Options.FOV ? this.fovSetting
				: (parOptions == GameSettings.Options.GAMMA ? this.gammaSetting
				: (parOptions == GameSettings.Options.SATURATION ? this.saturation
				: (parOptions == GameSettings.Options.SENSITIVITY ? this.mouseSensitivity
				: (parOptions == GameSettings.Options.CHAT_OPACITY ? this.chatOpacity
				: (parOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED ? this.chatHeightFocused
				: (parOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED ? this.chatHeightUnfocused
				: (parOptions == GameSettings.Options.CHAT_SCALE ? this.chatScale
				: (parOptions == GameSettings.Options.CHAT_WIDTH ? this.chatWidth
				: (parOptions == GameSettings.Options.FRAMERATE_LIMIT ? (float) this.limitFramerate
				: (parOptions == GameSettings.Options.MIPMAP_LEVELS ? (float) this.mipmapLevels
				: (parOptions == GameSettings.Options.RENDER_DISTANCE ? (float) this.renderDistanceChunks
				: (parOptions == GameSettings.Options.AO_LEVEL ? this.ofAoLevel
				: (parOptions == GameSettings.Options.CLOUD_HEIGHT ? this.ofCloudsHeight
				: (parOptions == GameSettings.Options.MIPMAP_TYPE ? (float)this.ofMipmapType
				: (parOptions == GameSettings.Options.AA_LEVEL ? (float)this.ofAaLevel
				: (parOptions == GameSettings.Options.AF_LEVEL ? (float)this.ofAfLevel
				: 0.0F))))))))))))))));
	}

	public boolean getOptionOrdinalValue(GameSettings.Options parOptions) {
		switch (parOptions) {
		case INVERT_MOUSE:
			return this.invertMouse;
		case VIEW_BOBBING:
			return this.viewBobbing;
		case ANAGLYPH:
			return this.anaglyph;
		case FBO_ENABLE:
			return this.fboEnable;
		case CHAT_COLOR:
			return this.chatColours;
		case CHAT_LINKS:
			return this.chatLinks;
		case CHAT_LINKS_PROMPT:
			return this.chatLinksPrompt;
		case SNOOPER_ENABLED:
			return this.snooperEnabled;
		case TOUCHSCREEN:
			return this.touchscreen;
		case FORCE_UNICODE_FONT:
			return this.forceUnicodeFont;
		case BLOCK_ALTERNATIVES:
			return this.allowBlockAlternatives;
		case REDUCED_DEBUG_INFO:
			return this.reducedDebugInfo;
		case ENTITY_SHADOWS:
			return this.field_181151_V;
		case HUD_COORDS:
			return this.hudCoords;
		case HUD_FPS:
			return this.hudFps;
		case HUD_PLAYER:
			return this.hudPlayer;
		case HUD_STATS:
			return this.hudStats;
		case HUD_WORLD:
			return this.hudWorld;
		case HUD_24H:
			return this.hud24h;
		case CHUNK_FIX:
			return this.chunkFix;
		case FULLSCREEN:
			return this.mc.isFullScreen();
		case USE_VBO:
			return this.useVBOs;
		case FAST_MATH:
			return this.ofFastMath;
		case SMOOTH_FPS:
			return ofSmoothFps;
		case SMOOTH_WORLD:
			return ofSmoothWorld;
		case LAGOMETER:
			return this.ofLagometer;
		case WEATHER:
			return ofWeather;
		case PROFILER:
			return this.ofProfiler;
		case SKY:
			return this.ofSky;
		case STARS:
			return this.ofStars;
		case SUN_MOON:
			return this.ofSunMoon;
		case HELD_ITEM_TOOLTIPS:
			return this.heldItemTooltips;
		case DYNAMIC_FOV:
			return this.ofDynamicFov;
		case SHOW_CAPES:
			return this.ofShowCapes;
		case CLEAR_WATER:
			return this.ofClearWater;
		case RANDOM_MOBS:
			return this.ofRandomMobs;
		case BETTER_SNOW:
			return this.ofBetterSnow;
		case CUSTOM_FONTS:
			return this.ofCustomFonts;
		case CUSTOM_SKY:
			return this.ofCustomSky;
		case TOGGLE_SPRINT:
			return this.toggleSprint;
		case CHUNK_BORDERS:
			return this.chunkBorders;
		case VIS_GRAPH:
			return this.experimentalVisGraph;
		case BUFFER_QUEUE:
			return this.experimentalBufferQueue;
		case CUSTOM_ITEMS:
			return this.ofCustomItems;
		case SWAMP_COLORS:
			return this.ofSwampColors;
		case SMOOTH_BIOMES:
			return this.ofSmoothBiomes;
		case CUSTOM_COLORS:
			return this.ofCustomColors;
		default:
			return false;
		}
	}

	/**+
	 * Returns the translation of the given index in the given
	 * String array. If the index is smaller than 0 or greater
	 * than/equal to the length of the String array, it is changed
	 * to 0.
	 */
	private static String getTranslation(String[] parArrayOfString, int parInt1) {
		if (parInt1 < 0 || parInt1 >= parArrayOfString.length) {
			parInt1 = 0;
		}

		return I18n.format(parArrayOfString[parInt1], new Object[0]);
	}
	
	private String getAntialiasingString() {
		String s3 = "";

        if (this.ofAaLevel != Config.getAntialiasingLevel()) {
            s3 = " (" + "restart" + ")";
        }

        return this.ofAaLevel == 0 ? Lang.getOff() + s3 : this.ofAaLevel + s3;
	}

	/**+
	 * Gets a key binding.
	 */
	public String getKeyBinding(GameSettings.Options parOptions) {
		String s = I18n.format(parOptions.getEnumString(), new Object[0]) + ": ";
		if (parOptions.getEnumFloat()) {
			float f1 = this.getOptionFloatValue(parOptions);
			float f = parOptions.normalizeValue(f1);
			return parOptions == GameSettings.Options.SENSITIVITY ? (f == 0.0F ? s + I18n.format("options.sensitivity.min", new Object[0]) : (f == 1.0F ? s + I18n.format("options.sensitivity.max", new Object[0]) : s + (int) (f * 200.0F) + "%"))
					: (parOptions == GameSettings.Options.FOV ? (f1 == 70.0F ? s + I18n.format("options.fov.min", new Object[0]) : (f1 == 110.0F ? s + I18n.format("options.fov.max", new Object[0]) : s + (int) f1))
					: (parOptions == GameSettings.Options.FRAMERATE_LIMIT ? (f1 == parOptions.valueMax ? s + I18n.format("options.framerateLimit.max", new Object[0]) : s + (int) f1 + " fps")
					: (parOptions == GameSettings.Options.RENDER_CLOUDS ? (f1 == parOptions.valueMin ? s + I18n.format("options.cloudHeight.min", new Object[0]) : s + ((int) f1 + 128))
					: (parOptions == GameSettings.Options.GAMMA ? (f == 0.0F ? s + I18n.format("options.gamma.min", new Object[0]) : (f == 1.0F ? s + I18n.format("options.gamma.max", new Object[0]) : s + "+" + (int) (f * 100.0F) + "%"))
					: (parOptions == GameSettings.Options.SATURATION ? s + (int) (f * 400.0F) + "%"
					: (parOptions == GameSettings.Options.CHAT_OPACITY ? s + (int) (f * 90.0F + 10.0F) + "%"
					: (parOptions == GameSettings.Options.CHAT_SCALE ? s + (int) (f * 90.0F + 10.0F) + "%"
					: (parOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED ? s + GuiNewChat.calculateChatboxHeight(f) + "px"
					: (parOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED ? s + GuiNewChat.calculateChatboxHeight(f) + "px"
					: (parOptions == GameSettings.Options.CHAT_WIDTH ? s + GuiNewChat.calculateChatboxWidth(f) + "px"
					: (parOptions == GameSettings.Options.RENDER_DISTANCE ? s + (int) f1 + (f1 == 1.0F ? " chunk" : " chunks") 
					: (parOptions == GameSettings.Options.MIPMAP_LEVELS ? (f == 0.0F ? s + I18n.format("options.off", new Object[0]) : s + (int) (f * 100.0F) + "%")
					: (parOptions == GameSettings.Options.AO_LEVEL ? (f == 0.0F ? s + "OFF" : s + (int) (f * 100.0F) + "%")
					: (parOptions == GameSettings.Options.CLOUD_HEIGHT ? (f == 0.0F ? s + "OFF" : s + (int)(f * 100.0F) + "%")
					: (parOptions == GameSettings.Options.MIPMAP_TYPE ? (ofMipmapType == 0 ? s + "Nearest" : (ofMipmapType == 1 ? s + "Linear" : (ofMipmapType == 2 ? s + "Bilinear" : (ofMipmapType == 3 ? s + "Trilinear" : s + "Nearest"))))
					: (parOptions == GameSettings.Options.AF_LEVEL ? (this.ofAfLevel == 1 ? s + Lang.getOff() : s + this.ofAfLevel)
					: (parOptions == GameSettings.Options.AA_LEVEL ? s + this.getAntialiasingString()
					: "yee")))))))))))))))));
		} else if (parOptions.getEnumBoolean()) {
			boolean flag = this.getOptionOrdinalValue(parOptions);
			return flag ? s + I18n.format("options.on", new Object[0]) : s + I18n.format("options.off", new Object[0]);
		} else if (parOptions == GameSettings.Options.GUI_SCALE) {
			return s + getTranslation(GUISCALES, this.guiScale);
		} else if (parOptions == GameSettings.Options.CHAT_VISIBILITY) {
			return s + I18n.format(this.chatVisibility.getResourceKey(), new Object[0]);
		} else if (parOptions == GameSettings.Options.PARTICLES) {
			return s + getTranslation(PARTICLES, this.particleSetting);
		} else if (parOptions == GameSettings.Options.AMBIENT_OCCLUSION) {
			return s + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
		} else if (parOptions == GameSettings.Options.RENDER_CLOUDS) {
			return s + getTranslation(field_181149_aW, this.clouds);
		} else if (parOptions == GameSettings.Options.GRAPHICS) {
			if (this.fancyGraphics) {
				return s + I18n.format("options.graphics.fancy", new Object[0]);
			} else {
				return s + I18n.format("options.graphics.fast", new Object[0]);
			}
		} else if (parOptions == GameSettings.Options.FXAA) {
			if (this.fxaa == 0) {
				return s + I18n.format("options.fxaa.auto");
			} else if (this.fxaa == 1) {
				return s + I18n.format("options.on");
			} else {
				return s + I18n.format("options.off");
			}
		} else if (parOptions == GameSettings.Options.FOG_FANCY) {
            switch (this.ofFogType) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                case 3:
                    return s + Lang.getOff();

                default:
                    return s + Lang.getOff();
            }
        } else if (parOptions == GameSettings.Options.FOG_START) {
            return s + this.ofFogStart;
        } else if (parOptions == GameSettings.Options.FAST_MATH) {
            return this.ofFastMath ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.SMOOTH_FPS) {
            return this.ofSmoothFps ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.SMOOTH_WORLD) {
            return this.ofSmoothWorld ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.CHUNK_UPDATES) {
            return s + this.ofChunkUpdates;
        } else if (parOptions == GameSettings.Options.LAGOMETER) {
            return this.ofLagometer ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.WEATHER) {
            return this.ofWeather ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.TIME) {
            return this.ofTime == 1 ? s + "Day Only" : (this.ofTime == 2 ? s + "Night Only" : s + Lang.getDefault());
        } else if (parOptions == GameSettings.Options.PROFILER) {
            return this.ofProfiler ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.AUTOSAVE_TICKS) {
            return this.ofAutoSaveTicks <= 40 ? s + Lang.getDefault() + " (2s)" : (this.ofAutoSaveTicks <= 400 ? s + "20s" : (this.ofAutoSaveTicks <= 4000 ? s + "3min" : s + "30min"));
        } else if (parOptions == GameSettings.Options.ANIMATED_WATER) {
            switch (this.ofAnimatedWater) {
                case 1:
                    return s + "Dynamic";

                case 2:
                    return s + Lang.getOff();

                default:
                    return s + Lang.getOn();
            }
        } else if (parOptions == GameSettings.Options.ANIMATED_LAVA) {
            switch (this.ofAnimatedLava) {
                case 1:
                    return s + "Dynamic";

                case 2:
                    return s + Lang.getOff();

                default:
                    return s + Lang.getOn();
            }
        } else if (parOptions == GameSettings.Options.ANIMATED_FIRE) {
            return this.ofAnimatedFire ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_PORTAL) {
            return this.ofAnimatedPortal ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_REDSTONE) {
            return this.ofAnimatedRedstone ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_EXPLOSION) {
            return this.ofAnimatedExplosion ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_FLAME) {
            return this.ofAnimatedFlame ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_SMOKE) {
            return this.ofAnimatedSmoke ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.VOID_PARTICLES) {
            return this.ofVoidParticles ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.WATER_PARTICLES) {
            return this.ofWaterParticles ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.PORTAL_PARTICLES) {
            return this.ofPortalParticles ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.POTION_PARTICLES) {
            return this.ofPotionParticles ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.FIREWORK_PARTICLES) {
            return this.ofFireworkParticles ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.DRIPPING_WATER_LAVA) {
            return this.ofDrippingWaterLava ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_TERRAIN) {
            return this.ofAnimatedTerrain ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.ANIMATED_TEXTURES) {
            return this.ofAnimatedTextures ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.RAIN_SPLASH) {
            return this.ofRainSplash ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.TREES) {
            switch (this.ofTrees) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                case 3:
                default:
                    return s + Lang.getDefault();

                case 4:
                	this.ofTrees = 2;
                	this.mc.renderGlobal.loadRenderers();
                	return this.getKeyBinding(GameSettings.Options.TREES);
            }
        } else if (parOptions == GameSettings.Options.RAIN) {
            switch (this.ofRain) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                case 3:
                    return s + Lang.getOff();

                default:
                    return s + Lang.getDefault();
            }
        } else if (parOptions == GameSettings.Options.SKY) {
            return this.ofSky ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.STARS) {
            return this.ofStars ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.SUN_MOON) {
            return this.ofSunMoon ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
            return this.heldItemTooltips ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.DYNAMIC_FOV) {
            return this.ofDynamicFov ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.VIGNETTE) {
            switch (this.ofVignette) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                default:
                    return s + Lang.getDefault();
            }
        } else if (parOptions == GameSettings.Options.TRANSLUCENT_BLOCKS) {
            return this.ofTranslucentBlocks == 1 ? s + Lang.getFast() : (this.ofTranslucentBlocks == 2 ? s + Lang.getFancy() : s + Lang.getDefault());
        } else if (parOptions == GameSettings.Options.DROPPED_ITEMS) {
            switch (this.ofDroppedItems) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                default:
                    return s + Lang.getDefault();
            }
        } else if (parOptions == GameSettings.Options.SHOW_CAPES) {
            return this.ofShowCapes ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.CLEAR_WATER) {
            return this.ofClearWater ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.RANDOM_MOBS) {
            return this.ofRandomMobs ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.BETTER_GRASS) {
            switch (this.ofBetterGrass) {
                case 1:
                    return s + Lang.getFast();

                case 2:
                    return s + Lang.getFancy();

                default:
                    return s + Lang.getOff();
            }
        } else if (parOptions == GameSettings.Options.BETTER_SNOW) {
            return this.ofBetterSnow ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.CUSTOM_FONTS) {
            return this.ofCustomFonts ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.CUSTOM_SKY) {
            return this.ofCustomSky ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.DYNAMIC_LIGHTS) {
            int k = indexOf(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
            return s + getTranslation(KEYS_DYNAMIC_LIGHTS, k);
        } else if (parOptions == GameSettings.Options.TOGGLE_SPRINT) {
			return this.toggleSprint ? s + "Toggle" : s + "Hold";
		} else if(parOptions == GameSettings.Options.CHUNK_BORDERS) {
			return this.chunkBorders ? s + Lang.getOn() : s + Lang.getOff();
		} else if(parOptions == GameSettings.Options.VIS_GRAPH) {
			return this.experimentalVisGraph ? s + Lang.getOn() : s + Lang.getOff();
		} else if(parOptions == GameSettings.Options.BUFFER_QUEUE) {
			return this.experimentalBufferQueue ? s + Lang.getOn() : s + Lang.getOff();
		} else if (parOptions == GameSettings.Options.CUSTOM_ITEMS) {
            return this.ofCustomItems ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.SWAMP_COLORS) {
            return this.ofSwampColors ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.SMOOTH_BIOMES) {
            return this.ofSmoothBiomes ? s + Lang.getOn() : s + Lang.getOff();
        } else if (parOptions == GameSettings.Options.CUSTOM_COLORS) {
            return this.ofCustomColors ? s + Lang.getOn() : s + Lang.getOff();
        } else {
			return s;
		}
	}

	/**+
	 * Loads the options from the options file. It appears that this
	 * has replaced the previous 'loadOptions'
	 */
	public void loadOptions() {
		byte[] options = EagRuntime.getStorage("g");
		if (options == null) {
			return;
		}
		loadOptions(options);
	}

	/**+
	 * Loads the options from the options file. It appears that this
	 * has replaced the previous 'loadOptions'
	 */
	public void loadOptions(byte[] data) {
		try {
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(EaglerZLIB.newGZIPInputStream(new EaglerInputStream(data))));
			String s = "";
			this.mapSoundLevels.clear();

			while ((s = bufferedreader.readLine()) != null) {
				try {
					String[] astring = s.split(":");
					if (astring[0].equals("mouseSensitivity")) {
						this.mouseSensitivity = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("fov")) {
						this.fovSetting = this.parseFloat(astring[1]) * 40.0F + 70.0F;
					}

					if (astring[0].equals("gamma")) {
						this.gammaSetting = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("saturation")) {
						this.saturation = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("invertYMouse")) {
						this.invertMouse = astring[1].equals("true");
					}

					if (astring[0].equals("renderDistance")) {
						this.renderDistanceChunks = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("guiScale")) {
						this.guiScale = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("particles")) {
						this.particleSetting = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("bobView")) {
						this.viewBobbing = astring[1].equals("true");
					}

					if (astring[0].equals("anaglyph3d")) {
						this.anaglyph = astring[1].equals("true");
					}

					if (astring[0].equals("maxFps")) {
						this.limitFramerate = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("fboEnable")) {
						this.fboEnable = astring[1].equals("true");
					}

					if (astring[0].equals("difficulty")) {
						this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(astring[1]));
					}

					if (astring[0].equals("hasCreatedDemoWorld")) {
						this.hasCreatedDemoWorld = astring[1].equals("true");
					}

					if (astring[0].equals("relayTimeout")) {
						this.relayTimeout = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("hideJoinCode")) {
						this.hideJoinCode = astring[1].equals("true");
					}

					if (astring[0].equals("fancyGraphics")) {
						this.fancyGraphics = astring[1].equals("true");
					}

					if (astring[0].equals("ao")) {
						if (astring[1].equals("true")) {
							this.ambientOcclusion = 2;
						} else if (astring[1].equals("false")) {
							this.ambientOcclusion = 0;
						} else {
							this.ambientOcclusion = Integer.parseInt(astring[1]);
						}
					}

					if (astring[0].equals("renderClouds")) {
						if (astring[1].equals("true")) {
							this.clouds = 2;
						} else if (astring[1].equals("false")) {
							this.clouds = 0;
						} else if (astring[1].equals("fast")) {
							this.clouds = 1;
						}
					}

					if (astring[0].equals("resourcePacks")) {
						this.resourcePacks.clear();
						for (Object o : (new JSONArray(s.substring(s.indexOf(58) + 1))).toList()) {
							if (o instanceof String) {
								this.resourcePacks.add((String) o);
							}
						}
						if (this.resourcePacks == null) {
							this.resourcePacks = Lists.newArrayList();
						}
					}

					if (astring[0].equals("incompatibleResourcePacks")) {
						this.field_183018_l.clear();
						for (Object o : (new JSONArray(s.substring(s.indexOf(58) + 1))).toList()) {
							if (o instanceof String) {
								this.field_183018_l.add((String) o);
							}
						}
						if (this.field_183018_l == null) {
							this.field_183018_l = Lists.newArrayList();
						}
					}

					if (astring[0].equals("lastServer") && astring.length >= 2) {
						this.lastServer = s.substring(s.indexOf(58) + 1);
					}

					if (astring[0].equals("lang") && astring.length >= 2) {
						this.language = astring[1];
					}

					if (astring[0].equals("chatVisibility")) {
						this.chatVisibility = EntityPlayer.EnumChatVisibility
								.getEnumChatVisibility(Integer.parseInt(astring[1]));
					}

					if (astring[0].equals("chatColors")) {
						this.chatColours = astring[1].equals("true");
					}

					if (astring[0].equals("chatLinks")) {
						this.chatLinks = astring[1].equals("true");
					}

					if (astring[0].equals("chatLinksPrompt")) {
						this.chatLinksPrompt = astring[1].equals("true");
					}

					if (astring[0].equals("chatOpacity")) {
						this.chatOpacity = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("snooperEnabled")) {
						this.snooperEnabled = astring[1].equals("true");
					}

					if (astring[0].equals("enableVsync")) {
						this.enableVsync = astring[1].equals("true");
					}

					if (astring[0].equals("hideServerAddress")) {
						this.hideServerAddress = astring[1].equals("true");
					}

					if (astring[0].equals("advancedItemTooltips")) {
						this.advancedItemTooltips = astring[1].equals("true");
					}

					if (astring[0].equals("pauseOnLostFocus")) {
						this.pauseOnLostFocus = astring[1].equals("true");
					}

					if (astring[0].equals("touchscreen")) {
						this.touchscreen = astring[1].equals("true");
					}

					if (astring[0].equals("overrideHeight")) {
						this.overrideHeight = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("overrideWidth")) {
						this.overrideWidth = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("heldItemTooltips")) {
						this.heldItemTooltips = astring[1].equals("true");
					}

					if (astring[0].equals("chatHeightFocused")) {
						this.chatHeightFocused = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("chatHeightUnfocused")) {
						this.chatHeightUnfocused = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("chatScale")) {
						this.chatScale = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("chatWidth")) {
						this.chatWidth = this.parseFloat(astring[1]);
					}

					if (astring[0].equals("showInventoryAchievementHint")) {
						this.showInventoryAchievementHint = astring[1].equals("true");
					}

					if (astring[0].equals("mipmapLevels")) {
						this.mipmapLevels = Integer.parseInt(astring[1]);
					}

					if (astring[0].equals("forceUnicodeFont")) {
						this.forceUnicodeFont = astring[1].equals("true");
					}

					if (astring[0].equals("allowBlockAlternatives")) {
						this.allowBlockAlternatives = astring[1].equals("true");
					}

					if (astring[0].equals("reducedDebugInfo")) {
						this.reducedDebugInfo = astring[1].equals("true");
					}

					if (astring[0].equals("useNativeTransport")) {
						this.field_181150_U = astring[1].equals("true");
					}

					if (astring[0].equals("entityShadows")) {
						this.field_181151_V = astring[1].equals("true");
					}

					if (astring[0].equals("hudFps")) {
						this.hudFps = astring[1].equals("true");
					}

					if (astring[0].equals("hudWorld")) {
						this.hudWorld = astring[1].equals("true");
					}

					if (astring[0].equals("hudCoords")) {
						this.hudCoords = astring[1].equals("true");
					}

					if (astring[0].equals("hudPlayer")) {
						this.hudPlayer = astring[1].equals("true");
					}

					if (astring[0].equals("hudStats")) {
						this.hudStats = astring[1].equals("true");
					}

					if (astring[0].equals("hud24h")) {
						this.hud24h = astring[1].equals("true");
					}

					if (astring[0].equals("chunkFix")) {
						this.chunkFix = astring[1].equals("true");
					}

					if (astring[0].equals("fxaa")) {
						this.fxaa = (astring[1].equals("true") || astring[1].equals("false")) ? 0
								: Integer.parseInt(astring[1]);
					}
					
					if (astring[0].equals("ofAoLevel") && astring.length >= 2) {
                        this.ofAoLevel = Float.valueOf(astring[1]).floatValue();
                        this.ofAoLevel = Config.limit(this.ofAoLevel, 0.0F, 1.0F);
                    }

					for (KeyBinding keybinding : this.keyBindings) {
						if (astring[0].equals("key_" + keybinding.getKeyDescription())) {
							keybinding.setKeyCode(Integer.parseInt(astring[1]));
						}
					}

					if (astring[0].equals("shaders")) {
						this.shaders = astring[1].equals("true");
					}

					if (astring[0].equals("enableUpdateSvc")) {
						this.enableUpdateSvc = astring[1].equals("true");
					}
					
					if(astring[0].equals("vbos")) {
						this.useVBOs = astring[1].equals("true");
					}
					
					if (astring[0].equals("ofFogType") && astring.length >= 2) {
                        this.ofFogType = Integer.valueOf(astring[1]).intValue();
                        this.ofFogType = Config.limit(this.ofFogType, 1, 3);
                    }

                    if (astring[0].equals("ofFogStart") && astring.length >= 2) {
                        this.ofFogStart = Float.valueOf(astring[1]).floatValue();

                        if (this.ofFogStart < 0.2F) {
                            this.ofFogStart = 0.2F;
                        }

                        if (this.ofFogStart > 0.81F) {
                            this.ofFogStart = 0.8F;
                        }
                    }
                    
                    if (astring[0].equals("ofFastMath") && astring.length >= 2) {
                        this.ofFastMath = Boolean.valueOf(astring[1]).booleanValue();
                        MathHelper.fastMath = this.ofFastMath;
                    }
                    
                    if (astring[0].equals("ofSmoothFps") && astring.length >= 2) {
                        this.ofSmoothFps = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofSmoothWorld") && astring.length >= 2) {
                        ofSmoothWorld = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofChunkUpdates") && astring.length >= 2) {
                        this.ofChunkUpdates = Integer.valueOf(astring[1]).intValue();
                        this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
                    }
                    
                    if (astring[0].equals("ofLagometer") && astring.length >= 2) {
                        this.ofLagometer = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofWeather") && astring.length >= 2) {
                        ofWeather = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofTime") && astring.length >= 2) {
                        ofTime = Integer.valueOf(astring[1]).intValue();
                        ofTime = Config.limit(ofTime, 0, 2);
                    }
                    
                    if (astring[0].equals("ofProfiler") && astring.length >= 2) {
                        this.ofProfiler = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofAutoSaveTicks") && astring.length >= 2) {
                        this.ofAutoSaveTicks = Integer.valueOf(astring[1]).intValue();
                        this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
                    }
                    
                    if (astring[0].equals("ofAnimatedWater") && astring.length >= 2) {
                        this.ofAnimatedWater = Integer.valueOf(astring[1]).intValue();
                        this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
                    }

                    if (astring[0].equals("ofAnimatedLava") && astring.length >= 2) {
                        this.ofAnimatedLava = Integer.valueOf(astring[1]).intValue();
                        this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
                    }

                    if (astring[0].equals("ofAnimatedFire") && astring.length >= 2) {
                        this.ofAnimatedFire = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedPortal") && astring.length >= 2) {
                        this.ofAnimatedPortal = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedRedstone") && astring.length >= 2) {
                        this.ofAnimatedRedstone = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedExplosion") && astring.length >= 2) {
                        this.ofAnimatedExplosion = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedFlame") && astring.length >= 2) {
                        this.ofAnimatedFlame = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedSmoke") && astring.length >= 2) {
                        this.ofAnimatedSmoke = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofVoidParticles") && astring.length >= 2) {
                        this.ofVoidParticles = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofWaterParticles") && astring.length >= 2) {
                        this.ofWaterParticles = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofPortalParticles") && astring.length >= 2) {
                        this.ofPortalParticles = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofPotionParticles") && astring.length >= 2) {
                        this.ofPotionParticles = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofFireworkParticles") && astring.length >= 2) {
                        this.ofFireworkParticles = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofDrippingWaterLava") && astring.length >= 2) {
                        this.ofDrippingWaterLava = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedTerrain") && astring.length >= 2) {
                        this.ofAnimatedTerrain = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofAnimatedTextures") && astring.length >= 2) {
                        this.ofAnimatedTextures = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofRainSplash") && astring.length >= 2) {
                        this.ofRainSplash = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofCloudsHeight") && astring.length >= 2) {
                        this.ofCloudsHeight = Float.valueOf(astring[1]).floatValue();
                        this.ofCloudsHeight = Config.limit(this.ofCloudsHeight, 0.0F, 1.0F);
                    }
                    
                    if (astring[0].equals("ofTrees") && astring.length >= 2) {
                        this.ofTrees = Integer.valueOf(astring[1]).intValue();
                        this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
                        
                        if(this.ofTrees == 4) {
                        	this.ofTrees = 2;
                        }
                    }
                    
                    if (astring[0].equals("ofRain") && astring.length >= 2) {
                        this.ofRain = Integer.valueOf(astring[1]).intValue();
                        this.ofRain = Config.limit(this.ofRain, 0, 3);
                    }
                    
                    if (astring[0].equals("ofSky") && astring.length >= 2) {
                        this.ofSky = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofStars") && astring.length >= 2) {
                        this.ofStars = Boolean.valueOf(astring[1]).booleanValue();
                    }

                    if (astring[0].equals("ofSunMoon") && astring.length >= 2) {
                        this.ofSunMoon = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofDynamicFov") && astring.length >= 2) {
                        this.ofDynamicFov = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofVignette") && astring.length >= 2) {
                        this.ofVignette = Integer.valueOf(astring[1]).intValue();
                        this.ofVignette = Config.limit(this.ofVignette, 0, 2);
                    }
                    
                    if (astring[0].equals("ofTranslucentBlocks") && astring.length >= 2) {
                        this.ofTranslucentBlocks = Integer.valueOf(astring[1]).intValue();
                        this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 0, 2);
                    }
                    
                    if (astring[0].equals("ofDroppedItems") && astring.length >= 2) {
                        this.ofDroppedItems = Integer.valueOf(astring[1]).intValue();
                        this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
                    }
                    
                    if (astring[0].equals("ofShowCapes") && astring.length >= 2) {
                        this.ofShowCapes = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofMipmapType") && astring.length >= 2) {
                        this.ofMipmapType = Integer.valueOf(astring[1]).intValue();
                        this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
                    }
                    
                    if (astring[0].equals("ofAaLevel") && astring.length >= 2) {
                        this.ofAaLevel = Integer.valueOf(astring[1]).intValue();
                        this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
                    }

                    if (astring[0].equals("ofAfLevel") && astring.length >= 2) {
                        this.ofAfLevel = Integer.valueOf(astring[1]).intValue();
                        this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
                    }
                    
                    if (astring[0].equals("ofClearWater") && astring.length >= 2) {
                        this.ofClearWater = Boolean.valueOf(astring[1]).booleanValue();
                        this.updateWaterOpacity();
                    }
                    
                    if (astring[0].equals("ofRandomMobs") && astring.length >= 2) {
                        this.ofRandomMobs = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofBetterGrass") && astring.length >= 2) {
                        this.ofBetterGrass = Integer.valueOf(astring[1]).intValue();
                        this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
                    }
                    
                    if (astring[0].equals("ofBetterSnow") && astring.length >= 2) {
                        this.ofBetterSnow = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofCustomFonts") && astring.length >= 2) {
                        this.ofCustomFonts = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofCustomSky") && astring.length >= 2) {
                        this.ofCustomSky = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofDynamicLights") && astring.length >= 2) {
                        this.ofDynamicLights = Integer.valueOf(astring[1]).intValue();
                        this.ofDynamicLights = limit(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
                    }
                    
                    if (astring[0].equals("toggleSprint") && astring.length >= 2) {
						this.toggleSprint = Boolean.valueOf(astring[1]).booleanValue();
					}
                    
                    if (astring[0].equals("chunkBorders") && astring.length >= 2) {
						this.chunkBorders = Boolean.valueOf(astring[1]).booleanValue();
					}
                    
                    if(astring[0].equals("visGraph") && astring.length >= 2) {
                    	this.experimentalVisGraph = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if(astring[0].equals("bufferQueue") && astring.length >= 2) {
                    	this.experimentalBufferQueue = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofCustomItems") && astring.length >= 2) {
                        this.ofCustomItems = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofSwampColors") && astring.length >= 2) {
                        this.ofSwampColors = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofSmoothBiomes") && astring.length >= 2) {
                        this.ofSmoothBiomes = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    
                    if (astring[0].equals("ofCustomColors") && astring.length >= 2) {
                        this.ofCustomColors = Boolean.valueOf(astring[1]).booleanValue();
                    }

					Keyboard.setFunctionKeyModifier(keyBindFunction.getKeyCode());

					for (SoundCategory soundcategory : SoundCategory._VALUES) {
						if (astring[0].equals("soundCategory_" + soundcategory.getCategoryName())) {
							this.mapSoundLevels.put(soundcategory, Float.valueOf(this.parseFloat(astring[1])));
						}
					}

					for (EnumPlayerModelParts enumplayermodelparts : EnumPlayerModelParts._VALUES) {
						if (astring[0].equals("modelPart_" + enumplayermodelparts.getPartName())) {
							this.setModelPartEnabled(enumplayermodelparts, astring[1].equals("true"));
						}
					}

					deferredShaderConf.readOption(astring[0], astring[1]);
				} catch (Exception var8) {
					logger.warn("Skipping bad option: " + s);
				}
			}

			KeyBinding.resetKeyBindingArrayAndHash();
		} catch (Exception exception) {
			logger.error("Failed to load options", exception);
		}

	}

	/**+
	 * Parses a string into a float.
	 */
	private float parseFloat(String parString1) {
		return parString1.equals("true") ? 1.0F : (parString1.equals("false") ? 0.0F : Float.parseFloat(parString1));
	}

	/**+
	 * Saves the options to the options file.
	 */
	public void saveOptions() {
		byte[] data = writeOptions();
		if (data != null) {
			EagRuntime.setStorage("g", data);
		}
		RelayManager.relayManager.save();
		this.sendSettingsToServer();
	}

	public byte[] writeOptions() {
		try {
			EaglerOutputStream bao = new EaglerOutputStream();
			PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(EaglerZLIB.newGZIPOutputStream(bao)));
			printwriter.println("invertYMouse:" + this.invertMouse);
			printwriter.println("mouseSensitivity:" + this.mouseSensitivity);
			printwriter.println("fov:" + (this.fovSetting - 70.0F) / 40.0F);
			printwriter.println("gamma:" + this.gammaSetting);
			printwriter.println("saturation:" + this.saturation);
			printwriter.println("renderDistance:" + this.renderDistanceChunks);
			printwriter.println("guiScale:" + this.guiScale);
			printwriter.println("particles:" + this.particleSetting);
			printwriter.println("bobView:" + this.viewBobbing);
			printwriter.println("anaglyph3d:" + this.anaglyph);
			printwriter.println("maxFps:" + this.limitFramerate);
			printwriter.println("fboEnable:" + this.fboEnable);
			printwriter.println("difficulty:" + this.difficulty.getDifficultyId());
			printwriter.println("hasCreatedDemoWorld:" + this.hasCreatedDemoWorld);
			printwriter.println("relayTimeout:" + this.relayTimeout);
			printwriter.println("hideJoinCode:" + this.hideJoinCode);
			printwriter.println("fancyGraphics:" + this.fancyGraphics);
			printwriter.println("ao:" + this.ambientOcclusion);
			switch (this.clouds) {
			case 0:
				printwriter.println("renderClouds:false");
				break;
			case 1:
				printwriter.println("renderClouds:fast");
				break;
			case 2:
				printwriter.println("renderClouds:true");
			}

			printwriter.println("resourcePacks:" + toJSONArray(this.resourcePacks));
			printwriter.println("incompatibleResourcePacks:" + toJSONArray(this.field_183018_l));
			printwriter.println("lastServer:" + this.lastServer);
			printwriter.println("lang:" + this.language);
			printwriter.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
			printwriter.println("chatColors:" + this.chatColours);
			printwriter.println("chatLinks:" + this.chatLinks);
			printwriter.println("chatLinksPrompt:" + this.chatLinksPrompt);
			printwriter.println("chatOpacity:" + this.chatOpacity);
			printwriter.println("snooperEnabled:" + this.snooperEnabled);
			printwriter.println("enableVsync:" + this.enableVsync);
			printwriter.println("hideServerAddress:" + this.hideServerAddress);
			printwriter.println("advancedItemTooltips:" + this.advancedItemTooltips);
			printwriter.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
			printwriter.println("touchscreen:" + this.touchscreen);
			printwriter.println("overrideWidth:" + this.overrideWidth);
			printwriter.println("overrideHeight:" + this.overrideHeight);
			printwriter.println("heldItemTooltips:" + this.heldItemTooltips);
			printwriter.println("chatHeightFocused:" + this.chatHeightFocused);
			printwriter.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
			printwriter.println("chatScale:" + this.chatScale);
			printwriter.println("chatWidth:" + this.chatWidth);
			printwriter.println("showInventoryAchievementHint:" + this.showInventoryAchievementHint);
			printwriter.println("mipmapLevels:" + this.mipmapLevels);
			printwriter.println("forceUnicodeFont:" + this.forceUnicodeFont);
			printwriter.println("allowBlockAlternatives:" + this.allowBlockAlternatives);
			printwriter.println("reducedDebugInfo:" + this.reducedDebugInfo);
			printwriter.println("useNativeTransport:" + this.field_181150_U);
			printwriter.println("entityShadows:" + this.field_181151_V);
			printwriter.println("hudFps:" + this.hudFps);
			printwriter.println("hudWorld:" + this.hudWorld);
			printwriter.println("hudCoords:" + this.hudCoords);
			printwriter.println("hudPlayer:" + this.hudPlayer);
			printwriter.println("hudStats:" + this.hudStats);
			printwriter.println("hud24h:" + this.hud24h);
			printwriter.println("chunkFix:" + this.chunkFix);
			printwriter.println("fxaa:" + this.fxaa);
			printwriter.println("shaders:" + this.shaders);
			printwriter.println("enableUpdateSvc:" + this.enableUpdateSvc);
			printwriter.println("ofAoLevel:" + this.ofAoLevel);
			printwriter.println("vbos:" + this.useVBOs);
			printwriter.println("ofFogType:" + this.ofFogType);
            printwriter.println("ofFogStart:" + this.ofFogStart);
            printwriter.println("ofFastMath:" + this.ofFastMath);
            printwriter.println("ofSmoothFps:" + this.ofSmoothFps);
            printwriter.println("ofSmoothWorld:" + this.ofSmoothWorld);
            printwriter.println("ofChunkUpdates:" + this.ofChunkUpdates);
            printwriter.println("ofLagometer:" + this.ofLagometer);
            printwriter.println("ofWeather:" + this.ofWeather);
            printwriter.println("ofTime:" + this.ofTime);
            printwriter.println("ofProfiler:" + this.ofProfiler);
            printwriter.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
            printwriter.println("ofAnimatedWater:" + this.ofAnimatedWater);
            printwriter.println("ofAnimatedLava:" + this.ofAnimatedLava);
            printwriter.println("ofAnimatedFire:" + this.ofAnimatedFire);
            printwriter.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
            printwriter.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
            printwriter.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
            printwriter.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
            printwriter.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
            printwriter.println("ofVoidParticles:" + this.ofVoidParticles);
            printwriter.println("ofWaterParticles:" + this.ofWaterParticles);
            printwriter.println("ofPortalParticles:" + this.ofPortalParticles);
            printwriter.println("ofPotionParticles:" + this.ofPotionParticles);
            printwriter.println("ofFireworkParticles:" + this.ofFireworkParticles);
            printwriter.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
            printwriter.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
            printwriter.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
            printwriter.println("ofRainSplash:" + this.ofRainSplash);
            printwriter.println("ofCloudsHeight:" + this.ofCloudsHeight);
            printwriter.println("ofTrees:" + this.ofTrees);
            printwriter.println("ofRain:" + this.ofRain);
            printwriter.println("ofSky:" + this.ofSky);
            printwriter.println("ofStars:" + this.ofStars);
            printwriter.println("ofSunMoon:" + this.ofSunMoon);
            printwriter.println("ofDynamicFov:" + this.ofDynamicFov);
            printwriter.println("ofVignette:" + this.ofVignette);
            printwriter.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
            printwriter.println("ofDroppedItems:" + this.ofDroppedItems);
            printwriter.println("ofShowCapes:" + this.ofShowCapes);
            printwriter.println("ofMipmapType:" + this.ofMipmapType);
            printwriter.println("ofAaLevel:" + this.ofAaLevel);
            printwriter.println("ofAfLevel:" + this.ofAfLevel);
            printwriter.println("ofClearWater:" + this.ofClearWater);
            printwriter.println("ofRandomMobs:" + this.ofRandomMobs);
            printwriter.println("ofBetterGrass:" + this.ofBetterGrass);
            printwriter.println("ofBetterSnow:" + this.ofBetterSnow);
            printwriter.println("ofCustomFonts:" + this.ofCustomFonts);
            printwriter.println("ofCustomSky:" + this.ofCustomSky);
            printwriter.println("ofDynamicLights:" + this.ofDynamicLights);
            printwriter.println("toggleSprint:" + this.toggleSprint);
            printwriter.println("chunkBorders:" + this.chunkBorders);
            printwriter.println("visGraph:" + this.experimentalVisGraph);
            printwriter.println("bufferQueue:" + this.experimentalBufferQueue);
            printwriter.println("ofCustomItems:" + this.ofCustomItems);
            printwriter.println("ofSwampColors:" + this.ofSwampColors);
            printwriter.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
            printwriter.println("ofCustomColors:" + this.ofCustomColors);

			for (KeyBinding keybinding : this.keyBindings) {
				printwriter.println("key_" + keybinding.getKeyDescription() + ":" + keybinding.getKeyCode());
			}

			Keyboard.setFunctionKeyModifier(keyBindFunction.getKeyCode());

			for (SoundCategory soundcategory : SoundCategory._VALUES) {
				printwriter.println(
						"soundCategory_" + soundcategory.getCategoryName() + ":" + this.getSoundLevel(soundcategory));
			}

			for (EnumPlayerModelParts enumplayermodelparts : EnumPlayerModelParts._VALUES) {
				printwriter.println("modelPart_" + enumplayermodelparts.getPartName() + ":"
						+ this.setModelParts.contains(enumplayermodelparts));
			}

			deferredShaderConf.writeOptions(printwriter);

			printwriter.close();
			return bao.toByteArray();
		} catch (Exception exception) {
			logger.error("Failed to save options", exception);
			return null;
		}

	}

	public float getSoundLevel(SoundCategory parSoundCategory) {
		return this.mapSoundLevels.containsKey(parSoundCategory)
				? ((Float) this.mapSoundLevels.get(parSoundCategory)).floatValue()
				: (parSoundCategory == SoundCategory.VOICE ? 0.0F : 1.0F);
	}

	public void setSoundLevel(SoundCategory parSoundCategory, float parFloat1) {
		this.mc.getSoundHandler().setSoundLevel(parSoundCategory, parFloat1);
		this.mapSoundLevels.put(parSoundCategory, Float.valueOf(parFloat1));
	}

	/**+
	 * Send a client info packet with settings information to the
	 * server
	 */
	public void sendSettingsToServer() {
		if (this.mc.thePlayer != null) {
			int i = 0;

			for (EnumPlayerModelParts enumplayermodelparts : this.setModelParts) {
				i |= enumplayermodelparts.getPartMask();
			}

			this.mc.thePlayer.sendQueue.addToSendQueue(new C15PacketClientSettings(this.language,
					Math.max(this.renderDistanceChunks, 2), this.chatVisibility, this.chatColours, i));
		}

	}

	public Set<EnumPlayerModelParts> getModelParts() {
		return ImmutableSet.copyOf(this.setModelParts);
	}

	public void setModelPartEnabled(EnumPlayerModelParts parEnumPlayerModelParts, boolean parFlag) {
		if (parFlag) {
			this.setModelParts.add(parEnumPlayerModelParts);
		} else {
			this.setModelParts.remove(parEnumPlayerModelParts);
		}

		this.sendSettingsToServer();
	}

	public void switchModelPartEnabled(EnumPlayerModelParts parEnumPlayerModelParts) {
		if (!this.getModelParts().contains(parEnumPlayerModelParts)) {
			this.setModelParts.add(parEnumPlayerModelParts);
		} else {
			this.setModelParts.remove(parEnumPlayerModelParts);
		}

		this.sendSettingsToServer();
	}

	public int func_181147_e() {
		return this.renderDistanceChunks >= 4 ? this.clouds : 0;
	}

	public boolean func_181148_f() {
		return this.field_181150_U;
	}

	private String toJSONArray(List<String> e) {
		JSONArray arr = new JSONArray();
		for (String s : e) {
			arr.put(s);
		}
		return arr.toString();
	}

	public static enum Options {
		INVERT_MOUSE("options.invertMouse", false, true), 
		SENSITIVITY("options.sensitivity", true, false),
		FOV("options.fov", true, false, 30.0F, 110.0F, 1.0F), 
		GAMMA("options.gamma", true, false),
		SATURATION("options.saturation", true, false),
		RENDER_DISTANCE("options.renderDistance", true, false, 1.0F, 16.0F, 1.0F),
		VIEW_BOBBING("options.viewBobbing", false, true), 
		ANAGLYPH("options.anaglyph", false, true),
		FRAMERATE_LIMIT("options.framerateLimit", true, false, 10.0F, 260.0F, 10.0F),
		FBO_ENABLE("options.fboEnable", false, true), 
		RENDER_CLOUDS("options.renderClouds", false, false),
		GRAPHICS("options.graphics", false, false), 
		AMBIENT_OCCLUSION("options.ao", false, false),
		GUI_SCALE("options.guiScale", false, false), 
		PARTICLES("options.particles", false, false),
		CHAT_VISIBILITY("options.chat.visibility", false, false), 
		CHAT_COLOR("options.chat.color", false, true),
		CHAT_LINKS("options.chat.links", false, true), 
		CHAT_OPACITY("options.chat.opacity", true, false),
		CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true), 
		SNOOPER_ENABLED("options.snooper", false, true),
		TOUCHSCREEN("options.touchscreen", false, true), 
		CHAT_SCALE("options.chat.scale", true, false),
		CHAT_WIDTH("options.chat.width", true, false), 
		CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
		CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false),
		MIPMAP_LEVELS("options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
		FORCE_UNICODE_FONT("options.forceUnicodeFont", false, true),
		STREAM_BYTES_PER_PIXEL("options.stream.bytesPerPixel", true, false),
		STREAM_VOLUME_MIC("options.stream.micVolumne", true, false),
		STREAM_VOLUME_SYSTEM("options.stream.systemVolume", true, false),
		STREAM_KBPS("options.stream.kbps", true, false), 
		STREAM_FPS("options.stream.fps", true, false),
		STREAM_COMPRESSION("options.stream.compression", false, false),
		STREAM_SEND_METADATA("options.stream.sendMetadata", false, true),
		STREAM_CHAT_ENABLED("options.stream.chat.enabled", false, false),
		STREAM_CHAT_USER_FILTER("options.stream.chat.userFilter", false, false),
		STREAM_MIC_TOGGLE_BEHAVIOR("options.stream.micToggleBehavior", false, false),
		BLOCK_ALTERNATIVES("options.blockAlternatives", false, true),
		REDUCED_DEBUG_INFO("options.reducedDebugInfo", false, true),
		ENTITY_SHADOWS("options.entityShadows", false, true), 
		HUD_FPS("options.hud.fps", false, true),
		HUD_COORDS("options.hud.coords", false, true), 
		HUD_STATS("options.hud.stats", false, true),
		HUD_WORLD("options.hud.world", false, true), 
		HUD_PLAYER("options.hud.player", false, true),
		HUD_24H("options.hud.24h", false, true), 
		CHUNK_FIX("options.chunkFix", false, true),
		FXAA("options.fxaa", false, false),
		FULLSCREEN("options.fullscreen", false, true), 
		FAST_MATH("options.fastMath", false, false),
		AO_LEVEL("Smooth Lighting Level", true, false),
		USE_VBO("Use VBOs", false, true),
		FOG_FANCY("Fog", false, false),
        FOG_START("Fog Start", false, false),
        SMOOTH_FPS("Smooth FPS", false, false),
        SMOOTH_WORLD("Smooth World", false, false),
        CHUNK_UPDATES("Chunk Updates", false, false),
        LAGOMETER("Lagometer", false, false),
        WEATHER("Weather", false, false),
        TIME("Time", false, false),
        PROFILER("Profiler", false, false),
        AUTOSAVE_TICKS("Autosave", false, false),
        ANIMATED_WATER("Water Animated", false, false),
        ANIMATED_LAVA("Lava Animated", false, false),
        ANIMATED_FIRE("Fire Animated", false, false),
        ANIMATED_PORTAL("Portal Animated", false, false),
        ANIMATED_REDSTONE("Redstone Animated", false, false),
        ANIMATED_EXPLOSION("Explosion Animated", false, false),
        ANIMATED_FLAME("Flame Animated", false, false),
        ANIMATED_SMOKE("Smoke Animated", false, false),
        VOID_PARTICLES("Void Particles", false, false),
        WATER_PARTICLES("Water Particles", false, false),
        RAIN_SPLASH("Rain Splash", false, false),
        PORTAL_PARTICLES("Portal Particles", false, false),
        POTION_PARTICLES("Potion Particles", false, false),
        FIREWORK_PARTICLES("Firework Particles", false, false),
        DRIPPING_WATER_LAVA("Dripping Water/Lava", false, false),
        ANIMATED_TERRAIN("Terrain Animated", false, false),
        ANIMATED_TEXTURES("Textures Animated", false, false),
        CLOUD_HEIGHT("Cloud Height", true, false),
        TREES("Trees", false, false),
        RAIN("Rain", false, false),
        SKY("Sky", false, false),
        STARS("Stars", false, false),
        SUN_MOON("Sun & Moon", false, false),
        HELD_ITEM_TOOLTIPS("Held Item Tooltips", false, false),
        DYNAMIC_FOV("Dynamic FOV", false, false),
        VIGNETTE("Vignette", false, false),
        TRANSLUCENT_BLOCKS("Translucent Blocks", false, false),
        DROPPED_ITEMS("Dropped Items", false, false),
        SHOW_CAPES("Show Capes", false, false),
        MIPMAP_TYPE("Mipmap Type", true, false, 0.0F, 3.0F, 1.0F),
        AA_LEVEL("Antialiasing", true, false, 0.0F, 16.0F, 1.0F),
        AF_LEVEL("Anisotropic Filtering", true, false, 1.0F, 16.0F, 1.0F),
        CLEAR_WATER("Clear Water", false, false),
        RANDOM_MOBS("Random Mobs", false, false),
        BETTER_GRASS("Better Grass", false, false),
        BETTER_SNOW("Better Snow", false, false),
        CUSTOM_FONTS("Custom Fonts", false, false),
        CUSTOM_SKY("Custom Sky", false, false),
        DYNAMIC_LIGHTS("Dynamic Lights", false, false),
        TOGGLE_SPRINT("Sprint", false, false),
        CHUNK_BORDERS("Chunk Borders", false, false),
        VIS_GRAPH("Experimental VisGraph", false, false),
        BUFFER_QUEUE("Experimental Queuing", false, false),
        CUSTOM_ITEMS("Custom Items", false, false),
        SWAMP_COLORS("Swamp Colors", false, false),
        SMOOTH_BIOMES("Smooth Biomes", false, false),
        CUSTOM_COLORS("Custom Colors", false, false);

		private final boolean enumFloat;
		private final boolean enumBoolean;
		private final String enumString;
		private final float valueStep;
		private float valueMin;
		private float valueMax;

		public static GameSettings.Options getEnumOptions(int parInt1) {
			for (GameSettings.Options gamesettings$options : values()) {
				if (gamesettings$options.returnEnumOrdinal() == parInt1) {
					return gamesettings$options;
				}
			}

			return null;
		}

		private Options(String parString2, boolean parFlag, boolean parFlag2) {
			this(parString2, parFlag, parFlag2, 0.0F, 1.0F, 0.0F);
		}

		private Options(String parString2, boolean parFlag, boolean parFlag2, float parFloat1, float parFloat2,
				float parFloat3) {
			this.enumString = parString2;
			this.enumFloat = parFlag;
			this.enumBoolean = parFlag2;
			this.valueMin = parFloat1;
			this.valueMax = parFloat2;
			this.valueStep = parFloat3;
		}

		public boolean getEnumFloat() {
			return this.enumFloat;
		}

		public boolean getEnumBoolean() {
			return this.enumBoolean;
		}

		public int returnEnumOrdinal() {
			return this.ordinal();
		}

		public String getEnumString() {
			return this.enumString;
		}

		public float getValueMax() {
			return this.valueMax;
		}

		public void setValueMax(float parFloat1) {
			this.valueMax = parFloat1;
		}

		public float normalizeValue(float parFloat1) {
			return MathHelper.clamp_float(
					(this.snapToStepClamp(parFloat1) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
		}

		public float denormalizeValue(float parFloat1) {
			return this.snapToStepClamp(
					this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(parFloat1, 0.0F, 1.0F));
		}

		public float snapToStepClamp(float parFloat1) {
			parFloat1 = this.snapToStep(parFloat1);
			return MathHelper.clamp_float(parFloat1, this.valueMin, this.valueMax);
		}

		protected float snapToStep(float parFloat1) {
			if (this.valueStep > 0.0F) {
				parFloat1 = this.valueStep * (float) Math.round(parFloat1 / this.valueStep);
			}

			return parFloat1;
		}
	}
	
	private void updateWaterOpacity() {
        if (this.mc.isIntegratedServerRunning()) {
            Config.waterOpacityChanged = true;
        }

        ClearWater.updateWaterOpacity(this, this.mc.theWorld);
    }

	public void setAllAnimations(boolean p_setAllAnimations_1_) {
        int i = p_setAllAnimations_1_ ? 0 : 2;
        this.ofAnimatedWater = i;
        this.ofAnimatedLava = i;
        this.ofAnimatedFire = p_setAllAnimations_1_;
        this.ofAnimatedPortal = p_setAllAnimations_1_;
        this.ofAnimatedRedstone = p_setAllAnimations_1_;
        this.ofAnimatedExplosion = p_setAllAnimations_1_;
        this.ofAnimatedFlame = p_setAllAnimations_1_;
        this.ofAnimatedSmoke = p_setAllAnimations_1_;
        this.ofVoidParticles = p_setAllAnimations_1_;
        this.ofWaterParticles = p_setAllAnimations_1_;
        this.ofRainSplash = p_setAllAnimations_1_;
        this.ofPortalParticles = p_setAllAnimations_1_;
        this.ofPotionParticles = p_setAllAnimations_1_;
        this.ofFireworkParticles = p_setAllAnimations_1_;
        this.particleSetting = p_setAllAnimations_1_ ? 0 : 2;
        this.ofDrippingWaterLava = p_setAllAnimations_1_;
        this.ofAnimatedTerrain = p_setAllAnimations_1_;
        this.ofAnimatedTextures = p_setAllAnimations_1_;
    }
	
	private static int nextValue(int p_nextValue_0_, int[] p_nextValue_1_) {
        int i = indexOf(p_nextValue_0_, p_nextValue_1_);

        if (i < 0) {
            return p_nextValue_1_[0];
        } else {
            ++i;

            if (i >= p_nextValue_1_.length) {
                i = 0;
            }

            return p_nextValue_1_[i];
        }
    }

    private static int limit(int p_limit_0_, int[] p_limit_1_) {
        int i = indexOf(p_limit_0_, p_limit_1_);
        return i < 0 ? p_limit_1_[0] : p_limit_0_;
    }
    
    private static int indexOf(int p_indexOf_0_, int[] p_indexOf_1_) {
        for (int i = 0; i < p_indexOf_1_.length; ++i) {
            if (p_indexOf_1_[i] == p_indexOf_0_) {
                return i;
            }
        }

        return -1;
    }
}