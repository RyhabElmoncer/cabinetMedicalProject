package com.project.cabinet.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdonnanceDTO {

    private Long id;
    private String details;
    private LocalDateTime date;
    private UserDTO patient;  // UserDTO for patient
    private UserDTO docteur;  // UserDTO for doctor

}
