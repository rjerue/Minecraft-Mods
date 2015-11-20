package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk extends UsefulCommands
{
	public UsefulCommands plugin;
	Afk(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("afk"))
		{
			if(args.length == 2 && args[0].equalsIgnoreCase("check"))
			{
				if(sender instanceof Player)
				{
					Player player = (Player)sender;
					if(player.hasPermission("olympus.usefulCommands,afk.check"))
					{
						String target = args[1];
						checkAfkStatus(sender, target);
						return true;
					}
				}
				else
				{
					String target = args[1];
					checkAfkStatus(sender, target);
					return true;
				}
			}
			else if(args.length == 0)
			{
				if(sender instanceof Player)
				{
					Player player = (Player)sender;
					if(player.hasPermission("olympus.usefulCommands.afk.toggle"))
					{
						toggleAfk(player);
						return true;
					}
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "This command can only be used as a player!");
					return true;
				}
			}
		}
		return false;
	}
	public void toggleAfk(Player player)
	{
		String target = player.getName();
		if(!plugin.getConfig().getConfigurationSection("Afk").contains(target))
		{
			plugin.getConfig().getConfigurationSection("Afk").createSection(target);
		}

		boolean afk = checkAfk(target);

		if(afk)
		{
			Bukkit.getServer().broadcastMessage(player.getDisplayName() + ChatColor.GRAY + " is now unafk!");
		}
		else
		{
			Bukkit.getServer().broadcastMessage(player.getDisplayName() + ChatColor.GRAY + " is now afk!");
		}

		afk = !afk;

		plugin.getConfig().set("Afk." + target, afk);

		plugin.saveConfig();
	}
	public boolean checkAfk(String target)
	{
		boolean afk = plugin.getConfig().getConfigurationSection("Afk").getBoolean(target);
		return afk;
	}
	public void checkAfkStatus(CommandSender sender, String target)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception e)
		{

		}
		boolean afk = checkAfk(target);
		if(afk)
		{
			sender.sendMessage(ChatColor.RED + "This player is afk!");
		}
		else
		{
			sender.sendMessage(ChatColor.GREEN + "This player is not afk!");
		}
	}
}
