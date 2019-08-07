package com.personal.j.twitch_alerter.DataGetter.API;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TwitchV5APIUserFollowsShould
{
	TwitchAPI twitchAPI;

	@Ignore("This is just to see the server data")
	@Test
	public void retrieveDataFromServer() throws IOException
	{
		twitchAPI = new TwitchV5APIUserFollows(
				"", "");
		System.out.println(twitchAPI.getServerData());
	}
}