package com.personal.j.twitch_alerter.Notifiiers;

import com.personal.j.twitch_alerter.Observer;
import org.gnome.gtk.Gtk;
import org.gnome.notify.Notification;
import org.gnome.notify.Notify;

public class LinuxNotifier implements Observer
{
	static
	{
		Gtk.init(new String[0]);
		Notify.init("TwitchAlerter");
	}

	public void update(String streamerName)
	{
		Notification streamAlert = new Notification(
				streamerName + " is live",
				"Go to Twitch.tv" + streamerName + " to watch",
				streamerName.toLowerCase());

		streamAlert.show();
	}
}
