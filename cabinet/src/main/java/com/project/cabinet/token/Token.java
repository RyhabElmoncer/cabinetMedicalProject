package com.project.cabinet.token;

import com.project.cabinet.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation JPA pour une entité MySQL
@Table(name = "tokens") // Nom de la table en base de données
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation pour MySQL
  private Long id;

  @Column(nullable = false, unique = true) // Token doit être unique
  private String token;

  @Enumerated(EnumType.STRING) // Pour stocker l'énumération en tant que chaîne de texte
  private TokenType tokenType = TokenType.BEARER;

  @Column(nullable = false) // Ne peut pas être null
  private boolean revoked;

  @Column(nullable = false) // Ne peut pas être null
  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY) // Relation avec l'utilisateur
  @JoinColumn(name = "user_id", nullable = false) // Colonne de clé étrangère
  private User user;
}
