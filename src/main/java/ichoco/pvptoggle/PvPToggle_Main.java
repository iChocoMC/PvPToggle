package ichoco.pvptoggle;

import org.bukkit.plugin.java.JavaPlugin;

import ichoco.pvptoggle.commands.*;
import ichoco.pvptoggle.others.StartFeatures;
import ichoco.pvptoggle.utils.*;

public class PvPToggle_Main extends JavaPlugin {

	private static PvPToggle_Main main;

	public void OnEnable(){
		main = this;
		Config_Util.startUtil(this.getDataFolder());
		StartFeatures.getFeatures();

		this.getCommand("toggle").setExecutor(new PvPToggle_Command());
	}

	public static PvPToggle_Main getInstance(){
		return main;
	}
}