package task;

import java.io.IOException;

public class InstructionHandler {
	public String instruction(String instruction, String param1, String param2) throws IOException {
		String answer = null;
		if (instruction.equals("add")) {
			Integer sum = Integer.valueOf(param1) + Integer.valueOf(param2);
			answer = sum.toString();
		} else if (instruction.equals("multiply")) {
			Integer product = Integer.valueOf(param1) * Integer.valueOf(param2);
			answer = product.toString();
		}

		else if (instruction.equals("concat")) {

			answer = param1 + param2;
		} else {
			throw new IOException();

		}
		return answer;
	}

}
