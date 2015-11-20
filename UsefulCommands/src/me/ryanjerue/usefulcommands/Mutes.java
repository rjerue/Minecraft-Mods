package me.ryanjerue.usefulcommands;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mutes extends UsefulCommands
{
	public UsefulCommands plugin;
	Mutes(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("mute"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.mute.mutePlayer"))
				{
					if(args.length == 1)
					{
						String target = args[0];
					  	mutePlayer(sender, target);
					  	return true;
					}
					else if(args.length > 1)
					{
						String target = args[0];
						String reason = "";
						for(String part: args)
						{
							reason = reason + part + " ";
						}
						mutePlayer(sender, target, reason);
						return true;
					}
				}
			}
			else
			{
				if(args.length ==1)
				{
					String target = args[0];
				  	mutePlayer(sender, target);
				  	return true;
				}
				else if(args.length > 1)
				{
					String target = args[0];
					String reason = "";
					for(String part: args)
					{
						reason = reason + part + " ";
					}
					mutePlayer(sender, target, reason);
					return true;
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("unmute"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.mute.unMutePlayer") && args.length == 1)
				{
					String target = args[0];
					unMutePlayer(sender, target);
					return true;
				}
			}
			else if (args.length == 1)
			{
				String target = args[0];
				unMutePlayer(sender, target);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("isplayermuted"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.mute.checkMute") && args.length == 1)
				{
					String target = args[0];
				  	try
				  	{
				  		Player targetPlayer = Bukkit.getServer().getPlayer(target);
				  		target = targetPlayer.getName();
				  	}
				  	catch(Exception e)
				  	{
				  	}

				  	if(checkMute(target))
				  	{
				  		player.sendMessage(ChatColor.GOLD + "This player is muted!");
				  	}
				  	else
				  	{
				  		player.sendMessage(ChatColor.GOLD + "This player is not muted!");
				  	}
				  	return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
			  	try
			  	{
			  		Player targetPlayer = Bukkit.getServer().getPlayer(target);
			  		target = targetPlayer.getName();
			  	}
			  	catch(Exception e)
			  	{
			  	}

			  	if(checkMute(target))
			  	{
			  		sender.sendMessage(ChatColor.GOLD + "This player is muted!");
			  	}
			  	else
			  	{
			  		sender.sendMessage(ChatColor.GOLD + "This player is not muted!");
			  	}
			  	return true;
			}
		}

		return false;
	}
	public void mutePlayer(CommandSender sender, String target)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception e)
		{
		}

		if(checkMute(target))
		{
			sender.sendMessage(ChatColor.GREEN + "This player is already muted!");
		}
		else
		{
			if (!plugin.getConfig().getConfigurationSection("Mutes").contains(target))
			{
				plugin.getConfig().getConfigurationSection("Mutes").createSection(target);
			}

			plugin.getConfig().set("Mutes." + target, true);

			try
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(target);
				targetPlayer.sendMessage(ChatColor.RED + "You have been muted!");
			}
			catch(Exception e)
			{
			}

			sender.sendMessage(ChatColor.RED + "The player has been muted!");

			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				log.info(ChatColor.RED + target + " has been muted by " + player.getDisplayName());
			}
			plugin.saveConfig();
		}
	}
	public void mutePlayer(CommandSender sender, String target, String reason)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception e)
		{
		}

		if(checkMute(target))
		{
			sender.sendMessage(ChatColor.GREEN + "This player is already muted!");
		}
		else
		{
			if (!plugin.getConfig().getConfigurationSection("Mutes").contains(target))
			{
				plugin.getConfig().getConfigurationSection("Mutes").createSection(target);
			}

			plugin.getConfig().set("Mutes." + target, true);

			try
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(target);
				targetPlayer.sendMessage(ChatColor.RED + "You have been muted for " + reason + "!");
			}
			catch(Exception e)
			{
			}
			sender.sendMessage(ChatColor.RED + "The player has been muted for " + reason + "!");
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				log.info(ChatColor.RED + target + "has been muted by " + player.getDisplayName() + "for " + reason);
			}
			plugin.saveConfig();
		}
	}
	public void unMutePlayer(CommandSender sender, String target)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception e)
		{
		}
		if(checkMute(target))
		{
			plugin.getConfig().set("Mutes." + target, false);
			try
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(target);
				targetPlayer.sendMessage(ChatColor.RED + "You have been unmuted!");
			}
			catch(Exception e)
			{
			}
			sender.sendMessage(ChatColor.GREEN + "The player has been unmuted!");
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				log.info(ChatColor.RED + target + "has been unmuted by " + player.getDisplayName());
			}
			plugin.saveConfig();
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "This player was not muted!");
		}
	}
	public boolean checkMute(String target)
	{
		boolean muted = plugin.getConfig().getConfigurationSection("Mutes").getBoolean(target);
		return muted;
	}
	public void wipeMutes()
	{
		Map<String, Object> map = plugin.getConfig().getConfigurationSection("Mutes").getValues(false);

		for (Entry<String, Object> map1 : map.entrySet())
		{
			map1.setValue(false);
		}
		plugin.saveConfig();
	}
}
