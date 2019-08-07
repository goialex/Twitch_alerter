package com.personal.j.twitch_alerter;

import com.personal.j.twitch_alerter.DataGetter.API.TwitchAPI;
import org.json.JSONObject;

public class TwitchAPIDataMock
{
	public String getUserFollowData()
	{
		return "{\"_total\":2,\"follows\":[{\"created_at\":\"2341\",\"channel\":{\"mature\":false,\"status\":Something},\"notifications\":false}," +
				"{\"created_at\":\"2341\",\"channel\":{\"mature\":true,\"status\":this},\"notifications\":false}}";

//		return "{\"_total\":25,\"follows\":[{\"created_at\":\"2019-05-28T07:44:26Z\",\"channel\":{\"mature\":false,\"status\":\" Unranked to" +
//				" Challenger w/ Manco|Challenger Mid Grind for Top 10!  | Champ Requests |    !Kanga | !youtube | " +
//				"!Discord\",\"broadcaster_language\":\"en\",\"broadcaster_software\":\"unknown_rtmp\",\"display_name\":\"Mcbaze\",\"game\":\"League of " +
//				"Legends\",\"language\":\"en\",\"_id\":\"32186133\",\"name\":\"mcbaze\",\"created_at\":\"2012-07-16T17:22:33Z\",\"updated_at\":\"2019-08-07T10:30:" +
//				"49Z\",\"partner\":false,\"logo\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/faae4100-b59b-484f-b32a-955f9f34bb80-profile_image-300x300.jp" +
//				"eg\",\"video_banner\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/5a5c5d1a-de5e-4181-b4a8-bff863873e1a-channel_offline_image-1920x1080.png\",\"" +
//				"profile_banner\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/89cad60a-7604-4791-9693-299e8698011e-profile_banner-480.png\",\"profile_banner_backgroun" +
//				"d_color\":null,\"url\":\"https://www.twitch.tv/mcbaze\",\"views\":120940,\"followers\":4546,\"broadcaster_type\":\"affiliate\",\"description\":\"Mid/Top ma" +
//				"in\",\"private_video\":false,\"privacy_options_enabled\":false},\"notifications\":false},{\"created_at\":\"2019-04-18T20:39:52Z\",\"channel\":{\"mature\":fal" +
//				"se,\"status\":\"Finishing payment code for Bot Land (day 705)\",\"broadcaster_language\":\"en\",\"broadcaster_software\":\"unknown_rtmp\",\"display_name\":\"Ad" +
//				"am13531\",\"game\":\"Science \\u0026 Technology\",\"language\":\"en\",\"_id\":\"47098493\",\"name\":\"adam13531\",\"created_at\":\"2013-08-04T08:" +
//				"50:34Z\",\"updated_at\":\"2019-08-07T13:37:44Z\",\"partner\":true,\"logo\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/b54cd931-2cb3-4aae-aa" +
//				"e9-27511297a73a-profile_image-300x300.png\",\"video_banner\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/f1be2f1c-ac8c-4c76-b14b-367dbebad85" +
//				"0-channel_offline_image-1920x1080.png\",\"profile_banner\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/adam13531-profile_banner-50f4ca3525e" +
//				"43af3-480.png\",\"profile_banner_background_color\":null,\"url\":\"https://www.twitch.tv/adam13531\",\"views\":671770,\"followers\":17684,\"broad" +
//				"caster_type\":\"partner\",\"description\":\"Goofy game developer and lights enthusiast.\",\"private_video\":false,\"privacy_options_enabled\":fals" +
//				"e},\"notifications\":false},{\"created_at\":\"2019-04-11T10:45:05Z\",\"channel\":{\"mature\":false,\"status\":\"not playing the pokemon theme song " +
//				"on ukulele\",\"broadcaster_language\":\"en\",\"broadcaster_software\":\"sdk\",\"display_name\":\"humanearthling\",\"game\":\"Just Chatting\",\"lan" +
//				"guage\":\"en\",\"_id\":\"271841932\",\"name\":\"humanearthling\",\"created_at\":\"2018-11-03T01:16:08Z\",\"updated_at\":\"2019-08-07T10:28:14Z\",\"" +
//				"partner\":false,\"logo\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/74ccc3c8-bc2e-4348-bafd-ff94133c2a10-profile_image-300x300.png\",\"video" +
//				"_banner\":null,\"profile_banner\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/b29d5cf7-9374-43c4-9d3a-b8e97724b2cb-profile_banner-480.jpeg\"," +
//				"\"profile_banner_background_color\":null,\"url\":\"https://www.twitch.tv/humanearthling\",\"views\":22992,\"followers\":2206,\"broadcaster_type\":" +
//				"\"affiliate\",\"description\":\"Hey! I'm Meghan, a human earthling from Toronto, Canada. I'm here to chill, happily talk with people in the chat a" +
//				"nd sing/play guitar for fun. Come say hi and let's talk about life and whatever!\",\"private_video\":false,\"privacy_options_enabled\":false},\"no" +
//				"tifications\":false}]}";
	}


	public String getStreamData(String[] forUsers)
	{
		JSONObject formattedObject = new JSONObject();

		for (String user : forUsers)
			formattedObject.append("data", getUserData(user));

		formattedObject.append("pagination", "{\"cursor\":\"eyJiIjpudWxsLCJhIjp7Ik9mZnNldCI6MX19\"}");

		return formattedObject.toString();
	}

	private JSONObject getUserData(String user)
	{
		JSONObject twitchSampleData = new JSONObject("{\"id\":\"35162156000\",\"user_id\":\"26610234\",\"user_name\":\"" + user + "\"" +
				",\"game_id\":\"495202\",\"type\":\"live\",\"title\":\"Fire Emblem! (Hard/Classic/Blue) - " +
				"Rebel Galaxy: Outlaw !SOON - !ModApp - !Interview - !Reddit - !Store\",\"viewer_count\"" +
				":11436,\"started_at\":\"2019-08-04T11:53:02Z\",\"language\":\"en\",\"thumbnail_url\":" +
				"\"https://static-cdn.jtvnw.net/previews-ttv/live_user_cohhcarnage-{width}x{height}.jpg\"" +
				",\"tag_ids\":[\"6ea6bca4-4712-4ab9-a906-e3336a9d8039\",\"96b6073f-450d-4248-8ed4-988e28f3f759\"," +
				"\"eaba0ad7-c4e1-4878-b37f-01308dbb65c8\",\"e90b5f6e-4c6e-4003-885b-4d0d5adeb580\",\"" +
				"ba2c968b-867a-49ce-aebc-3d978a204f4a\"]}");

		return twitchSampleData;
	}
}
