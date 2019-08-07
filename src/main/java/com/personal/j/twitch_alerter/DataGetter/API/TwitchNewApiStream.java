package com.personal.j.twitch_alerter.DataGetter.API;

import java.io.IOException;
import java.util.Set;

public class TwitchNewApiStream extends TwitchAPI
{
	private final static String CLIENT_HEADER_KEY = "Client-ID";
	private final static String BASE_URL          =
			"https://api.twitch.tv/helix/streams";
	private final static String PARAMETER_NAME    = "user_login";


	private String      clientHeaderValue;
	private Set<String> userNames;

	public TwitchNewApiStream(String clientHeaderValue, Set<String> userNames)
	{
		this.clientHeaderValue = clientHeaderValue;
		this.userNames = userNames;
	}

	@Override
	public String getServerData() throws IOException
	{
		HttpConnectionBuilder connectionBuilder = new HttpConnectionBuilder(BASE_URL);
		connectionBuilder.putHeader(CLIENT_HEADER_KEY, clientHeaderValue);
		userNames.forEach((username) ->
				connectionBuilder.putParameter(PARAMETER_NAME, username));

		return readResponseContent(connectionBuilder.getConnection());
	}
}