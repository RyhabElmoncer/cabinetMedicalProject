package com.project.cabinet.Controller;

import com.project.cabinet.DTO.RendezVousDTO;
import com.project.cabinet.Services.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {
    private static final Logger log = LoggerFactory.getLogger(RendezVousController.class);

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping("/rendezvous")
    public ResponseEntity<RendezVousDTO> demanderRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        if (rendezVousDTO.getPatient() == null || rendezVousDTO.getPatient().getId() == null) {
            throw new IllegalArgumentException("Patient information is required.");
        }
        log.info("Received RendezVous request: {}", rendezVousDTO);
        RendezVousDTO response = rendezVousService.demanderRendezVous(rendezVousDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatientId(@PathVariable Long id) {
        List<RendezVousDTO> rendezVousList = rendezVousService.getRendezVousByPatientId(id);
        return ResponseEntity.ok(rendezVousList);
    }



    @GetMapping("/all")
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        List<RendezVousDTO> rendezVousList = rendezVousService.getAllRendezVous();
        return ResponseEntity.ok(rendezVousList);
    }
    @PutMapping("/{id}/accepter")
    public ResponseEntity<RendezVousDTO> accepterRendezVous(@PathVariable Long id) {
        RendezVousDTO updatedRendezVous = rendezVousService.accepterRendezVous(id);
        return ResponseEntity.ok(updatedRendezVous);
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<RendezVousDTO> refuserRendezVous(@PathVariable Long id) {
        RendezVousDTO updatedRendezVous = rendezVousService.refuserRendezVous(id);
        return ResponseEntity.ok(updatedRendezVous);
    }
}