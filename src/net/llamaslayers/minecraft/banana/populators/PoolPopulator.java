package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PoolPopulator extends BlockPopulator {
	public static final int NO_LAVA_NEAR_SPAWN_RADIUS = 4; // Chunks
	public static final int POOL_CHANCE = 20;
	public static final int LAVA_CHANCE = 10;

	@Override
	public void populate(World world, Random random, Chunk source) {
		boolean noLava = false;
		if (Math.abs(source.getX()) < NO_LAVA_NEAR_SPAWN_RADIUS
				|| Math.abs(source.getZ()) < NO_LAVA_NEAR_SPAWN_RADIUS) {
			noLava = true;
		}

		ChunkSnapshot snapshot = source.getChunkSnapshot();

		if (random.nextInt(100) < POOL_CHANCE) {
			Material poolType = Material.WATER;

			if (!noLava && random.nextInt(100) < LAVA_CHANCE) {
				poolType = Material.LAVA;
			}

			int x = random.nextInt(14) + 1;
			int z = random.nextInt(14) + 1;
			int w = random.nextInt(6);
			int l = random.nextInt(6);

			for (int i = Math.max(x - w / 2, 1); i < Math
					.min(x - w / 2 + w, 14); i++) {
				for (int j = Math.max(z - l / 2, 1); j < Math.min(
						z - l / 2 + l, 14); j++) {
					source.getBlock(i, snapshot.getHighestBlockYAt(i, j) - 1, j)
							.setType(poolType);
				}
			}
		}
	}
}
