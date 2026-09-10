package com.example.foodexplorer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

   	@GetMapping
	public String health() {
		return "Server rodando";
	}

	@PostMapping
	public String ping() {
		return "Server rodando";
	}

}