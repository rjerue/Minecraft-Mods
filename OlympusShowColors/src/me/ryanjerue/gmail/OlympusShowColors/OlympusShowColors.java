package me.ryanjerue.gmail.OlympusShowColors;
 
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
 
public class OlympusShowColors extends JavaPlugin
{      
        public final Logger  log = Logger.getLogger("Minecraft");
        public static OlympusShowColors plugin;
       
        public void onEnable()
        {
                PluginDescriptionFile pdfFile = this.getDescription();
                this.log.info(pdfFile.getName() + " Version " + pdfFile.getVersion()  + " Has been Enabled!");
        }
        public void onDisable()
        {
                PluginDescriptionFile pdfFile = this.getDescription();
                this.log.info(pdfFile.getName() + " Has been Disabled!");      
        }
        public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args)
        {
                Player player = (Player) sender;
                ChatColor BLACK = ChatColor.BLACK;
                ChatColor DARKBLUE = ChatColor.DARK_BLUE;
                ChatColor DARKGREEN = ChatColor.DARK_GREEN;
                ChatColor DARKAQUA = ChatColor.DARK_AQUA;
                ChatColor DARKRED = ChatColor.DARK_RED;
                ChatColor PURPLE = ChatColor.DARK_PURPLE;
                ChatColor ORANGE = ChatColor.GOLD;
                ChatColor LIGHTGRAY = ChatColor.GRAY;
                ChatColor DARKGRAY = ChatColor.DARK_GRAY;
                ChatColor LIGHTBLUE = ChatColor.BLUE;
                ChatColor LIGHTGREEN = ChatColor.GREEN;
                ChatColor TEAL = ChatColor.AQUA;
                ChatColor RED = ChatColor.RED;
                ChatColor PINK = ChatColor.LIGHT_PURPLE;
                ChatColor YELLOW = ChatColor.YELLOW;
                ChatColor WHITE = ChatColor.WHITE;
               
                if( commandLabel.equalsIgnoreCase("showcolors") && args.length < 1 || args == null && player.hasPermission("olympus.showcolors.show") )
                {
                        player.sendMessage(BLACK + "Black &0" + WHITE + " | " + DARKBLUE + "Dark Blue &1" + WHITE + " | " + DARKGREEN + "Dark Green &2" + WHITE + " | " + DARKAQUA + "Dark Aqua &3");
                        player.sendMessage(DARKRED + "Dark Red &4" + WHITE + " | " + PURPLE + "Purple &5" + WHITE + " | " + ORANGE + "Orange &6" + WHITE + " | " + LIGHTGRAY + "Light Gray &7");
                        player.sendMessage(DARKGRAY + "Dark Gray &8" + WHITE + " | " + LIGHTBLUE + "Light Blue &9" + WHITE + " | " + LIGHTGREEN + "Light Green &a" + WHITE + " | " + TEAL + "Teal &b");
                        player.sendMessage(RED + "Red &c" + WHITE + " | " + PINK + "Pink &d" + WHITE + " | " + YELLOW + "Yellow &e" + WHITE + " | " + WHITE + "White &f");
                }
                if(commandLabel.equalsIgnoreCase("showcolors") && args.length > 1)
                {
                        player.sendMessage("Searching not yet enabled!");
                }
                return false;
        }      
}