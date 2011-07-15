package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class MossPopulator extends BlockPopulator {
	public static final int ITERATIONS = 30;
	public static final int CHANCE = 40;
	public static final int MIN_Y = 15;
	public static final int MAX_Y = 29;
	public static final Material OLD_MATERIAL = Material.COBBLESTONE;
	public static final Material NEW_MATERIAL = Material.MOSSY_COBBLESTONE;

	@Override
	public void populate(World world, Random random, Chunk source) {
		for (int i = 0; i < ITERATIONS; i++) {
			if (random.nextInt(100) < CHANCE) {
				Block block = source.getBlock(random.nextInt(16),
						random.nextInt(MAX_Y - MIN_Y + 1) + MIN_Y,
						random.nextInt(16));
				if (block.getType() == OLD_MATERIAL) {
					block.setType(NEW_MATERIAL);
				}
			}
		}
	}
}
