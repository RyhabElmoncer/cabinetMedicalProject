package com.project.cabinet.Mapper;

import com.project.cabinet.DTO.OrdonnanceDTO;
import com.project.cabinet.DTO.UserDTO;
import com.project.cabinet.Model.Ordonnance;
import org.springframework.stereotype.Component;

@Component
public class OrdonnanceMapper {

    public OrdonnanceDTO toDto(Ordonnance ordonnance) {
        return OrdonnanceDTO.builder()
                .id(ordonnance.getId())
                .details(ordonnance.getDetails())
                .date(ordonnance.getDate())
                .patient(UserDTO.builder()
                        .id(ordonnance.getPatient().getId())
                        .username(ordonnance.getPatient().getUsername())
                        .email(ordonnance.getPatient().getEmail())
                        .build())
                .docteur(UserDTO.builder()
                        .id(ordonnance.getDocteur().getId())
                        .username(ordonnance.getDocteur().getUsername())
                        .email(ordonnance.getDocteur().getEmail())
                        .build())
                .build();
    }

    public Ordonnance toEntity(OrdonnanceDTO dto) {
        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setDetails(dto.getDetails());
        ordonnance.setDate(dto.getDate());
        return ordonnance;
    }
}
