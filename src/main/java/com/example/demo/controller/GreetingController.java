package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.rest.LmsRequest;
import com.example.demo.rest.YamahaRequest;

@CrossOrigin
@Controller
public class GreetingController {

	@Value("${spring.application.name}")
	String appName;

	@RequestMapping(value = { "/", "/index.html" }, method = { RequestMethod.GET })
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "index";
	}

	@RequestMapping(value = { "/start.html" }, method = { RequestMethod.GET })
	public String startPage(Model model) {
		model.addAttribute("appName", appName);
		return "start";
	}

	@RequestMapping(value = "/avPowerOn", method = { RequestMethod.GET })
	public String avPowerOn(Model model) {

		YamahaRequest.powerOn();
		return "redirect:/#audio";
	}

	@RequestMapping(value = "/avPowerStandby", method = { RequestMethod.GET })
	public String avPowerStandBy(Model model) {

		YamahaRequest.powerStandby();
		return "redirect:/#audio";
	}

	@RequestMapping(value = "/HDMI1", method = { RequestMethod.GET })
	public String avHdmi1(Model model) {

		YamahaRequest.setHdmi1();
		YamahaRequest.setSound();
		YamahaRequest.setPure();
		YamahaRequest.setStraight();
		return "redirect:/#here";
	}

	@RequestMapping(value = "/AUDIO1", method = { RequestMethod.GET })
	public String avAudio1(Model model) {

		YamahaRequest.setAudio1();
		YamahaRequest.setSound();
		YamahaRequest.setPure();
		YamahaRequest.setStraight();
		return "redirect:/#audio";
	}
}