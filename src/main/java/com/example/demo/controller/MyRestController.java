package com.example.demo.controller;

import java.util.Map;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.LmsRequest;

@RestController
public class MyRestController {

	private LmsRequest LMS = new LmsRequest();

	@PostMapping("/artist")
	public ResponseEntity<String> getArtist(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getArtist(playerName));
		return response;
	}

	@PostMapping("/title")
	public ResponseEntity<String> getTitle(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getTitle(playerName));
		return response;
	}

	@PostMapping("/album")
	public String getAlbum(Model model) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getAlbum(LmsRequest.PLAYER_FRED_MAC));
		return "redirect:/#audio";
	}

	@PostMapping("/full")
	public String getFull(Model model) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getFullInfo(LmsRequest.PLAYER_FRED_MAC));
		return "redirect:/#audio";
	}

	@PostMapping("/setStation")
	public ResponseEntity<String> setStation(
			@RequestParam(value = "station", defaultValue = LmsRequest.CLASSIC_ROCK) String stationName) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playStation(stationName));
		return response;
	}

	@PostMapping("/playerVolume")
	public ResponseEntity<String> playerVolume(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerVolume(playerName, setting));
		return response;
	}

	@PostMapping("/playerPower")
	public ResponseEntity<String> power(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerPower(playerName, setting));
		return response;
	}

	@PostMapping("/nextSong")
	public ResponseEntity<String> nextSong(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.nextSong(playerName));
		return response;
	}

	@PostMapping("/prevSong")
	public ResponseEntity<String> prevSong(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.prevSong(playerName));
		return response;
	}

	@PostMapping("/playerPause")
	public ResponseEntity<String> playerPause(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerPause(playerName, setting));
		return response;
	}

	@PostMapping("/playButton")
	public ResponseEntity<String> playButton(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerPlay(playerName, setting));
		return response;
	}

	@PutMapping("/konnected/device/{deviceId}")
	@ResponseStatus(HttpStatus.OK)
	public void konnectedPost2(@PathVariable String deviceId, @RequestBody String allParams) {

		System.out.println("Put: " + deviceId + ": " + allParams);
	}

	@GetMapping("/konnected/device/{deviceId}")
	@ResponseStatus(HttpStatus.OK)
	public void konnectedGet(@PathVariable String deviceId, @RequestParam Map<String, String> allParams) {

		System.out.println("Get: " + deviceId + " " + allParams.entrySet());
	}
}
