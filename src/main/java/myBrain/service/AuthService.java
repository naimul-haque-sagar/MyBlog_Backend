package myBrain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myBrain.dto.LoginRequest;
import myBrain.dto.RegisterRequest;
import myBrain.model.AppUser;
import myBrain.model.AppUserGroup;
import myBrain.repository.AppUserGroupRepository;
import myBrain.repository.AppUserRepository;
import myBrain.security.JwtProvider;

@Service
public class AuthService {
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private AppUserGroupRepository userGroupRepository;
	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	
	public void signup(RegisterRequest registerRequest) {
		AppUser user=new AppUser();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);
		
		AppUserGroup userGroup=new AppUserGroup();
		userGroup.setUsername(registerRequest.getUsername());
		userGroup.setUser_group("user");
		userGroupRepository.save(userGroup);
	}

	private String passwordEncoder(String p) {
		return passEncoder.encode(p);
	}

	public String login(LoginRequest loginRequest) {

		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return (String) jwtProvider.generateToken(authentication)+ authentication.getAuthorities();
	}
	
}
