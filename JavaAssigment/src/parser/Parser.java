package parser;

import java.io.IOException;
import json.JsonArray;
import json.JsonDocument;
import json.JsonObject;
import json.JsonString;

public class Parser {

	public JsonArray parseArray(String json) throws IOException {
		Lexer2 lexer = new Lexer2(json);
		JsonArray array = new JsonArray();

		while (true) {
			JsonSymbol symbol = lexer.next();

			if (symbol.symbol == Symbol.STRING) {
				array.add(new JsonString(symbol.value));
			} else if (symbol.symbol == Symbol.CLOSED_SQUARE) {
				return array;
			} else if (symbol.symbol == Symbol.COMMA) {
				// do nothing
			} else if (symbol.symbol == Symbol.OPEN_CURLY) {
				JsonObject obj = parseObject(lexer);
				array.add(obj);
			} else if (symbol.symbol == Symbol.SPACE) {
				// parse space
				array.add(new JsonString(symbol.value));
			} else if (symbol.symbol == Symbol.NUMBER) {
				// parse number
				array.add(new JsonString(symbol.value));

			} else if (symbol.symbol == Symbol.OPEN_SQUARE) {
				// parse array

			} else {
				throw new IOException("not valid json");
			}
		}
	}

	public JsonDocument parse(String json) throws IOException {
		Lexer2 lexer = new Lexer2(json);
		JsonSymbol s = lexer.next();

		JsonDocument jToken;
		Symbol symbolType = s.symbol;
		if (symbolType == Symbol.OPEN_CURLY) {
			jToken = parseObject(lexer);
		} else {
			throw new IOException("JSON does not begin with {");
		}
		return jToken;

	}

	public JsonObject parseObject(Lexer2 lexer) throws IOException {

		JsonObject object = new JsonObject();
		String key = null;
		Symbol lastsymbol = Symbol.OPEN_CURLY;
		while (true) {
			JsonSymbol jsonSymbol = lexer.next();
			System.out.println(jsonSymbol.value);

			Symbol symbol = jsonSymbol.symbol;
			if (symbol == Symbol.STRING && (lastsymbol == Symbol.COMMA || lastsymbol == Symbol.OPEN_CURLY)) {
				key = jsonSymbol.value;
				System.out.println("added key, " + key);
				lastsymbol = symbol;
			} else if (symbol == Symbol.STRING && lastsymbol == Symbol.COLON) {
				System.out.println("added value " + key);
				object.put(key, new JsonString(jsonSymbol.value));
				lastsymbol = symbol;
			} else if (symbol == Symbol.CLOSED_CURLY) {
				return object;
			} else if (symbol == Symbol.COMMA) {
				// do nothing
				lastsymbol = symbol;
			} else if (symbol == Symbol.OPEN_CURLY) {
				JsonObject obj = parseObject(lexer);
				object.put(key, obj);
				lastsymbol = Symbol.CLOSED_CURLY;

			} else if (symbol == Symbol.SPACE) {
				// parse space

			} else if (symbol == Symbol.COLON) {
				lastsymbol = symbol;

			} else if (symbol == Symbol.NUMBER) {
				// parse number
				// list.add(jsonSymbol.value);

			} else if (symbol == Symbol.OPEN_SQUARE) {
				// parse array
				lastsymbol = Symbol.CLOSED_SQUARE;
			} else {
				throw new IOException("not valid json");
			}
		}
	}

}
