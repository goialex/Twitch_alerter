package com.personal.j.twitch_alerter.DataGetter.API;

import java.io.IOException;

public class TwitchV5APIUserFollows extends TwitchAPI
{
	private final static String CLIENT_HEADER_KEY = "Client-ID";
	private final static String ACCEPT_HEADER_KEY = "Accept";
	private final static String ACCEPT_HEADER_VALUE =
			"application/vnd.twitchtv.v5+json";


	private final static String BASE_URL   = "https://api.twitch.tv/kraken/users";
	private final static String URL_SUFFIX = "follows/channels";

	private HttpConnectionBuilder connectionBuilder;

	public TwitchV5APIUserFollows(String clientID,
								  String userID)
	{
		connectionBuilder = new HttpConnectionBuilder(BASE_URL);
		connectionBuilder.putHeader(CLIENT_HEADER_KEY, clientID);
		connectionBuilder.putHeader(ACCEPT_HEADER_KEY, ACCEPT_HEADER_VALUE);
		connectionBuilder.putParameter("limit", "100");
		connectionBuilder.appendStringToURL(userID).appendStringToURL(URL_SUFFIX);
	}

	@Override
	public String getServerData() throws IOException
	{
		return readResponseContent(connectionBuilder.getConnection());
	}
}
