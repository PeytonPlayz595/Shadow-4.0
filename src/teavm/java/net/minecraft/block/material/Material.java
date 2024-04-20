package net.minecraft.block.material;

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
public class Material extends ModData {
	public static final Material air = new MaterialTransparent(MapColor.airColor);
	public static final Material grass = new Material(MapColor.grassColor);
	public static final Material ground = new Material(MapColor.dirtColor);
	public static final Material wood = (new Material(MapColor.woodColor)).setBurning();
	public static final Material rock = (new Material(MapColor.stoneColor)).setRequiresTool();
	public static final Material iron = (new Material(MapColor.ironColor)).setRequiresTool();
	public static final Material anvil = (new Material(MapColor.ironColor)).setRequiresTool().setImmovableMobility();
	public static final Material water = (new MaterialLiquid(MapColor.waterColor)).setNoPushMobility();
	public static final Material lava = (new MaterialLiquid(MapColor.tntColor)).setNoPushMobility();
	public static final Material leaves = (new Material(MapColor.foliageColor)).setBurning().setTranslucent()
			.setNoPushMobility();
	public static final Material plants = (new MaterialLogic(MapColor.foliageColor)).setNoPushMobility();
	public static final Material vine = (new MaterialLogic(MapColor.foliageColor)).setBurning().setNoPushMobility()
			.setReplaceable();
	public static final Material sponge = new Material(MapColor.yellowColor);
	public static final Material cloth = (new Material(MapColor.clothColor)).setBurning();
	public static final Material fire = (new MaterialTransparent(MapColor.airColor)).setNoPushMobility();
	public static final Material sand = new Material(MapColor.sandColor);
	public static final Material circuits = (new MaterialLogic(MapColor.airColor)).setNoPushMobility();
	public static final Material carpet = (new MaterialLogic(MapColor.clothColor)).setBurning();
	public static final Material glass = (new Material(MapColor.airColor)).setTranslucent().setAdventureModeExempt();
	public static final Material redstoneLight = (new Material(MapColor.airColor)).setAdventureModeExempt();
	public static final Material tnt = (new Material(MapColor.tntColor)).setBurning().setTranslucent();
	public static final Material coral = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material ice = (new Material(MapColor.iceColor)).setTranslucent().setAdventureModeExempt();
	public static final Material packedIce = (new Material(MapColor.iceColor)).setAdventureModeExempt();
	public static final Material snow = (new MaterialLogic(MapColor.snowColor)).setReplaceable().setTranslucent()
			.setRequiresTool().setNoPushMobility();
	/**+
	 * The material for crafted snow.
	 */
	public static final Material craftedSnow = (new Material(MapColor.snowColor)).setRequiresTool();
	public static final Material cactus = (new Material(MapColor.foliageColor)).setTranslucent().setNoPushMobility();
	public static final Material clay = new Material(MapColor.clayColor);
	public static final Material gourd = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material dragonEgg = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material portal = (new MaterialPortal(MapColor.airColor)).setImmovableMobility();
	public static final Material cake = (new Material(MapColor.airColor)).setNoPushMobility();
	public static final Material web = (new Material(MapColor.clothColor) {
		/**+
		 * Returns if this material is considered solid or not
		 */
		public boolean blocksMovement() {
			return false;
		}
	}).setRequiresTool().setNoPushMobility();
	/**+
	 * Pistons' material.
	 */
	public static final Material piston = (new Material(MapColor.stoneColor)).setImmovableMobility();
	public static final Material barrier = (new Material(MapColor.airColor)).setRequiresTool().setImmovableMobility();
	private boolean canBurn;
	private boolean replaceable;
	private boolean isTranslucent;
	private final MapColor materialMapColor;
	/**+
	 * Determines if the material can be harvested without a tool
	 * (or with the wrong tool)
	 */
	private boolean requiresNoTool = true;
	private int mobilityFlag;
	private boolean isAdventureModeExempt;

	public Material(MapColor color) {
		this.materialMapColor = color;
	}

