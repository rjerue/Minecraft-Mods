package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;

public class Ranks extends UsefulCommands
{
	public UsefulCommands plugin;
	Ranks(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ranks"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.ranks.ranks"))
				{
					if(args.length == 0)
					{
						ranks(sender);
					  	return true;
					}
					if(args.length == 1)
					{
						String description = args[0];
						rankInfo(sender, description);
						return true;
					}
				}
			}
			else
			{
				if(args.length == 0)
				{
					ranks(sender);
				  	return true;
				}
				if(args.length == 1)
				{
					String description = args[0];
					rankInfo(sender, description);
					return true;
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("staff"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.ranks.staffInfo") && args.length == 0)
				{
					staffInfo(player);
					return true;
				}
			}
			else if(args.length == 0)
			{
				staffInfoConsole(sender);
				return true;
			}
		}
		return false;
	}
	public void ranks(CommandSender sender)
	{
		sender.sendMessage("The ranks on this server are " + ChatColor.GRAY + "Grey Rank" + ChatColor.WHITE + ", White Rank, "
			+ ChatColor.GOLD + "Trusted" + ChatColor.WHITE + ", " + ChatColor.GREEN + "Iron VIP" + ChatColor.WHITE + ", " + ChatColor.DARK_GREEN + "Gold VIP"
			+ ChatColor.WHITE + ", " + ChatColor.BLUE + "Jr Developer" + ChatColor.WHITE + ", " + ChatColor.YELLOW + "Retired Staff" + ChatColor.WHITE + ", "
			+ ChatColor.AQUA + "Mod" + ChatColor.WHITE + ", " + ChatColor.RED + "Admin" + ChatColor.WHITE + ", " + ChatColor.DARK_RED + "Uber Admin"
			+ ChatColor.WHITE + ", and " + ChatColor.DARK_PURPLE + "Owner" + ChatColor.WHITE + ".");

		sender.sendMessage(ChatColor.GREEN + "For more information on a certain rank, use /ranks [rank].");
	}
	public void staffInfo(Player sender)
	{
		sender.sendMessage("The staff ranks are " + ChatColor.AQUA + "Mod" + ChatColor.WHITE + ", " + ChatColor.RED + "Admin" +
			ChatColor.WHITE + ", " + ChatColor.DARK_RED + "Uber Admin" + ChatColor.WHITE + ", and " + ChatColor.DARK_PURPLE + "Owner" +
			ChatColor.WHITE + ".");

		String mods = ""; String admins = ""; String ubers = ""; String owner = "";

		for (Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			try
			{
				if(player1.hasPermission("olympus.usefulCommands.ranks.mod") && VanishNoPacket.canSee(sender, player1))
				{
					mods = mods + player1.getDisplayName() + " ";
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.admin") && VanishNoPacket.canSee(sender, player1))
				{
					admins = admins + player1.getDisplayName() + " ";
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.uber") && VanishNoPacket.canSee(sender, player1))
				{
					ubers = ubers + player1.getDisplayName() + " ";
				}
				else if(player1.hasPermission("olympus.usefulCommands.ranks.owner") && VanishNoPacket.canSee(sender, player1))
				{
					owner = owner + player1.getDisplayName() + " ";
				}
			}
			catch(Exception e)
			{

			}
		}
		if ((mods.length() == 0) && (admins.length() == 0) && (ubers.length() == 0) && (owner.length() == 0))
		{
			sender.sendMessage(ChatColor.GREEN + "There are " + ChatColor.ITALIC + "no" + ChatColor.RESET +
				ChatColor.GREEN + " staff members online!");
		}
		else
		{
			sender.sendMessage("The following staff members are online: " + ChatColor.AQUA + mods + ChatColor.RED +
				admins + ChatColor.DARK_RED + ubers + ChatColor.DARK_PURPLE + owner);
		}
	}
	public void staffInfoConsole(CommandSender sender)
	{
		sender.sendMessage("The staff ranks are " + ChatColor.AQUA + "Mod" + ChatColor.WHITE + ", " + ChatColor.RED + "Admin" +
				ChatColor.WHITE + ", " + ChatColor.DARK_RED + "Uber Admin" + ChatColor.WHITE + ", and " + ChatColor.DARK_PURPLE + "Owner" +
				ChatColor.WHITE + ".");

			String mods = ""; String admins = ""; String ubers = ""; String owner = "";

			for (Player player1 : Bukkit.getServer().getOnlinePlayers())
			{
				try
				{
					if(player1.hasPermission("olympus.usefulCommands.ranks.mod"))
					{
						mods = mods + player1.getDisplayName() + " ";
					}
					else if(player1.hasPermission("olympus.usefulCommands.ranks.admin"))
					{
						admins = admins + player1.getDisplayName() + " ";
					}
					else if(player1.hasPermission("olympus.usefulCommands.ranks.uber"))
					{
						ubers = ubers + player1.getDisplayName() + " ";
					}
					else if(player1.hasPermission("olympus.usefulCommands.ranks.owner"))
					{
						owner = owner + player1.getDisplayName() + " ";
					}
				}
				catch(Exception e)
				{

				}
			}
			if ((mods.length() == 0) && (admins.length() == 0) && (ubers.length() == 0) && (owner.length() == 0))
			{
				sender.sendMessage(ChatColor.GREEN + "There are " + ChatColor.ITALIC + "no" + ChatColor.RESET +
					ChatColor.GREEN + " staff members online!");
			}
			else
			{
				sender.sendMessage("The following staff members are online: " + ChatColor.AQUA + mods + ChatColor.RED +
					admins + ChatColor.DARK_RED + ubers + ChatColor.DARK_PURPLE + owner);
			}
	}
	public void rankInfo(CommandSender sender, String description)
	{
		boolean found = false;

		if(description.equalsIgnoreCase("grey") || description.equalsIgnoreCase("gray") || description.equalsIgnoreCase("default"))
		{
			sender.sendMessage(ChatColor.GRAY + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Grey").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("white") || description.equalsIgnoreCase("member"))
		{
			sender.sendMessage(plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("White").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("gold") || description.equalsIgnoreCase("yellow") || description.equalsIgnoreCase("orange") ||
			description.equalsIgnoreCase("trusted"))
		{
			sender.sendMessage(ChatColor.GOLD + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Trusted").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("green") || description.equalsIgnoreCase("vip") || description.equalsIgnoreCase("lightgreen") || description.equalsIgnoreCase("iron") || description.equalsIgnoreCase("ironvip"))
		{
			sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("IronVIP").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("green") || description.equalsIgnoreCase("vip") || description.equalsIgnoreCase("darkgreen") || description.equalsIgnoreCase("gold") || description.equalsIgnoreCase("goldvip"))
		{
			sender.sendMessage(ChatColor.DARK_GREEN + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("GoldVIP").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("blue") || description.equalsIgnoreCase("jr") || description.equalsIgnoreCase("dev") ||
			description.equalsIgnoreCase("jrdev") || description.equalsIgnoreCase("developer"))
		{
			sender.sendMessage(ChatColor.BLUE + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("JrDev").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("yellow") || description.equalsIgnoreCase("retired") || description.equalsIgnoreCase("staff") ||
			description.equalsIgnoreCase("retiredstaff") || description.equalsIgnoreCase("retired") || description.equalsIgnoreCase("retiredstaff"))
		{
			sender.sendMessage(ChatColor.YELLOW + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Retired").getString("description"));
		}
		if(description.equalsIgnoreCase("blue") || description.equalsIgnoreCase("aqua") || description.equalsIgnoreCase("cyan") ||
			description.equalsIgnoreCase("mod") || description.equalsIgnoreCase("moderator") || description.equalsIgnoreCase("staff"))
		{
			sender.sendMessage(ChatColor.AQUA + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Mod").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("red") || description.equalsIgnoreCase("admin") || description.equalsIgnoreCase("staff"))
		{
			sender.sendMessage(ChatColor.RED + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Admin").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("red") || description.equalsIgnoreCase("dark") || description.equalsIgnoreCase("darkred") ||
			description.equalsIgnoreCase("uber") || description.equalsIgnoreCase("uberadmin") || description.equalsIgnoreCase("staff"))
		{
			sender.sendMessage(ChatColor.DARK_RED + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Uber").getString("description"));
			found = true;
		}
		if(description.equalsIgnoreCase("purple") || description.equalsIgnoreCase("owner") || description.equalsIgnoreCase("geek") || description.equalsIgnoreCase("staff"))
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + plugin.getConfig().getConfigurationSection("Ranks").getConfigurationSection("Owner").getString("description"));
			found = true;
		}
		if(!found)
		{
			sender.sendMessage(ChatColor.RED + "This group has not been found!");
		}
	}
}
