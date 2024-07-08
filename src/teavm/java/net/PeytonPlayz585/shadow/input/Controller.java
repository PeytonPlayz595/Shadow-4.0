package net.PeytonPlayz585.shadow.input;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.browser.Navigator;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.html.HTMLBodyElement;
import org.teavm.jso.dom.html.HTMLImageElement;
import org.teavm.jso.gamepad.GamepadEvent;

import net.lax1dude.eaglercraft.v1_8.Display;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.minecraft.client.Minecraft;

import org.teavm.jso.gamepad.Gamepad;

public class Controller {
	
	static List<Integer> connectedControllers = new ArrayList<Integer>();
	
	private static double dx = 0;
	private static double dy = 0;
	
	private static boolean forward = false;
	private static boolean backwards = false;
	private static boolean left = false;
	private static boolean right = false;
	
	//Fixes 'slight' issues of stick drift people have been complaining about
	private static double threshold = 0.3;
	private static double cameraThreshold = 4.0;
	private static double cursorThreshold = 0.4;
	
	private static int activeController = -1;
	private static ButtonState[] button = new ButtonState[30];
	
	public static HTMLImageElement cursor;
	
	public static final int getDX() {
		if(dx < 0.0) {
			if(dx < -cameraThreshold) {
				return (int)dx;
			}
		} else if(dx > 0.0) {
			if(dx > cameraThreshold) {
				return (int)dx;
			}
		}
		return 0;
	}
	
	public static final int getDY() {
		if(dy < 0.0) {
			if(dy < -cameraThreshold) {
				return (int)dy;
			}
		} else if(dy > 0.0) {
			if(dy > cameraThreshold) {
				return (int)dy;
			}
		}
		return 0;
	}
	
	public static boolean forward() {
		return forward;
	}
	
	public static boolean backwards() {
		return backwards;
	}
	
	public static boolean left() {
		return left;
	}
	
	public static boolean right() {
		return right;
	}
	
	public static boolean jump() {
		return isButtonPressed(0) || isButtonDown(0);
	}
	
	public static boolean crouch() {
		return isButtonPressed(1) || isButtonDown(1) || isButtonPressed(11) || isButtonDown(11);
	}
	
	public static boolean sprint() {
		return isButtonPressed(10) || isButtonDown(10);
	}
	
	public static boolean itemChangeLeft() {
		return button[4] == ButtonState.PRESSED;
	}
	
	public static boolean itemChangeRight() {
		return button[5] == ButtonState.PRESSED;
	}
	
	public static boolean inventory() {
		return button[2] == ButtonState.PRESSED || button[3] == ButtonState.PRESSED;
	}
	
	public static boolean togglePerspective() {
		return button[12] == ButtonState.PRESSED;
	}
	
	public static boolean playerList() {
		return button[15] == ButtonState.HELD;
	}
	
	public static boolean smoothCamera() {
		return button[14] == ButtonState.PRESSED;
	}
	
	public static boolean dropItem() {
		return button[13] == ButtonState.PRESSED;
	}
	
	public static boolean isButtonPressed(int i) {
		return button[i] == ButtonState.PRESSED;
	}
	
	public static boolean isButtonDown(int i) {
		return button[i] == ButtonState.HELD;
	}

	private static void updateAxes(Gamepad gamePad) {
		double[] axes = gamePad.getAxes();
		int multiplier = 50;
		
		/* 
		 * Not sure if these are controller specific
		 * ¯\_(ツ)_/¯
		 */
		dx = axes[2] * multiplier;
		
		/*
		 * Idk if it's inverted for all controllers
		 * or just the one I'm using
		 */
		dy = -axes[3] * multiplier;
		
		if(cursor != null) {
			int dx1 = getDX();
			int dy1 = getDY();
			
			if(dx1 > 0 || dx1 < 0) {
				updateCursorX(dx1);
			}
			
			if(dy1 > 0 || dy1 < 0) {
				updateCursorY(-dy1);
			}
		}
		
		forward = axes[1] < -threshold;
		backwards = axes[1] > threshold;
		left = axes[0] < -threshold;
		right = axes[0] > threshold;
	}
	
	private static void updateButtons(Gamepad gamePad, int index) {
		for(int i = 0; i < gamePad.getButtons().length; i++) {
			if(gamePad.getButtons()[i].isPressed()) {
				if(activeController != index) {
					activeController = index;
					resetButtonStates();
				}
				
				if(button[i] == ButtonState.PRESSED) {
					button[i] = ButtonState.HELD;
				} else {
					if(!(button[i] == ButtonState.HELD)) {
						button[i] = ButtonState.PRESSED;
					}
				}
			} else if(!gamePad.getButtons()[i].isPressed() && index == activeController) {
				button[i] = ButtonState.DEFAULT;
			}
		}
	}
	
	public static void tick() {
		for(Integer index : connectedControllers) {
			Gamepad gamePad = getGamepad(index);
			updateAxes(gamePad);
			updateButtons(gamePad, index);
		}
		
		if(cursor != null && isButtonPressed(0) || isButtonDown(0)) {
			Minecraft.getMinecraft().currentScreen.controllerClicked(Mouse.getX(), Mouse.getY());
		}
	}
	
