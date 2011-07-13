package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

public class CeilingPopulator extends BlockPopulator {
	public static final int OCTAVES = 3;
	public static final double FREQUENCY = 0.8;
	public static final double AMPLITUDE = 0.6;
	public static final double THRESHOLD = -0.95;
	public static final Material CEILING_MATERIAL = Material.STONE;
	public static final Material BROKEN_CEILING_MATERIAL = Material.COBBLESTONE;

	@Override
	public void populate(World world, Random random, Chunk source) {
		NoiseGenerator noise = new SimplexNoiseGenerator(world);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				if (noise.noise(x + source.getX() << 4, z + source.getZ() << 4,
						OCTAVES, FREQUENCY, AMPLITUDE) > THRESHOLD) {
					source.getBlock(x, 30, z).setType(CEILING_MATERIAL);
				} else if (noise.noise(x + source.getX() << 4,
						z + source.getZ() << 4, OCTAVES, FREQUENCY, AMPLITUDE) > (THRESHOLD + 1) * 2 - 1) {
					source.getBlock(x, 30, z).setType(BROKEN_CEILING_MATERIAL);
				}
			}
		}
	}
}
