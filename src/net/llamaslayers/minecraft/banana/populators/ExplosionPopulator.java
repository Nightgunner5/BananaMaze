package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.generator.BlockPopulator;

public class ExplosionPopulator extends BlockPopulator {
	public static final int EXPLOSION_CHANCE = 50;
	public static final int BIG_EXPLOSION_CHANCE = 25;
	public static final int HUGE_EXPLOSION_CHANCE = 2;

	@Override
	public void populate(World world, Random random, Chunk source) {
		if (random.nextInt(100) < EXPLOSION_CHANCE) {
			float power = 2.0f;
			if (random.nextInt(100) < BIG_EXPLOSION_CHANCE) {
				power = 4.0f;
				if (random.nextInt(100) < HUGE_EXPLOSION_CHANCE) {
					for (int x = -3; x <= 3; x++) {
						for (int z = -3; z <= 3; z++) {
							if (x != 0 || z != 0) {
								world.loadChunk(x + source.getX(),
										z + source.getZ());
							}
						}
					}
					Location location = source.getBlock(8, 40, 8).getLocation();
					int tntCount = random.nextInt(10) + 10;
					while (tntCount-- > 0) {
						TNTPrimed tnt = world.spawn(location, TNTPrimed.class);
						tnt.setIsIncendiary(false);
						tnt.setFuseTicks(random.nextInt(25) + 2);
					}
					for (int x = -3; x <= 3; x++) {
						for (int z = -3; z <= 3; z++) {
							if (x != 0 || z != 0) {
								world.unloadChunkRequest(x + source.getX(), z
										+ source.getZ());
							}
						}
					}
					return;
				}
			}
			double x = random.nextDouble() * 16 + source.getX() * 16;
			double z = random.nextDouble() * 16 + source.getZ() * 16;
			world.createExplosion(x,
					world.getHighestBlockYAt((int) x, (int) z), z, power);
		}
	}
}
