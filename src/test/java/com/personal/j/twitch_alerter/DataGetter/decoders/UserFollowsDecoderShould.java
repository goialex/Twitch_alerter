package com.personal.j.twitch_alerter.DataGetter.decoders;

import com.personal.j.twitch_alerter.TwitchAPIDataMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class UserFollowsDecoderShould
{
	UserFollowsDecoder followsDecoder;

	@Before
	public void setUp() throws Exception
	{
		followsDecoder = new UserFollowsDecoder();
	}

	@Test
	public void decode_valid_user_data()
	{
		List<Map<String, String>> expected = new ArrayList<>();
		List<Map<String, String>> result   = followsDecoder.getFields(getTestResponse());

		Map<String, String> eMap = new HashMap<>();
		eMap.put("mature", "true");
		eMap.put("status", "this");
		expected.add(new HashMap<>(eMap));
		eMap.put("mature", "false");
		eMap.put("status", "Something");
		expected.add(eMap);

		assertListContainsSameElem(expected, result);
	}

	private void assertListContainsSameElem(List<Map<String, String>> expected, List<Map<String, String>> result)
	{
		expected.forEach((stringStringMap) -> { assert(result.contains(stringStringMap)); });
	}

	private String getTestResponse()
	{
		return "{\"_total\":2,\"follows\":[{\"created_at\":\"2341\",\"channel\":{\"mature\":false,\"status\":\"Something\"},\"notifications\":false}," +
				"{\"created_at\":\"2341\",\"channel\":{\"mature\":true,\"status\":\"this\"},\"notifications\":false}]}";
	}
}