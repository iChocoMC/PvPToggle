package ichoco.pvptoggle.others.features;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import ichoco.pvptoggle.others.Methods_Abstract;

public class UniqueInventory extends Methods_Abstract {
	public static PlayerInventory inventory;

	public boolean toggle(String mode, Player player) {
		if(inventory == null){
			inventory = player.getInventory();
			return true;
		}

		if(player.getInventory().equals(inventory)){
			backInventory(mode, player);
			return true;
		}

		addItems(mode, player);
		return false;
	}

	public void backInventory(String mode, Player player) {
		PlayerInventory playerInventory = player.getInventory();
		playerInventory.setArmorContents(inventory.getArmorContents());
		playerInventory.setContents(inventory.getContents());
	}
}