	public static ModData makeModDataStatic() {
		ModData data = new ModData();
		data.set("air", air.makeModData());
		data.set("grass", grass.makeModData());
		data.set("ground", ground.makeModData());
		data.set("wood", wood.makeModData());
		data.set("rock", rock.makeModData());
		data.set("iron", iron.makeModData());
		data.set("anvil", anvil.makeModData());
		data.set("water", water.makeModData());
		data.set("lava", lava.makeModData());
		data.set("leaves", leaves.makeModData());
		data.set("plants", plants.makeModData());
		data.set("vine", vine.makeModData());
		data.set("sponge", sponge.makeModData());
		data.set("cloth", cloth.makeModData());
		data.set("fire", fire.makeModData());
		data.set("sand", sand.makeModData());
		data.set("circuits", circuits.makeModData());
		data.set("carpet", carpet.makeModData());
		data.set("glass", glass.makeModData());
		data.set("redstoneLight", redstoneLight.makeModData());
		data.set("tnt", tnt.makeModData());
		data.set("coral", coral.makeModData());
		data.set("ice", ice.makeModData());
		data.set("packedIce", packedIce.makeModData());
		data.set("snow", snow.makeModData());
		data.set("craftedSnow", craftedSnow.makeModData());
		data.set("cactus", cactus.makeModData());
		data.set("clay", clay.makeModData());
		data.set("gourd", gourd.makeModData());
		data.set("dragonEgg", dragonEgg.makeModData());
		data.set("portal", portal.makeModData());
		data.set("cake", cake.makeModData());
		data.set("web", web.makeModData());
		data.set("piston", piston.makeModData());
		data.set("barrier", barrier.makeModData());
		return data;
	}

	public void loadModData(BaseData data) {
		canBurn = data.getBoolean("canBurn");
		replaceable = data.getBoolean("replaceable");
		requiresNoTool = data.getBoolean("requiresNoTool");
		isTranslucent = data.getBoolean("isTranslucent");
		isAdventureModeExempt = data.getBoolean("isAdventureModeExempt");
		materialMapColor.loadModData(data.getBaseData("materialMapColor"));

		mobilityFlag = data.getInt("mobilityFlag");
	}

	public ModData makeModData() {
		ModData data = new ModData();
		data.setCallbackObject("getRef", () -> {
			return this;
		});
		data.set("canBurn", canBurn);
		data.set("replaceable", replaceable);
		data.set("isTranslucent", isTranslucent);
		data.set("requiresNoTool", requiresNoTool);
		data.set("mobilityFlag", mobilityFlag);
		data.set("isAdventureModeExempt", isAdventureModeExempt);
		data.set("materialMapColor", materialMapColor.makeModData());

		data.setCallbackVoid("reload", () -> {
			loadModData(data);
		});

		data.setCallbackBoolean("isLiquid", () -> {
			/**+
			 * Returns if blocks of these materials are liquids.
			 */
			return isLiquid();
		});
		data.setCallbackBoolean("isSolid", () -> {
			/**+
			 * Returns true if the block is a considered solid. This is true
			 * by default.
			 */
			return isSolid();
		});
		data.setCallbackBoolean("isReplaceable", () -> {
			/**+
			 * Returns whether the material can be replaced by other blocks
			 * when placed - eg snow, vines and tall grass.
			 */
			return isReplaceable();
		});
		data.setCallbackBoolean("isToolNotRequired", () -> {
			/**+
			 * Returns true if the material can be harvested without a tool
			 * (or with the wrong tool)
			 */
			return isToolNotRequired();
		});
		data.setCallbackBoolean("isOpaque", () -> {
			/**+
			 * Indicate if the material is opaque
			 */
			return isOpaque();
		});
		data.setCallbackBoolean("getCanBurn", () -> {
			/**+
			 * Returns if the block can burn or not.
			 */
			return getCanBurn();
		});
		data.setCallbackBoolean("blocksLight", () -> {
			/**+
			 * Will prevent grass from growing on dirt underneath and kill
			 * any grass below it if it returns true
			 */
			return blocksLight();
		});
		data.setCallbackBoolean("blocksMovement", () -> {
			/**+
			 * Returns if this material is considered solid or not
			 */
			return blocksMovement();
		});
		data.setCallbackObject("setTranslucent", () -> {
			/**+
			 * Marks the material as translucent
			 */
			return setTranslucent().makeModData();
		});
		data.setCallbackObject("setRequiresTool", () -> {
			/**+
			 * Makes blocks with this material require the correct tool to
			 * be harvested.
			 */
			return setRequiresTool().makeModData();
		});
		data.setCallbackObject("setBurning", () -> {
			/**+
			 * Set the canBurn bool to True and return the current object.
			 */
			return setBurning().makeModData();
		});
		data.setCallbackObject("setReplaceable", () -> {
			/**+
			 * Sets {@link #replaceable} to true.
			 */
			return setReplaceable().makeModData();
		});
		data.setCallbackObject("setNoPushMobility", () -> {
			/**+
			 * This type of material can't be pushed, but pistons can move
			 * over it.
			 */
			return setNoPushMobility().makeModData();
		});
		data.setCallbackInt("getMaterialMobility", () -> {
			/**+
			 * Returns the mobility information of the material, 0 = free, 1
			 * = can't push but can move over, 2 = total immobility and stop
			 * pistons.
			 */
			return getMaterialMobility();
		});
		data.setCallbackObject("setImmovableMobility", () -> {
			/**+
			 * This type of material can't be pushed, and pistons are
			 * blocked to move.
			 */
			return setImmovableMobility().makeModData();
		});
		data.setCallbackObject("setAdventureModeExempt", () -> {
			/**+
			 * @see #isAdventureModeExempt()
			 */
			return setAdventureModeExempt().makeModData();
		});
		return data;
	}

