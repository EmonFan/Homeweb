//Collection of methods to control the home hardware.

/**
 * Selects the HDMI1 input on the Yamaha receiver and turns off all players
 * 
 * @returns nothing
 */
function listenToKodi() {
	var xmlHTTP = new XMLHttpRequest();
	xmlHTTP.open("GET", "/HDMI1", true);
	xmlHTTP.send(null);

	playerPower("FRED", "OFF");
	playerPower("KITCHEN", "OFF");
	playerPower("LOFT", "OFF");
	playerPower("WORKSHOP", "OFF");
	updateControlStates();
}

/**
 * Selects the Audio1 input on the Yamaha receiver and turns on all players
 * 
 * @returns nothing
 */
function listenToLMS() {
	var xmlHTTP = new XMLHttpRequest();
	xmlHTTP.open("GET", "/AUDIO1", true);
	xmlHTTP.send(null);

	playerPower("FRED", "ON");
	playerPower("KITCHEN", "ON");
	playerPower("LOFT", "ON");
	playerPower("WORKSHOP", "ON");
	updateControlStates();
}

/**
 * Call the Logitech Media Server with the passed in parameters
 * 
 * @param player
 *            the name of the targeted player
 * @param operation
 *            the name of the operation to be performed
 * @param setting
 *            the value to be used for the operation.
 * @returns the response from the server.
 */
function callLMS(player, operation, setting) {
	var xmlHTTP = new XMLHttpRequest();
	var params = "player=" + player + "&setting=" + setting;

	xmlHTTP.open("POST", operation, false);
	xmlHTTP.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");

	var response = "";
	xmlHTTP.onload = function() {
		if (XMLHttpRequest.DONE) {
			response = xmlHTTP.responseText;
		}
	}

	xmlHTTP.send(params);

	return response;
}

/**
 * Sets or gets the player volume
 * 
 * @param player
 *            the name of the player we are targeting
 * @param setting
 *            the volume level to be set, or retrieved if equal to ?
 * @returns the current volume
 */
function playerVolume(player, setting) {

	var response = callLMS(player, "/playerVolume", setting);
	var result;

	// Example Single digit response
	// {"method":"slim.request","result":{"_volume":"4"},"params":["00:04:20:07:eb:17",["mixer","volume","?"]],"id":1}
	var resultIndex = response.search("volume\":");
	if (resultIndex >= 0) {
		// Fetch the three possible volume values, from 0 to 100
		result = response.substring(resultIndex + 9, resultIndex + 9 + 3);
		if (result.search("\"") == 1) { // Single digit
			return result.substring(0, 1);
		} else if (result.search("\"") == 2) { // Double digit
			return result.substring(0, 2);
		} else
			// Must be 100
			return "100";

	} else
		return "0";
}

/**
 * Sets the requested station to play
 * 
 * @param station
 *            the name of the station
 * @returns nothing
 */
function setStation(station) {
	var xmlHTTP = new XMLHttpRequest();
	var params = "station=" + station;

	xmlHTTP.open("POST", "/setStation", true);
	xmlHTTP.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");

	xmlHTTP.send(params);
}

/**
 * Get the player name by parsing the id of the control
 * 
 * @param control
 *            the actual html control
 * @returns the player name
 */
function getPlayerName(control) {
	var id = $(control).attr("id");

	return getPlanerNameById(id);
}

/**
 * Get the player name by parsing the id of the control
 * 
 * @param control
 *            the actual html control
 * @returns the player name
 */
function getPlayerNameById(id) {
	if (id.search("Fred") >= 0)
		return "FRED";
	else if (id.search("Loft") >= 0)
		return "LOFT";
	else if (id.search("Work") >= 0)
		return "WORKSHOP";
	else if (id.search("Kitch") >= 0)
		return "KITCHEN";
}

/**
 * Returns the volume control id based matching the player name
 * 
 * @param playerName
 *            the requested control id's player name
 * @returns
 */
function getControlNameFromId(playerName, prefix) {
	var volumeControlId;

	if (playerName.search("FRED") >= 0) {
		volumeControlId = "#" + prefix + "Fred";
	} else if (playerName.search("KITCHEN") >= 0) {
		volumeControlId = "#" + prefix + "Kitchen";
	} else if (playerName.search("LOFT") >= 0) {
		volumeControlId = "#" + prefix + "Loft";
	} else if (playerName.search("WORKSHOP") >= 0) {
		volumeControlId = "#" + prefix + "Workshop";
	}
	return volumeControlId;
}

/**
 * Sets or queries the power setting of a player
 * 
 * @param player
 *            the name of the player
 * @param setting
 *            1 for on, 0 for off, or ? to query the state.
 * @returns true if on, false if off
 */
function playerPower(player, setting) {
	var response = callLMS(player, "/playerPower", setting);
	if (response.search("_power\":0") >= 0) {
		return false;
	} else
		return true;
}

function updateTrackInfo() {

	// Artist:
	// {"method":"slim.request","result":{"_artist":"Beatles"},"params":["00:04:20:07:eb:17",["artist","?"]],"id":1}
	// Title:
	// {"method":"slim.request","params":["00:04:20:07:eb:17",["title","?"]],"result":{"_title":"Come
	// Together "},"id":1}

	var artist = callLMS("FRED", "/artist", "?");
	var title = callLMS("FRED", "/title", "?");

	var startIndex = artist.indexOf("_artist\"");
	var stopIndex = artist.indexOf("}", startIndex);

	artist = artist.substring(startIndex + 10, stopIndex - 1);

	startIndex = title.indexOf("_title\"");
	stopIndex = title.indexOf("}", startIndex);

	title = title.substring(startIndex + 9, stopIndex - 1);

	// alert("Artist: "+artist+" Title: "+title);

	// <strong>Playing:&nbsp;</strong> Against The
	// Wind<strong><br>by:&nbsp;</strong> Bob Seger & The Silver Bullet Band</a>

	artist.replace("\"", "");
	title.replace("\"", "");

	var html = "<strong>Playing:&nbsp;</strong>__title__<strong><br>by:&nbsp;</strong>__artist__</a>"
	html = html.replace("__title__", title);
	html = html.replace("__artist__", artist);

	$("#trackInfo").html(html);
}

function nextSong() {
	callLMS("FRED", "/nextSong", "");
}

function playButton(switchControl, player) {
	var playerName = getPlayerName(switchControl);

	callLMS(playerName, "/playButton", "");
}