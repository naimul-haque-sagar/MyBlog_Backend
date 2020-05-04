package myBrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myBrain.dto.LoginRequest;
import myBrain.dto.RegisterRequest;
import myBrain.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class Authcontroller {
	@Autowired
	AuthService authService;
	
	@PostMapping("signup")
	public ResponseEntity<HttpStatus> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
