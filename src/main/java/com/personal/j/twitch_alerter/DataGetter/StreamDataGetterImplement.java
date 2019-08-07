package com.personal.j.twitch_alerter.DataGetter;


import com.personal.j.twitch_alerter.DataGetter.API.TwitchNewApiStream;
import com.personal.j.twitch_alerter.DataGetter.decoders.StreamDataDecoder;

import java.io.IOException;
import java.util.Set;

public class StreamDataGetterImplement implements StreamDataGetter
{
	private TwitchNewApiStream twitchStreamApi;
	private StreamDataDecoder  streamDataDecoder;

	public StreamDataGetterImplement(String client_ID, Set<String> usernames)
	{
		twitchStreamApi = new TwitchNewApiStream(client_ID, usernames);
		streamDataDecoder = new StreamDataDecoder();
	}

	@Override
	public Set<String> getActiveStreams() throws IOException
	{
		String serverData = twitchStreamApi.getServerData();
		return streamDataDecoder.getFields(serverData);
	}
}
