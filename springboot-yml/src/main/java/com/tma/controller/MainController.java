package com.tma.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tma.config.ApplicationSetup;

@RestController
public class MainController {
	@Resource
	private ApplicationSetup applicationSetup;

	@RequestMapping(value = "/")
	public String showEnv() {
		return applicationSetup.toString();
	}
}
