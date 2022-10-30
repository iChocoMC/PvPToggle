package ichoco.pvptoggle.others;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import ichoco.pvptoggle.utils.Config_Util;
import ichoco.pvptoggle.utils.Location_Util;

public class StartFeatures {

	private static StartFeatures features;

	public Methods_Abstract random_Locations, unique_inventory, normal;
	public Location_Util location_Util;
	public PlayerInventory inventory;

	private StartFeatures(){
		start();
	}

	public void toggle(String mode, Player player){
		normal.toggle(mode, player);
	}

	private void start(){
		boolean 
			randomLocations_Boolean = false,
			uniqueInventory_Boolean = false;

		if(Config_Util.getConfiguration().getBoolean("random-locations.enable")){
			randomLocations_Boolean = true;

			location_Util = new Location_Util();
			location_Util.setupUtil();

			for(String line : Config_Util.getConfiguration().getStringList("random-locations-list")){
				location_Util.newLocation(line);
			}

			random_Locations = new Methods_Abstract() {
				@Override
				public boolean toggle(String mode, Player player) {
					if (!super.toggle(mode, player)){
						location_Util.teleport(player);
					}
					return false;
				}
			};
		}

		if(Config_Util.getConfiguration().getBoolean("unique-inventory")){
			uniqueInventory_Boolean = true;

			if (random_Locations == null){
				unique_inventory = new Methods_Abstract() {
					@Override
					public void backInventory(String mode, Player player) {
						if(inventory == null){
							inventory = player.getInventory();
							return;
						}
	
						PlayerInventory playerInventory = player.getInventory();
						playerInventory.setArmorContents(inventory.getArmorContents());
						playerInventory.setContents(inventory.getContents());
					}
				};
			} else {
				unique_inventory = new Methods_Abstract() {
					@Override
					public boolean toggle(String mode, Player player) {
						random_Locations.toggle(mode, player);
						return false;
					}

					public void backInventory(String mode, Player player) {
						if(inventory == null){
							inventory = player.getInventory();
							return;
						}
	
						PlayerInventory playerInventory = player.getInventory();
						playerInventory.setArmorContents(inventory.getArmorContents());
						playerInventory.setContents(inventory.getContents());
					}
				};
			}
		}

		if(randomLocations_Boolean == false && uniqueInventory_Boolean == false){
			normal = new Methods_Abstract() {};
			return;
		}
		if(randomLocations_Boolean == false && uniqueInventory_Boolean == true){
			normal = unique_inventory;
			return; 
		}
		if(randomLocations_Boolean == true && uniqueInventory_Boolean == false){
			normal = random_Locations;
			return; 
		}
	}

	public static StartFeatures getFeatures(){
		if (features == null){
			features = new StartFeatures();
		}
		return features;
	}
}