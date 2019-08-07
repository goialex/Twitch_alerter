package com.personal.j.twitch_alerter.DataGetter.decoders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class UserFollowsDecoder
{
	public List<Map<String, String>> getFields(String data)
	{
		List<Map<String, String>> result = new ArrayList<>();
		JSONObject                jData = new JSONObject(data);
		JSONArray                 followerData = jData.getJSONArray("follows");

		for (int i = 0; i < followerData.length(); i++)
		{
			Map<String, String> map = new HashMap<>();
			JSONObject          channelData = followerData.getJSONObject(i).getJSONObject("channel");
			channelData.keys().forEachRemaining((name) -> map.put(name, channelData.get(name).toString()));

			result.add(map);
		}

		return result;
	}
}
