package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.TwitchUserFollowsDataGetter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.*;

public class FollowerIcons
{
	private TwitchUserFollowsDataGetter followsGetter;

	public FollowerIcons(TwitchUserFollowsDataGetter followsGetter)
	{
		this.followsGetter = followsGetter;
	}

	public void downloadFollowerIcons(String toPath)
	{
		Map<String, String> followerInfo = getFollowers();

		followerInfo.forEach((name, logoUrl) -> {
			try
			{
				File file = new File(toPath + "/" + name + ".png");
				if (!file.exists())
					ImageIO.write(ImageIO.read(new URL(logoUrl)), "PNG", file);

			} catch (IOException e)
			{
				System.out.println("Failed to download image: \n" + e.getMessage());
			}
		});
	}

	public Map<String, String> getFollowers()
	{
		Map<String, String>       followers  = new HashMap<>();
		List<Map<String, String>> followData = getUserFollowData(followsGetter);

		followData.forEach(stringMap ->
				followers.put(stringMap.get("name"), stringMap.get("logo")));

		return followers;
	}

	private List<Map<String, String>> getUserFollowData(TwitchUserFollowsDataGetter getter)
	{
		try
		{
			return getter.getUserFollowData();
		} catch (IOException e)
		{
			throw new UncheckedIOException("Could not get follow data from server: \n" + e.getMessage(), e);
		}
	}
}
