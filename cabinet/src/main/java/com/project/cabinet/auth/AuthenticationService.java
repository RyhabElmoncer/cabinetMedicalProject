package com.project.cabinet.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cabinet.Enum.Role;
import com.project.cabinet.Model.User;
import com.project.cabinet.Repository.UserRepository;
import com.project.cabinet.config.JwtService;
import com.project.cabinet.token.Token;
import com.project.cabinet.token.TokenRepository;
import com.project.cabinet.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse signup(RegisterRequest request) {
    if (repository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("L'email est déjà utilisé");
    }

    // Set default role to PATIENT if no role is provided
    var role = request.getRole() != null ? request.getRole() : Role.PATIENT;

    var user = User.builder()
            .username(request.getUsername())
            .lastName(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(role) // Assign the role here
            .build();

    repository.save(user);

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(null)
            .message("Inscription réussie ! Vous pouvez maintenant vous connecter.")
            .build();
  }


  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );

      var user = repository.findByEmail(request.getEmail())
              .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + request.getEmail()));

      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);

      // Revoke and save tokens
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);

      // Include the role in the response
      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .role(user.getRole().toString()) // Convert role to String
              .build();

    } catch (Exception e) {
      // Log exception for debugging
      System.err.println("Authentication failed: " + e.getMessage());
      throw e; // Re-throw exception
    }
  }


  private void saveUserToken(User user, String jwtToken) {
    Token token = Token.builder()
            .user(user)  // Utilisation de la classe User correctement
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }



  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(Long.valueOf(user.getId()));
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
