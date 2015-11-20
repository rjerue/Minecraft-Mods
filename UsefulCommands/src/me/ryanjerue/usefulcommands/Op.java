package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Op extends UsefulCommands
{
	public UsefulCommands plugin;
	Op(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("checkop"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.op.checkOp") && args.length == 1)
				{
					String target = args[0];
					checkOp(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				checkOp(sender, target);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("fakeop"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.op.fakeOp") && args.length == 1)
				{
					String target = args[0];
					fakeOp(sender, target);
					return true;
				}
			}
			else if(args.length == 1)
			{
				String target = args[0];
				fakeOp(sender, target);
				return true;
			}
		}
		return false;
	}
	public void checkOp(CommandSender sender, String target)
	{
		OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
		if (targetPlayer.isOp())
		{
			sender.sendMessage(ChatColor.GOLD + "This player is an op!");
		}
		else
		{
			sender.sendMessage(ChatColor.GOLD + "This player is not an op!");
		}
	}
	public void fakeOp(CommandSender sender, String targetPlayer)
	{
		try
		{
			Player target = Bukkit.getServer().getPlayer(targetPlayer);

			target.sendMessage(ChatColor.YELLOW + "You are now op!");
			sender.sendMessage(ChatColor.GREEN + "The player has been fake op'd!");
		}
		catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "This player is not online!");
		}
	}
}
