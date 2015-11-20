package me.ryanjerue.OlympusApplicationHelp;
 
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
 
public class OlympusApplicationHelp extends JavaPlugin
{ 
    public final Logger  logger = Logger.getLogger("Minecraft");
    public static OlympusApplicationHelp plugin;
   
    @Override
    public void onEnable()
    {
        PluginDescriptionFile pdffile = this.getDescription();
        this.logger.info(pdffile.getName() + ", version: " + pdffile.getVersion() + ", is now enabled.");           
    }
    
    @Override 
    public void onDisable()
    {
        PluginDescriptionFile pdffile = this.getDescription();
        this.logger.info(pdffile.getName() + " is now disabled.");
    }    
    public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        ChatColor LIGHTGREEN = ChatColor.GREEN;
        ChatColor GRAY = ChatColor.GRAY;
        ChatColor WHITE = ChatColor.WHITE;
        PluginDescriptionFile pdffile = this.getDescription();
        
        try{
	        if(commandLabel.equalsIgnoreCase("apphelp") && isOnline(args[0], sender) && (sender instanceof ConsoleCommandSender))
			{
	        	Player grey = (Bukkit.getServer().getPlayer(args[0]));
	            sendMessage(grey); //Sends to gray
	            toStaff(grey); //Sends confirm to all staff
	            return true;
			}
	        if(commandLabel.equalsIgnoreCase("apphelp") && (sender instanceof ConsoleCommandSender)){
	        	return true;
	        }
        }
        catch( ArrayIndexOutOfBoundsException e ){}
        try{
	        Player player = (Player) sender;       
	        if(commandLabel.equalsIgnoreCase("apphelp") && player.hasPermission("olympus.applicationhelp.help") && (args.length < 1 || args == null || args[0].equalsIgnoreCase("help")) && (sender instanceof Player)) //help menu
		    {
			    player.sendMessage(LIGHTGREEN + "[APPHELP] " + WHITE + "Version: " + pdffile.getVersion());
		        player.sendMessage("/apphelp " + GRAY + "membername");
		        player.sendMessage("/apphelp message");
		    }
		    else if(commandLabel.equalsIgnoreCase("apphelp") && player.hasPermission("olympus.applicationhelp.helpme") && (args.length < 1 || args == null || args[0].equalsIgnoreCase("message"))&& (sender instanceof Player)) //player wants the message
		    {
			    sendMessage(player);
		    }
		    else if(commandLabel.equalsIgnoreCase("apphelp") && player.hasPermission("olympus.applicationhelp.help") && isOnline(args[0], player)&& (sender instanceof Player)) //sends selected player the message
		    {
		        Player grey = (Bukkit.getServer().getPlayer(args[0]));
		        sendMessage(grey); //Sends to gray
		        toStaff(grey); //Sends confirm to all staff
		    }
		    else if(commandLabel.equalsIgnoreCase("apphelp") && (player.hasPermission("olympus.applicationhelp.help") == false)&& (sender instanceof Player)) //if not grey and not staff
		    {
		        player.sendMessage(LIGHTGREEN + "[APPHELP]" + LIGHTGREEN + " You must be a staff member to use /apphelp!");
		    }
        }
        catch( ClassCastException e){sender.sendMessage("[APPHELP] Please send this command as a player.");}
        
        return false;
    }               
    public boolean isOnline(String pn, CommandSender sender)
    {
        Player other = (Bukkit.getServer().getPlayer(pn));
        if (other == null) 
        {
        	sender.sendMessage(ChatColor.RED + pn + " is not online!");
        	return false;
        }
        return true;
    }
    
    public void toStaff(Player gray) //sends message to staff saying that the message was sent to gray
    {
    	ChatColor GRAY = ChatColor.GRAY;
    	ChatColor LIGHTGREEN = ChatColor.GREEN;
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(player.hasPermission("olympus.staff"))
            {
            	player.sendMessage(LIGHTGREEN + "[APPHELP] " + GRAY + gray.getName() + LIGHTGREEN + " has been sent apphelp!");
            }
        }
    	
    	
    			
    }
    public void sendMessage(Player player)
    {
    	ChatColor LIGHTGREEN = ChatColor.GREEN;
        ChatColor RED = ChatColor.RED;
        ChatColor PINK = ChatColor.LIGHT_PURPLE;
        ChatColor GOLD = ChatColor.GOLD;
        ChatColor DARKGREEN = ChatColor.DARK_GREEN;
        ChatColor GRAY = ChatColor.GRAY;
    	player.sendMessage(PINK + "= = = = = = = = = = = = = = = = = = = = = = = =");
        player.sendMessage(RED + "Welcome to Olympus! " + LIGHTGREEN + "To Join please make an account on our");
        player.sendMessage(LIGHTGREEN + "forums at " +RED + "http://Olympus-Gaming.net/");
        player.sendMessage( LIGHTGREEN + "Once you have made an account,");
        player.sendMessage(LIGHTGREEN + "Please visit:" + RED + " http://olympus-gaming.net/apply/ ");
        player.sendMessage(LIGHTGREEN + "Remember, applying is " + DARKGREEN + "EASY " + LIGHTGREEN + "and " + GOLD + "FREE" + LIGHTGREEN + "!" );
        player.sendMessage(PINK + "= = = = = = = = = = = = = = = = = = = = = = = =");
        logger.info(LIGHTGREEN + "[APPHELP] " + GRAY + player.getDisplayName() + LIGHTGREEN + " has been sent apphelp!");
    }
}