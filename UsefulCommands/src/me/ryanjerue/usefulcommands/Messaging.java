package me.ryanjerue.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;

public class Messaging extends UsefulCommands
{
	public UsefulCommands plugin;
	Messaging(UsefulCommands instance)
	{
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("messagespy"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.messageSpy.messageSpy"))
				{
					if(args.length == 0)
					{
						messageSpyToggle(player);
						return true;
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("check"))
					{
						String target = args[1];
						try
						{
							Player targetPlayer = Bukkit.getServer().getPlayer(target);
							target = targetPlayer.getName();
						}
						catch(Exception localException1)
						{
						}

						boolean enabled = checkMessageSpy(target);

						if(enabled)
						{
							player.sendMessage(ChatColor.GREEN + "This player has Message Spy enabled!");
						}
						else
						{
							player.sendMessage(ChatColor.GREEN + "This player does not have Message Spy enabled!");
						}
						return true;
					}
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("message"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.message.message") && args.length > 1)
				{
					String target = args[0];
					String message = "";

					for(int i = 1; i < args.length; i++)
					{
						message = message + args[i] + " ";
					}
					message = message.replaceAll("  ", " ");

					messagePlayer(sender, target, message);
					return true;
				}
			}
			else if(args.length > 1)
			{
				String target = args[0];
				String message = "";

				for(int i = 1; i < args.length; i++)
				{
					message = message + args[i] + " ";
				}
				message = message.replaceAll("  ", " ");

				messagePlayer(sender, target, message);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("reply"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(player.hasPermission("olympus.usefulCommands.message.reply") && args.length > 0)
				{
					String message = "";

					for(int i = 0; i < args.length; i++)
					{
						message = message + args[i] + " ";
					}

					messageReply(player, message);
					return true;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
				return true;
			}
		}
		return false;
	}
	public void messageSpyToggle(Player sender)
	{
		String playerName = sender.getName();
		if(!plugin.getConfig().getConfigurationSection("MessageSpy").contains(playerName))
		{
			plugin.getConfig().getConfigurationSection("MessageSpy").createSection(playerName);
		}

		boolean enabled = checkMessageSpy(playerName);
		enabled = !enabled;

		plugin.getConfig().set("MessageSpy." + playerName, enabled);

		if(enabled)
		{
			sender.sendMessage(ChatColor.RED + "You will now see messages!");
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "You will cease to see messages!");
		}

		plugin.saveConfig();
	}
	public boolean checkMessageSpy(String target)
	{
		boolean enabled = plugin.getConfig().getConfigurationSection("MessageSpy").getBoolean(target);
		return enabled;
	}
	public void messageSpy(CommandSender sender, String target, String message)
	{
		Player targetPlayer = Bukkit.getServer().getPlayer(target);
		String targetName = targetPlayer.getDisplayName();
		String senderName;
		ChatColor colorSender;
		if(sender instanceof Player)
		{
			Player player = (Player)sender;
			senderName = player.getDisplayName();
			colorSender = groupColor(player);
		}
		else
		{
			senderName = "Console";
			colorSender = ChatColor.RED;
		}
		ChatColor colorTarget = groupColor(targetPlayer);

		for(Player player1 : Bukkit.getServer().getOnlinePlayers())
		{
			if(!player1.equals(targetPlayer) && !player1.equals(sender))
			{
				String playerName = player1.getName();
				boolean enabled = checkMessageSpy(playerName);
				if(player1.hasPermission("olympus.usefulCommands.messageSpy.messageSpy") && enabled)
				{
					player1.sendMessage(ChatColor.GRAY + "[MS] " + colorSender + senderName + ChatColor.GRAY + ChatColor.BLUE + " -" +
						ChatColor.GREEN + "-" + ChatColor.AQUA + "-" + ChatColor.RED + "> " + colorTarget + targetName + ChatColor.GRAY + ": " + message);
				}
			}
		}
	}
	public void messagePlayer(CommandSender sender, String target, String message)
	{
		try
		{
			Player targetPlayer = Bukkit.getServer().getPlayer(target);
			target = targetPlayer.getName();
			if(sender instanceof Player)
			{

				Mutes mute = new Mutes(plugin);
				if(mute.checkMute(sender.getName()) && !targetPlayer.hasPermission("olympus.usefulCommands.ranks.staff"))
				{
					sender.sendMessage(ChatColor.RED + "You may only messages staff while you are muted!");
					return;
				}
				Player player = (Player)sender;
				if(VanishNoPacket.canSee(player, targetPlayer))
				{
					if(((Player)sender).equals(targetPlayer))
					{
						ChatColor colorSender = groupColor(player);
						sender.sendMessage(ChatColor.GRAY + "[PM] " + colorSender + "Me" + ChatColor.BLUE + " -" + ChatColor.GREEN + "-" + ChatColor.AQUA + "-" + ChatColor.RED + "> " + colorSender + "Me" + ChatColor.GRAY + ": " + message);
						log.info(ChatColor.GRAY + "(PM) " + player.getDisplayName() + ChatColor.AQUA + " -" + ChatColor.RED + "> " + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + message + ChatColor.RESET);

						messageSpy(sender, targetPlayer.getName(), message);

						plugin.getConfig().set("LastMessaged." + sender.getName(), target);
						plugin.getConfig().set("LastMessaged." + target, sender.getName());
						plugin.saveConfig();
					}
					else
					{
						ChatColor colorSender = groupColor(player);
						ChatColor colorTarget = groupColor(targetPlayer);

						boolean targetAfk = plugin.getConfig().getConfigurationSection("Afk").getBoolean(target);

						if(targetAfk)
						{
							sender.sendMessage(ChatColor.RED + "This player is afk! They may not see your message!");
						}

						sender.sendMessage(ChatColor.GRAY + "[PM] " + colorSender + "Me" + ChatColor.BLUE + " -" + ChatColor.GREEN + "-" + ChatColor.AQUA + "-" + ChatColor.RED + "> " + colorTarget + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + message);
						targetPlayer.sendMessage(ChatColor.GRAY + "[PM] " + colorSender + player.getDisplayName() + ChatColor.GRAY + ChatColor.BLUE + " -" + ChatColor.GREEN + "-" + ChatColor.AQUA + "-" + ChatColor.RED + "> " + colorTarget + "Me" + ChatColor.GRAY + ": " + message);
						log.info(ChatColor.GRAY + "(PM) " + player.getDisplayName() + ChatColor.AQUA + " -" + ChatColor.RED + "> " + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + message + ChatColor.RESET);

						messageSpy(sender, targetPlayer.getName(), message);

						plugin.getConfig().set("LastMessaged." + sender.getName(), target);
						plugin.getConfig().set("LastMessaged." + target, sender.getName());
						plugin.saveConfig();
					}
				}
				else
				{
					log.info("(PM) "+ player.getDisplayName() + " tried to message " + targetPlayer.getDisplayName() + " while " + targetPlayer.getDisplayName() + " was vanished!");
					throw new Exception("Tried to message vanished player");
				}
			}
			else
			{
				ChatColor colorSender = ChatColor.RED;
				ChatColor colorTarget = groupColor(targetPlayer);

				boolean targetAfk = plugin.getConfig().getConfigurationSection("Afk").getBoolean(target);

				if(targetAfk)
				{
					sender.sendMessage(ChatColor.RED + "This player is afk! They may not see your message!");
				}

				targetPlayer.sendMessage(ChatColor.GRAY + "[PM] " + colorSender + "Console" + ChatColor.GRAY + ChatColor.BLUE + " -" + ChatColor.GREEN + "-" + ChatColor.AQUA + "-" + ChatColor.RED + "> " + colorTarget + "Me" + ChatColor.GRAY + ": " + message);
				log.info(ChatColor.GRAY + "(PM) " + ChatColor.RED + "Console -> " + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + message);

				messageSpy(sender, targetPlayer.getName(), message);

				plugin.getConfig().set("LastMessaged." + target, sender.getName());
				plugin.saveConfig();
			}
		}
		catch(Exception e)
		{
			sender.sendMessage(ChatColor.GREEN + "There is no player with that name online!");
		}
	}
	public void messageReply(Player sender, String message)
	{
		String target = plugin.getConfig().getConfigurationSection("LastMessaged").getString(sender.getName());

		if(target.equalsIgnoreCase(""))
		{
			sender.sendMessage(ChatColor.RED + "You have not been messaged yet this session!");
		}
		else
		{
			messagePlayer(sender, target, message);
		}
	}
	public ChatColor groupColor(Player target)
	{
		ChatColor color;

		if(target.hasPermission("olympus.usefulcommands.ranks.grey"))
		{
			color = ChatColor.GRAY;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.trusted"))
		{
			color = ChatColor.GOLD;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.ironvip"))
		{
			color = ChatColor.GREEN;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.goldvip"))
		{
			color = ChatColor.DARK_GREEN;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.jrdev"))
		{
			color = ChatColor.BLUE;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.retired"))
		{
			color = ChatColor.YELLOW;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.mod"))
		{
			color = ChatColor.AQUA;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.admin"))
		{
			color = ChatColor.RED;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.uber"))
		{
			color = ChatColor.DARK_RED;
		}
		else if(target.hasPermission("olympus.usefulcommands.ranks.owner"))
		{
			color = ChatColor.DARK_PURPLE;
		}
		else
		{
			color = ChatColor.WHITE;
		}

		return color;
	}
}
