package com.personal.j.twitch_alerter.DataGetter;

import com.personal.j.twitch_alerter.DataGetter.API.TwitchAPI;
import com.personal.j.twitch_alerter.DataGetter.API.TwitchV5APIUserFollows;
import com.personal.j.twitch_alerter.DataGetter.decoders.UserFollowsDecoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserFollowsGetter
{
	private TwitchAPI          twitchAPI;
	private UserFollowsDecoder followsDecoder;

	public UserFollowsGetter(String clientID, String userId)
	{
		twitchAPI = new TwitchV5APIUserFollows(clientID, userId);
		this.followsDecoder = new UserFollowsDecoder();
	}

	public List<Map<String, String>> getUserFollowData() throws IOException
	{
		String serverData = twitchAPI.getServerData();

		return followsDecoder.getFields(serverData);
	}
}
