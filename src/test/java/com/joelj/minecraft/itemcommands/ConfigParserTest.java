package com.joelj.minecraft.itemcommands;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.block.Action;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * These tests run great in IntelliJ just can't figure them out in Maven
 * User: Joel Johnson
 * Date: 1/13/13
 * Time: 2:33 PM
 */
public class ConfigParserTest {
//	@Test
//	public void testParsingSimpleFile() {
//		URL resource = this.getClass().getClassLoader().getResource("example-config.yml");
//		File file = new File(resource.getPath());
//		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
//
//		ItemCommandListener itemCommandListener = new ItemCommandListener(Logger.getLogger(this.getClass().getCanonicalName()), yamlConfiguration);
//		Collection<Command> commands = itemCommandListener.getCommandFor(345, Action.LEFT_CLICK_AIR);
//		Assert.assertEquals(1, commands.size(), "Should only return one item");
//
//		Command next = commands.iterator().next();
//		List<String> commandsStr = next.getCommands();
//		Assert.assertEquals(2, commandsStr.size(), "Should return two items");
//
//		String permission = next.getPermission();
//		Assert.assertEquals(permission, "some.custom.permission", "should have the permission specified in the yml.");
//	}
//
//	@Test
//	public void testParsingDuplicates() {
//		URL resource = this.getClass().getClassLoader().getResource("example-config.yml");
//		File file = new File(resource.getPath());
//		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
//
//		ItemCommandListener itemCommandListener = new ItemCommandListener(Logger.getLogger(this.getClass().getCanonicalName()), yamlConfiguration);
//		Collection<Command> duplicate1 = itemCommandListener.getCommandFor(1, Action.RIGHT_CLICK_AIR);
//		Collection<Command> duplicate2 = itemCommandListener.getCommandFor(1, Action.RIGHT_CLICK_BLOCK);
//
//		Command duplicateCommand1 = duplicate1.iterator().next();
//		Command duplicateCommand2 = duplicate2.iterator().next();
//
//		//Yeah, I was too lazy to do the test so I'm abusing the toString a little.
//		Assert.assertEquals(duplicateCommand1.toString(), "Command{commands=[command 1], permission='some.custom.permission1'}");
//		Assert.assertEquals(duplicateCommand2.toString(), "Command{commands=[command 2], permission='some.custom.permission2'}");
//	}
}
