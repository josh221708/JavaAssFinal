package task;

import java.io.IOException;

import com.google.gson.JsonArray;

public class MainMethord {

	public static void main(String[] args) throws IOException {
		TaskUrlReqester urlreqester = new TaskUrlReqester();
		JsonArray taskurls = urlreqester.retreveTaskUrls("S189263");
		TaskProcesser processer = new TaskProcesser();
		processer.processUrls(taskurls);

	}
}
