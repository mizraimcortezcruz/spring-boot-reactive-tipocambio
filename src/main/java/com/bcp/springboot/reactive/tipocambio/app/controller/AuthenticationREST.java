package com.bcp.springboot.reactive.tipocambio.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.springboot.reactive.tipocambio.app.model.security.AuthRequest;
import com.bcp.springboot.reactive.tipocambio.app.model.security.AuthResponse;
import com.bcp.springboot.reactive.tipocambio.app.security.JWTUtil;
import com.bcp.springboot.reactive.tipocambio.app.security.PBKDF2Encoder;
import com.bcp.springboot.reactive.tipocambio.app.service.UserService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationREST {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
    private PBKDF2Encoder passwordEncoder;
    @Autowired
    private UserService userService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
    	logger.info("AuthenticationREST login");
    	userService.findByUsername(ar.getUsername());
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
