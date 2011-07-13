package com.ubempire.dungeon;

import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonPlugin extends JavaPlugin {
	private static final DungeonGenerator dg = new DungeonGenerator();
	private boolean inBukkitYML = false;

	@Override
	public void onDisable() {
		getServer().unloadWorld("dungeon", true);
	}

	@Override
	public void onEnable() {
		getServer().getScheduler().scheduleSyncDelayedTask(this,
				new Runnable() {
					public void run() {
						if (!inBukkitYML) {
							getServer().createWorld("dungeon",
									Environment.NORMAL, dg);
						}
					}
				});
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		inBukkitYML = true;
		return dg;
	}
}
