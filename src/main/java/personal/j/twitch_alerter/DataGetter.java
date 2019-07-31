package personal.j.twitch_alerter;

import org.json.JSONArray;
import org.json.JSONObject;
import personal.j.twitch_alerter.twitch.TwitchAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGetter
{
	private TwitchAPI twitchAPI;

	public DataGetter(TwitchAPI twitchAPI)
	{
		this.twitchAPI = twitchAPI;
	}

	public List<Map<String, String>> getData(String... userNames) throws IOException
	{
		String response = twitchAPI.getServerResponse(userNames);

		JSONArray serverDataJson =
				(JSONArray) new JSONObject(response).get("data");

		List<Map<String, String>> result = parseJson(serverDataJson);
		return result;
	}

	private List<Map<String, String>> parseJson(JSONArray serverDataJson)
	{
		List<Map<String, String>> result = new ArrayList<>();

		for (int i = 0; i < serverDataJson.length(); i++)
		{
			Map<String, String> data = new HashMap<>();
			for (String key : ((JSONObject) serverDataJson.get(i)).keySet())
				 data.put(key, ((JSONObject) serverDataJson.get(i)).get(key).toString());
			result.add(data);
		}

		return result;
	}
}
