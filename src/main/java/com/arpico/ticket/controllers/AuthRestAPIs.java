package com.arpico.ticket.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.arpico.ticket.models.ERole;
import com.arpico.ticket.models.Role;
import com.arpico.ticket.models.User;
import com.arpico.ticket.payload.request.LoginRequest;
import com.arpico.ticket.payload.request.SignupRequest;
import com.arpico.ticket.payload.response.JwtResponse;
import com.arpico.ticket.payload.response.MessageResponse;
import com.arpico.ticket.repository.RoleRepository;
import com.arpico.ticket.repository.UserRepository;
import com.arpico.ticket.security.jwt.JwtUtils;
import com.arpico.ticket.security.services.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

		// return ResponseEntity.ok(
		// new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
		// userDetails.getEmail(), roles));

		// Authentication authentication = authenticationManager.authenticate(
		// new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
		// loginRequest.getPassword()));
		//
		// SecurityContextHolder.getContext().setAuthentication(authentication);
		//
		// String jwt = jwtProvider.generateJwtToken(authentication);
		// UserDetails userDetails = (UserDetails)
		// authentication.getPrincipal();
		//
		// return ResponseEntity.ok(new JwtResponse(jwt,
		// userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	// @GetMapping("/user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByName(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				case "node":
					Role nodRole = roleRepository.findByName(ERole.ROLE_NODE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(nodRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
