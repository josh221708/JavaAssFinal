package task;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestTask {

	@Test
	public void testadd() throws IOException {
		InstructionHandler handler = new InstructionHandler();
		String awnser = handler.instruction("add", "10", "20");
		assertEquals("30", awnser);
	}

	@Test
	public void testmultiply() throws IOException {
		InstructionHandler handler = new InstructionHandler();
		String awnser = handler.instruction("multiply", "10", "20");
		assertEquals("200", awnser);
	}

	@Test
	public void testconcat() throws IOException {
		InstructionHandler handler = new InstructionHandler();
		String awnser = handler.instruction("concat", "10", "20");
		assertEquals("1020", awnser);
	}

	@Test(expected = IOException.class)
	public void testsubtract() throws IOException {
		InstructionHandler handler = new InstructionHandler();
		handler.instruction("subtract", "10", "20");

	}

}
