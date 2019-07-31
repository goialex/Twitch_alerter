package personal.j.twitch_alerter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitchWatcher extends Thread
{
	private String[]             watchedStreamers;
	private Map<String, Boolean> streamersOldStatus;
	private Alerter              alerter;
	private DataGetter           dataGetter;

	public TwitchWatcher(Alerter alerter, DataGetter dataGetter, String... streamersUserNames) throws IOException
	{
		assert (streamersUserNames.length > 0) : "You forgot to specify any users";

		this.alerter = alerter;
		this.dataGetter = dataGetter;
		this.watchedStreamers = streamersUserNames;

		streamersOldStatus = getCurrentStatus(watchedStreamers);
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Map<String, Boolean> streamCurrentStatus = getCurrentStatus(watchedStreamers);
				updateIfStatusChanged(streamCurrentStatus);

				Thread.sleep(60_000);
			} catch (InterruptedException e)
			{
				System.out.println("Thread stopped");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public synchronized Map<String, Boolean> getStreamerStatus()
	{
		return new HashMap<>(streamersOldStatus);
	}

	private void updateIfStatusChanged(Map<String, Boolean> streamCurrentStatus)
	{
		for (String userName : streamCurrentStatus.keySet())
		{
			if (isStreamerOnline(streamersOldStatus, userName) != isStreamerOnline(streamCurrentStatus, userName))
			{
				streamersOldStatus.put(userName, isStreamerOnline(streamCurrentStatus, userName));
				if (isStreamerOnline(streamCurrentStatus, userName))
					alerter.update(userName);
			}
		}
	}

	private Boolean isStreamerOnline(Map<String, Boolean> streamCurrentStatus, String userName)
	{
		return streamCurrentStatus.get(userName);
	}

	private Map<String, Boolean> getCurrentStatus(String[] usernames) throws IOException
	{
		Map<String, Boolean>      streamStatus = new HashMap<>(usernames.length);
		List<Map<String, String>> streamerData = dataGetter.getData(usernames);

		for (String username : usernames)
			streamStatus.put(username, false);

		for (Map<String, String> streamerD : streamerData)
			streamStatus.put(streamerD.get("user_name"), true);
		return streamStatus;
	}

}
