package com.personal.j.twitch_alerter.DataGetter.decoders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class StreamDataDecoder implements Decoder
{
	public Set<String> getFields(String data)
	{
		Set<String> result = new HashSet<>();
		JSONObject jsonData = new JSONObject(data);

		JSONArray jsonStreams =  jsonData.getJSONArray("data");
		for (int i = 0; i < jsonStreams.length(); i++)
		{
			JSONObject streamData = jsonStreams.getJSONObject(i);
			result.add(streamData.get("user_name").toString());
		}

		return result;
	}
}
