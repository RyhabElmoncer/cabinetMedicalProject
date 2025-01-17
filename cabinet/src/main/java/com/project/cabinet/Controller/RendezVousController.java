package com.project.cabinet.Controller;

import com.project.cabinet.DTO.RendezVousDTO;
import com.project.cabinet.Services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping("/demander")
    public ResponseEntity<RendezVousDTO> demanderRendezVous(@RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO createdRendezVous = rendezVousService.demanderRendezVous(rendezVousDTO);
        return ResponseEntity.ok(createdRendezVous);
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