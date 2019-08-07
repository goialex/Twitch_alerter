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
public class NotificationSendFeature
{
	@Mock
	private Observer         notifier;
	@Mock
	private StreamDataGetter streamDataGetter;

	private TwitchAPIDataMock streamDataMock;

	private StateKeeper   stateKeeper;
	private ServerQuerier serverQuerier;
	private Set<String>   streamersWatched;

	@Before
	public void setUp() throws Exception
	{
		streamersWatched = new HashSet<>(Arrays.asList("kitboga", "t1"));

		stateKeeper = new StateKeeper();
		stateKeeper.subscribeOnlineObserver(name -> notifier.update(name));

		streamDataMock = new TwitchAPIDataMock();
		serverQuerier = new ServerQuerier(streamDataGetter, stateKeeper);
	}

	@Test
	public void send_notification_containing_name_of_user() throws IOException
	{
		Mockito.when(streamDataGetter.getActiveStreams())
			   .thenReturn(streamersWatched);
		serverQuerier.update();

		for (String streamer : streamersWatched)
			Mockito.verify(notifier).update(streamer);
	}
}

