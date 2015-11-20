package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StarGates extends UsefulCommands
{
	public UsefulCommands plugin;
	StarGates(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("stargateadd") && args.length > 0)
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(args.length == 1 && player.hasPermission("olympus.usefulCommands.stargateAdd." + player.getName()))
				{
					String target = args[0];
					String network = player.getName();
					add(sender, target, network);
					return true;
				}
				else if (args.length == 2 && (player.hasPermission("olympus.usefulCommands.stargateAdd." + args[1]) || player.hasPermission("olympus.usefulCommands.stargateAdd.*")))
				{
					String target = args[0];
					String network = args[1];
					add(sender, target, network);
					return true;
				}
			}
			else if(args.length == 2)
			{
				String target = args[0];
				String network = args[1];
				add(sender, target, network);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("stargateremove") && args.length > 0)
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;

				if(args.length == 1 && player.hasPermission("olympus.usefulCommands.stargateRemove." + player.getName()))
				{
					String target = args[0];
					String network = player.getName();
					remove(sender, target, network);
					return true;
				}
				else if (args.length == 2 && (player.hasPermission("olympus.usefulCommands.stargateRemove." + args[1]) || player.hasPermission("olympus.usefulCommands.stargateRemove.*")))
				{
					String target = args[0];
					String network = args[1];
					remove(sender, target, network);
					return true;
				}
			}
			else if(args.length == 2)
			{
				String target = args[0];
				String network = args[1];
				remove(sender, target, network);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("stargateregister") && args.length > 0)
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.stargate.register") && args.length == 1)
				{
					String network = args[0];
					register(sender, network);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String network = args[0];
				register(sender, network);
				return true;
			}
		}
		return false;
	}
	public void add(CommandSender sender, String target, String network)
	{
		try
		{
			Player player = getServer().getPlayer(target);
			target = player.getName();
		}
		catch(Exception e)
		{
		}

		if(network.length() > 11)
		{
			network = network.substring(0, 11);
		}

		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " stargate.network." + network + " true");
		sender.sendMessage(ChatColor.GOLD + "The player has been added to the network.");
		try
		{
			Player player = getServer().getPlayer(target);
			player.sendMessage(ChatColor.GOLD + "You have been added to " + network + "'s network.");
		}
		catch(Exception e)
		{
		}
	}
	public void remove(CommandSender sender, String target, String network)
	{
		try
		{
			Player player = getServer().getPlayer(target);
			target = player.getName();
		}
		catch(Exception e)
		{
		}

		if(network.length() > 11)
		{
			network = network.substring(0, 11);
		}

		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player unsetperm " + target + " stargate.network." + network);
		sender.sendMessage(ChatColor.GOLD + "The player has been removed from the network.");
		try
		{
			Player player = getServer().getPlayer(target);
			player.sendMessage(ChatColor.GOLD + "You have been removed from " + network + "'s network.");
		}
		catch(Exception e)
		{
		}
	}
	public void register(CommandSender sender, String target)
	{
		try
		{
			Player player = getServer().getPlayer(target);
			target = player.getName();
		}
		catch(Exception e)
		{
		}

		String network = target;

		if(network.length() > 11)
		{
			network = network.substring(0, 11);
		}

		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions group setperm default stargate.network." + network + " false");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " olympus.usefulCommands.stargateAdd." + target + " true");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " olympus.usefulCommands.stargate.add"+ " true");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " olympus.usefulCommands.stargateRemove." + target + " true");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " olympus.usefulCommands.stargate.remove"+ " true");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player setperm " + target + " stargate.network." + network + " true");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions group setperm modonduty stargate.network." + network + " true");
		sender.sendMessage(ChatColor.GOLD + "The network '" + network + "' has been registered to " + target + ".");
		log.info("Network " + network + " registered to " + target + ".");
	}
}
