package me.ryanjerue.usefulcommands;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nicks extends UsefulCommands
{
	public UsefulCommands plugin;
	Nicks(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("nick"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(args.length == 1 && player.hasPermission("olympus.usefulCommands.nickNames.nickSelf"))
				{
					String nick = args[0];
					nickSelf(player, nick);
					return true;
				}
				if(args.length == 2 && player.hasPermission("olympus.usefulCommands.nickNames.nickOther"))
				{
					if(args[1].equalsIgnoreCase("-reset"))
					{
						String target = args[0];
						nickReset(sender, target);
					}
					else
					{
						String target = args[0];
						String nick = args[1];
						nickOther(sender, target, nick);
					}
					return true;
				}
			}
			else
			{
				if(args.length == 1 )
				{
					sender.sendMessage(ChatColor.RED + "You must be a player to use this comand!");
					return true;
				}
				if(args.length == 2)
				{
					if(args[1].equalsIgnoreCase("-reset"))
					{
						String target = args[0];
						nickReset(sender, target);
					}
					else
					{
						String target = args[0];
						String nick = args[1];
						nickOther(sender, target, nick);
					}
					return true;
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("realname"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.nickNames.nickReal") && args.length == 1)
				{
					String target = args[0];
					realName(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				realName(sender, target);
				return true;
			}

		}
		return false;
	}
	public void nickSelf(Player sender, String desiredName)
	{
		String senderName = sender.getName();

		if (!plugin.getConfig().getConfigurationSection("NickNames").contains(senderName))
		{
			plugin.getConfig().getConfigurationSection("NickNames").createSection(senderName);
		}

		plugin.getConfig().set("NickNames." + senderName, desiredName);
		sender.setDisplayName(plugin.getConfig().getConfigurationSection("NickNames").getString(senderName));
		sender.sendMessage(ChatColor.GREEN + "Your nickname has been changed to " + desiredName + "!");

		plugin.saveConfig();
	}
	public void nickOther(CommandSender sender, String playerName, String desiredName)
	{
		try
		{
			Player player = Bukkit.getServer().getPlayer(playerName);
			playerName = player.getName();
			String place = "NickNames." + playerName;

			if(!plugin.getConfig().getConfigurationSection("NickNames").contains(playerName))
			{
				plugin.getConfig().getConfigurationSection("NickNames").createSection(playerName);
			}

			plugin.getConfig().set(place, desiredName);
			player.setDisplayName(plugin.getConfig().getConfigurationSection("NickNames").getString(playerName));

			player.sendMessage(ChatColor.GREEN + "Your nickname has been changed to " + desiredName + "!");
			sender.sendMessage(ChatColor.GREEN + "The player " + playerName + " has had their nickname changed to " + desiredName + ".");
		}
		catch (Exception e)
		{
			for(OfflinePlayer player1 : Bukkit.getServer().getOfflinePlayers())
			{
				if(player1.getName().equalsIgnoreCase(playerName))
				{
					playerName = player1.getName();
				}
			}

			String place = "NickNames." + playerName;

			if(!plugin.getConfig().getConfigurationSection("NickNames").contains(playerName))
			{
				plugin.getConfig().getConfigurationSection("NickNames").createSection(playerName);
			}

			plugin.getConfig().set(place, desiredName);
			sender.sendMessage(ChatColor.GREEN + "This player is not online, but their nickname will change when they login.  Be sure that their name was spelled correctly.");
		}
		plugin.saveConfig();
	}
	public void realName(CommandSender sender, String targetName)
	{
		Map<String, Object> map = plugin.getConfig().getConfigurationSection("NickNames").getValues(false);
		boolean found = false;

		for(Entry<String, Object> map1 : map.entrySet())
		{
			if (targetName.equalsIgnoreCase((String)map1.getValue()))
			{
				sender.sendMessage(ChatColor.GREEN + "This player's real name is " + (String)map1.getKey() + ".");
				found = true;
			}
		}
		if(!found)
		{
			sender.sendMessage(ChatColor.GREEN + "This nickname was not found! Did you spell it correctly?");
		}
	}
	public void nickReset(CommandSender sender, String targetName)
	{
		String playerName = "";
		Map<String, Object> map = plugin.getConfig().getConfigurationSection("NickNames").getValues(false);
		boolean found = false;
		for(Entry<String, Object> map1 : map.entrySet())
		{
			if(targetName.equalsIgnoreCase((String)map1.getKey()))
			{
				playerName = (String)map1.getKey();
				plugin.getConfig().set("NickNames." + playerName, "");
				found = true;
				plugin.saveConfig();
				try
				{
					Player player1 = Bukkit.getServer().getPlayerExact(playerName);

					player1.setDisplayName(playerName);

					player1.sendMessage(ChatColor.GREEN + "Your nickname has been reset!");
				}
				catch (Exception e)
				{
				}
			}
		}
		if(!found)
		{
			sender.sendMessage(ChatColor.GREEN + "This player was not found! Did you spell their name correctly?");
		}
		else
		{
			sender.sendMessage(ChatColor.GREEN + "The player " + playerName + " has had their nickname reset!");
		}
	}
}
