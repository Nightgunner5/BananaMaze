package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class LanternPopulator extends BlockPopulator {
	public static final int CHANCE_OF_1 = 70;
	public static final int CHANCE_OF_2 = 30;
	public static final int MIN_HEIGHT = 0;
	public static final int MAX_HEIGHT = 3;
	public static final int FLOOR_HEIGHT = 20;
	public static final int LIT_CHANCE = 60;

	@Override
	public void populate(World world, Random random, Chunk source) {
		ChunkSnapshot snapshot = source.getChunkSnapshot();

		if (random.nextInt(100) < CHANCE_OF_1) {
			{
				int x, z, x2, z2;
				if (random.nextBoolean()) {
					x = random.nextBoolean() ? 0 : 15;
					x2 = x == 0 ? 1 : 14;
					z2 = z = random.nextInt(14) + 1;
				} else {
					x2 = x = random.nextInt(14) + 1;
					z = random.nextBoolean() ? 0 : 15;
					z2 = z == 0 ? 1 : 14;
				}
				if (snapshot.getHighestBlockYAt(x, z) > FLOOR_HEIGHT
						+ MAX_HEIGHT
						&& snapshot.getHighestBlockYAt(x2, z2) <= FLOOR_HEIGHT
								+ MIN_HEIGHT) {
					Material type = random.nextInt(100) < LIT_CHANCE ? Material.JACK_O_LANTERN
							: Material.PUMPKIN;
					source.getBlock(
							x,
							snapshot.getHighestBlockYAt(x2, z2)
									+ random.nextInt(MAX_HEIGHT - MIN_HEIGHT
											+ 1) + MIN_HEIGHT, z)
							.setTypeIdAndData(type.getId(),
									getData(x, z, x2, z2, type), true);
				}
			}

			if (random.nextInt(100) < CHANCE_OF_2) {
				int x, z, x2, z2;
				if (random.nextBoolean()) {
					x = random.nextBoolean() ? 0 : 15;
					x2 = x == 0 ? 1 : 14;
					z2 = z = random.nextInt(14) + 1;
				} else {
					x2 = x = random.nextInt(14) + 1;
					z = random.nextBoolean() ? 0 : 15;
					z2 = z == 0 ? 1 : 14;
				}
				if (snapshot.getHighestBlockYAt(x, z) > FLOOR_HEIGHT
						+ MAX_HEIGHT
						&& snapshot.getHighestBlockYAt(x2, z2) <= FLOOR_HEIGHT
								+ MIN_HEIGHT) {
					Material type = random.nextInt(100) < LIT_CHANCE ? Material.JACK_O_LANTERN
							: Material.PUMPKIN;
					source.getBlock(
							x,
							snapshot.getHighestBlockYAt(x2, z2)
									+ random.nextInt(MAX_HEIGHT - MIN_HEIGHT
											+ 1) + MIN_HEIGHT, z)
							.setTypeIdAndData(type.getId(),
									getData(x, z, x2, z2, type), true);
				}
			}
		}
	}

	private byte getData(int x, int z, int x2, int z2, Material type) {
		if (x == x2) {
			if (z < z2)
				return (byte) (type == Material.JACK_O_LANTERN ? 0 : 2);
			return (byte) (type == Material.JACK_O_LANTERN ? 2 : 0);
		}
		if (x < x2)
			return (byte) (type == Material.JACK_O_LANTERN ? 3 : 1);
		return (byte) (type == Material.JACK_O_LANTERN ? 1 : 3);
	}
}
