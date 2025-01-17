package com.project.cabinet.Model;

import com.project.cabinet.Enum.StatusRendezVous;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private StatusRendezVous status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "docteur_id", nullable = false)
    private User docteur;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public StatusRendezVous getStatus() {
        return status;
    }

    public void setStatus(StatusRendezVous status) {
        this.status = status;
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
