package personal.j.twitch_alerter;

import org.json.JSONArray;
import org.json.JSONObject;
import personal.j.twitch_alerter.twitch.TwitchAPI;
import personal.j.twitch_alerter.twitch.TwitchAPIStreamByUserName;
import personal.j.twitch_alerter.twitch.TwitchAPIUserInfo;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class Entry
{
	public static void main(String[] args) throws IOException
	{
		if (args.length < 2)
		{
			System.out.println("Wrong parameters, expected: \n" +
					"Twitch client ID which you get from their development website, \n" +
					"followed by the names of the streamers you want to get updated on.");
			return;
		}

		TwitchAPI.CLIENT_ID = args[0];

		downloadStreamerIcons(Arrays.copyOfRange(args, 1, args.length));
		TwitchWatcher twitchWatcher = new TwitchWatcher(new LinuxAlerter(),
				new DataGetter(new TwitchAPIStreamByUserName()),
				Arrays.copyOfRange(args, 1, args.length));
		twitchWatcher.start();
		System.out.println("Started listening...");
	}

	private static void downloadStreamerIcons(String... streamerNames)
	{
		TwitchAPI twitchAPI = new TwitchAPIUserInfo();
		for (String streamerName : streamerNames)
		{
			try
			{
				saveImages(twitchAPI, streamerName);

			} catch (IOException e)
			{
				System.out.println("Something went wrong,\nYou probably misspelled a username!");
				System.err.println(Arrays.toString(e.getStackTrace()));
			}
		}
	}

	private static void saveImages(TwitchAPI twitchAPI, String streamerName) throws IOException
	{
		JSONArray streamerInfo = (JSONArray) new
				JSONObject(twitchAPI.getServerResponse(streamerName)).get("data");

		String imageUrl = (String) ((JSONObject)streamerInfo.get(0)).get("profile_image_url");

		ImageIO.write(ImageIO.read(new URL(imageUrl)), "png",
				new File(System.getProperty("user.home") + "/.icons/"
						+ streamerName + ".png"));
	}
}
