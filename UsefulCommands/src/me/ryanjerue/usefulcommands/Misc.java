package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.kitteh.vanish.staticaccess.VanishNoPacket;

public class Misc extends UsefulCommands
{
	public UsefulCommands plugin;
	Misc(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("antioch"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.antioch") && args.length == 0)
				{
					antioch(player);
					return true;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("getip"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.getIP") && args.length == 1)
				{
					String target = args[0];
					getIP(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				getIP(sender, target);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("me"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.me") && args.length > 0)
				{
					String action = "";

					for(int i = 0; i < args.length; i++)
					{
						action = action + args[i] + " ";
					}
					me(sender, action);
					return true;
				}
			}
			else if(args.length > 0)
			{
				String action = "";

				for(int i = 0; i < args.length; i++)
				{
					action = action + args[i] + " ";
				}
				me(sender, action);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("who"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.who") && args.length == 0)
				{
					who(player);
					return true;
				}
			}
			else if(args.length == 0)
			{
				whoAll(sender);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("usefulCommands") && args.length == 1 && args[0].equalsIgnoreCase("reload"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.reload"))
				{
					reloadConfig(sender);
					return true;
				}
			}
			else
			{
				reloadConfig(sender);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("broadcast"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.misc.broadcast") && args.length > 0)
				{
					String message = "";

					for(int i = 0; i < args.length; i++)
					{
						message = message + args[i] + " ";
					}
					broadcast(message);
					return true;
				}
			}
			else if(args.length > 0)
			{
				String message = "";

				for(int i = 0; i < args.length; i++)
				{
					message = message + args[i] + " ";
				}
				broadcast(message);
				return true;
			}
		}
		return false;
	}
	public void getIP(CommandSender sender, String target)
	{
		try
		{
			Player player = Bukkit.getServer().getPlayerExact(target);
			String address = player.getAddress().toString();
			String ip = address.substring(address.indexOf("/") + 1, address.indexOf(":"));

			sender.sendMessage(ChatColor.GREEN + "This player's IP is " + ip + " .");
		}
		catch(Exception E)
		{
			sender.sendMessage(ChatColor.RED + "This player is not online!");
		}
	}
	public void antioch(Player sender)
	{
		Bukkit.getServer().broadcastMessage("...lobbest thou thy Holy Hand Grenade of Antioch towards thy foe,");
		Bukkit.getServer().broadcastMessage("who being naughty in My sight, shall snuff it.");

		Block block = sender.getTargetBlock(null, 50);
		Location location = block.getLocation();
		location.getWorld().spawn(location, TNTPrimed.class);
	}
	public void me(CommandSender sender, String action)
	{
		String name = "";
		ChatColor color = ChatColor.GRAY;
		if(sender instanceof Player)
		{
			name = ((Player)sender).getDisplayName();
		}
		else
		{
			name = ChatColor.RED + sender.getName();
		}
		Bukkit.getServer().broadcastMessage(color + "* " + name + " " + color + action);
	}
	public void who(Player sender)
	{
		String greys = "", whites = "", trusteds = "", ironvips = "", goldvips = "", jrdevs = "", retiredstaff = "", mods = "", admins = "", ubers = "", owner = "";
		int grey = 0, white = 0, trusted = 0, ironvip = 0, goldvip = 0, jrdev = 0, retired = 0, mod = 0, admin = 0, uber = 0, ownerNum = 0;
		int onlinePlayers = 0;
		for (Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			try
			{
				if(VanishNoPacket.canSee(sender, player1))
				{
					onlinePlayers++;
				}
				if(player1.hasPermission("olympus.usefulCommands.ranks.grey") && VanishNoPacket.canSee(sender, player1))
				{
					greys = greys + player1.getDisplayName() + " ";
					grey++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.white") && VanishNoPacket.canSee(sender, player1))
				{
					whites = whites + player1.getDisplayName() + " ";
					white++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.trusted") && VanishNoPacket.canSee(sender, player1))
				{
					trusteds = trusteds + player1.getDisplayName() + " ";
					trusted++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.ironvip") && VanishNoPacket.canSee(sender, player1))
				{
					ironvips = ironvips + player1.getDisplayName() + " ";
					ironvip++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.goldvip") && VanishNoPacket.canSee(sender, player1))
				{
					goldvips = goldvips + player1.getDisplayName() + " ";
					goldvip++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.jrdev") && VanishNoPacket.canSee(sender, player1))
				{
					jrdevs = jrdevs + player1.getDisplayName() + " ";
					jrdev++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.retired") && VanishNoPacket.canSee(sender, player1))
				{
					retiredstaff = retiredstaff + player1.getDisplayName() + " ";
					retired++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.mod") && VanishNoPacket.canSee(sender, player1))
				{
					mods = mods + player1.getDisplayName() + " ";
					mod++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.admin") && VanishNoPacket.canSee(sender, player1))
				{
					admins = admins + player1.getDisplayName() + " ";
					admin++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.uber") && VanishNoPacket.canSee(sender, player1))
				{
					ubers = ubers + player1.getDisplayName() + " ";
					uber++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.owner") && VanishNoPacket.canSee(sender, player1))
				{
					owner = owner + player1.getDisplayName() + " ";
					ownerNum++;
				}
			}
			catch(Exception e)
			{

			}
		}
		if(onlinePlayers == 1)
		{
			sender.sendMessage(ChatColor.GOLD + "There is " + ChatColor.GREEN + onlinePlayers + ChatColor.GOLD + " player online");
		}
		else
		{
			sender.sendMessage(ChatColor.GOLD + "There are " + onlinePlayers + " players online");
		}

		if(greys.length() != 0)
		{
			sender.sendMessage(ChatColor.GRAY + "Grey Ranks" + ChatColor.GREEN + "(" + grey + ")" + ChatColor.GRAY + ": " + greys);
		}
		if(whites.length() != 0)
		{
			sender.sendMessage(ChatColor.WHITE + "White Ranks" + ChatColor.GREEN + "(" + white + ")" + ChatColor.WHITE + ": " + whites);
		}
		if(trusteds.length() != 0)
		{
			sender.sendMessage(ChatColor.GOLD + "Trusteds" + ChatColor.GREEN + "(" + trusted + ")" + ChatColor.GOLD + ": " + trusteds);
		}
		if(ironvips.length() != 0)
		{
			sender.sendMessage(ChatColor.GREEN + "Iron VIPs(" + ironvip + "): " + ironvips);
		}
		if(goldvips.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_GREEN + "Gold VIPs" + ChatColor.GREEN + "(" + goldvip + ")" + ChatColor.DARK_GREEN + ": " + goldvips);
		}
		if(jrdevs.length() != 0)
		{
			sender.sendMessage(ChatColor.BLUE + "Jr Developers" + ChatColor.GREEN + "(" + jrdev + ")" + ChatColor.BLUE + ": " + jrdevs);
		}
		if(retiredstaff.length() != 0)
		{
			sender.sendMessage(ChatColor.YELLOW + "Retired Staff" + ChatColor.GREEN + "(" + retired + ")" + ChatColor.YELLOW + ": " + retiredstaff);
		}
		if(mods.length() != 0)
		{
			sender.sendMessage(ChatColor.AQUA + "Mods" + ChatColor.GREEN + "(" + mod + ")" + ChatColor.AQUA + ": " + mods);
		}
		if(admins.length() != 0)
		{
			sender.sendMessage(ChatColor.RED + "Admins" + ChatColor.GREEN + "(" + admin + ")" + ChatColor.RED + ": " + admins);
		}
		if(ubers.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_RED + "Uber Admins" + ChatColor.GREEN + "(" + uber + ")" + ChatColor.DARK_RED + ": " + ubers);
		}
		if(owner.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + "Owner" + ChatColor.GREEN + "(" + ownerNum + ")" + ChatColor.DARK_PURPLE + ": " + owner);
		}
	}
	public void whoAll(CommandSender sender)
	{
		String greys = "", whites = "", trusteds = "", ironvips = "", goldvips = "", jrdevs = "", retiredstaff = "", mods = "", admins = "", ubers = "", owner = "";
		int grey = 0, white = 0, trusted = 0, ironvip = 0, goldvip = 0, jrdev = 0, retired = 0, mod = 0, admin = 0, uber = 0, ownerNum = 0;
		int onlinePlayers = 0;
		for (Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			try
			{
				onlinePlayers++;

				if(player1.hasPermission("olympus.usefulCommands.ranks.grey"))
				{
					greys = greys + player1.getDisplayName() + " ";
					grey++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.white"))
				{
					whites = whites + player1.getDisplayName() + " ";
					white++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.trusted"))
				{
					trusteds = trusteds + player1.getDisplayName() + " ";
					trusted++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.ironvip"))
				{
					ironvips = ironvips + player1.getDisplayName() + " ";
					ironvip++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.goldvip"))
				{
					goldvips = goldvips + player1.getDisplayName() + " ";
					goldvip++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.jrdev"))
				{
					jrdevs = jrdevs + player1.getDisplayName() + " ";
					jrdev++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.retired"))
				{
					retiredstaff = retiredstaff + player1.getDisplayName() + " ";
					retired++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.mod"))
				{
					mods = mods + player1.getDisplayName() + " ";
					mod++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.admin"))
				{
					admins = admins + player1.getDisplayName() + " ";
					admin++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.uber"))
				{
					ubers = ubers + player1.getDisplayName() + " ";
					uber++;
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.owner"))
				{
					owner = owner + player1.getDisplayName() + " ";
					ownerNum++;
				}
			}
			catch(Exception e)
			{

			}
		}
		if(onlinePlayers == 1)
		{
			sender.sendMessage(ChatColor.GOLD + "There is " + ChatColor.GREEN + onlinePlayers + ChatColor.GOLD + " player online");
		}
		else
		{
			sender.sendMessage(ChatColor.GOLD + "There are " + onlinePlayers + " players online");
		}

		if(greys.length() != 0)
		{
			sender.sendMessage(ChatColor.GRAY + "Grey Ranks" + ChatColor.GREEN + "(" + grey + ")" + ChatColor.GRAY + ": " + greys);
		}
		if(whites.length() != 0)
		{
			sender.sendMessage(ChatColor.WHITE + "White Ranks" + ChatColor.GREEN + "(" + white + ")" + ChatColor.WHITE + ": " + whites);
		}
		if(trusteds.length() != 0)
		{
			sender.sendMessage(ChatColor.GOLD + "Trusteds" + ChatColor.GREEN + "(" + trusted + ")" + ChatColor.GOLD + ": " + trusteds);
		}
		if(ironvips.length() != 0)
		{
			sender.sendMessage(ChatColor.GREEN + "Iron VIPs(" + ironvip + "): " + ironvips);
		}
		if(goldvips.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_GREEN + "Gold VIPs" + ChatColor.GREEN + "(" + goldvip + ")" + ChatColor.DARK_GREEN + ": " + goldvips);
		}
		if(jrdevs.length() != 0)
		{
			sender.sendMessage(ChatColor.BLUE + "Jr Developers" + ChatColor.GREEN + "(" + jrdev + ")" + ChatColor.BLUE + ": " + jrdevs);
		}
		if(retiredstaff.length() != 0)
		{
			sender.sendMessage(ChatColor.YELLOW + "Retired Staff" + ChatColor.GREEN + "(" + retired + ")" + ChatColor.YELLOW + ": " + retiredstaff);
		}
		if(mods.length() != 0)
		{
			sender.sendMessage(ChatColor.AQUA + "Mods" + ChatColor.GREEN + "(" + mod + ")" + ChatColor.AQUA + ": " + mods);
		}
		if(admins.length() != 0)
		{
			sender.sendMessage(ChatColor.RED + "Admins" + ChatColor.GREEN + "(" + admin + ")" + ChatColor.RED + ": " + admins);
		}
		if(ubers.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_RED + "Uber Admins" + ChatColor.GREEN + "(" + uber + ")" + ChatColor.DARK_RED + ": " + ubers);
		}
		if(owner.length() != 0)
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + "Owner" + ChatColor.GREEN + "(" + ownerNum + ")" + ChatColor.DARK_PURPLE + ": " + owner);
		}
	}
	private void reloadConfig(CommandSender sender)
	{
		plugin.reloadConfig();
		plugin.getPluginLoader().disablePlugin(plugin);
		plugin.getPluginLoader().enablePlugin(plugin);
		sender.sendMessage(ChatColor.GOLD + "[UsefulCommands] Config reloaded!");
		plugin.log.info(ChatColor.GOLD + "[UsefulCommands] Config reloaded!");
	}
	public void broadcast(String message)
	{
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Broadcast] " + ChatColor.GREEN + message);
	}
}
