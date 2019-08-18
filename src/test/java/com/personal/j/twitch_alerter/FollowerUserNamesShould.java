package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.TwitchUserFollowsDataGetter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class FollowerUserNamesShould
{
	@Mock
	TwitchUserFollowsDataGetter followsGetter;

	FollowerUserNames followerInformation;

	@Before
	public void setUp() throws Exception
	{
		followerInformation = new FollowerUserNames(followsGetter);
	}

	@Test
	public void call_followsGetter() throws IOException
	{
		followerInformation.getFollowerNames();
		Mockito.verify(followsGetter).getUserFollowData();
	}

	@Test(expected = UncheckedIOException.class)
	public void throw_an_exception_on_server_failure()
	{
		Mockito.when(followerInformation.getFollowerNames()).thenThrow(new IOException());

		followerInformation.getFollowerNames();
	}

	@Test
	public void return_a_set_of_followed_channels_for_a_user() throws IOException
	{
		String name1 = "test1";
		String name2 = "test2";

		Set<String> expected = new HashSet<>();
		expected.add(name1);
		expected.add(name2);

		Mockito.when(followsGetter.getUserFollowData()).thenReturn(createFollowNameListMock(name1, name2));

		Assert.assertEquals(expected, followerInformation.getFollowerNames());
	}

	private List<Map<String, String>> createFollowNameListMock(String name1, String name2)
	{
		List<Map<String, String>> followMock = new ArrayList<>();
		followMock.add(new HashMap<>());
		followMock.add(new HashMap<>());
		followMock.get(0).put("name", name1);
		followMock.get(1).put("name", name2);
		return followMock;
	}
}