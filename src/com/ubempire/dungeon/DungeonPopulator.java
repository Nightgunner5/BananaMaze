package com.ubempire.dungeon;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class DungeonPopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random rand, Chunk chunk) {
		int y = 20;
		int chunkX = chunk.getX() * 16;
		int chunkZ = chunk.getZ() * 16;

		int[] NSEW1 = doNSEW1(world, chunk, y);
		int[] NSEW2 = doNSEW2(world, chunk, y);
		int hasExit = hasExit(NSEW1, NSEW2);
		if (hasExit == -1) {
			for (y = 20; y < 30; y++) {
				for (int z = 1; z < 15; z++) {
					world.getBlockAt(chunkX, y, chunkZ + z).setTypeId(0);
					world.getBlockAt(chunkX - 1, y, chunkZ + z).setTypeId(0);
				}
			}
		}
	}

	public int hasExit(int[] NSEW1, int[] NSEW2) {
		for (int i = 0; i < 4; i++) {
			if (NSEW1[i] == NSEW2[i]) {
				if ((NSEW1[i] == 0) && (NSEW2[i] == 0))
					return i;
			}
		}
		return -1;
	}

	public int[] doNSEW1(World world, Chunk chunk, int y) {
		/*
		 * Do the main checks for NSEW
		 */
		int chunkX = chunk.getX() * 16;
		int chunkZ = chunk.getZ() * 16;
		// check face 1
		int N = world.getBlockTypeIdAt(chunkX, y, chunkZ + 1);
		// check face 2
		int S = world.getBlockTypeIdAt(chunkX + 15, y, chunkZ + 1);
		// check face 3
		int E = world.getBlockTypeIdAt(chunkX + 1, y, chunkZ);
		// check face 4
		int W = world.getBlockTypeIdAt(chunkX + 1, y, chunkZ + 15);
		return new int[] { N, S, E, W };
	}

	public int[] doNSEW2(World world, Chunk chunk, int y) {
		/*
		 * Do the secondary checks for NSEW
		 */
		int chunkX = chunk.getX() * 16;
		int chunkZ = chunk.getZ() * 16;
		// check face 1
		int N = world.getBlockTypeIdAt(chunkX - 1, y, chunkZ + 1);
		// check face 2
		int S = world.getBlockTypeIdAt(chunkX + 16, y, chunkZ + 1);
		// check face 3
		int E = world.getBlockTypeIdAt(chunkX + 1, y, chunkZ - 1);
		// check face 4
		int W = world.getBlockTypeIdAt(chunkX + 1, y, chunkZ + 16);
		return new int[] { N, S, E, W };
	}
}