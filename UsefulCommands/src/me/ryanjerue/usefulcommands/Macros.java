package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Macros extends UsefulCommands
{
	public UsefulCommands plugin;
	Macros(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("promote"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.promote.promote"))
				{
					if(args.length == 1)
					{
						promotePlayer(sender, args[0]);
						return true;
					}
				}
				else
				{
					player.sendMessage("You do not have permission to use this command!");
				}
			}
			else if(args.length == 1)
			{
				promotePlayer(sender, args[0]);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("trusted"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.promote.trusted"))
				{
					if(args.length == 1)
					{
						trustPlayer(sender, args[0]);
						return true;
					}
				}
				else
				{
					player.sendMessage("You do not have permission to use this command!");
				}
			}
			else if(args.length == 1)
			{
				trustPlayer(sender, args[0]);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("setrank"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.setrank.setrank") && args.length == 2)
				{
					String target = args[0];
					String group = args[1];

					setRank(sender, target, group);
					return true;
				}
			}
			else if(args.length == 2)
			{
				String target = args[0];
				String group = args[1];

				setRank(sender, target, group);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("survival"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.gameMode.survival") && args.length == 0)
				{
					survival(player);
					return true;
				}
				else if(player.hasPermission("olympus.usefulCommands.gameMode.survivalOther") && args.length == 1)
				{
					String target = args[0];
					try
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(target);
						survival(targetPlayer);
					}
					catch(Exception e)
					{
						sender.sendMessage(ChatColor.RED + "No player found with that name.");
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
					survival(targetPlayer);
				}
				catch(Exception e)
				{
					sender.sendMessage(ChatColor.RED + "No player found with that name.");
				}
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("creative"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(player.hasPermission("olympus.usefulCommands.gameMode.creative"))
				{
					if(args.length == 0)
					{
						creative(player);
						return true;
					}
				}
				else if(player.hasPermission("olympus.usefulCommands.gameMode.creativeOther") && args.length == 1)
				{
					String target = args[0];
					try
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(target);
						creative(targetPlayer);
					}
					catch(Exception e)
					{
						sender.sendMessage(ChatColor.RED + "No player found with that name.");
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
					creative(targetPlayer);
				}
				catch(Exception e)
				{
					sender.sendMessage(ChatColor.RED + "No player found with that name.");
				}
				return true;
			}
		}
		/*else if(cmd.getName().equalsIgnoreCase("fly"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.flight.allow"))
				{
					fly(player);
					player.sendMessage(ChatColor.GOLD + "Your flight has been toggled!");
					return true;
				}
			}
			else if(args.length == 1)
			{
				String targetName = args[0];
				try
				{
					Player player = getServer().getPlayer(targetName);
					fly(player);
				}
				catch(Exception e)
				{
				}
				return true;
			}
		}*/
		return false;
	}
	public void promotePlayer(CommandSender sender, String targetName)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayerExact(targetName);
			if(targetPlayer.hasPermission("olympus.usefulCommands.ranks.grey"))
			{
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " user");
				sender.sendMessage(ChatColor.GREEN + "Player " + ChatColor.WHITE + targetName + ChatColor.GREEN + " has been promoted!");
				targetPlayer.sendMessage(ChatColor.GREEN + "You've been promoted! You may now build!");
			}
			else
			{
				sender.sendMessage("This player is not a grey rank!");
			}
		}
		catch(Exception e)
		{
			sender.sendMessage(ChatColor.DARK_RED + "Promotion failed - Did you spell the player name correctly?");
		}
	}
	public void trustPlayer(CommandSender sender, String targetName)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayerExact(targetName);
			if(targetPlayer.hasPermission("olympus.usefulCommands.ranks.white"))
			{
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " trusted");
				sender.sendMessage(ChatColor.GREEN + "Player " + ChatColor.GOLD + targetName + ChatColor.GREEN + " has been promoted!");
				targetPlayer.sendMessage(ChatColor.GREEN + "You've been promoted! You are now a trusted member!");
			}
			else
			{
				sender.sendMessage("This player is not a white rank!");
			}
		}
		catch(Exception e)
		{
			sender.sendMessage(ChatColor.DARK_RED + "Promotion failed - Did you spell the player name correctly?");
		}
	}
	public void setRank(CommandSender sender, String target, String group)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch (Exception e)
		{
		}
		Bukkit.getServer().dispatchCommand(sender, "permissions player setgroup " + target + " " + group);
	}
	public void survival(Player sender)
	{
		sender.setGameMode(GameMode.SURVIVAL);
	}
	public void creative(Player sender)
	{
		sender.setGameMode(GameMode.CREATIVE);
	}
	/*public void fly(Player target)
	{
		target.setAllowFlight(true);
		target.setFlying(!(target.isFlying()));
	}*/
}
