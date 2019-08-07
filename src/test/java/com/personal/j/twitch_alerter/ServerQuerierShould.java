package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.StreamDataGetter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class ServerQuerierShould
{
	@Mock
	private StreamDataGetter streamDataGetter;
	@Mock
	private StateKeeper      stateKeeper;

	private TwitchAPIDataMock apiSampler;

	private ServerQuerier serverQuerier;

	private Set<String> streamersWatched;

	@Before
	public void setUp() throws Exception
	{
		streamersWatched = new HashSet<>(Arrays.asList("streamer1", "streamers2"));

		serverQuerier = new ServerQuerier(streamDataGetter, stateKeeper);

		apiSampler = new TwitchAPIDataMock();
	}

	@Test
	public void call_StreamDataGetter_when_update_is_called() throws IOException
	{
		serverQuerier.update();
		Mockito.verify(streamDataGetter).getActiveStreams();
	}

	@Test
	public void call_stateKeeper_update_with_live_streamers() throws IOException
	{
		Mockito.when(streamDataGetter.getActiveStreams())
			   .thenReturn(streamersWatched);

		serverQuerier.update();

		Mockito.verify(stateKeeper).update(streamersWatched);
	}

	@Test
	public void return_empty_set_when_DataGetter_throws() throws IOException
	{
		Mockito.when(streamDataGetter.getActiveStreams())
			   .thenThrow(new IOException());

		serverQuerier.update();

		Mockito.verify(stateKeeper).update(new HashSet<>());
	}


}