package com.personal.j.twitch_alerter.DataGetter.decoders;

import com.personal.j.twitch_alerter.TwitchAPIDataMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StreamDataDecoderShould
{
	private StreamDataDecoder streamDataDecoder;

	private TwitchAPIDataMock streamDataMock;

	@Before
	public void setUp() throws Exception
	{
		streamDataDecoder = new StreamDataDecoder();
		streamDataMock = new TwitchAPIDataMock();
	}

	@Test
	public void return_user_name_list_from_response_data_single_user()
	{
		String userName = "TestUsername1";
		List<String> expected = Arrays.asList(userName);
		Set<String> result = streamDataDecoder.getFields(
				streamDataMock.getStreamData(new String[]{userName}));

		Assert.assertTrue(result.containsAll(expected));
	}

	@Test
	public void return_user_name_list_from_response_data_multiple_users()
	{
		String userName = "TestUsername1";
		String userName2 = "TestUsername2";
		List<String> expected = Arrays.asList(userName, userName2);
		Set<String> result = streamDataDecoder.getFields(
				streamDataMock.getStreamData(new String[]{userName, userName2}));

		Assert.assertTrue(result.containsAll(expected));
	}

	@Test
	public void return_empty_set_when_no_data()
	{
		Set<String> expected = Collections.emptySet();
		Set<String> result = streamDataDecoder.getFields("{\"data\":[],\"pagination\":{}}");

		Assert.assertTrue(result.containsAll(expected));
	}
}