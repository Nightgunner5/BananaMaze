package com.ubempire.dungeon;

import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonPlugin extends JavaPlugin {
	private static final DungeonGenerator dg = new DungeonGenerator();

	@Override
	public void onDisable() {
		getServer().unloadWorld("dungeon", true);
	}

	@Override
	public void onEnable() {
		getServer().createWorld("dungeon", Environment.NORMAL, dg);
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return dg;
	}
}
