package me.ryanjerue.usefulcommands;

import java.util.StringTokenizer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class OlympusListener implements Listener
{
	public static UsefulCommands plugin;

	public OlympusListener(UsefulCommands instance)
	{
		plugin = instance;
	}
	@EventHandler
	public void PlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String playerName = player.getName();

		if(checkMute(playerName))
		{
			player.sendMessage(ChatColor.RED + "You are muted!");
			event.setCancelled(true);
			return;
		}
		if(event.getMessage().startsWith("@") && !event.getMessage().substring(1, 2).equalsIgnoreCase(" "))			//weird interactions with coloredtexts
		{
			String message = event.getMessage();
			String target = message.substring(1, message.indexOf(" "));
			int tarLength = target.length();
			try
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(target);
				target = targetPlayer.getName();
			}
			catch(Exception e)
			{
			}
			event.setCancelled(true);
			Bukkit.getServer().dispatchCommand(player, "msg " + target + " " + message.substring(tarLength + 2));
		}
		if(plugin.getConfig().getConfigurationSection("Afk").getBoolean(event.getPlayer().getName()))
		{
			plugin.getConfig().set("Afk." + event.getPlayer().getName(), false);
			Bukkit.getServer().broadcastMessage(event.getPlayer().getDisplayName() + ChatColor.GRAY + " is now unafk!");
			plugin.saveConfig();
		}
	}
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		String playerName = player.getName();

		if(!(plugin.getConfig().getConfigurationSection("LastMessaged").contains(playerName)))
		{
			plugin.getConfig().getConfigurationSection("LastMessaged").createSection(playerName);
		}
		if(!(plugin.getConfig().getConfigurationSection("MessageSpy").contains(playerName)))
		{
			plugin.getConfig().getConfigurationSection("MessageSpy").createSection(playerName);
			plugin.getConfig().set("MessageSpy." + playerName, false);
		}
		if(!(plugin.getConfig().getConfigurationSection("Mutes").contains(playerName)))
		{
			plugin.getConfig().getConfigurationSection("Mutes").createSection(playerName);
			plugin.getConfig().set("Mutes." + playerName, false);
		}
		if(!(plugin.getConfig().getConfigurationSection("NickNames").contains(playerName)))
		{
			plugin.getConfig().getConfigurationSection("NickNames").createSection(playerName);
			plugin.getConfig().set("NickNames." + playerName, "");
		}
		if(!(plugin.getConfig().getConfigurationSection("Afk").contains(playerName)))
		{
			plugin.getConfig().getConfigurationSection("Afk").createSection(playerName);
			plugin.getConfig().set("Afk." + playerName, false);
		}
		if(plugin.getConfig().getConfigurationSection("NickNames").contains(playerName) && plugin.getConfig().isSet("NickNames." + playerName) && !(plugin.getConfig().getConfigurationSection("NickNames").getString(playerName).equalsIgnoreCase("")))
		{
			player.setDisplayName(plugin.getConfig().getConfigurationSection("NickNames").getString(playerName));
			player.sendMessage(ChatColor.GREEN + "Your nickname has been changed to " + plugin.getConfig().getConfigurationSection("NickNames").getString(playerName) + "!");
		}

		if(player.hasPermission("olympus.usefulCommands.ranks.grey"))
		{
			for(Player player1: Bukkit.getServer().getOnlinePlayers())
			{
				if(player1.hasPermission("olympus.usefulCommands.ranks.alert"))
				{
					player1.sendMessage(ChatColor.DARK_PURPLE + "A grey rank has joined!" + ChatColor.RESET);
				}
			}
			plugin.log.info(ChatColor.DARK_PURPLE + "A grey rank has joined!" + ChatColor.RESET);
			greyNoStaff(player);
		}
		Bukkit.getServer().dispatchCommand(player, "who");
		plugin.getConfig().set("LastMessaged." + playerName, "");
		plugin.saveConfig();
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void interaction(EntityDamageByEntityEvent event)
	{
		if(event.getCause().toString().equals("ENTITY_ATTACK"))
		{
			EntityDamageByEntityEvent damageEvent = event;
			if(damageEvent.getDamager() instanceof Player && damageEvent.getEntity() instanceof Player)
			{
				Player player = (Player)damageEvent.getDamager();
				Player target = (Player)damageEvent.getEntity();
				int blockId = player.getItemInHand().getType().getId();
				if(blockId == plugin.getConfig().getInt("BanItem") && player.hasPermission("olympus.usefulCommands.banHammer.allowBan"))
				{
					Bukkit.getServer().dispatchCommand(player, "ban " + target.getName() + " The BanHammer Has Spoken!");
				}
				else if(blockId == plugin.getConfig().getInt("KickItem") && player.hasPermission("olympus.usefulCommands.banHammer.allowKick"))
				{
					Bukkit.getServer().dispatchCommand(player, "kick " + target.getName() + " Booted!");
					//target.kickPlayer("Booted!");
				}
			}
		}
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void PlayerCommand(PlayerCommandPreprocessEvent event)
	{
		Player sender = event.getPlayer();
		String said = event.getMessage();
		StringTokenizer token = new StringTokenizer(said);
		String command = token.nextToken();

		if(command.equalsIgnoreCase(plugin.getConfig().getString("Ban Command")) && sender.hasPermission(plugin.getConfig().getString("Ban Node")))
		{
			try
			{
				String playerName = token.nextToken();
				Player target = Bukkit.getPlayer(playerName);
				World world = target.getWorld();

				world.strikeLightningEffect(target.getLocation());
			}
			catch(Exception e)
			{
				World world = sender.getWorld();
				world.strikeLightningEffect(world.getSpawnLocation());
			}
		}
		else if(event.getMessage().toLowerCase().startsWith("/plugins") || event.getMessage().toLowerCase().startsWith("/pl ") || event.getMessage().toLowerCase().equals("/pl") || event.getMessage().toLowerCase().startsWith("/ver") || event.getMessage().toLowerCase().startsWith("/gc") || event.getMessage().toLowerCase().startsWith("/version"))
		{
			event.setCancelled(!(event.getPlayer().hasPermission("olympus.usefulCommands.plugins.see")));
		}
		else if(event.getMessage().toLowerCase().startsWith("/help"))
		{
			event.setCancelled(!(event.getPlayer().hasPermission("olympus.usefulCommands.ranks.staff")));
		}
	}
	@EventHandler
	public void PlayerMove(PlayerMoveEvent event)
	{
		if(plugin.getConfig().getConfigurationSection("Afk").getBoolean(event.getPlayer().getName()))
		{
			plugin.getConfig().set("Afk." + event.getPlayer().getName(), false);
			Bukkit.getServer().broadcastMessage(event.getPlayer().getDisplayName() + ChatColor.GRAY + " is now unafk!");
			plugin.saveConfig();
		}
	}
	public boolean checkMute(String target)
	{
		try
		{
			Player targetPlayer = plugin.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception localException)
		{
		}

		boolean muted = plugin.getConfig().getConfigurationSection("Mutes").getBoolean(target);
		return muted;
	}
	public boolean checkMessageSpy(String target)
	{
		try
		{
			Player targetPlayer = plugin.getServer().getPlayer(target);
			target = targetPlayer.getName();
		}
		catch(Exception localException)
		{
		}

		boolean enabled = plugin.getConfig().getConfigurationSection("MessageSpy").getBoolean(target);
		return enabled;
	}
	public void greyNoStaff(Player joiner)
	{
		int staff = 0;
		ChatColor start = ChatColor.DARK_PURPLE, regular = ChatColor.GREEN, olympus = ChatColor.DARK_AQUA;

		for(Player player: Bukkit.getOnlinePlayers())
		{
			if(player.hasPermission("olympus.usefulCommands.ranks.staff"))
			{
				staff++;
			}
		}
		if(staff == 0)
		{
			joiner.sendMessage(start + "Hey there!");
			joiner.sendMessage(regular + "-It would appear that we don't have any staff on at this time to help you register in person, but you can sign up on your own.");
			joiner.sendMessage(regular + "-To do so, use the \"helpme\" command.");
			joiner.sendMessage(regular + "-Once again, " + olympus + "Welcome to Olympus!");
		}
	}
}
