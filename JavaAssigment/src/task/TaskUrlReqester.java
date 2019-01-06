package task;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import http.GetRequester;

public class TaskUrlReqester {

	public JsonArray retreveTaskUrls(String studentid) throws IOException {

		GetRequester requester = new GetRequester();
		String initialConnection = requester.sendGetRequest("/student?id=" + studentid);

		JsonParser jsonParser = new JsonParser();
		JsonElement firstrespose = jsonParser.parse(initialConnection);
		JsonObject firstresposeobject = firstrespose.getAsJsonObject();
		JsonArray taskurls = firstresposeobject.get("tasks").getAsJsonArray();
		return taskurls;
	}
}
