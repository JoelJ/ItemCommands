package com.joelj.minecraft.itemcommands;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * User: Joel Johnson
 * Date: 1/12/13
 * Time: 1:41 PM
 */
public class ItemCommands extends JavaPlugin {
	private File customConfigFile = null;

	@Override
	public void onEnable() {
		getLogger().info("[ItemCommands] enabled");

		setupConfiguration();

		getServer().getPluginManager().registerEvents(new ItemCommandListener(this.getLogger(), this.getConfig()), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("[ItemCommands] disabled");
	}

	private void setupConfiguration() {
		getLogger().info("Loading Config");
		try {
			if (customConfigFile == null) {
				File dataFolder = getDataFolder();
				if(!dataFolder.exists() && !dataFolder.mkdirs()) {
					getLogger().severe("Couldn't create " + dataFolder.getAbsolutePath());
				}
				customConfigFile = new File(dataFolder, "config.yml");
			}

			boolean fileExists = customConfigFile.exists();
			if (!fileExists) {
				getLogger().info("Creating default configuration file...");
				if(!customConfigFile.createNewFile()) { //double checking config file exists
					getLogger().severe("Could not create " + customConfigFile.getAbsolutePath());
				}
			}

			getConfig().options().configuration().load(customConfigFile);
			saveConfig();
		} catch (IOException e) {
			getLogger().severe(e.getMessage());
		} catch (InvalidConfigurationException e) {
			getLogger().severe(e.getMessage());
		}
	}
}
