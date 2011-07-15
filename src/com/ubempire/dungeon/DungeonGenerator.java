package com.ubempire.dungeon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.llamaslayers.minecraft.banana.populators.CeilingPopulator;
import net.llamaslayers.minecraft.banana.populators.ChestPopulator;
import net.llamaslayers.minecraft.banana.populators.ExplosionPopulator;
import net.llamaslayers.minecraft.banana.populators.LanternPopulator;
import net.llamaslayers.minecraft.banana.populators.PoolPopulator;
import net.llamaslayers.minecraft.banana.populators.RuinsPopulator;
import net.llamaslayers.minecraft.banana.populators.TorchPopulator;
import net.llamaslayers.minecraft.banana.populators.WebPopulator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class DungeonGenerator extends ChunkGenerator {
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return Arrays.asList(new DungeonPopulator(), new PoolPopulator(),
				new ExplosionPopulator(), new RuinsPopulator(),
				new LanternPopulator(), new ChestPopulator(),
				new WebPopulator(), new TorchPopulator(),
				/* CeilingPopulator MUST be last */new CeilingPopulator());
	}

	/**
	 * This needs to be set to return true to override minecraft's default
	 * behaviour.
	 */
	@Override
	public boolean canSpawn(World world, int x, int z) {
		return true;
	}

	@Override
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world, 8, 20, 8);
	}

	/**
	 * This converts relative chunk locations to bytes that can be written to
	 * the chunk
	 */
	public int xyzToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}

	@Override
	public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
		byte[] result = new byte[32768];

		// This will set the floor of each chunk to stone.
		for (int y = 29; y > 0; y--) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					result[xyzToByte(x, y, z)] = (byte) Material.STONE.getId();
				}
			}
		}
		int xr = (rand.nextInt(3) - 1) * 15;
		int zr = (rand.nextInt(3) - 1) * 15;

		// Hollow out the floor and add walls
		for (int y = 18 + rand.nextInt(3); y < 30; y++) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					if ((x == 0 || x == 15) && (z == 0 || z == 15)) {
						result[xyzToByte(x, y, z)] = (byte) Material.COBBLESTONE
								.getId();
					} else if (xr == x) {
						result[xyzToByte(x, y, z)] = (byte) Material.COBBLESTONE
								.getId();
					} else if (zr == z) {
						result[xyzToByte(x, y, z)] = (byte) Material.COBBLESTONE
								.getId();
					} else {
						result[xyzToByte(x, y, z)] = (byte) Material.AIR
								.getId();
					}
				}
			}
		}

		// Set the lowest layer to bedrock
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				result[xyzToByte(x, 0, z)] = (byte) Material.BEDROCK.getId();
			}
		}

		return result;
	}
}