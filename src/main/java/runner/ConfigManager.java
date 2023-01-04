package runner;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigManager {

	private ConfigReader configReader;
	
	public ConfigManager() throws IOException {

		this.configReader = new ConfigReader("config.json");
	}
	
	public String getConfigValue(String configKey) {
		
		return configReader.getString(configKey);
	}
	
	private class ConfigReader {

		private JsonNode rootNode;

		public ConfigReader(String fileName) throws IOException {

			ObjectMapper mapper = new ObjectMapper();
			rootNode = mapper.readTree(new File(fileName));
		}

		public String getString(String fieldName) {

			return rootNode.get(fieldName).textValue();
		}

	}

}
