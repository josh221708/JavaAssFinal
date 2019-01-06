package task;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import http.GetRequester;
import http.PostRequester;

public class TaskProcesser {
	private JsonParser jsonParser = new JsonParser();
	private GetRequester requester = new GetRequester();

	public void processUrls(JsonArray taskUrls) throws IOException {
		for (JsonElement element : taskUrls) {
			processTask(element);
		}
	}

	String processTask(JsonElement element) throws IOException {
		String taskURL = null;
		try {
			taskURL = element.getAsString();

			String firstTask = requester.sendGetRequest(taskURL);
			JsonObject firstTaskResponse = jsonParser.parse(firstTask).getAsJsonObject();
			String answerUrl = firstTaskResponse.get("response URL").getAsString(); 
			String instruction = firstTaskResponse.get("instruction").getAsString(); 

			JsonArray parameters = firstTaskResponse.get("parameters").getAsJsonArray();
			InstructionHandler instructionHandler = new InstructionHandler();
			String answer = instructionHandler.instruction(instruction, parameters.get(0).getAsString(),
					parameters.get(1).getAsString());

			PostRequester postRequester = new PostRequester();
			postRequester.sendPostRequest(answerUrl, answer);
		} catch (IOException e) {
			System.out.println("Not a valid instruction");
			PostRequester postRequester = new PostRequester();
			postRequester.sendPostRequest(taskURL, "Error not valid instruction");

		} catch (IllegalStateException e) {
			System.out.println("Error Invaild Json");
			PostRequester postRequester = new PostRequester();
			postRequester.sendPostRequest(taskURL, "Error invailid Json");
		}
		return taskURL;
	}
}
