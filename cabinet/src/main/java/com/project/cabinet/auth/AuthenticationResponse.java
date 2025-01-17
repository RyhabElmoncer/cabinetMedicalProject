package com.project.cabinet.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cabinet.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  private String message;
  private String role ;
}
