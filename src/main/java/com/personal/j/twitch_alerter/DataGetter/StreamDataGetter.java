package com.personal.j.twitch_alerter.DataGetter;

import java.io.IOException;
import java.util.Set;

public interface StreamDataGetter
{
	Set<String> getActiveStreams() throws IOException;
}