	/**+
	 * Returns if blocks of these materials are liquids.
	 */
	public boolean isLiquid() {
		return false;
	}

	/**+
	 * Returns true if the block is a considered solid. This is true
	 * by default.
	 */
	public boolean isSolid() {
		return true;
	}

	/**+
	 * Will prevent grass from growing on dirt underneath and kill
	 * any grass below it if it returns true
	 */
	public boolean blocksLight() {
		return true;
	}

	/**+
	 * Returns if this material is considered solid or not
	 */
	public boolean blocksMovement() {
		return true;
	}

	/**+
	 * Marks the material as translucent
	 */
	private Material setTranslucent() {
		this.isTranslucent = true;
		return this;
	}

	/**+
	 * Makes blocks with this material require the correct tool to
	 * be harvested.
	 */
	protected Material setRequiresTool() {
		this.requiresNoTool = false;
		return this;
	}

	/**+
	 * Set the canBurn bool to True and return the current object.
	 */
	protected Material setBurning() {
		this.canBurn = true;
		return this;
	}

	/**+
	 * Returns if the block can burn or not.
	 */
	public boolean getCanBurn() {
		return this.canBurn;
	}

	/**+
	 * Sets {@link #replaceable} to true.
	 */
	public Material setReplaceable() {
		this.replaceable = true;
		return this;
	}

	/**+
	 * Returns whether the material can be replaced by other blocks
	 * when placed - eg snow, vines and tall grass.
	 */
	public boolean isReplaceable() {
		return this.replaceable;
	}

	/**+
	 * Indicate if the material is opaque
	 */
	public boolean isOpaque() {
		return this.isTranslucent ? false : this.blocksMovement();
	}

	/**+
	 * Returns true if the material can be harvested without a tool
	 * (or with the wrong tool)
	 */
	public boolean isToolNotRequired() {
		return this.requiresNoTool;
	}

	/**+
	 * Returns the mobility information of the material, 0 = free, 1
	 * = can't push but can move over, 2 = total immobility and stop
	 * pistons.
	 */
	public int getMaterialMobility() {
		return this.mobilityFlag;
	}

	/**+
	 * This type of material can't be pushed, but pistons can move
	 * over it.
	 */
	protected Material setNoPushMobility() {
		this.mobilityFlag = 1;
		return this;
	}

	/**+
	 * This type of material can't be pushed, and pistons are
	 * blocked to move.
	 */
	protected Material setImmovableMobility() {
		this.mobilityFlag = 2;
		return this;
	}

	/**+
	 * @see #isAdventureModeExempt
	 */
	protected Material setAdventureModeExempt() {
		this.isAdventureModeExempt = true;
		return this;
	}

	/**+
	 * Retrieves the color index of the block. This is is the same
	 * color used by vanilla maps to represent this block.
	 */
	public MapColor getMaterialMapColor() {
		return this.materialMapColor;
	}
}