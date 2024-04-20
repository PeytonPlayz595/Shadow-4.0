package net.minecraft.util;

import com.google.common.base.Objects;
import net.eaglerforge.api.BaseData;
import net.eaglerforge.api.ModData;

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
public class Vec3i extends ModData implements Comparable<Vec3i> {
	/**+
	 * The Null vector constant (0, 0, 0)
	 */
	public static final Vec3i NULL_VECTOR = new Vec3i(0, 0, 0);
	public int x;
	public int y;
	public int z;

	public Vec3i(int xIn, int yIn, int zIn) {
		this.x = xIn;
		this.y = yIn;
		this.z = zIn;
	}

	public Vec3i(double xIn, double yIn, double zIn) {
		this(MathHelper.floor_double(xIn), MathHelper.floor_double(yIn), MathHelper.floor_double(zIn));
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof Vec3i)) {
			return false;
		} else {
			Vec3i vec3i = (Vec3i) object;
			return this.getX() != vec3i.getX() ? false
					: (this.getY() != vec3i.getY() ? false : this.getZ() == vec3i.getZ());
		}
	}

	public void loadModData(BaseData data) {
		x = data.getInt("x");
		y = data.getInt("y");
		z = data.getInt("z");
	}

	public static Vec3i fromModData(BaseData data) {
		return new Vec3i(data.getInt("x"), data.getInt("y"), data.getInt("z"));
	}

	public ModData makeModData() {
		ModData data = new ModData();
		data.setCallbackVoid("reload", () -> {
			loadModData(data);
		});
		data.setCallbackObject("getRef", () -> {
			return this;
		});
		data.set("x", x);
		data.set("y", y);
		data.set("z", z);
		data.setCallbackInt("hashCode", () -> {
			return hashCode();
		});
		data.setCallbackIntWithDataArg("compareTo", (BaseData vec) -> {
			return compareTo(Vec3i.fromModData(vec));
		});
		data.setCallbackInt("getX", () -> {
			return hashCode();
		});
		data.setCallbackInt("getY", () -> {
			/**+
			 * Get the Y coordinate
			 */
			return getY();
		});
		data.setCallbackInt("getZ", () -> {
			/**+
			 * Get the Z coordinate
			 */
			return getZ();
		});
		data.setCallbackObjectWithDataArg("crossProduct", (BaseData vec) -> {
			/**+
			 * Calculate the cross product of this and the given Vector
			 */
			return crossProduct(Vec3i.fromModData(vec)).makeModData();
		});
		data.setCallbackDoubleWithDataArg("distanceSq", (BaseData vec) -> {
			/**+
			 * Calculate squared distance to the given coordinates
			 */
			return distanceSq(Vec3i.fromModData(vec));
		});
		data.setCallbackDoubleWithDataArg("distanceSqToCenter", (BaseData params) -> {
			/**+
			 * Compute square of distance from point x, y, z to center of
			 * this Block
			 */
			return distanceSqToCenter(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
		});
		data.setCallbackString("toString", () -> {
			return toString();
		});
		return data;
	}

	public int hashCode() {
		return (this.getY() + this.getZ() * 31) * 31 + this.getX();
	}

	public int compareTo(Vec3i vec3i) {
		return this.getY() == vec3i.getY()
				? (this.getZ() == vec3i.getZ() ? this.getX() - vec3i.getX() : this.getZ() - vec3i.getZ())
				: this.getY() - vec3i.getY();
	}

	/**+
	 * Get the X coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**+
	 * Get the Y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**+
	 * Get the Z coordinate
	 */
	public int getZ() {
		return this.z;
	}

	/**+
	 * Calculate the cross product of this and the given Vector
	 */
	public Vec3i crossProduct(Vec3i vec3i) {
		return new Vec3i(this.getY() * vec3i.getZ() - this.getZ() * vec3i.getY(),
				this.getZ() * vec3i.getX() - this.getX() * vec3i.getZ(),
				this.getX() * vec3i.getY() - this.getY() * vec3i.getX());
	}

	/**+
	 * Calculate squared distance to the given coordinates
	 */
	public double distanceSq(double toX, double toY, double toZ) {
		double d0 = (double) this.getX() - toX;
		double d1 = (double) this.getY() - toY;
		double d2 = (double) this.getZ() - toZ;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	/**+
	 * Compute square of distance from point x, y, z to center of
	 * this Block
	 */
	public double distanceSqToCenter(double xIn, double yIn, double zIn) {
		double d0 = (double) this.getX() + 0.5D - xIn;
		double d1 = (double) this.getY() + 0.5D - yIn;
		double d2 = (double) this.getZ() + 0.5D - zIn;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	/**+
	 * Calculate squared distance to the given coordinates
	 */
	public double distanceSq(Vec3i to) {
		return this.distanceSq((double) to.getX(), (double) to.getY(), (double) to.getZ());
	}

	public String toString() {
		return Objects.toStringHelper(this).add("x", this.getX()).add("y", this.getY()).add("z", this.getZ())
				.toString();
	}
}