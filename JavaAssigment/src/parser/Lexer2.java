package parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

public class Lexer2 {
	private PushbackReader reader;

	public Lexer2(String mystring) {
		reader = new PushbackReader(new StringReader(mystring));
	}

	public JsonSymbol next() throws IOException {

		int c = reader.read();
		if (c == '{') {
			return new JsonSymbol(Symbol.OPEN_CURLY, "{");
		}
		if (c == '}') {
			return new JsonSymbol(Symbol.CLOSED_CURLY, "}");

		}

		if (c == ',') {
			return new JsonSymbol(Symbol.COMMA, ",");

		}

		if (c == ' ') {
			return new JsonSymbol(Symbol.SPACE, " ");

		}

		if (c == '5') {
			return new JsonSymbol(Symbol.NUMBER, "5");

		}

		if (c == '[') {
			return new JsonSymbol(Symbol.OPEN_SQUARE, "[");

		}

		if (c == ']') {
			return new JsonSymbol(Symbol.CLOSED_SQUARE, "]");
		}
		if (c == ':') {
			return new JsonSymbol(Symbol.COLON, ":");
		}
		if (Character.isLetterOrDigit(c)) {
			StringBuffer value = new StringBuffer();
			while (Character.isLetterOrDigit(c)) { // collect extra digits
				value.append((char) c);
				c = reader.read();
			}
			if (-1 != c)
				reader.unread(c);
			return new JsonSymbol(Symbol.STRING, value.toString());
		}

		return null;
	}

}
