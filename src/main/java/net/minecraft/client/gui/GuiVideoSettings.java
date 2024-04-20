package net.minecraft.client.gui;

import java.io.IOException;

import net.PeytonPlayz585.shadow.Config;
import net.PeytonPlayz585.shadow.GuiAnimationSettingsOF;
import net.PeytonPlayz585.shadow.GuiDetailSettingsOF;
import net.PeytonPlayz585.shadow.GuiOptionButtonOF;
import net.PeytonPlayz585.shadow.GuiOptionSliderOF;
import net.PeytonPlayz585.shadow.GuiOtherSettingsOF;
import net.PeytonPlayz585.shadow.GuiPerformanceSettingsOF;
import net.PeytonPlayz585.shadow.GuiQualitySettingsOF;
import net.PeytonPlayz585.shadow.GuiShaders;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

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
public class GuiVideoSettings extends GuiScreen {
	private GuiScreen parentGuiScreen;
	protected String screenTitle = "Video Settings";
	private GameSettings guiGameSettings;
	/**+
	 * An array of all of GameSettings.Options's video options.
	 */
	private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {
			GameSettings.Options.GRAPHICS, 
			GameSettings.Options.RENDER_DISTANCE, 
			GameSettings.Options.AMBIENT_OCCLUSION, 
			GameSettings.Options.FRAMERATE_LIMIT, 
			GameSettings.Options.AO_LEVEL, 
			GameSettings.Options.VIEW_BOBBING, 
			GameSettings.Options.GUI_SCALE, 
			GameSettings.Options.USE_VBO, 
			GameSettings.Options.GAMMA, 
			GameSettings.Options.BLOCK_ALTERNATIVES,
			GameSettings.Options.FOG_FANCY, 
			GameSettings.Options.FOG_START
	};

	public GuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
		this.parentGuiScreen = parentScreenIn;
		this.guiGameSettings = gameSettingsIn;
	}

	/**+
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
		this.buttonList.clear();
		for (int i = 0; i < videoOptions.length; ++i) {
            GameSettings.Options gamesettings$options = videoOptions[i];

            if (gamesettings$options != null) {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;

                if (gamesettings$options.getEnumFloat()) {
                    this.buttonList.add(new GuiOptionSliderOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                } else {
                    this.buttonList.add(new GuiOptionButtonOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }
		
		int l = this.height / 6 + 21 * (videoOptions.length / 2) - 12;
        int i1 = 0;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(231, i1, l, "Shaders..."));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(202, i1, l, "Quality..."));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(201, i1, l, "Details..."));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(212, i1, l, "Performance..."));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(211, i1, l, "Animations..."));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(222, i1, l, "Other..."));
        l = l + 21;
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
	}

	/**+
	 * Handles mouse input.
	 */
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	/**+
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 200) {
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(this.parentGuiScreen);
			}

			if (parGuiButton.id < 200 && parGuiButton instanceof GuiOptionButton) {
				this.guiGameSettings.setOptionValue(((GuiOptionButton)parGuiButton).returnEnumOptions(), 1);
				parGuiButton.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(parGuiButton.id));
			}
			
			if (parGuiButton.id == 201) {
                this.mc.gameSettings.saveOptions();
                GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guidetailsettingsof);
            }

            if (parGuiButton.id == 202) {
                this.mc.gameSettings.saveOptions();
                GuiQualitySettingsOF guiqualitysettingsof = new GuiQualitySettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiqualitysettingsof);
            }

            if (parGuiButton.id == 211) {
                this.mc.gameSettings.saveOptions();
                GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guianimationsettingsof);
            }

            if (parGuiButton.id == 212) {
                this.mc.gameSettings.saveOptions();
                GuiPerformanceSettingsOF guiperformancesettingsof = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiperformancesettingsof);
            }

            if (parGuiButton.id == 222) {
                this.mc.gameSettings.saveOptions();
                GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiothersettingsof);
            }
            
            if (parGuiButton.id == 231) {
                if (Config.isAnisotropicFiltering()) {
                    Config.showGuiMessage("Shaders are not compatible with Anisotropic Filtering.", "Please set Quality -> Anisotropic Filtering to OFF. ");
                    return;
                }

                this.mc.gameSettings.saveOptions();
                GuiShaders guishaders = new GuiShaders(this);
                this.mc.displayGuiScreen(guishaders);
            }
		}
	}

	/**+
	 * Called when the mouse is clicked. Args : mouseX, mouseY,
	 * clickedButton
	 */
	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		int i = this.guiGameSettings.guiScale;
		super.mouseClicked(parInt1, parInt2, parInt3);
		if (this.guiGameSettings.guiScale != i) {
			ScaledResolution scaledresolution = new ScaledResolution(this.mc);
			int j = scaledresolution.getScaledWidth();
			int k = scaledresolution.getScaledHeight();
			this.setWorldAndResolution(this.mc, j, k);
		}

	}

	/**+
	 * Called when a mouse button is released. Args : mouseX,
	 * mouseY, releaseButton
	 */
	protected void mouseReleased(int i, int j, int k) {
		int l = this.guiGameSettings.guiScale;
		super.mouseReleased(i, j, k);
		if (this.guiGameSettings.guiScale != l) {
			ScaledResolution scaledresolution = new ScaledResolution(this.mc);
			int i1 = scaledresolution.getScaledWidth();
			int j1 = scaledresolution.getScaledHeight();
			this.setWorldAndResolution(this.mc, i1, j1);
		}

	}

	/**+
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 5, 16777215);
		super.drawScreen(i, j, f);
	}
}