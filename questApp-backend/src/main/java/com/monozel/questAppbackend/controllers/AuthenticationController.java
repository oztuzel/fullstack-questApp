package com.monozel.questAppbackend.controllers;

import com.monozel.questAppbackend.requests.RegisterRequest;
import com.monozel.questAppbackend.responses.AuthenticationResponse;
import com.monozel.questAppbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
//        return new ResponseEntity<>(authenticationService.register(request), HttpStatusCode.valueOf())
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody RegisterRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }


}
