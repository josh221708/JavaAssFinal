package parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import json.JsonArray;
import json.JsonDocument;
import json.JsonObject;

public class TestParser {
	@Test
	public void testSingleString() throws IOException {
		Parser parser = new Parser();
		JsonArray list = parser.parseArray("[bob]");
		assertEquals("bob", list.get(0).toString());
	}

	@Test
	public void testDoubleString() throws IOException {
		Parser parser = new Parser();
		JsonArray list = parser.parseArray("[bob,abc]");
		assertEquals(2, list.size());
		assertEquals("bob", list.get(0).toString());
		assertEquals("abc", list.get(1).toString());
	}

	@Test
	public void testNumber() throws IOException {
		Parser parser = new Parser();
		JsonArray list = parser.parseArray("[5]");
		assertEquals(1, list.size());
		assertEquals("5", list.get(0).toString());
	}

	@Test
	public void testSpace() throws IOException {
		Parser parser = new Parser();
		JsonArray list = parser.parseArray("[ ]");
		assertEquals(1, list.size());
		assertEquals(" ", list.get(0).toString());
	}

	@Test
	public void testNumberAndSpace() throws IOException {
		Parser parser = new Parser();
		JsonArray list = parser.parseArray("[7, ]");
		assertEquals(2, list.size());
		assertEquals("7", list.get(0).toString());
		assertEquals(" ", list.get(1).toString());
	}

	@Test
	public void testJsonObject() throws IOException {
		Parser parser = new Parser();
		JsonDocument map = parser.parse("{a:b}");
		assertEquals("{a=b}", map.toString());
	}

	@Test
	public void testObjectInObject() throws IOException {
		Parser parser = new Parser();
		JsonDocument map = parser.parse("{a:{b:c}}");
		assertEquals("{a={b=c}}", map.toString());
	}
}
