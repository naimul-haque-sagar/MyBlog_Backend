package myBrain.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myBrain.dto.JwtTokenResponse;
import myBrain.dto.LoginRequest;
import myBrain.dto.RegisterRequest;
import myBrain.model.AppUser;
import myBrain.model.AppUserGroup;
import myBrain.repository.AppUserGroupRepository;
import myBrain.repository.AppUserRepository;
import myBrain.security.JwtProvider;

@Service
@AllArgsConstructor
public class AuthService {

	private final AppUserRepository userRepository;

	private final AppUserGroupRepository userGroupRepository;

	private final PasswordEncoder passEncoder;

	private final AuthenticationManager authenticationManager;

	private final JwtProvider jwtProvider;
	
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

	public JwtTokenResponse login(LoginRequest loginRequest) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String authenticationToken = jwtProvider.generateToken(authentication);
		
		return new JwtTokenResponse(authenticationToken, loginRequest.getUsername());
	}

	public Optional<User> getCurrentUser() {
		User principal=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}
	
}
