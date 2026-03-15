package com.booking.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/auth")
public class TestController {

	
	@GetMapping("/test")
	public String testMethod() {
		return "CONNECTED AUTH";
	}
}
