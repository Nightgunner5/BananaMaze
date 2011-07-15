package net.llamaslayers.minecraft.banana.populators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class WebPopulator extends BlockPopulator {
	public static final int ITERATIONS = 7;
	public static final int CHANCE = 40;
	public static final int CORNER_CHANCE = 20;
	public static final int CEILING_HEIGHT = 30;
	public static final Material WEB_MATERIAL = Material.WEB;
	public static final Set<Material> ALLOWED_BASE_BLOCK = new HashSet<Material>(
			Arrays.asList(Material.STONE, Material.COBBLESTONE, Material.WEB,
					Material.JACK_O_LANTERN, Material.PUMPKIN));

	@Override
	public void populate(World world, Random random, Chunk source) {
		ChunkSnapshot snapshot;

		for (int i = 0; i < ITERATIONS; i++) {
			if (random.nextInt(100) < CHANCE) {
				snapshot = source.getChunkSnapshot();
				if (random.nextInt(100) < CORNER_CHANCE) {
					int x, z;
					switch (random.nextInt(4)) {
					case 0:
						x = 1;
						z = 1;
						break;
					case 1:
						x = 14;
						z = 1;
						break;
					case 2:
						x = 1;
						z = 14;
						break;
					case 3:
						x = 14;
						z = 14;
						break;
					default:
						continue; // wtf?
					}
					source.getBlock(x, CEILING_HEIGHT, z).setType(WEB_MATERIAL);
				} else {
					int x = random.nextInt(14) + 1;
					int z = random.nextInt(14) + 1;
					int y = snapshot.getHighestBlockYAt(x, z);

					if (ALLOWED_BASE_BLOCK.contains(source
							.getBlock(x, y - 1, z).getType())) {
						source.getBlock(x, y, z).setType(WEB_MATERIAL);
					}
				}
			}
		}
	}
}
