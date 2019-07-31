package personal.j.twitch_alerter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import personal.j.twitch_alerter.twitch.TwitchAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class DataGetterTest
{
	DataGetter dataGetter;

	@Test
	void singleDataObj() throws IOException
	{
		TwitchAPI twitchAPIMock = Mockito.mock(TwitchAPI.class);
		dataGetter = new DataGetter(twitchAPIMock);

		when(twitchAPIMock.getServerResponse(anyString())).thenReturn(jsonExample);

		List<Map<String, String>> result = dataGetter.getData("LIRIK");

		assertMapEquals(jsonEquivalent, result.get(0));
	}

	private void assertMapEquals(Map<String, String> jsonEquivalent,
								 Map<String, String> stringStringMap)
	{
		assert (jsonEquivalent.size() == stringStringMap.size());

		for (String key : jsonEquivalent.keySet())
		{
			assert (stringStringMap.containsKey(key));
			assertEquals(jsonEquivalent.get(key), stringStringMap.get(key));
		}
	}

	private final static Map<String, String> jsonEquivalent = new HashMap<>();

	static
	{
		jsonEquivalent.put("id", "26007494656");
		jsonEquivalent.put("user_name", "LIRIK");
		jsonEquivalent.put("game_id", "417752");
		jsonEquivalent.put("community_ids",
				"[\"5181e78f-2280-42a6-873d-758e25a7c313\"," +
						"\"848d95be-90b3-44a5-b143-6e373754c382\"," +
						"\"fd0eab99-832a-4d7e-8cc0-04d73deb2e54\"]");
	}

	private final static String jsonExample =
			"{\n" +
					"  \"data\": [\n" +
					"    {\n" +
					"      \"id\": \"26007494656\",\n" +
					"      \"user_name\": \"LIRIK\",\n" +
					"      \"game_id\": \"417752\",\n" +
					"      \"community_ids\": [\n" +
					"        \"5181e78f-2280-42a6-873d-758e25a7c313\",\n" +
					"        \"848d95be-90b3-44a5-b143-6e373754c382\",\n" +
					"        \"fd0eab99-832a-4d7e-8cc0-04d73deb2e54\"\n" +
					"      ],\n" +
					"    },\n" +
					"  ],\n" +
					"  \"pagination\": {\n" +
					"    \"cursor\": \"eyJiIjpudWxsLCJhIjp7Ik9mZnNldCI6MjB9fQ==\"\n" +
					"  }\n" +
					"}";
}