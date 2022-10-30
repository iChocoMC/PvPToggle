package ichoco.pvptoggle.utils;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ichoco.pvptoggle.PvPToggle_Main;

public class Config_Util {

	private File file;
	private FileConfiguration config;

	private Config_Util(File location, String name) {
		this.file = new File(location, name);
		if (!this.file.exists()){
			PvPToggle_Main.getInstance().saveResource(name, false); 
		}
		this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
	}

	private FileConfiguration getConfig()
	{ return this.config; }

	/*
	 * Start Util:
	 * create messages.yml - config.yml and modes.yml
	 */

	private static FileConfiguration modes_Config;
	private static FileConfiguration configuration_config;

	public static void startUtil(File folder){
		if (!folder.exists()){
			folder.mkdirs();
		}
		configuration_config = new Config_Util(folder, "config.yml").getConfig();
		modes_Config = new Config_Util(folder, "modes.yml").getConfig();
	}

	//Getters methods
	public static FileConfiguration getModes()
	{ return modes_Config; }

	public static FileConfiguration getConfiguration()
	{ return configuration_config; }//End here
}
