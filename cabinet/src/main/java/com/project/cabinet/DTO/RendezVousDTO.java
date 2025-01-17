package com.project.cabinet.DTO;

import com.project.cabinet.DTO.UserDTO;
import com.project.cabinet.Enum.StatusRendezVous;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RendezVousDTO {

    private Long id;
    private LocalDateTime date;
    private StatusRendezVous status;
    private UserDTO patient; // UserDTO for patient
    private UserDTO docteur; // UserDTO for doctor

}
