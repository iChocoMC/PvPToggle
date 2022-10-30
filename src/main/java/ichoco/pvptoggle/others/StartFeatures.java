package ichoco.pvptoggle.others;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import ichoco.pvptoggle.others.features.UniqueInventory;
import ichoco.pvptoggle.utils.Config_Util;
import ichoco.pvptoggle.utils.Location_Util;

public class StartFeatures {

	private static StartFeatures features;

	public Methods_Abstract normal;
	public Location_Util location_Util;
	public PlayerInventory inventory;

	private StartFeatures(){
		start();
	}

	public static StartFeatures getFeatures(){
		if (features == null){
			features = new StartFeatures();
		}
		return features;
	}

	//Start Features Here
	
	public void toggle(String mode, Player player){
		normal.toggle(mode, player);
	}

	private void start(){
		boolean 
			randomLocations = false,
			uniqueInventory = false;

		// Checks start here
		if(Config_Util.getConfiguration().getBoolean("unique-inventory")){
			uniqueInventory = true;
		}
		
		if(Config_Util.getConfiguration().getBoolean("random-locations.enable")){
			randomLocations = true;

			location_Util = new Location_Util();
			location_Util.setupUtil();

			for(String line : Config_Util.getConfiguration().getStringList("random-locations-list")){
				location_Util.newLocation(line);
			}
		}//End Here

		//Start options
		if(!uniqueInventory && randomLocations){
			normal = new Methods_Abstract() {
				@Override
				public boolean toggle(String mode, Player player) {
					if (!super.toggle(mode, player)){
						location_Util.teleport(player);
					}
					return false;
				}
			};
			return;
		}

		if(uniqueInventory && randomLocations){
			normal = new UniqueInventory(){
				@Override
				public boolean toggle(String mode, Player player) {
					super.toggle(mode, player);
					location_Util.teleport(player);
					return false;
				}
			};
			return;
		}

		if(uniqueInventory && !randomLocations){
			normal = new UniqueInventory(){};
			return;
		}
		normal = new Methods_Abstract(){};
		//End Options
	}
}