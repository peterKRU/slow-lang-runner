package runner;

import java.io.IOException;

public class ConstantsTable {
	
	private static ConfigManager configManager;
	
	static {
		
		try {
			
			configManager = new ConfigManager();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static final String COMPILER_EXECUTABLE_NAME = configManager.getConfigValue("COMPILER_EXECUTABLE_NAME");
	public static final String EXECUTION_ENGINE_EXECUTABLE_NAME = configManager.getConfigValue("EXECUTION_ENGINE_EXECUTABLE_NAME");
	
}
