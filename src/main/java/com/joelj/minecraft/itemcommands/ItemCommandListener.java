package com.joelj.minecraft.itemcommands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Joel Johnson
 * Date: 1/12/13
 * Time: 1:45 PM
 */
public class ItemCommandListener implements Listener {
	private final Map<Integer, Map<String, List<Command>>> itemCache = new HashMap<Integer, Map<String, List<Command>>>();
	private final Logger logger;

	public ItemCommandListener(Logger logger, FileConfiguration fileConfiguration) {
		if(logger == null) {
			throw new NullPointerException("logger");
		}
		if(fileConfiguration == null) {
			throw new NullPointerException("fileConfiguration");
		}
		this.logger = logger;
		populateCache(itemCache, fileConfiguration, logger);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player == null) {
			logger.warning("Player was not populated. I didn't think this would be possible. Skipping event.");
			return;
		}

		ItemStack itemInHand = player.getItemInHand();
		Action action = event.getAction();
		if(action == null || itemInHand == null) {
			logger.warning("Either the action ("+action+") or the item ("+itemInHand+") was null. Skipping.");
			return;
		}

		// This is useful for debugging, but it would be too much log spam on a live server.
		//   Do the if statement so we don't have to do the string concatenation unless we actually want to log it.
		//   Otherwise the server can spend a lot of time concatenating strings that are never used.
		if(logger.isLoggable(Level.FINE)) {
			logger.fine(player.getName() + " " + action.toString() + " with item " + itemInHand.getTypeId());
		}

		Collection<Command> commands = getCommandFor(itemInHand.getTypeId(), action);
		if(commands != null) {
			for (Command command : commands) {
				if(command.getPermission() == null || command.getPermission().isEmpty() || player.hasPermission(command.getPermission())) {
					for (String commandToExecute : command.getCommands()) {
						if(!player.performCommand(commandToExecute)) {
							logger.warning(player.getName() + " tried to execute '" + commandToExecute + "' but it failed, perhaps the command requires additional permissions?");
						}
					}
				} else {
					if(logger.isLoggable(Level.FINE)) {
						logger.fine(player.getName() + " tried to execute " + command);
					}
				}
			}
		}
	}

	Collection<Command> getCommandFor(int itemCode, Action itemAction) {
		Map<String, List<Command>> itemData = itemCache.get(itemCode);
		if(itemData != null && itemAction != null) {
			return itemData.get(itemAction.toString());
		}
		return null;
	}

	private void populateCache(Map<Integer, Map<String, List<Command>>> itemCache, FileConfiguration config, Logger logger) {
		logger.info("Loading '" + config.getName() + "'");
		itemCache.clear();

		ConfigurationSection itemsConfig = config.getConfigurationSection("items");
		if(itemsConfig == null) {
			return;
		}

		Set<String> keys = itemsConfig.getKeys(false);

		for (String key : keys) {
			ConfigurationSection configurationSection = itemsConfig.getConfigurationSection(key);
			int item = configurationSection.getInt("item");
			Map<String, List<Command>> itemData = itemCache.get(item);
			if(itemData == null) {
				logger.info("creating map for item " + item);
				itemData = new HashMap<String, List<Command>>();
				itemCache.put(item, itemData);
			}

			List<String> actionStr = configurationSection.getStringList("actions");
			if(actionStr == null || actionStr.isEmpty()) {
				logger.info("no action defined for " + key + " using default: LEFT_CLICK_AIR and LEFT_CLICK_BLOCK");
				actionStr = Arrays.asList(Action.LEFT_CLICK_AIR.toString(), Action.LEFT_CLICK_BLOCK.toString());
			}

			List<String> commands = configurationSection.getStringList("commands");
			String permission = configurationSection.getString("permission");

			for (String action : actionStr) {
				List<Command> commandsList = itemData.get(action);
				if(commandsList == null) {
					logger.info("creating map for item " + item + " and action " + action);
					commandsList = new LinkedList<Command>();
					itemData.put(action, commandsList);
				}
				Command command = new Command(commands, permission);
				commandsList.add(command);
				logger.info("Added command for '" + item + "' with action '" + action + "': " + command);
			}
		}
	}
}
