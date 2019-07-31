package personal.j.twitch_alerter.twitch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class TwitchAPI
{
	public static String CLIENT_ID;

	protected String BaseUrl;
	protected String parameterName;

	public String getServerResponse(String... params) throws IOException
	{
		assert (!CLIENT_ID.equals("")) : "You have to provide a valid Client-ID";

		URL twitchStreamUrl = formatURL(BaseUrl, parameterName, params);
		HttpURLConnection connection = getHttpConnection(twitchStreamUrl);
		String responseContent = getResponse(connection);

		return responseContent;
	}

	private String getResponse(HttpURLConnection connection) throws IOException
	{
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String       inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			content.append(inputLine);
		in.close();

		return content.toString();
	}

	private HttpURLConnection getHttpConnection(URL twitchStreamInfo) throws IOException
	{
		HttpURLConnection connection = (HttpURLConnection) twitchStreamInfo.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Client-ID", CLIENT_ID);

		return connection;
	}

	private URL formatURL(String baseURL, String paramName, String... params) throws MalformedURLException
	{
		StringBuilder result = new StringBuilder();

		result.append(baseURL)
			  .append("?");

		for (int i = 0; i < params.length; i++)
		{
			result.append(paramName)
				  .append("=")
				  .append(params[i]);
			if (i != params.length - 1)
				result.append("&");
		}

		return new URL(result.toString());
	}
}
