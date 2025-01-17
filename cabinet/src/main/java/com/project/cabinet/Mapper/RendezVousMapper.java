package com.project.cabinet.Mapper;

import com.project.cabinet.DTO.RendezVousDTO;
import com.project.cabinet.Model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    @Mapping(source = "patient.id", target = "patient.id")
    RendezVousDTO toDto(RendezVous rendezVous);

    @Mapping(target = "docteur", ignore = true) // Ignorer le champ docteur
    RendezVous toEntity(RendezVousDTO rendezVousDTO);
}
