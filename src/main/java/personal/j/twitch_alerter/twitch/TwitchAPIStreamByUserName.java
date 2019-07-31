package personal.j.twitch_alerter.twitch;

public class TwitchAPIStreamByUserName extends TwitchAPI
{
	{
		super.BaseUrl = "https://api.twitch.tv/helix/streams";
		super.parameterName = "user_login";
	}
}
