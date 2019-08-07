package com.personal.j.twitch_alerter.DataGetter;

import com.personal.j.twitch_alerter.DataGetter.API.HttpConnectionBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class HttpConnectionBuilderShould
{
	String                BASE_URL = "https://base.url.com";
	HttpConnectionBuilder connectionBuilder;

	@Before
	public void setUp() throws Exception
	{
		connectionBuilder = new HttpConnectionBuilder(BASE_URL);
	}

	@Test
	public void construct__httpConnection() throws IOException
	{
		Assert.assertEquals(BASE_URL,
				connectionBuilder.getConnection().getURL().toString());
	}

	@Test(expected = MalformedURLException.class)
	public void throw_IOException_if_malformed_http() throws IOException
	{
		connectionBuilder = new HttpConnectionBuilder(BASE_URL.substring(1));
		connectionBuilder.getConnection();
	}

	@Test
	public void append_strings_to_the_url() throws IOException
	{
		connectionBuilder.appendStringToURL("something");

		Assert.assertEquals(BASE_URL + "/something",
				connectionBuilder.getConnection().getURL().toString());
	}

	@Test
	public void append_strings_to_the_url2() throws IOException
	{
		connectionBuilder.appendStringToURL("/something/");

		Assert.assertEquals(BASE_URL + "/something",
				connectionBuilder.getConnection().getURL().toString());
	}

	@Test
	public void append_parameter_to_URL() throws IOException
	{
		StringBuilder expected = new StringBuilder();
		String        key      = "user";
		String        value    = "username";

		connectionBuilder.putParameter("user", "username");
		expected.append(BASE_URL)
				.append("?")
				.append(key)
				.append("=")
				.append(value);

		Assert.assertEquals(expected.toString(),
				connectionBuilder.getConnection().getURL().toString());
	}

	@Test
	public void append_parameters_to_URL() throws IOException
	{
		StringBuilder expected = new StringBuilder();
		String        key      = "user";
		String        value    = "username431";
		String        value2   = "username";

		connectionBuilder.putParameter(key, value);
		connectionBuilder.putParameter(key, value2);

		expected.append(BASE_URL)
				.append("?")
				.append(key)
				.append("=")
				.append(value)
				.append("&")
				.append(key)
				.append("=")
				.append(value2);

		Assert.assertEquals(expected.toString(),
				connectionBuilder.getConnection().getURL().toString());
	}

	@Test
	public void add_header_to_connection() throws IOException
	{
		String key = "ID1";
		String value = "t1";

		connectionBuilder.putHeader(key, value);
		HttpURLConnection connection = connectionBuilder.getConnection();

		Assert.assertEquals(value, connection.getRequestProperty(key));
	}

	@Test
	public void add_headers_to_connection() throws IOException
	{
		String key = "ID1";
		String value = "t1";
		String value2 = "t1";

		connectionBuilder.putHeader(key, value);
		connectionBuilder.putHeader(key, value2);
		HttpURLConnection connection = connectionBuilder.getConnection();

		Assert.assertEquals(value, connection.getRequestProperty(key));
	}

	@Test
	public void set_request_method() throws IOException
	{
		String method = "POST";
		connectionBuilder.setRequestMethod(method);

		Assert.assertEquals(method, connectionBuilder.getConnection().getRequestMethod());
	}

	@Test(expected = ProtocolException.class)
	public void Athrow_ProtocolException_on_wrong_request_method() throws IOException
	{
		String method = "PST";
		connectionBuilder.setRequestMethod(method);

		Assert.assertEquals(method, connectionBuilder.getConnection().getRequestMethod());
	}


}