package net.llamaslayers.minecraft.banana.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;

public class ChestPopulator extends BlockPopulator {
	public static final int CHANCE_OF_CHEST = 10;
	public static final Material CHEST_MATERIAL = Material.CHEST;
	public static final Material FLOOR_MATERIAL = Material.STONE;
	public static final int[] CHANCES = new int[] { 5, 40, 70, 99, 100 };
	public static final Material[] REWARD_MATERIALS = new Material[] {
			Material.WOOD_PICKAXE, Material.WOOD_SWORD, Material.STONE_PICKAXE,
			Material.STONE_SWORD, Material.GOLD_PICKAXE, Material.GOLD_SWORD,
			Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE,
			Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS,
			Material.IRON_BOOTS, Material.IRON_CHESTPLATE,
			Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.ARROW,
			Material.STRING, Material.BONE, Material.APPLE, Material.COOKIE,
			Material.RAW_FISH, Material.COOKED_FISH, Material.PORK,
			Material.GRILLED_PORK };
	public static final Material[] RARE_REWARDS = new Material[] {
			Material.IRON_PICKAXE, Material.IRON_SWORD,
			Material.DIAMOND_PICKAXE, Material.DIAMOND_SWORD,
			Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE,
			Material.GOLD_HELMET, Material.GOLD_LEGGINGS,
			Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE,
			Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS,
			Material.GOLDEN_APPLE, Material.BOW, Material.FISHING_ROD };
	public static final int RARE_REWARD_CHANCE = 20;

	@Override
	public void populate(World world, Random random, Chunk source) {
		if (random.nextInt(100) < CHANCE_OF_CHEST) {
			Block block = world.getHighestBlockAt(
					source.getX() << 4 + 1 + random.nextInt(14),
					source.getZ() << 4 + 1 + random.nextInt(14));
			if (block.getRelative(BlockFace.DOWN).getType() == FLOOR_MATERIAL) {
				block.setType(CHEST_MATERIAL);
				Chest chest = (Chest) block.getState();

				int count = 0;
				for (int chance : CHANCES) {
					if (random.nextInt(100) < chance) {
						break;
					}
					count++;
				}

				for (; count > 0; count--) {
					Material[] list = random.nextInt(100) < RARE_REWARD_CHANCE ? RARE_REWARDS
							: REWARD_MATERIALS;
					chest.getInventory().setItem(
							random.nextInt(chest.getInventory().getSize()),
							new ItemStack(list[random.nextInt(list.length)]));
				}

				chest.update();
			}
		}
	}
}
