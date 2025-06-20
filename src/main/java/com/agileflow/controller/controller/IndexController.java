package com.agileflow.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dashboard")
@Controller
public class IndexController {

	@GetMapping("")
	public String dashboard() {
		return "index";
	}

}
