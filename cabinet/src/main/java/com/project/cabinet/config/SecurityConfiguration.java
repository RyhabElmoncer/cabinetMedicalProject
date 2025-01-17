package com.project.cabinet.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    // URLs accessibles sans authentification
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/authenticate",
            "/api/v1/auth/register",
            "/login",
            "/",
            "/inscription",
            "/home"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Default LogoutHandler can be used if no custom one is required
    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler(); // Default handler for clearing security context
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuration CORS globale
                .csrf(AbstractHttpConfigurer::disable) // Désactive CSRF, non nécessaire pour les API stateless
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll() // Permet l'accès non authentifié pour les URLs dans la liste blanche
                        .anyRequest().authenticated() // Toute autre requête nécessite une authentification
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Sessions stateless (JWT)
                .authenticationProvider(authenticationProvider) // Utilisation de votre provider d'authentification
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Filtre JWT
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler()) // Utilise le bean de LogoutHandler
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()) // Nettoyage du contexte
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("*")); // You can specify specific origins if needed
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Applique cette configuration à toutes les routes
        return source;
    }
}
