package net.PeytonPlayz585.shadow;

import net.minecraft.client.resources.I18n;

public class Lang {
	
	public static String getFast() {
		return I18n.format("options.graphics.fast", new Object[0]);
	}
	
	public static String getFancy() {
		return I18n.format("options.graphics.fancy", new Object[0]);
	}
	
	public static String getOff() {
		return I18n.format("options.off");
	}
	
	public static String getOn() {
		return I18n.format("options.on");
	}

	public static String getDefault() {
		return I18n.format("generator.default", new Object[0]);
	}

}