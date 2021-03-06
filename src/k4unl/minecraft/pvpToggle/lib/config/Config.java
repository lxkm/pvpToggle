package k4unl.minecraft.pvpToggle.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Config {
	private static class configOption{
		private String key;
		private boolean isBool;
		private boolean val;
		private boolean def;
		private int valInt;
		private int defInt;
		
		public configOption(String _key, boolean _def){
			key = _key;
			val = _def;
			def = _def;
			isBool = true;
		}
		public configOption(String _key, int _def){
			key = _key;
			valInt = _def;
			defInt = _def;
			isBool = false;
		}
		
		public String getKey(){
			return key;
		}
		
		public boolean getBool(){
			return val;
		}
		
		public int getInt(){
			return valInt;
		}
		
		public void loadFromConfig(Configuration config){
			if(isBool){
				val = config.get(config.CATEGORY_GENERAL, key, def).getBoolean(def);
			}else{
				valInt = config.get(config.CATEGORY_GENERAL, key, defInt).getInt(defInt);
			}
		}
	}
	private static final List<configOption> configOptions = new ArrayList<configOption>();

	static {
        configOptions.add(new configOption("keepInventoryOnPVPDeath", false));
        configOptions.add(new configOption("keepExperienceOnPVPDeath", false));
        configOptions.add(new configOption("showMessageOnLogin", true));
        configOptions.add(new configOption("coolDownInSeconds", 30));
	}
	
	public static void loadConfigOptions(Configuration c){

		for(configOption config : configOptions){
			config.loadFromConfig(c);
		}
	}
	public static boolean getBool(String key){
		for(configOption config : configOptions){
			if(config.getKey() == key){
				return config.getBool();
			}
		}
		return false;
	}
	
	public static int getInt(String key){
		for(configOption config : configOptions){
			if(config.getKey() == key){
				return config.getInt();
			}
		}
		return 0;
	}
}
