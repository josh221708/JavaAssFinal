package parser;

public class JsonSymbol {
	public final Symbol symbol;
	public final String value;

	JsonSymbol(Symbol type, String value) {
		this.symbol = type;
		this.value = value;
	}
}
