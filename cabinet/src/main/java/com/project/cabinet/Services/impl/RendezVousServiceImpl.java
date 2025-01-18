package com.project.cabinet.Services.impl;

import com.project.cabinet.DTO.RendezVousDTO;
import com.project.cabinet.Enum.StatusRendezVous;
import com.project.cabinet.Mapper.RendezVousMapper;
import com.project.cabinet.Model.RendezVous;
import com.project.cabinet.Repository.RendezVousRepository;
import com.project.cabinet.Repository.UserRepository;
import com.project.cabinet.Services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RendezVousServiceImpl implements RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RendezVousMapper rendezVousMapper;

    private static final Long DOCTEUR_ID = 1L; // ID du docteur unique
    @Override
    public RendezVousDTO demanderRendezVous(RendezVousDTO rendezVousDTO) {
        // Vérifiez si les informations minimales nécessaires sont fournies
        if (rendezVousDTO.getPatient() == null || rendezVousDTO.getPatient().getId() == null) {
            throw new IllegalArgumentException("Les informations du patient sont incomplètes ou manquantes.");
        }

        // Mapper le DTO vers l'entité
        RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);

        // Sauvegarder dans la base de données
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);

        // Retourner le DTO correspondant
        return rendezVousMapper.toDto(savedRendezVous);
    }
    @Override
    public List<RendezVousDTO> getRendezVousByPatientId(Long patientId) {
        // Récupère les rendez-vous pour un patient donné
        List<RendezVous> rendezVousList = rendezVousRepository.findByPatientId(patientId);

        // Utilise le mapper pour convertir les entités en DTO
        return rendezVousList.stream()
                .map(rendezVousMapper::toDto)
                .toList();
    }


    @Override
    public List<RendezVousDTO> getAllRendezVous() {
        return rendezVousRepository.findAll()
                .stream()
                .map(rendezVousMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public RendezVousDTO accepterRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Rendez-vous introuvable."));

        rendezVous.setStatus(StatusRendezVous.ACCEPTE);
        rendezVous = rendezVousRepository.save(rendezVous);

        return rendezVousMapper.toDto(rendezVous);
    }

    @Override
    public RendezVousDTO refuserRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Rendez-vous introuvable."));

        rendezVous.setStatus(StatusRendezVous.REFUSE);
        rendezVous = rendezVousRepository.save(rendezVous);

        return rendezVousMapper.toDto(rendezVous);
    }

}
