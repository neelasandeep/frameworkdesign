package models.dbModel;

import java.util.HashMap;
import java.util.Map;

public class DatabaseModel {
	private Map<String, Object> modelData = null;

	public DatabaseModel() {
		modelData = new HashMap<>();
	}

	public void addModelProperty(String key, Object value) {
		modelData.put(key, value);

	}

	public Object getValueForProperty(String key) {
		return modelData.get(key);
	}

	public String getValueAsString(String key) {
		return modelData.get(key).toString();
	}

	public boolean isKeyPresent(String key) {
		return modelData.containsKey(key);
	}
}
