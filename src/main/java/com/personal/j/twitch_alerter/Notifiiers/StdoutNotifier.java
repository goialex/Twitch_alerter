package com.personal.j.twitch_alerter.Notifiiers;

import com.personal.j.twitch_alerter.Observer;

public class StdoutNotifier implements Observer
{
	private final String prefixString;

	public StdoutNotifier(String prefixString)
	{
		this.prefixString = prefixString;
	}

	@Override
	public void update(String name)
	{
		System.out.println(prefixString + name);
	}
}
