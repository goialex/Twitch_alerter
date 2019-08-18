package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.StreamDataGetter;
import com.personal.j.twitch_alerter.DataGetter.StreamDataGetterImplement;
import com.personal.j.twitch_alerter.DataGetter.TwitchUserFollowsDataGetter;
import com.personal.j.twitch_alerter.Notifiiers.LinuxNotifier;
import com.personal.j.twitch_alerter.Notifiiers.StdoutNotifier;

import java.util.*;

public class Entry
{
	public static void main(String[] args) throws InterruptedException
	{
		if (args.length < 2)
			throw new IllegalArgumentException("Not enough arguments");

		String clientID = args[0];
		String userID = args[1];

		downloadStreamerIcons(clientID, userID, System.getProperty("user.home") + "/.icons");
		ServerQuerier serverQuerier = initializeServerQuerier(clientID, userID);
		System.out.println("Started querying");

		while(true)
		{
			serverQuerier.update();
			Thread.sleep(15000);
		}
	}

	private static ServerQuerier initializeServerQuerier(String clientID, String userID)
	{
		Set<String> streamersToWatch = new FollowerUserNames(new TwitchUserFollowsDataGetter(clientID, userID))
				.getFollowerNames();

		StreamDataGetter streamDataGetter = new StreamDataGetterImplement(
				clientID, streamersToWatch);

		StateKeeper stateKeeper = initStateKeeper();
		ServerQuerier serverQuerier = new ServerQuerier(streamDataGetter,
				stateKeeper);

		return serverQuerier;
	}

	private static StateKeeper initStateKeeper()
	{
		StateKeeper stateKeeper = new StateKeeper();

		stateKeeper.subscribeOnlineObserver(new StdoutNotifier("Just went live: "));
		stateKeeper.subscribeOnlineObserver(new LinuxNotifier());
		return stateKeeper;
	}

	private static void downloadStreamerIcons(String clientID, String userID, String toPath)
	{
		new FollowerIcons(new TwitchUserFollowsDataGetter(clientID, userID))
				.downloadFollowerIcons(toPath);
	}
}
