package com.joelj.minecraft.itemcommands;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Joel Johnson
 * Date: 1/12/13
 * Time: 2:13 PM
 */
public class Command {
	private final List<String> commands;
	private final String permission;

	public Command(List<String> commands, String permission) {
		this.commands = commands;
		this.permission = permission;
	}

	public List<String> getCommands() {
		return commands;
	}

	public String getPermission() {
		return permission;
	}

	@Override
	public String toString() {
		return "Command{" +
				"commands=" + Arrays.toString(commands.toArray()) +
				", permission='" + permission + '\'' +
				'}';
	}
}
