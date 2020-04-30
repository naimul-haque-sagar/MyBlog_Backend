package myBrain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myBrain.dto.RegisterRequest;
import myBrain.model.User;
import myBrain.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passEncoder;
	
	public void signup(RegisterRequest registerRequest) {
		User user=new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);
	}

	private String passwordEncoder(String p) {
		return passEncoder.encode(p);
	}
	
}
