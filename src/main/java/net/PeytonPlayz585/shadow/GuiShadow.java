package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiShadow extends GuiScreen {
	
	private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.TOGGLE_SPRINT, GameSettings.Options.CHUNK_BORDERS, GameSettings.Options.HIDE_PASSWORD};
	
	private GuiScreen parentScreen;
	protected String title;
	
	public GuiShadow(GuiScreen screen) {
		this.parentScreen = screen;
	}
	
	/**+
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		this.title = I18n.format("Shadow Client", new Object[0]);
		this.buttonList.clear();
		
		for (int i = 0; i < enumOptions.length; ++i) {
            GameSettings.Options gamesettings$options = enumOptions[i];
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;

            if (!gamesettings$options.getEnumFloat()) {
                this.buttonList.add(new GuiOptionButtonOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, Minecraft.getMinecraft().gameSettings.getKeyBinding(gamesettings$options)));
            } else {
                this.buttonList.add(new GuiOptionSliderOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
            }
        }
		
		this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 6 + 168 + 11 - 35, I18n.format("Experimental Settings...", new Object[0])));
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
	}
	
	/**+
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton parGuiButton) {
		if(parGuiButton.enabled) {
			
			if (parGuiButton.id < 200 && parGuiButton instanceof GuiOptionButton) {
				Minecraft.getMinecraft().gameSettings.setOptionValue(((GuiOptionButton)parGuiButton).returnEnumOptions(), 1);
                parGuiButton.displayString = Minecraft.getMinecraft().gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(parGuiButton.id));
            }
			
			if(parGuiButton.id == 200) {
				this.mc.displayGuiScreen(parentScreen);
			}
			
			if(parGuiButton.id == 201) {
				this.mc.displayGuiScreen(new GuiShadowExperimental(this));
			}
		}
	}
	
	/**+
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
		super.drawScreen(i, j, f);
	}
	
	protected void mouseClicked(int mx, int my, int button) {
		super.mouseClicked(mx, my, button);
	}
}