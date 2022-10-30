package ichoco.pvptoggle.others;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import ichoco.pvptoggle.utils.Config_Util;

public abstract class Methods_Abstract {
	private static HashMap<UUID, PlayerInventory> playersInventory;

	public boolean toggle(String mode, Player player){
		if(playersInventory.containsKey(player.getUniqueId())){
			backInventory(mode, player);
			return true;
		}

		addItems(mode, player);
		return false;
	}

	public void backInventory(String mode, Player player){
		PlayerInventory playerInventory = player.getInventory();

		if(playersInventory.containsKey(player.getUniqueId())){
			PlayerInventory Inventory_HASHMAP = playersInventory.get(player.getUniqueId());

			playerInventory.setContents(Inventory_HASHMAP.getContents());
			playerInventory.setArmorContents(Inventory_HASHMAP.getArmorContents());

			playersInventory.remove(player.getUniqueId(), playerInventory);
			return;
		}
	}

	//Start static methods
	private static ItemStack item(String string){
		return new ItemStack(Material.getMaterial(Config_Util.getModes().getString(string)));
	}

	private static void addItems(String mode, Player player){
		String
			armor = mode+".armor.",
			items = mode+".items";

		PlayerInventory playerInventory = player.getInventory();

		playerInventory.setArmorContents(new ItemStack[]{
			item(armor + "boots"),
			item(armor + "leggins"),
			item(armor + "chestplate"),
			item(armor + "healtmet")
		});

		for(String line : Config_Util.getModes().getStringList(items)){
			if(line.contains(",")){
				String[] slipt = line.split(",");
				ItemStack item = new ItemStack(Material.getMaterial((slipt[0])));
				item.setAmount(Integer.parseInt(slipt[1]));

				playerInventory.addItem(item);
			return;
		}
		playerInventory.addItem(new ItemStack(Material.getMaterial((line))));
		}
	}//End here
}