	public static void addCursor(int x, int y) {
		cursor = (HTMLImageElement) Window.current().getDocument().createElement("img");
		cursor.setAttribute("id", "cursor");
		cursor.setSrc(cursorSrc);
		cursor.setAttribute("draggable", "false");
		cursor.getStyle().setProperty("position", "fixed");
        cursor.getStyle().setProperty("top", "0");
        cursor.getStyle().setProperty("left", "0");
        cursor.getStyle().setProperty("width", "auto");
        cursor.getStyle().setProperty("height", "auto");
        
        HTMLBodyElement body = (HTMLBodyElement) Window.current().getDocument().getBody();
        body.appendChild(cursor);
	}
	
	public static void removeCursor() {
    	if (cursor != null) {
    		cursor.getParentNode().removeChild(cursor);
        }
		cursor = null;
	}
	
	private static void updateCursorX(int x) {
		int newX = cursor.getOffsetLeft() + x;
		
		newX = Math.max(0, Math.min(newX, PlatformRuntime.canvas.getClientWidth() - cursor.getWidth()));
		cursor.getStyle().setProperty("left", Integer.toString(newX) + "px");
	}
	
	private static void updateCursorY(int y) {
		int newY = cursor.getOffsetTop() + y;
		
		newY = Math.max(0, Math.min(newY, PlatformRuntime.canvas.getClientHeight() - cursor.getHeight()));
		cursor.getStyle().setProperty("top", Integer.toString(newY) + "px");
	}
	
	private static Gamepad getGamepad(int index) {
		return Navigator.getGamepads()[index];
	}
	
	private static void resetButtonStates() {
		for(int i = 0; i < button.length; i++) {
			button[i] = ButtonState.DEFAULT;
		}
	}
	
	private static enum ButtonState {
		DEFAULT /* not pressed */, PRESSED, HELD
	}
	
	static {
		Window.current().addEventListener("gamepadconnected", new EventListener<GamepadEvent>() {
			@Override
			public void handleEvent(GamepadEvent arg0) {
				connectedControllers.add(arg0.getGamepad().getIndex());
				System.out.println("Controller connected!");
			}
		});
		
		Window.current().addEventListener("gamepaddisconnected", new EventListener<GamepadEvent>() {
			@Override
			public void handleEvent(GamepadEvent arg0) {
				int index = arg0.getGamepad().getIndex();
				if(connectedControllers.contains(index)) {
					connectedControllers.remove(index);
					resetButtonStates();
					System.out.println("Controller disconnected!");
				}
			}
		});
		
		resetButtonStates();
	}
	
	private static String cursorSrc = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAAWCAYAAAAmaHdCAAABhGlDQ1BJQ0MgcHJvZmlsZQAAKJF9kT1Iw0AcxV/TiiIVBzOIOGSoThbELxylikWwUNoKrTqYXPoFTRqSFBdHwbXg4Mdi1cHFWVcHV0EQ/ABxc3NSdJES/5cUWsR4cNyPd/ced+8AoVFhmhUaBzTdNlPxmJTNrUrdrwhDRAjTiMrMMhLpxQx8x9c9Any9i/Is/3N/jj41bzEgIBHPMcO0iTeIZzZtg/M+schKskp8Tjxm0gWJH7muePzGueiywDNFM5OaJxaJpWIHKx3MSqZGPEUcUTWd8oWsxyrnLc5apcZa9+QvDOf1lTTXaQ4jjiUkkIQEBTWUUYGNKK06KRZStB/z8Q+5/iS5FHKVwcixgCo0yK4f/A9+d2sVJie8pHAM6HpxnI8RoHsXaNYd5/vYcZonQPAZuNLb/moDmP0kvd7WIkdA/zZwcd3WlD3gcgcYfDJkU3alIE2hUADez+ibcsDALdC75vXW2sfpA5ChrpZvgINDYLRI2es+7+7p7O3fM63+fgDbd3LRJJH9vQAAAAZiS0dEAJ0AnQCdq6CchAAAAAlwSFlzAAAuIwAALiMBeKU/dgAAAeJJREFUOMvdlL9rFEEUx7+Zd0x3tWSQEEEIXBUCueawELQQERULxdZObMXGVrh/wuqqtEKapEihFtlA7kT8EUjpjhHhjsO9c3dv3jwLM+vmbr1TsPILC7Ps7me+b7/vDWGOSJumovonRfWWovqR8LcYfyvSRoJIG/ndewr/QP87hLRpkjY7VT+StJHTZ82FiURRt0iknI6ISBR1pQQzfxTr9JqZhbSRV6/3f4GC9WBzHoSZZTKZCGkjo9FI9vZeCmmzA9JGYnsisT0JIKlSAKRpKqSNDIdD6ff7QtqIArALAGb5HFwWX60q0XsP7z2YGcyM/tcPYGY454p0nq6sbuzaz18AAE8eP5qNUCnUajWMx2NkWVYACgjnNgKwtbK6sQ0At25eq5yRwWBw5v4MBAA4t88BNA4OerK5ub5U/jBcQUmSIE1TOOdw2H0LAO+XStE+AHDbZfF17z2UUjO7J0lSrJkZF9da7emO7QBo7EeH8N4vBJyWchfAVgHh3OYAnrUu3dhm5p/uiCob0jmH3pt3APCdc9ubnp0OgEYUdUFExY5pmhYOQjr37j9sA3gxM4DBzeUrd9rlGJ1zBTD0SigFAGoVbjsAPs4DeO8BgDi3vUoI5zYnbY6Xz69fWHCMHIXFD7PBln0/6ZSqAAAAAElFTkSuQmCC";
}
