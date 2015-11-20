package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Seen extends UsefulCommands
{
	public UsefulCommands plugin;
	Seen(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("firstseen"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.seen.firstseen") && args.length == 1)
				{
					String target = args[0];
					firstSeen(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				firstSeen(sender, target);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("seen"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.seen.seen") && args.length == 1)
				{
					String target = args[0];
					lastSeen(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				lastSeen(sender, target);
				return true;
			}
		}
		return false;
	}
	public void firstSeen(CommandSender sender, String target)
	{
		OfflinePlayer player = Bukkit.getOfflinePlayer(target);
		Long joined = player.getFirstPlayed();
		if (longToNormal(joined).equals(""))
		{
			sender.sendMessage(ChatColor.GREEN + "This player has never been on the server before!");
		}
		else
		{
			sender.sendMessage("This still needs to be fixed.");
			sender.sendMessage(ChatColor.GREEN + "This player joined " + longToNormal(joined) + "ago.");
		}
	}
	public void lastSeen(CommandSender sender, String target)
	{
		try
		{
			Player player = Bukkit.getServer().getPlayerExact(target);

			if (player.isOnline())
			{
				sender.sendMessage(ChatColor.RED + "This player is online!");
			}
		}
		catch (Exception e)
		{
			OfflinePlayer player = Bukkit.getOfflinePlayer(target);
			Long seen = player.getLastPlayed();

			if (longToNormal(seen).equals(""))
			{
				sender.sendMessage(ChatColor.GREEN + "This player has never been seen!");
			}
			else
			{
				sender.sendMessage(ChatColor.GREEN + "This player was last seen " + longToNormal(seen) + "ago.");
			}
		}
	}
	public String longToNormal(Long seen)
	{
		if(seen.longValue() == 0L)
		{
			return "";
		}

		Long time = Long.valueOf((System.currentTimeMillis() - seen.longValue()) / 1000L);
		int totalsecondsi = (int)(time.longValue() / 1L);
		int minutesi = totalsecondsi / 60 % 60;
		int hoursi = totalsecondsi / 3600 % 24;
		int daysi = totalsecondsi / 86400;
		int secondsi = totalsecondsi - ((daysi * 24 + hoursi) * 60 + minutesi) * 60;
		String seconds = ""; String minutes = ""; String hours = ""; String days = "";

		if(daysi != 0)
		{
			if(daysi == 1)
			{
				days = days + daysi + " day ";
			}
			else
			{
				days = days + daysi + " days ";
			}
		}
		if(hoursi != 0)
		{
			if(hoursi == 1)
			{
				hours = hours + hoursi + " hour ";
			}
			else
			{
				hours = hours + hoursi + " hours ";
			}
		}
		if(minutesi != 0)
		{
			if(minutesi == 1)
			{
				minutes = minutes + minutesi + " minute ";
			}
			else
			{
				minutes = minutes + minutesi + " minutes ";
			}
		}
		if(secondsi != 0)
		{
			if(secondsi == 1)
			{
				seconds = seconds + secondsi + " second ";
			}
			else
			{
				seconds = seconds + secondsi + " seconds ";
			}
		}
		return days + hours + minutes + seconds;
	}
}
