package personal.j.twitch_alerter.twitch;

public class TwitchAPIUserInfo extends TwitchAPI
{
	{
		super.BaseUrl = "https://api.twitch.tv/helix/users";
		super.parameterName = "login";
	}
}
