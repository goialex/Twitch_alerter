package com.personal.j.twitch_alerter;

import java.util.*;

public class StateKeeper
{
	private Map<String, Boolean> streamerStatus;

	private List<Observer> onlineObservers;
	private List<Observer> offlineObservers;

	public StateKeeper()
	{
		this.streamerStatus = new HashMap<>();

		this.onlineObservers = new ArrayList<>();
		this.offlineObservers = new ArrayList<>();
	}

	public void update(Set<String> activeStreamers)
	{
		for (String activeStreamer : activeStreamers)
			if (!streamerStatus.getOrDefault(activeStreamer, false))
			{
				streamerStatus.put(activeStreamer, true);
				callOnlineObserver(activeStreamer);
			}

		for (String key : streamerStatus.keySet())
			if (!activeStreamers.contains(key))
			{
				streamerStatus.put(key, false);
				callOfflineObserver(key);
			}
	}

	private void callOnlineObserver(String name)
	{
		for (Observer observer : onlineObservers)
			observer.update(name);
	}

	private void callOfflineObserver(String name)
	{
		for (Observer observer : offlineObservers)
			observer.update(name);
	}


	public void subscribeOnlineObserver(Observer onlineObserver)
	{
		onlineObservers.add(onlineObserver);
	}

	public void subscribeOfflineObserver(Observer offlineObserver)
	{
		offlineObservers.add(offlineObserver);
	}
}
