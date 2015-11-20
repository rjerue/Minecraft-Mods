package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AppHelp extends UsefulCommands
{
	public UsefulCommands plugin;
	AppHelp(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("apphelp"))
		{
			if(sender instanceof Player)
			{

				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.appHelp.message"))
				{
					if(args.length == 1 && args[0].equalsIgnoreCase("message"))
					{
						sendMessage(sender);
						return true;
					}
					if(args.length == 0)
					{
						appHelp(sender);
						return true;
					}
				}
				if(player.hasPermission("olympus.usefulCommands.appHelp.send"))
				{
					if(args.length == 1)
					{
						String target = args[0];
						appHelp(sender, target);
						return true;
					}
				}
			}
			else
			{

				if(args.length == 0)
				{
					appHelp(sender);
					return true;
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("message"))
				{
					sendMessage(sender);
					return true;
				}
				if(args.length == 1)
				{
					String target = args[0];
					appHelp(sender, target);
					return true;
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("helpme"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.ranks.grey"))
				{
					sendMessage(sender);
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.GREEN + "This command is for grey ranks.  Use /apphelp message to view it as a member.");
					return true;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "This can only be used by a player!");
				return true;
			}
		}
		return false;
	}
	public void appHelp(CommandSender sender, String target)
	{
		try
		{
			Player player = Bukkit.getPlayer(target);
			sendMessage(player);
			String name = sender.getName();
			if(sender instanceof Player)
			{
				name = ((Player) sender).getPlayerListName();
			}
			for(Player player1: Bukkit.getServer().getOnlinePlayers())
			{
	            if(player1.hasPermission("olympus.usefulCommands.appHelp.send"))
	            {
	            	player1.sendMessage(ChatColor.GREEN + "[APPHELP] " + ChatColor.GRAY + player.getName() + ChatColor.GREEN + " has been sent apphelp by " + name + ChatColor.GREEN + "!");
	            }
			}
			plugin.log.info(ChatColor.GREEN + "[APPHELP] " + ChatColor.GRAY + player.getName() + ChatColor.GREEN + " has been sent apphelp by " + sender.getName() + "!" + ChatColor.RESET);

		}
		catch(Exception e)
		{
			sender.sendMessage(ChatColor.RED + "No player online with this name!");
		}
	}
	public void sendMessage(CommandSender sender)
	{
		sender.sendMessage(ChatColor.LIGHT_PURPLE + "= = = = = = = = = = = = = = = = = = = = = = = =");
		sender.sendMessage(ChatColor.RED + "Welcome to Olympus! " + ChatColor.GREEN + "To Join please make an account on our");
		sender.sendMessage(ChatColor.GREEN + "forums at " +ChatColor.RED + "http://Olympus-Gaming.net/");
		sender.sendMessage(ChatColor.GREEN + "Once you have made an account,");
		sender.sendMessage(ChatColor.GREEN + "Please visit:" + ChatColor.RED + " http://olympus-gaming.net/apply/ ");
		sender.sendMessage(ChatColor.GREEN + "Remember, applying is " + ChatColor.DARK_GREEN + "EASY " + ChatColor.GREEN + "and " + ChatColor.GOLD + "FREE" + ChatColor.GREEN + "!" );
		sender.sendMessage(ChatColor.LIGHT_PURPLE + "= = = = = = = = = = = = = = = = = = = = = = = =");
	}
	public void appHelp(CommandSender sender)
	{
		sender.sendMessage(ChatColor.GOLD + "/apphelp - Displays this menu");
		sender.sendMessage(ChatColor.GOLD + "/apphelp message - Displays the help message");
		if((sender instanceof Player && ((Player)sender).hasPermission("olympus.usefulCommands.appHelp.send")) || sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage(ChatColor.GOLD + "/apphelp <player> - Sends the help message to a player");
		}
	}
}
