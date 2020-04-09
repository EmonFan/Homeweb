package com.example.demo.rest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YamahaRequest {

	private static final String MAIN = "MAIN:";
	private static final String POWER = "PWR=";
	private static final String INPUT = "INP=";
	private static final String STRAIGHT = "STRAIGHT=";
	private static final String PURE = "PUREDIRMODE=";
	private static final String SOUND = "SOUNDPRG=";
	private static final String CRLF = "\r\n";
	private static final int PORT = 50000;
	private static final String HOSTNAME = "RX-A830.athome";
	private static final String HDMI1 = "HDMI1";
	private static final String HDMI2 = "HDMI2";
	private static final String STANDARD = "Standard";
	private static final String ON = "On";
	private static final String OFF = "Off";
	private static final String STANDBY = "Standby";
	private static final String ENHANCER = "ENHANCER";

	public static String getEnhancer() {
		return callYamaha(ENHANCER, "?");
	}

	public static String getStraight() {
		return callYamaha(STRAIGHT, "?");
	}

	public static String getSound() {
		return callYamaha(SOUND, "?");
	}

	@Deprecated
	public static String setEnhancer() {
		final String result;
		if (!getEnhancer().contains(OFF)) {
			result = callYamaha(ENHANCER, OFF);
		} else {
			result = "";
		}

		return result;
	}

	public static String setSound() {
		final String result;
		if (!getSound().contains(STANDARD)) {
			result = callYamaha(SOUND, STANDARD);
		} else {
			result = "";
		}

		return result;
	}

	public static String getPure() {
		return callYamaha(PURE, "?");
	}

	public static String setPure() {
		final String result;
		if (!getPure().contains(ON)) {
			result = callYamaha(PURE, ON);
		} else {
			result = "";
		}
		return result;
	}

	public static String setStraight() {
		final String result;
		if (!getStraight().contains(ON)) {
			return callYamaha(STRAIGHT, ON);
		} else {
			result = "";
		}
		return result;
	}

	public static String powerOn() {
		final String result;
		if (!getPower().contains(ON)) {
			result = callYamaha(POWER, ON);
		} else {
			result = "";
		}
		return result;
	}

	public static String powerStandby() {
		final String result;
		if (!getPower().contains(STANDBY)) {
			result = callYamaha(POWER, STANDBY);
		} else {
			result = "";
		}
		return result;
	}

	public static String getPower() {
		return callYamaha(POWER, "?");
	}

	public static String setHdmi1() {
		final String result;
		if (!getInput().contains(HDMI1)) {
			result = callYamaha(INPUT, HDMI1);
		} else {
			result = "";
		}
		return result;
	}

	//Selects HDMI2 with AUDIO1 as input
	public static String setAudio1(){
		final String result;
		if (!getInput().contains(HDMI2)) {
			result = callYamaha(INPUT, HDMI2);
		} else {
			result = "";
		}
		return result;
	}

	public static String getInput() {
		return callYamaha(INPUT, "?");
	}

	private static String callYamaha(String function, String command) {
		String response = "No Response";

		try (Socket socket = new Socket(HOSTNAME, PORT)) {
			BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Send the command
			socketWriter.write('@');
			socketWriter.write(MAIN + function + command + CRLF);
			socketWriter.flush();

			// Get the server response
			byte[] data = new byte[2048];
			int bytes = socket.getInputStream().read(data, 0, data.length);
			response = new String(data, 0, bytes, "ASCII");

			socketWriter.close();
			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// If we try to set the receiver to the current mode it is in
			// ie." input=HDMI1 and we try to set the input to HDMI1
			// The server doesn't play nice and just resets the connection.
			// Eat the exception
			e.printStackTrace();
		}

		return response;
	}
}
