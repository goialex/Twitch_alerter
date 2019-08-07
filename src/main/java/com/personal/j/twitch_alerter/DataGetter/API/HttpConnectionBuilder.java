package com.personal.j.twitch_alerter.DataGetter.API;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpConnectionBuilder
{
	private Map<String, List<String>> headerFields;
	private Map<String, List<String>> parameterFields;
	private StringBuilder             url;

	private String method = "GET";

	public HttpConnectionBuilder(String url)
	{
		this.url = new StringBuilder(url.endsWith("/") ?
				url.substring(0, url.length() - 1) : url);

		this.headerFields = new HashMap<>();
		this.parameterFields = new HashMap<>();
	}


	public HttpURLConnection getConnection() throws IOException
	{
		HttpURLConnection connection = (HttpURLConnection) constructURL().openConnection();
		connection.setRequestMethod(method);
		addHeadersToConnection(connection);

		return connection;
	}

	private URL constructURL() throws MalformedURLException
	{
		return new URL(getURLWithParameters());
	}

	private String getURLWithParameters()
	{
		StringBuilder URLWithParameters = new StringBuilder();

		URLWithParameters.append(url);
		if (!parameterFields.isEmpty())
			URLWithParameters.append("?");
		parameterFields.forEach((key, values) ->
		{
			for (String value : values)
				URLWithParameters
						.append(key)
						.append("=")
						.append(value)
						.append("&");
		});
		if (!parameterFields.isEmpty())
			URLWithParameters.deleteCharAt(URLWithParameters.length() - 1);

		return URLWithParameters.toString();
	}

	private void addHeadersToConnection(HttpURLConnection connection)
	{
		headerFields.forEach((s, values) ->
		{
			for (String value : values)
			{
				connection.setRequestProperty(s, value);
			}
		});
	}

	public HttpConnectionBuilder appendStringToURL(String str)
	{
		str = str.startsWith("/") ? str.substring(1) : str;
		str = str.endsWith("/") ? str.substring(0, str.length() - 1) : str;

		url.append("/").append(str);

		return this;
	}

	public HttpConnectionBuilder putParameter(String key, String value)
	{
		List<String> values = parameterFields.getOrDefault(key, new ArrayList<>());
		values.add(value);
		parameterFields.put(key, values);
		return this;
	}

	public HttpConnectionBuilder putHeader(String key, String value)
	{
		List<String> values = headerFields.getOrDefault(key, new ArrayList<>());
		values.add(value);
		headerFields.put(key, values);
		return this;
	}

	/**
	 * Initialized to GET
	 */
	public void setRequestMethod(String method)
	{
		this.method = method;
	}
}
