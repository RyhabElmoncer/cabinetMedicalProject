package com.project.cabinet.Services.impl;

import com.project.cabinet.DTO.OrdonnanceDTO;
import com.project.cabinet.Mapper.OrdonnanceMapper;
import com.project.cabinet.Model.Ordonnance;
import com.project.cabinet.Repository.OrdonnanceRepository;
import com.project.cabinet.Repository.UserRepository;
import com.project.cabinet.Services.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdonnanceServiceImpl implements OrdonnanceService {

    @Autowired
    private OrdonnanceRepository ordonnanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdonnanceMapper ordonnanceMapper;

    @Override
    public OrdonnanceDTO ajouterOrdonnance(OrdonnanceDTO ordonnanceDTO) {
        Ordonnance ordonnance = ordonnanceMapper.toEntity(ordonnanceDTO);

        ordonnance.setDate(LocalDateTime.now());

        ordonnance.setPatient(userRepository.findById(String.valueOf(ordonnanceDTO.getPatient().getId()))
                .orElseThrow(() -> new IllegalArgumentException("Patient introuvable.")));

        ordonnance.setDocteur(userRepository.findById(String.valueOf(ordonnanceDTO.getDocteur().getId()))
                .orElseThrow(() -> new IllegalArgumentException("Docteur introuvable.")));

        ordonnance = ordonnanceRepository.save(ordonnance);
        return ordonnanceMapper.toDto(ordonnance);
    }


    @Override
    public List<OrdonnanceDTO> consulterOrdonnancesParPatient(Long patientId) {
        return ordonnanceRepository.findByPatientId(patientId)
                .stream()
                .map(ordonnanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdonnanceDTO> getAllOrdonnances() {
        return ordonnanceRepository.findAll()
                .stream()
                .map(ordonnanceMapper::toDto)
                .collect(Collectors.toList());
    }
}
