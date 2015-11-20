package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lightning extends UsefulCommands
{
	public UsefulCommands plugin;
	Lightning(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{

		if(cmd.getName().equalsIgnoreCase("lightning"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.lightning.lighting"))
				{
					boolean effect = false;
					boolean goOn = false;

					if(args.length == 2 && args[0].equalsIgnoreCase("-e"))
					{
						effect = true;
						goOn = true;
					}
					else if(args.length == 1)
					{
						effect = false;
						goOn = true;
					}
					if(goOn)
					{
						if((!effect && args[0].equalsIgnoreCase("*") || effect && args[1].equalsIgnoreCase("*")) && player.hasPermission("olympus.usefulCommands.lightning.lightningAll"))
						{
							lightningAll(sender, effect);
						}
						else
						{
							String target;
							if(effect)
							{
								target = args[1];
							}
							else
							{
								target = args[0];
							}
							lightningPlayer(sender, target, effect);
						}
						return true;
					}
				}
			}
			else
			{
				boolean effect = false;
				boolean goOn = false;

				if(args.length == 2 && args[0].equalsIgnoreCase("-e"))
				{
					effect = true;
					goOn = true;
				}
				else if(args.length == 1)
				{
					effect = false;
					goOn = true;
				}
				if(goOn)
				{
					if((!effect && args[0].equalsIgnoreCase("*") || effect && args[1].equalsIgnoreCase("*")))
					{
						lightningAll(sender, effect);
					}
					else
					{
						String target;
						if(effect)
						{
							target = args[1];
						}
						else
						{
							target = args[0];
						}
						lightningPlayer(sender, target, effect);
					}
					return true;
				}
			}
		}
		return false;
	}
	public void lightningPlayer(CommandSender sender, String player, boolean effect)
	{
		if(effect)
		{
			try
			{
				Player player1 = Bukkit.getServer().getPlayer(player);

				player1.getWorld().strikeLightningEffect(player1.getLocation());
				sender.sendMessage(ChatColor.GOLD + "The player has been struck!");
			}
			catch (Exception e)
			{
				sender.sendMessage(ChatColor.RED + "The player was not found!");
			}
		}
		else
		{
			try
			{
				Player player1 = Bukkit.getServer().getPlayer(player);

				player1.getWorld().strikeLightning(player1.getLocation());
				sender.sendMessage(ChatColor.GOLD + "The player has been struck!");
			}
			catch (Exception e)
			{
				sender.sendMessage(ChatColor.RED + "The player was not found!");
			}
		}
	}
	public void lightningAll(CommandSender sender, boolean effect)
	{
		if(effect)
		{
			for (Player player1 : Bukkit.getServer().getOnlinePlayers())
			{
				player1.getWorld().strikeLightningEffect(player1.getLocation());
			}
		}
		else
		{
			for (Player player1 : Bukkit.getServer().getOnlinePlayers())
			{
				player1.getWorld().strikeLightning(player1.getLocation());
			}
		}
		sender.sendMessage(ChatColor.GOLD + "The players have been struck!");
	}
}
