package net.lax1dude.eaglercraft.v1_8.sp.server.internal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Uint8Array;

import net.lax1dude.eaglercraft.v1_8.internal.IClientConfigAdapter;
import net.lax1dude.eaglercraft.v1_8.internal.IPCPacketData;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformFilesystem;
import net.lax1dude.eaglercraft.v1_8.internal.teavm.TeaVMClientConfigAdapter;
import net.lax1dude.eaglercraft.v1_8.internal.teavm.TeaVMUtils;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.server.MinecraftServer;

/**
 * Copyright (c) 2022-2024 lax1dude. All Rights Reserved.
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
public class ServerPlatformSingleplayer {

	private static final Logger logger = LogManager.getLogger("ServerPlatformSingleplayer");

	private static final LinkedList<IPCPacketData> messageQueue = new LinkedList();

	@JSFunctor
	private static interface WorkerBinaryPacketHandler extends JSObject {
		public void onMessage(String channel, ArrayBuffer buf);
	}

	private static class WorkerBinaryPacketHandlerImpl implements WorkerBinaryPacketHandler {
		
		public void onMessage(String channel, ArrayBuffer buf) {
			if(channel == null) {
				logger.error("Recieved IPC packet with null channel");
				return;
			}
			
			if(buf == null) {
				logger.error("Recieved IPC packet with null buffer");
				return;
			}
			
			String s = null;
			
			try {
				s = new String(TeaVMUtils.wrapUnsignedByteArray(Uint8Array.create(buf)));
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
			
			synchronized(messageQueue) {
				messageQueue.add(new IPCPacketData(channel, TeaVMUtils.wrapUnsignedByteArray(Uint8Array.create(buf))));
			}
		}
		
	}

	@JSBody(params = { "wb" }, script = "onmessage = function(o) { wb(o.data.ch, o.data.dat); };")
	private static native void registerPacketHandler(WorkerBinaryPacketHandler wb);

	public static void register() {
		registerPacketHandler(new WorkerBinaryPacketHandlerImpl());
	}

	public static void initializeContext() {
		PlatformFilesystem.initialize(getClientConfigAdapter().getWorldsDB());
	}

	@JSBody(params = { "ch", "dat" }, script = "postMessage({ ch: ch, dat : dat });")
	public static native void sendPacketTeaVM(String channel, ArrayBuffer arr);

	public static void sendPacket(IPCPacketData packet) {
		ArrayBuffer arb = ArrayBuffer.create(packet.contents.length);
		Uint8Array ar = Uint8Array.create(arb);
		ar.set(packet.contents);
		sendPacketTeaVM(packet.channel, arb);
	}

	public static List<IPCPacketData> recieveAllPacket() {
		synchronized(messageQueue) {
			if(messageQueue.size() == 0) {
				return null;
			}else {
				List<IPCPacketData> ret = new ArrayList(messageQueue);
				messageQueue.clear();
				return ret;
			}
		}
	}

	public static IClientConfigAdapter getClientConfigAdapter() {
		return TeaVMClientConfigAdapter.instance;
	}
}
