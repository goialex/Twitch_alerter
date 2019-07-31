package personal.j.twitch_alerter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class TwitchWatcherTest
{
	private TwitchWatcher            twitchWatcher;
	private Alerter                  alerter;
	private String                   test_username;
	private HashMap<String, Boolean> statusTest;

	@BeforeEach
	void setUp()
	{
		test_username = "test";
		statusTest = new HashMap<>();
		statusTest.put(test_username, false);
	}

	@Test
	void init_oneUser() throws IOException
	{
		alerter = Mockito.mock(Alerter.class);
		DataGetter dataGetter = Mockito.mock(DataGetter.class);

		twitchWatcher = new TwitchWatcher(alerter, dataGetter, test_username);

		verify(dataGetter).getData(anyString());
		assertMapEquals(statusTest, twitchWatcher.getStreamerStatus());
	}

	@Test
	void UserStartOfflineMoveOnline() throws IOException, InterruptedException
	{
		alerter = Mockito.mock(Alerter.class);
		DataGetter dataGetter = Mockito.mock(DataGetter.class);
		twitchWatcher = new TwitchWatcher(alerter, dataGetter, test_username);

		List<Map<String, String>> userList = getListWithUserOnline(test_username);
		when(dataGetter.getData(test_username)).thenReturn(userList);

		twitchWatcher.start();
		Thread.sleep(1000);
		twitchWatcher.interrupt();
		verify(alerter).update(anyString());

		statusTest.put(test_username, true);
		assertMapEquals(statusTest, twitchWatcher.getStreamerStatus());
	}

	@Test
	void UserStartOnlineMoveOffline() throws IOException, InterruptedException
	{
		alerter = Mockito.mock(Alerter.class);
		DataGetter dataGetter = Mockito.mock(DataGetter.class);

		List<Map<String, String>> userList = getListWithUserOnline(test_username);

		when(dataGetter.getData(test_username)).thenReturn(userList);
		twitchWatcher = new TwitchWatcher(alerter, dataGetter, test_username);
		when(dataGetter.getData(test_username)).thenReturn(new ArrayList<>());

		twitchWatcher.start();
		Thread.sleep(1000);
		twitchWatcher.interrupt();
		verify(alerter, never()).update(test_username);

		assertMapEquals(statusTest, twitchWatcher.getStreamerStatus());
	}

	private List<Map<String, String>> getListWithUserOnline(String test_username)
	{
		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String>       map  = new HashMap<>();
		map.put("user_name", test_username);
		data.add(map);
		return data;
	}

	private <T, K> void assertMapEquals(Map<T, K> map1,
										Map<T, K> map2)
	{
		assert (map1.size() == map2.size());

		for (T key : map1.keySet())
			assert (map1.get(key).equals(map2.get(key)));
	}
}