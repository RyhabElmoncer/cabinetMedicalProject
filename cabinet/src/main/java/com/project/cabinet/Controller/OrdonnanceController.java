package com.project.cabinet.Controller;

import com.project.cabinet.DTO.OrdonnanceDTO;
import com.project.cabinet.Services.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordonnances")
public class OrdonnanceController {

    @Autowired
    private OrdonnanceService ordonnanceService;

    @PostMapping("/ajouter")
    public ResponseEntity<OrdonnanceDTO> ajouterOrdonnance(@RequestBody OrdonnanceDTO ordonnanceDTO) {
        OrdonnanceDTO createdOrdonnance = ordonnanceService.ajouterOrdonnance(ordonnanceDTO);
        return ResponseEntity.ok(createdOrdonnance);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<OrdonnanceDTO>> consulterOrdonnancesParPatient(@PathVariable Long patientId) {
        List<OrdonnanceDTO> ordonnances = ordonnanceService.consulterOrdonnancesParPatient(patientId);
        return ResponseEntity.ok(ordonnances);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrdonnanceDTO>> getAllOrdonnances() {
        List<OrdonnanceDTO> ordonnances = ordonnanceService.getAllOrdonnances();
        return ResponseEntity.ok(ordonnances);
    }
}
