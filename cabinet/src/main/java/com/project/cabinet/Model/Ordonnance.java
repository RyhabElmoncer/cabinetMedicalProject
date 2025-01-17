package com.project.cabinet.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ordonnance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Champ obligatoire
    private String details;

    @Column(nullable = false) // Champ obligatoire
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false) // Clé étrangère vers User (patient)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "docteur_id", nullable = false) // Clé étrangère vers User (docteur)
    private User docteur;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDocteur() {
        return docteur;
    }

    public void setDocteur(User docteur) {
        this.docteur = docteur;
    }
}
