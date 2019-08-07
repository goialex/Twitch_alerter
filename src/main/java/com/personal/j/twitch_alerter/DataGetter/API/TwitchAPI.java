package com.personal.j.twitch_alerter.DataGetter.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public abstract class TwitchAPI
{
	public abstract String getServerData() throws IOException;

	protected String readResponseContent(HttpURLConnection connection) throws IOException
	{

		System.out.println("Accessing url: " + connection.getURL());
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String       inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			content.append(inputLine);
		in.close();

		return content.toString();
	}
}
