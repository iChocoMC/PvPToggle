package ichoco.pvptoggle.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Location_Util{
	private ArrayList<Location> locations;
	private Random random;

	public void setupUtil(){
		locations = new ArrayList<Location>();
		random = new Random(); 
	}

	public void newLocation(String line) {
		String[] parts = line.split(",");
		locations.add(new Location(
			Bukkit.getWorld(parts[3]), 
			Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
	}

	public void addLocation(Location location){
		locations.add(location);
	}

	public void teleport(Player player){
		player.teleport(locations.get(random.nextInt(locations.size())));
	}
}