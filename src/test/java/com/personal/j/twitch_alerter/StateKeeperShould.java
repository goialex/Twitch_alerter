package com.personal.j.twitch_alerter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class StateKeeperShould
{
	@Mock
	private Observer onlineObserver;
	@Mock
	private Observer offlineObserver;

	private StateKeeper stateKeeper;

	@Before
	public void setUp() throws Exception
	{
		stateKeeper = new StateKeeper();
	}

	@Test
	public void subscribe_online_and_offline_events()
	{
		stateKeeper.subscribeOnlineObserver(onlineObserver);
		stateKeeper.subscribeOfflineObserver(offlineObserver);
	}

	@Test
	public void call_online_event_observers_on_active_stream()
	{
		String testStream1 = "test1";

		stateKeeper.subscribeOnlineObserver(onlineObserver);
		stateKeeper.update(new HashSet<>(Arrays.asList(testStream1)));

		Mockito.verify(onlineObserver).update(testStream1);
	}

	@Test
	public void call_online_event_observers_on_active_streams()
	{
		String testStream1 = "test1";
		String testStream2 = "test2";

		stateKeeper.subscribeOnlineObserver(onlineObserver);
		stateKeeper.update(new HashSet<>(Arrays.asList(testStream1, testStream2)));

		Mockito.verify(onlineObserver).update(testStream1);
		Mockito.verify(onlineObserver).update(testStream2);
	}

	@Test
	public void call_offline_event_observers_on_offline_streams()
	{
		String testStream1 = "test1";
		String testStream2 = "test2";

		stateKeeper.subscribeOfflineObserver(offlineObserver);
		stateKeeper.update(new HashSet<>(Arrays.asList(testStream1, testStream2)));
		stateKeeper.update(new HashSet<>());

		Mockito.verify(offlineObserver).update(testStream1);
		Mockito.verify(offlineObserver).update(testStream2);
	}
}