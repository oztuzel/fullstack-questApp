package com.monozel.questAppbackend.services;

import com.monozel.questAppbackend.config.JwtService;
import com.monozel.questAppbackend.entities.Role;
import com.monozel.questAppbackend.entities.User;
import com.monozel.questAppbackend.repos.UserRepository;
import com.monozel.questAppbackend.requests.RegisterRequest;
import com.monozel.questAppbackend.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
//        if(userService.getOneUserByUsername(request.getUsername()) != null){
//            return AuthenticationResponse.builder()
//                    .message("user already in use.")
//                    .build();
//        }

//        User user = User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userService.saveOneUser(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .message("User succesfully registered.")
                .userId(user.getId())
                .build();
    }


    public AuthenticationResponse login (RegisterRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userService.getOneUserByUsername((request.getUsername()));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token("Bearer "+jwtToken)
                .userId(user.getId())
                .message("Current User")
                .build();
    }

    //    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                    request.getUsername(),
//                    request.getPassword()
//        ));
//        var user = userRepository.findByUsername(request.getUsername());
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
}
