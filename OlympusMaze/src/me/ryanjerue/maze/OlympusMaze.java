package me.ryanjerue.maze;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class OlympusMaze extends JavaPlugin{
	public final Logger  logger = Logger.getLogger("Minecraft");
    public static OlympusMaze plugin;
    public int mn;
    public Location sLoc;
    public ArrayList<String> winners = new ArrayList<String>();
    
    @SuppressWarnings("unchecked")
	@Override
    public void onEnable()
    {
    	try{
			SerializableLocation sp = (SerializableLocation) SLAPI.load("plugins/olympusmaze/sLoc.bin");
			sLoc = sp.getLocation();
		} 
    	catch (Exception e) {
			logger.info("[MAZE] Error Loading Location file");
			sLoc = Bukkit.getWorlds().get(1).getSpawnLocation();
		}
    	try{
			winners = (ArrayList<String>) SLAPI.load("plugins/olympusmaze/winners.bin");
		} 
    	catch (Exception e) {
			logger.info("[MAZE] Error Loading Winners file");
		}
        PluginDescriptionFile pdffile = this.getDescription();
        this.logger.info(pdffile.getName() + ", version: " + pdffile.getVersion() + ", is now enabled.");           
    }
    
    @Override
    public void onDisable()
    {
    	save();
        PluginDescriptionFile pdffile = this.getDescription();
        this.logger.info(pdffile.getName() + " is now disabled.");
    }
    
    public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args){
    	PluginDescriptionFile pdffile = this.getDescription();
    	Player player = (Player) sender;
    	ChatColor LIGHTGREEN = ChatColor.GREEN;
        ChatColor RED = ChatColor.RED;
        ChatColor DARKGREEN = ChatColor.DARK_GREEN;
        ChatColor WHITE = ChatColor.WHITE;
        ChatColor DARKRED = ChatColor.DARK_RED;
        if(commandLabel.equalsIgnoreCase("mazehelp") && (args.length < 1 || args == null) && (sender instanceof Player)){ //help
        	player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] Version: " + pdffile.getVersion() + " by " + DARKRED + "Doombuggie41");
        	if(player.hasPermission("olympus.maze.canwin"))
        		player.sendMessage("/mazewin -Type when you are in the maze win area!");
        	if(player.hasPermission("olympus.maze.canspawn"))
        		player.sendMessage("/mazespawn -Moves you to the beggining of the maze!");
        	if(player.hasPermission("olympus.maze.cansetspawn"))
        		player.sendMessage("/mazespawnset -Allows you to set spawn of the maze");
        	if(player.hasPermission("olympus.maze.canseewinners"))
        		player.sendMessage("/mazewinlist # -Lets you 5 winners, more if you use a number after command.");
        	
        }
        
        else if(commandLabel.equalsIgnoreCase("mazewin") && player.hasPermission("olympus.maze.canwin") && (args.length < 1 || args == null) && (sender instanceof Player)){
        	if (hasWon(player.getName(), winners) == false){
        		winners.add(player.getName());
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + " Congratulations! Tell an " + RED + "Admin+" + WHITE + " that you completed the maze to win a prize!");
        	}
        	else
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] You have already won the maze before!");
        	player.teleport(sLoc);
        	save();
        }
        
        else if(commandLabel.equalsIgnoreCase("mazespawnset")&& player.hasPermission("olympus.maze.cansetspawn") && (args.length < 1 || args == null) && (sender instanceof Player)){
        	sLoc = player.getLocation();
        	player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + "Maze Spawn Set!");
        	save();
        }
        
        else if(commandLabel.equalsIgnoreCase("mazespawn")&& player.hasPermission("olympus.maze.canspawn") && (args.length < 1 || args == null) && (sender instanceof Player)){
        	player.teleport(sLoc);
        	player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + "You have returned to Maze Spawn.");
        }
        
        else if(commandLabel.equalsIgnoreCase("mazewinlist")&& player.hasPermission("olympus.maze.canseewinners") && (sender instanceof Player)){
        	if (winners.isEmpty())
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + "Nobody has won the maze yet!.");
        	else{
        		int wn = 5;
            	try{
            	    wn = Integer.parseInt(args[0]);;
            	}
            	catch (Exception e){
            	}
        		if(wn > winners.size())
        			wn = winners.size();
        		String wnS = String.valueOf(wn);
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + "The latest " + DARKGREEN + wnS + WHITE + " winners are:");
        		for (int c = 0; c < wn; c++ )
        			player.sendMessage(winners.get(winners.size() -1 - c));
        	}
        }
        
        else if(commandLabel.equalsIgnoreCase("mazewinsearch")&& player.hasPermission("olympus.maze.canseewinners") && (sender instanceof Player)){
        	if (hasWon(args[0], winners))
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + args[0] + " has completed the maze before!");
        	else
        		player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] " + args[0] + " has " + RED + "NOT " + WHITE + "completed the maze before!");
        }
        
        else if(commandLabel.equalsIgnoreCase("mazewinsearch")&& player.hasPermission("olympus.maze.save") && (sender instanceof Player)){
        	save();
        	player.sendMessage("[" + LIGHTGREEN + "MAZE" + WHITE + "] Maze files saved");
        }
        
    	return false;
    }
    
    public void save(){
        SerializableLocation sp = new SerializableLocation(sLoc);
    	try{
    		SLAPI.save(sp, "plugins/olympusmaze/sLoc.bin");
    		SLAPI.save(winners, "plugins/olympusmaze/winners.bin");
    	}
    	catch(Exception e){
    		logger.info("[MAZE] Error saving file(s)!");
    	}
    }
    
    public static boolean hasWon(String s, ArrayList<String> winners){
    	for (int c = 0; c < winners.size(); c ++){
    		if (s.equals(winners.get(c)))
    			return true;
    	}
    	return false;
    }
}
