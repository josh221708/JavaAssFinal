package json;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonDocument {
	private List<JsonDocument> list = new ArrayList<>();

	public void add(JsonDocument doc) {
		list.add(doc);
	}

	public JsonDocument get(int i) {
		return list.get(i);
	}

	public int size() {
		return list.size();
	}
}
