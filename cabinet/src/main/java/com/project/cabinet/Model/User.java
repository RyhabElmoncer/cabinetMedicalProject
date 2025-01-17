package com.project.cabinet.Model;

import com.project.cabinet.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Utilisation de SINGLE_TABLE pour l'héritage
@DiscriminatorColumn(name = "user_role", discriminatorType = DiscriminatorType.STRING) // Renamed the discriminator column
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role") // Ensure the role field maps correctly
    private Role role;

    // Méthodes de l'interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Pas de rôles supplémentaires à renvoyer
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Compte valide par défaut
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Compte non verrouillé par défaut
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Identifiants valides par défaut
    }

    @Override
    public boolean isEnabled() {
        return true; // Compte activé par défaut
    }
}
