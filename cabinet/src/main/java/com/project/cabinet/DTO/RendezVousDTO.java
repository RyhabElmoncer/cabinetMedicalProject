package com.project.cabinet.DTO;

import com.project.cabinet.DTO.UserDTO;
import com.project.cabinet.Enum.StatusRendezVous;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RendezVousDTO {

    private Long id;
    private LocalDateTime date;
    private StatusRendezVous status;
    private LocalTime heure; // L'heure du rendez-vous
    @NotNull(message = "Patient information is required")

    private UserDTO patient; // UserDTO for patient

}
