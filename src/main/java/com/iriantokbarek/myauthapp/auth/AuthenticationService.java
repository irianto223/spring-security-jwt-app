package com.iriantokbarek.myauthapp.auth;

import com.iriantokbarek.myauthapp.config.JwtService;
import com.iriantokbarek.myauthapp.user.Role;
import com.iriantokbarek.myauthapp.user.User;
import com.iriantokbarek.myauthapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  public AuthenticationResponse register(RegisterRequest request) {

    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    // save to database
    repository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    // lanjut di https://youtu.be/KxqlJblhzfI?t=6740
    return null;
  }
}
