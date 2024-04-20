package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiYesNoCallback;

public class GuiYesNoCallbackBufferQueue implements GuiYesNoCallback {

	@Override
	public void confirmClicked(boolean var1, int var2) {
		Minecraft.getMinecraft().gameSettings.experimentalBufferQueue = var1;
		if(var1) {
			Minecraft.getMinecraft().renderGlobal.loadRenderers();
		}
	}

}