package com.example.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.auth.entity.User;
import com.example.auth.jwt.JwtUtil;
import com.example.auth.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthService service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@GetMapping("/test")
	public String test() {
		return "JWT is working 🔥";
	}

	@GetMapping("/admin/data")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "Admin Access ✅";
	}

	@GetMapping("/user/data")
	@PreAuthorize("hasRole('USER')")
	public String user() {
		return "User Access ✅";
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.register(user);
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestParam String username,
	                                @RequestParam String password) {

	    authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(username, password)
	    );

	    User user = service.getUserByUsername(username);

	    String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
	    String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

	    return Map.of(
	        "accessToken", accessToken,
	        "refreshToken", refreshToken
	    );
	}
	@PostMapping("/refresh")
	public String refresh(@RequestParam String refreshToken) {

		String username = jwtUtil.extractUsername(refreshToken);
		User user = service.getUserByUsername(username);

		return jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
	}
}