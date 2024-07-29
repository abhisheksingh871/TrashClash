package com.trashclash.trashclash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

	@GetMapping("/")
	public String getHome() {
		return "home";
	}
}
