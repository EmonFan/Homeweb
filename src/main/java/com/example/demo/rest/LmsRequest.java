package com.example.demo.rest;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * Creates a valid {@link String} to be used as a parameter to {@link JSONObject} 
 */
public class LmsRequest {

	private static final String PREAMBLE = "{\"id\":1,\"params\":[\"";
	private static final String ARTIST = "\",[\"artist\"";
	private static final String TITLE = "\",[\"title\"";
	private static final String ALBUM = "\",[\"album\"";
	private static final String FULL_INFO = "\",[\"current_title\"";
	private static final String BUTTON = "\",[\"button\"";
	private static final String POWER_QUERY = "\",[\"power\"";
	private static final String VOLUME_UP = ",\"volume_up\"";
	private static final String VOLUME_DN = ",\"volume_down\"";
	private static final String PLAY = ",\"play\"";
	private static final String PAUSE = ",\"pause\"";
	private static final String POWER = ",\"power\"";
	private static final String POWER_ON = ",\"1\"";
	private static final String POWER_OFF = ",\"0\"";

	private static final String MIXER = "\",[\"mixer\"";
	private static final String VOLUME = ",\"volume\"";
	private static final String QUESTION = ",\"?\"";
	private static final String VALUE_PROLOG = ",\"";
	private static final String VALUE_EPILOG = "\"";
	private static final String PLAYLIST = "\",[\"playlist\"";

	private static final String SEVENTIES_URL = ",\"http://listen.livestreamingservice.com:8066/\"";
	private static final String CLASSIC_ROCK_URL = ",\"http://listen.livestreamingservice.com:8030/\"";
	private static final String HARD_ROCK_URL = ",\"http://listen.livestreamingservice.com:8064/\"";
	private static final String BLUES_URL = ",\"http://listen.livestreamingservice.com/181-blues_128k.mp3\"";
	private static final String HAIR_BANDS_URL = ",\"http://listen.livestreamingservice.com:8014/\"";

	public static final String PLAYER_FRED_MAC = "00:04:20:07:eb:17";
	public static final String PLAYER_LOFT_MAC = "00:04:20:17:41:dd";
	public static final String PLAYER_KITCHEN_MAC = "00:04:20:12:0e:52";
	public static final String PLAYER_WORKSHOP_MAC = "00:04:20:06:78:07";

	public static final String SEVENTIES = "SEVENTIES";
	public static final String CLASSIC_ROCK = "CLASSIC_ROCK";
	public static final String HARD_ROCK = "HARD_ROCK";
	public static final String BLUES = "BLUES";
	public static final String HAIR_BANDS = "HAIR_BANDS";

	public static final String PLAYER_FRED = "FRED";
	public static final String PLAYER_KITCHEN = "KITCHEN";
	public static final String PLAYER_LOFT = "LOFT";
	public static final String PLAYER_WORKSHOP = "WORKSHOP";

	private static final String EPILOG = "]],\"method\":\"slim.request\"}";

	private final RestTemplate restTemplate = new RestTemplate();
	private static final String LMS_URL = "http://squeezer.athome:9000/jsonrpc.js";
	private final HttpHeaders headers = new HttpHeaders();

	/*
	 * Constructs a valid String to query the volume on the player
	 */
	public static JSONObject playerVolume(String playerName, String setting) {
		final String playerMacAddress;
		
		if (PLAYER_FRED.equals(playerName)) {
			playerMacAddress = PLAYER_FRED_MAC;
		} else if (PLAYER_KITCHEN.equals(playerName)) {
			playerMacAddress = PLAYER_KITCHEN_MAC;
		} else if (PLAYER_LOFT.equals(playerName)) {
			playerMacAddress = PLAYER_LOFT_MAC;
		} else if (PLAYER_WORKSHOP.equals(playerName)) {
			playerMacAddress = PLAYER_WORKSHOP_MAC;
		} else {
			playerMacAddress = PLAYER_FRED_MAC;
		}

		final String lmsCommand;
		if ("?".equalsIgnoreCase(setting)) {
			lmsCommand = PREAMBLE + playerMacAddress + MIXER + VOLUME + QUESTION + EPILOG;
		} else {
			lmsCommand = PREAMBLE + playerMacAddress + MIXER + VOLUME + ",\"" + setting +"\"" + EPILOG;
		}

		return new JSONObject(lmsCommand);
	}

