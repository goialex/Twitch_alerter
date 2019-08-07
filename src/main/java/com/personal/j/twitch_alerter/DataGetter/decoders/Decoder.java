package com.personal.j.twitch_alerter.DataGetter.decoders;

import java.util.Set;

public interface Decoder
{
	Set<String> getFields(String data);
}