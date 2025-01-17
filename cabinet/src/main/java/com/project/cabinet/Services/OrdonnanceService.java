package com.project.cabinet.Services;

import com.project.cabinet.DTO.OrdonnanceDTO;
import java.util.List;

public interface OrdonnanceService {
    OrdonnanceDTO ajouterOrdonnance(OrdonnanceDTO ordonnanceDTO);
    List<OrdonnanceDTO> consulterOrdonnancesParPatient(Long patientId);
    List<OrdonnanceDTO> getAllOrdonnances();
}
