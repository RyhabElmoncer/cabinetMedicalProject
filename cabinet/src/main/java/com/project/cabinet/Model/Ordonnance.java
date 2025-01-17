package com.project.cabinet.Model;
@Entity
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;
    private LocalDateTime date;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User docteur;

}
