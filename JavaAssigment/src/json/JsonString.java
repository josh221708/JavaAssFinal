package json;

public class JsonString extends JsonDocument {
	private final String string;

	public JsonString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

}
