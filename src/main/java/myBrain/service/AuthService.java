package myBrain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myBrain.dto.RegisterRequest;
import myBrain.model.User;
import myBrain.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;

	public void signup(RegisterRequest registerRequest) {
		User user=new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);
	}
	
}
