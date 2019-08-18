package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.TwitchUserFollowsDataGetter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FollowerUserNames
{
	private TwitchUserFollowsDataGetter followsGetter;

	public FollowerUserNames(TwitchUserFollowsDataGetter followsGetter)
	{
		this.followsGetter = followsGetter;
	}

	public Set<String> getFollowerNames()
	{
		Set<String>               followers  = new HashSet<>();
		List<Map<String, String>> followData = getUserFollowData(followsGetter);

		followData.forEach(dataMap -> followers.add(dataMap.get("name")));

		return followers;
	}

	private List<Map<String, String>> getUserFollowData(TwitchUserFollowsDataGetter getter)
	{
		try
		{
			return getter.getUserFollowData();
		} catch (IOException e)
		{
			throw new UncheckedIOException("Could not get follow data from server: \n" + e.getMessage(), e);
		}
	}

}
