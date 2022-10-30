package ichoco.pvptoggle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ichoco.pvptoggle.others.StartFeatures;
import ichoco.pvptoggle.utils.Location_Util;

public class PvPToggle_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player)sender;
		if(args.length == 1){ //TEST
			if(args[0].equals("addloc")){
				new Location_Util().addLocation(player.getLocation());
			}
			return true;
		}
		player.sendMessage("Beta PvPToggle - 0.2");
		StartFeatures.getFeatures().toggle("easy", player);
		return false;
	}
}
