package parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import parser.Lexer2;
import parser.Symbol;

public class TestLexer2 {

	@Test
	public void testOpenCurley() throws IOException {
		Lexer2 mylexer = new Lexer2("{");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.OPEN_CURLY, mysymbol.symbol);
	}

	@Test
	public void testClosedCurley() throws IOException {
		Lexer2 mylexer = new Lexer2("}");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.CLOSED_CURLY, mysymbol.symbol);
	}

	@Test
	public void testComma() throws IOException {
		Lexer2 mylexer = new Lexer2(",");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.COMMA, mysymbol.symbol);
	}

	@Test
	public void testOpenSquare() throws IOException {
		Lexer2 mylexer = new Lexer2("[");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.OPEN_SQUARE, mysymbol.symbol);
	}

	@Test
	public void testClosedSquare() throws IOException {
		Lexer2 mylexer = new Lexer2("]");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.CLOSED_SQUARE, mysymbol.symbol);
	}

	@Test
	public void testBracketsAndColon() throws IOException {
		Lexer2 mylexer = new Lexer2("{:}");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.OPEN_CURLY, mysymbol.symbol);

		JsonSymbol colon = mylexer.next();
		assertEquals(Symbol.COLON, colon.symbol);

		JsonSymbol closedCurley = mylexer.next();
		assertEquals(Symbol.CLOSED_CURLY, closedCurley.symbol);
	}

	@Test
	public void teststring() throws IOException {
		Lexer2 mylexer = new Lexer2("bob");
		JsonSymbol mysymbol = mylexer.next();
		assertEquals(Symbol.STRING, mysymbol.symbol);
		assertEquals("bob", mysymbol.value);
	}

	@Test
	public void teststringinarray() throws IOException {
		Lexer2 mylexer = new Lexer2("[bob]");
		JsonSymbol open = mylexer.next();
		assertEquals(Symbol.OPEN_SQUARE, open.symbol);
		assertEquals("[", open.value);

		JsonSymbol string = mylexer.next();
		assertEquals(Symbol.STRING, string.symbol);
		assertEquals("bob", string.value);

		JsonSymbol closed = mylexer.next();
		assertEquals(Symbol.CLOSED_SQUARE, closed.symbol);
		assertEquals("]", closed.value);
	}
}
