package br.com.theapache.apachebank.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	@RequestMapping("/")
	public String home() {
		
		return "Welcome to Apache Bank API Server";
	}
}
