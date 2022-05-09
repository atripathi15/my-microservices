package com.ashish.security.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "<br>Welcome</br>";
	}

	@GetMapping("/user")
	public String userHome() {
		return "<br>Welcome User</br>";
	}

	@GetMapping("/admin")
	public String adminHome() {
		return "<br>Welcome Admin</br>";
	}

}
