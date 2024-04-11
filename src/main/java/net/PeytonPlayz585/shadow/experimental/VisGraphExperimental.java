package net.PeytonPlayz585.shadow.experimental;

import java.util.BitSet;
import java.util.EnumSet;
import java.util.Set;

import net.PeytonPlayz585.shadow.experimental.math.IntStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class VisGraphExperimental {

	private static final int X_OFFSET = (int) Math.pow(16.0D, 0.0D);
	private static final int Z_OFFSET = (int) Math.pow(16.0D, 1.0D);
	private static final int Y_OFFSET = (int) Math.pow(16.0D, 2.0D);
	private static final int[] EDGES = new int[1352];
	private static final SetVisibilityExperimental ALL_VIS = new SetVisibilityExperimental();

	static {
		ALL_VIS.setAllVisible(true);
		int var2 = 0;

		for (int var3 = 0; var3 < 16; ++var3) {
			for (int var4 = 0; var4 < 16; ++var4) {
				for (int var5 = 0; var5 < 16; ++var5) {
					if (var3 == 0 || var3 == 15 || var4 == 0 || var4 == 15 || var5 == 0 || var5 == 15) {
						EDGES[var2++] = getIndex(var3, var4, var5);
					}
				}
			}
		}
	}

	/*
	 * This is a pretty hefty structure: 1340 bytes per 16^3 (40+bytes per object, and the array of long[] in BitSet)
	 * weighing in around 190 bytes for BitSets, 40 bytes for SetVisibility, and 50 bytes for this.
	 * ~4,824,000 bytes at view distance 7; This could be halved if it were not reusable, but reusability is part
	 * of what makes it speedy when recalculating the viewable area.
	 */
	private final BitSet opaqueBlocks = new BitSet(4096);
	private final BitSet visibleBlocks = new BitSet(4096);
	private short transparentBlocks = 4096;
	private boolean dirty = true, computedVis = true;
	private SetVisibilityExperimental visibility;

	public void func_178606_a(BlockPos pos) {
        this.opaqueBlocks.set(getIndex(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15), true);
        --this.transparentBlocks;
    }

	private static int getIndex(int x, int y, int z) {

		return x << 0 | y << 8 | z << 4;
	}

	public boolean isDirty() {

		return dirty;
	}

	public boolean isRenderDirty() {

		if (isDirty()) {
			return true;
		}
		boolean r = computedVis;
		computedVis = false;
		return r;
	}

	public void setOpaque(int x, int y, int z, boolean opaque) {

		boolean prev = opaqueBlocks.get(getIndex(x, y, z));
		if (prev != opaque) {
			opaqueBlocks.set(getIndex(x, y, z), opaque);
			transparentBlocks += opaque ? -1 : 1;
			dirty = true;
		}
	}

	public SetVisibilityExperimental getVisibility() {

		SetVisibilityExperimental setvisibility = visibility;
		if (setvisibility != null) {
			return setvisibility;
		}
		return ALL_VIS;
	}

	public SetVisibilityExperimental computeVisibility() {
		dirty = false;
		SetVisibilityExperimental setvisibility = new SetVisibilityExperimental();

		if (4096 - transparentBlocks < 256) {
			setvisibility.setAllVisible(true);
		} else if (transparentBlocks == 0) {
			setvisibility.setAllVisible(false);
		} else {
			int[] edges = EDGES;
			int i = edges.length;

			visibleBlocks.andNot(visibleBlocks);
			visibleBlocks.or(opaqueBlocks);
			IntStack linkedlist = new IntStack(1024, 512);
			for (int j = 0; j < i; ++j) {
				int k = edges[j];

				if (!opaqueBlocks.get(k)) {
					setvisibility.setManyVisible(computeVisibleFacingsFrom(k, linkedlist));
				}
				linkedlist.setSize(0);
			}
		}

		visibility = setvisibility;
		computedVis = true;

		return setvisibility;
	}

	public Set<EnumFacing> getVisibleFacingsFrom(int x, int y, int z) {

		visibleBlocks.andNot(visibleBlocks);
		visibleBlocks.or(opaqueBlocks);
		return computeVisibleFacingsFrom(getIndex(x & 15, y & 15, z & 15), new IntStack(256, 512));
	}

	public Set<EnumFacing> getVisibleFacingsFrom(BlockPos pos) {
		visibleBlocks.andNot(visibleBlocks);
		visibleBlocks.or(opaqueBlocks);
		return computeVisibleFacingsFrom(getIndex(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15), new IntStack(256, 512));
	}

	private EnumSet<EnumFacing> computeVisibleFacingsFrom(int index, IntStack linkedlist) {

		EnumSet<EnumFacing> enumset = EnumSet.noneOf(EnumFacing.class);
		linkedlist.add(index);
		BitSet blocks = this.visibleBlocks;
		blocks.set(index, true);

		EnumFacing[] facings = EnumFacing.values();
		int k = facings.length;
		while (!linkedlist.isEmpty()) {
			int j = linkedlist.poll();
			addSides(j, enumset);

			for (int l = 0; l < k; ++l) {
				EnumFacing face = facings[l];
				int i1 = stepTo(j, face);

				if (i1 >= 0 && !blocks.get(i1)) {
					blocks.set(i1, true);
					linkedlist.add(i1);
				}
			}
		}

		return enumset;
	}

	private void addSides(int index, Set<EnumFacing> set) {

		int j = index >> 0 & 15;

		if (j == 0) {
			set.add(EnumFacing.WEST);
		} else if (j == 15) {
			set.add(EnumFacing.EAST);
		}

		int k = index >> 8 & 15;

		if (k == 0) {
			set.add(EnumFacing.DOWN);
		} else if (k == 15) {
			set.add(EnumFacing.UP);
		}

		int l = index >> 4 & 15;

		if (l == 0) {
			set.add(EnumFacing.NORTH);
		} else if (l == 15) {
			set.add(EnumFacing.SOUTH);
		}
	}

	private int stepTo(int index, EnumFacing side) {

		switch (side) {
		case DOWN:
			if ((index >> 8 & 15) == 0) {
				return -1;
			}

			return index - Y_OFFSET;
		case UP:
			if ((index >> 8 & 15) == 15) {
				return -1;
			}

			return index + Y_OFFSET;
		case NORTH:
			if ((index >> 4 & 15) == 0) {
				return -1;
			}

			return index - Z_OFFSET;
		case SOUTH:
			if ((index >> 4 & 15) == 15) {
				return -1;
			}

			return index + Z_OFFSET;
		case WEST:
			if ((index >> 0 & 15) == 0) {
				return -1;
			}

			return index - X_OFFSET;
		case EAST:
			if ((index >> 0 & 15) == 15) {
				return -1;
			}

			return index + X_OFFSET;
		default:
			return -1;
		}
	}

}