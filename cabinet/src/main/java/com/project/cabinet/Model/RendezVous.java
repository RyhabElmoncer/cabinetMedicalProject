package com.project.cabinet.Model;
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private StatusRendezVous status;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User docteur;



    // Getters et Setters
}
