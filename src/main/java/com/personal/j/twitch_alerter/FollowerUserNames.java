package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.UserFollowsGetter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FollowerUserNames
{
	private UserFollowsGetter followsGetter;

	public FollowerUserNames(UserFollowsGetter followsGetter)
	{
		this.followsGetter = followsGetter;
	}

	public Set<String> getFollowers()
	{
		Set<String>               followers  = new HashSet<>();
		List<Map<String, String>> followData = getUserFollowData(followsGetter);

		followData.forEach(stringStringMap -> followers.add(stringStringMap.get("name")));

		return followers;
	}

	private List<Map<String, String>> getUserFollowData(UserFollowsGetter getter)
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
