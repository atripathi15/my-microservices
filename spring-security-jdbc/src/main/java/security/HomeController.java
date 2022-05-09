package security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "<h2>Welcome</h2>";
	}

	@GetMapping("/user")
	public String userHome() {
		return "<h2>Welcome User</h2>";
	}

	@GetMapping("/admin")
	public String adminHome() {
		return "<h2>Welcome Admin</h2>";
	}

}
