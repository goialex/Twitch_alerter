package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.StreamDataGetter;
import com.personal.j.twitch_alerter.DataGetter.StreamDataGetterImplement;
import com.personal.j.twitch_alerter.DataGetter.UserFollowsGetter;
import com.personal.j.twitch_alerter.Notifiiers.LinuxNotifier;
import com.personal.j.twitch_alerter.Notifiiers.StdoutNotifier;

import java.util.*;

public class Entry
{
	public static void main(String[] args) throws InterruptedException
	{
		if (args.length < 2)
			throw new IllegalStateException("Not enough arguments");
		String clientID = args[0];
		String userID = args[1];

		new FollowerIcons(new UserFollowsGetter(clientID, userID))
				.downloadFollowerIcons(System.getProperty("user.home") + "/.icons");

		Set<String> streamersToWatch = new FollowerUserNames(new UserFollowsGetter(clientID, userID))
				.getFollowers();

		StreamDataGetter streamDataGetter = new StreamDataGetterImplement(
				clientID, streamersToWatch);

		StateKeeper stateKeeper = new StateKeeper();
		ServerQuerier serverQuerier = new ServerQuerier(streamDataGetter,
				stateKeeper);

		stateKeeper.subscribeOnlineObserver(new StdoutNotifier("Just went live: "));
		stateKeeper.subscribeOnlineObserver(new LinuxNotifier());

		new StdoutNotifier("").update("Started querying");
		while(true)
		{
			serverQuerier.update();
			Thread.sleep(15000);
		}
	}
}
