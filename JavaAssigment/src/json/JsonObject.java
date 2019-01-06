package json;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonDocument {
	private Map<String, JsonDocument> map = new HashMap<>();

	public void put(String string, JsonDocument doc) {
		map.put(string, doc);

	}

	@Override
	public String toString() {
		return map.toString();
	}
}
