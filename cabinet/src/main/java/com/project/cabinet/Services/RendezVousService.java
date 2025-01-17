package com.project.cabinet.Services;

import com.project.cabinet.DTO.RendezVousDTO;
import java.util.List;

public interface RendezVousService {
    RendezVousDTO demanderRendezVous(RendezVousDTO rendezVousDTO);
    List<RendezVousDTO> getAllRendezVous();
    RendezVousDTO accepterRendezVous(Long id);
    RendezVousDTO refuserRendezVous(Long id);
}
