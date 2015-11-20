package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report extends UsefulCommands
{
	public UsefulCommands plugin;
	Report(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("report"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.report.report") && args.length > 1)
				{
					String message = "";

					for(int i = 1; i < args.length; i++)
					{
						message = message + args[i] + " ";
					}

					String group = args[0];

					message = message.replaceAll("\"", "\\\"");

					if(group.equalsIgnoreCase("staff"))
					{
						reportStaff(sender, message);
						return true;
					}
					if(group.equalsIgnoreCase("admin"))
					{
						reportadmin(sender, message);
						return true;
					}
					if(group.equalsIgnoreCase("uber"))
					{
						reportUber(sender, message);
						return true;
					}
				}
			}
			else if(args.length > 1)
			{
				String message = "";

				for(int i = 1; i < args.length; i++)
				{
					message = message + args[i] + " ";
				}

				String group = args[0];

				if(group.equalsIgnoreCase("staff"))
				{
					reportStaff(sender, message);
					return true;
				}
				if(group.equalsIgnoreCase("admin"))
				{
					reportadmin(sender, message);
					return true;
				}
				if(group.equalsIgnoreCase("uber"))
				{
					reportUber(sender, message);
					return true;
				}
			}
		}
		return false;
	}
	public void reportStaff(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.DARK_GREEN + "Your report has been sent to the staff!");
		String name = "";
		if(sender instanceof Player)
		{
			name = ((Player)sender).getDisplayName();
		}
		else
		{
			name = ChatColor.RED + sender.getName();
		}

		for(Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			if(player1.hasPermission("olympus.usefulCommands.report.see"))
			{
				player1.getWorld().playEffect(player1.getLocation(), Effect.GHAST_SHRIEK, 0, 1);
				player1.sendMessage(ChatColor.GOLD + "[StaffReport from " + name + ChatColor.GOLD + "] " + message);
			}
		}
		log.info(ChatColor.GOLD + "[StaffReport from " + name + ChatColor.GOLD + "] " + message + ChatColor.RESET);
	}
	public void reportadmin(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.DARK_GREEN + "Your report has been sent to the admins!");
		String name = "";
		if(sender instanceof Player)
		{
			name = ((Player)sender).getDisplayName();
		}
		else
		{
			name = ChatColor.RED + sender.getName();
		}

		for (Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			if (player1.hasPermission("olympus.usefulCommands.report.adminsee"))
			{
				player1.sendMessage(ChatColor.RED + "[AdminReport from " + name + ChatColor.RED + "] " + message);
			}
		}
		log.info(ChatColor.RED + "[AdminReport from " + name + ChatColor.RED + "] " + message + ChatColor.RESET);
	}
	public void reportUber(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.DARK_GREEN + "Your report has been sent to the ubers!");
		String name = "";
		if(sender instanceof Player)
		{
			name = ((Player)sender).getDisplayName();
		}
		else
		{
			name = ChatColor.RED + sender.getName();
		}

		for (Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			if (player1.hasPermission("olympus.usefulCommands.report.ubersee"))
			{
				player1.sendMessage(ChatColor.DARK_RED + "[UberReport from " + name + ChatColor.DARK_RED + "] " + message);
			}
		}
		log.info(ChatColor.DARK_RED + "[UberReport from " + name + ChatColor.DARK_RED + "] " + message + ChatColor.RESET);
	}
}
