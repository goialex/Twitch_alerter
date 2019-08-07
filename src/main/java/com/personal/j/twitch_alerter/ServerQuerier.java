package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.StreamDataGetter;
import com.personal.j.twitch_alerter.Notifiiers.StdoutNotifier;

import java.io.IOException;
import java.util.*;

public class ServerQuerier
{
	private StreamDataGetter dataGetter;
	private StateKeeper stateKeeper;
	private Set<String>    streamersWatched;

	public ServerQuerier(StreamDataGetter dataGetter, StateKeeper stateKeeper)
	{
		this.dataGetter = dataGetter;
		this.stateKeeper = stateKeeper;
		this.streamersWatched = new HashSet<>();
	}

	public void update()
	{
		Set<String> activeStreams = getActiveStreams();
		stateKeeper.update(activeStreams);
	}

	private Set<String> getActiveStreams()
	{
		try
		{
			return dataGetter.getActiveStreams();
		} catch (IOException e)
		{
			System.out.println("Server error: " + e.getMessage());
			return new HashSet<>();
		}
	}
}
