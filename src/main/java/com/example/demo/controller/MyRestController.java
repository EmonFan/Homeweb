package com.example.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.Greeting;
import com.example.demo.rest.LmsRequest;

@RestController
public class MyRestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private LmsRequest LMS = new LmsRequest();

	@PostMapping("/artist")
	public String getArtist(Model model) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getArtist(LmsRequest.PLAYER_FRED_MAC));
		return "redirect:/#audio";
	}

	@PostMapping("/title")
	public String getTitle(Model model) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.getTitle(LmsRequest.PLAYER_FRED_MAC));
		return "redirect:/#audio";
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

	@PostMapping("/playerVolume")
	public ResponseEntity<String> playerVolume(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerVolume(playerName, setting));
		return response;
	}

	@PostMapping("/setVolume")
	public String setVolume(Model model) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.setVolume(LmsRequest.PLAYER_FRED_MAC, "50"));
		return "redirect:/#audio";
	}

	@PostMapping("/setStation")
	public void setStation(
			@RequestParam(value = "station", defaultValue = LmsRequest.CLASSIC_ROCK) String stationName) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playStation(stationName));
	}

	@PostMapping("/playerPower")
	public ResponseEntity<String> power(
			@RequestParam(value = "player", defaultValue = LmsRequest.PLAYER_FRED) String playerName,
			@RequestParam(value = "setting") String setting) {

		ResponseEntity<String> response = LMS.sendRequest(LmsRequest.playerPower(playerName, setting));
		return response;
	}
}
