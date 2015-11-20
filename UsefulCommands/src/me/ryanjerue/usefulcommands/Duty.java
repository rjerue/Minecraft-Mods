package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Duty extends UsefulCommands
{
	public UsefulCommands plugin;
	Duty(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("duty"))
		{

			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(cmd.getName().equalsIgnoreCase("duty") && player.hasPermission("olympus.usefulCommands.ranks.staff") && args.length == 0)
				{
					if(player.hasPermission("olympus.usefulCommands.ranks.mod"))
					{
						modChange(player);
					}
					else if(player.hasPermission("olympus.usefulCommands.ranks.admin"))
					{
						adminChange(player);
					}
					else if(player.hasPermission("olympus.usefulCommands.ranks.uber"))
					{
						uberChange(player);
					}
					else if(player.hasPermission("olympus.usefulCommands.ranks.owner"))
					{
						ownerChange(player);
					}
					return true;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
			}
		}
		return false;
	}
	public void modChange(Player sender)
	{
		String targetName = sender.getName();
		if(sender.hasPermission("olympus.usefulCommands.admin.onDuty"))
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " mod");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ungod " + targetName);
			sender.sendMessage(ChatColor.GOLD + "You are now off duty!");
		}
		else
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " modonduty");
			sender.sendMessage(ChatColor.GOLD + "You are now on duty!");
		}
	}
	public void adminChange(Player sender)
	{
		String targetName = sender.getName();
		if(sender.hasPermission("olympus.usefulCommands.admin.onDuty"))
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " admin");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ungod " + targetName);
			sender.sendMessage(ChatColor.GOLD + "You are now off duty!");
		}
		else
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " adminonduty");
			sender.sendMessage(ChatColor.GOLD + "You are now on duty!");
		}
	}
	public void uberChange(Player sender)
	{
		String targetName = sender.getName();
		if(sender.hasPermission("olympus.usefulCommands.admin.onDuty"))
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " uberadmin");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ungod " + targetName);
			sender.sendMessage(ChatColor.GOLD + "You are now off duty!");
		}
		else
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " uberonduty");
			sender.sendMessage(ChatColor.GOLD + "You are now on duty!");
		}
	}
	public void ownerChange(Player sender)
	{
		String targetName = sender.getName();
		if(sender.hasPermission("olympus.usefulCommands.admin.onDuty"))
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " owner");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ungod " + targetName);
			sender.sendMessage(ChatColor.GOLD + "You are now off duty!");
		}
		else
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "permissions player setgroup " + targetName + " owneronduty");
			sender.sendMessage(ChatColor.GOLD + "You are now on duty!");
		}
	}
}