	/*
	 * Constructs a valid String to set the volume on the player
	 */
	public static JSONObject setVolume(String macAddress, String value) {
		return new JSONObject(PREAMBLE + macAddress + MIXER + VOLUME + VALUE_PROLOG + value + VALUE_EPILOG + EPILOG);
	}

	/*
	 * Constructs a valid String to increase the volume on the player
	 */
	public static JSONObject volumeUp(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + BUTTON + VOLUME_UP + EPILOG);
	}

	/*
	 * Constructs a valid String to decrease the volume on the player
	 */
	public static JSONObject volumeDn(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + BUTTON + VOLUME_DN + EPILOG);
	}

	/*
	 * Constructs a valid String to return the currently playing track artist
	 */
	public static JSONObject getArtist(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + ARTIST + QUESTION + EPILOG);
	}

	/*
	 * Constructs a valid String to return the currently playing track title
	 */
	public static JSONObject getTitle(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + TITLE + QUESTION + EPILOG);
	}

	/*
	 * Constructs a valid String to return the currently playing track album
	 */
	public static JSONObject getAlbum(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + ALBUM + QUESTION + EPILOG);
	}

	/*
	 * Constructs a valid String to return the currently playing track album and
	 * artist
	 */
	public static JSONObject getFullInfo(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + FULL_INFO + QUESTION + EPILOG);
	}

	/*
	 * Constructs a valid String to toggle the power on the player
	 */
	public static JSONObject powerToggle(String macAddress) {
		return new JSONObject(PREAMBLE + macAddress + BUTTON + POWER + EPILOG);
	}

	public static JSONObject playerPower(String playerName, String setting) {
		final String playerMacAddress;
		
		if (PLAYER_FRED.equals(playerName)) {
			playerMacAddress = PLAYER_FRED_MAC;
		} else if (PLAYER_KITCHEN.equals(playerName)) {
			playerMacAddress = PLAYER_KITCHEN_MAC;
		} else if (PLAYER_LOFT.equals(playerName)) {
			playerMacAddress = PLAYER_LOFT_MAC;
		} else if (PLAYER_WORKSHOP.equals(playerName)) {
			playerMacAddress = PLAYER_WORKSHOP_MAC;
		} else {
			playerMacAddress = PLAYER_FRED_MAC;
		}

		final String lmsCommand;

		if ("on".equalsIgnoreCase(setting)) {
			lmsCommand = PREAMBLE + playerMacAddress + POWER_QUERY + POWER_ON + EPILOG;
		} else if ("off".equalsIgnoreCase(setting)) {
			lmsCommand = PREAMBLE + playerMacAddress + POWER_QUERY + POWER_OFF + EPILOG;
		} else {
			lmsCommand = PREAMBLE + playerMacAddress + POWER_QUERY + QUESTION + EPILOG;
		}

		return new JSONObject(lmsCommand);
	}

	public static JSONObject playStation(String stationName) {
		final String stationUrl;

		if (SEVENTIES.equals(stationName)) {
			stationUrl = SEVENTIES_URL;
		} else if (CLASSIC_ROCK.equals(stationName)) {
			stationUrl = CLASSIC_ROCK_URL;
		} else if (HARD_ROCK.equals(stationName)) {
			stationUrl = HARD_ROCK_URL;
		} else if (BLUES.equals(stationName)) {
			stationUrl = BLUES_URL;
		} else if (HAIR_BANDS.equals(stationName)) {
			stationUrl = HAIR_BANDS_URL;
		} else {
			stationUrl = CLASSIC_ROCK_URL;
		}

		return new JSONObject(PREAMBLE + PLAYER_FRED_MAC + PLAYLIST + PLAY + stationUrl + EPILOG);
	}

	public ResponseEntity<String> sendRequest(JSONObject jsonObject) {
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		return restTemplate.postForEntity(LMS_URL, request, String.class);
	}
}
