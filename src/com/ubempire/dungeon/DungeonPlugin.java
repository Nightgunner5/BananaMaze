package com.ubempire.dungeon;

import java.io.File;
import java.util.List;

import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

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
						{
							// CraftBukkit-specific
							Configuration bukkitYML = new Configuration(
									new File("bukkit.yml"));
							bukkitYML.load();
							List<String> worlds = bukkitYML.getKeys("worlds");
							if (worlds != null) {
								for (String world : worlds) {
									if (bukkitYML.getString(
											"worlds." + world + ".generator",
											".").split(":")[0]
											.equals(getDescription().getName())
											&& getServer().getWorld(world) == null) {
										getServer().createWorld(world,
												Environment.NORMAL, dg);
										inBukkitYML = true;
									}
								}
							}
						}

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
