package net.lax1dude.eaglercraft.v1_8.sp.internal;

import java.util.ArrayList;
import java.util.List;

import net.lax1dude.eaglercraft.v1_8.internal.IPCPacketData;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.sp.server.internal.lwjgl.CrashScreenPopup;
import net.lax1dude.eaglercraft.v1_8.sp.server.internal.lwjgl.DesktopIntegratedServer;
import net.lax1dude.eaglercraft.v1_8.sp.server.internal.lwjgl.MemoryConnection;
import net.minecraft.server.MinecraftServer;

/**
 * Copyright (c) 2023-2024 lax1dude, ayunami2000. All Rights Reserved.
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
public class ClientPlatformSingleplayer {

	private static CrashScreenPopup crashOverlay = null;

	public static void startIntegratedServer() {
		DesktopIntegratedServer.startIntegratedServer();
	}

	public static void sendPacket(IPCPacketData packet) {
		String s = null;
		byte[] data = packet.contents;
		try {
			s = new String(data);
		} catch(Exception e) {
			s = null;
		}
		
		if(s != null) {
			if(s.contains("weather:true")) {
				MinecraftServer.weather = true;
				return;
			} else if(s.contains("weather:false")) {
				MinecraftServer.weather = false;
				return;
			} else if(s.equals("smoothWorld:true")) {
				MinecraftServer.smoothWorld = true;
				return;
			} else if(s.equals("smoothWorld:false")) {
				MinecraftServer.smoothWorld = false;
				return;
			} else if(s.contains("ofTrees")) {
				String[] value = s.split(":");
				int i = Integer.parseInt(value[1]);
				MinecraftServer.trees = i;
				return;
			} else if(s.contains("graphics")) {
				String[] value = s.split(":");
				String s1 = value[1];
				if(s1.contains("true")) {
					MinecraftServer.fancyGraphics = true;
				} else {
					MinecraftServer.fancyGraphics = false;
				}
				return;
			} else if(s.contains("updateBlocks")) {
				MinecraftServer.getServer().worldServers[0].updateBlocks();
				return;
			} else {
				s = null;
			}
		}
		
		synchronized(MemoryConnection.clientToServerQueue) {
			MemoryConnection.clientToServerQueue.add(packet);
		}
	}

	public static IPCPacketData recievePacket() {
		synchronized(MemoryConnection.serverToClientQueue) {
			if(MemoryConnection.serverToClientQueue.size() > 0) {
				return MemoryConnection.serverToClientQueue.remove(0);
			}
		}
		return null;
	}

	public static List<IPCPacketData> recieveAllPacket() {
		synchronized(MemoryConnection.serverToClientQueue) {
			if(MemoryConnection.serverToClientQueue.size() == 0) {
				return null;
			}else {
				List<IPCPacketData> ret = new ArrayList(MemoryConnection.serverToClientQueue);
				MemoryConnection.serverToClientQueue.clear();
				return ret;
			}
		}
	}

	public static boolean canKillWorker() {
		return false;
	}

	public static void killWorker() {
		throw new IllegalStateException("Cannot kill worker thread on desktop! (memleak)");
	}

	public static boolean isRunningSingleThreadMode() {
		return false;
	}

	public static void showCrashReportOverlay(String report, int x, int y, int w, int h) {
		if(crashOverlay == null) {
			crashOverlay = new CrashScreenPopup();
		}
		int[] wx = new int[1];
		int[] wy = new int[1];
		PlatformRuntime.getWindowXY(wx, wy);
		crashOverlay.setBounds(wx[0] + x, wy[0] + y, w, h);
		crashOverlay.setCrashText(report);
		crashOverlay.setVisible(true);
		crashOverlay.requestFocus();
	}

	public static void hideCrashReportOverlay() {
		crashOverlay.setVisible(false);
	}

}
