package me.ryanjerue.usefulcommands;

import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class UsefulCommands extends JavaPlugin
{
	public final Logger log = Logger.getLogger("Minecraft");
	public static UsefulCommands plugin;

	public void onEnable()
	{
		PluginDescriptionFile pdfFile = getDescription();

		this.log.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Enabled!");
		getServer().getPluginManager().registerEvents(new OlympusListener(this), this);
		getConfig().options().copyDefaults(true);

		if(!getConfig().contains("BanItem"))
		{
			getConfig().createSection("BanItem");
		}
		if(!getConfig().contains("KickItem"))
		{
			getConfig().createSection("KickItem");
		}
		if(!getConfig().contains("LastMessaged"))
		{
			getConfig().createSection("LastMessaged");
		}
		if(!getConfig().contains("MessageSpy"))
		{
			getConfig().createSection("MessageSpy");
		}
		if(!getConfig().contains("Mutes"))
		{
			getConfig().createSection("Mutes");
		}
		if(!getConfig().contains("NickNames"))
		{
			getConfig().createSection("NickNames");
		}
		if(!getConfig().contains("Afk"))
		{
			getConfig().createSection("Afk");
		}

		saveConfig();
	}
	public void onDisable()
	{
  		PluginDescriptionFile pdfFile = getDescription();
	  	this.log.info(pdfFile.getName() + " Has been Disabled!");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Duty duty = new Duty(this);
		Lightning lightning = new Lightning(this);
		Macros macros = new Macros(this);
		Messaging messaging = new Messaging(this);
		Misc misc = new Misc(this);
		Mutes mutes = new Mutes(this);
		Nicks nicks = new Nicks(this);
		Op op = new Op(this);
		Ranks ranks = new Ranks(this);
		Report report = new Report(this);
		Seen seen = new Seen(this);
		StarGates starGates = new StarGates(this);
		AppHelp appHelp = new AppHelp(this);
		Afk afk = new Afk(this);

		boolean one = duty.onCommand(sender, cmd, commandLabel, args);
		boolean two = lightning.onCommand(sender, cmd, commandLabel, args);
		boolean three = macros.onCommand(sender, cmd, commandLabel, args);
		boolean four = messaging.onCommand(sender, cmd, commandLabel, args);
		boolean five = misc.onCommand(sender, cmd, commandLabel, args);
		boolean six = mutes.onCommand(sender, cmd, commandLabel, args);
		boolean seven = nicks.onCommand(sender, cmd, commandLabel, args);
		boolean eight = op.onCommand(sender, cmd, commandLabel, args);
		boolean nine = ranks.onCommand(sender, cmd, commandLabel, args);
		boolean ten = report.onCommand(sender, cmd, commandLabel, args);
		boolean eleven = seen.onCommand(sender, cmd, commandLabel, args);
		boolean twelve = starGates.onCommand(sender, cmd, commandLabel, args);
		boolean thirteen = appHelp.onCommand(sender, cmd, commandLabel, args);
		boolean fourteen = afk.onCommand(sender, cmd, commandLabel, args);

		if(one || two || three || four || five || six || seven || eight || nine || ten || eleven || twelve || thirteen || fourteen)
		{
			return true;
		}
		return false;
	}
}
