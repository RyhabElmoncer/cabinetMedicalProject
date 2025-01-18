package com.project.cabinet.Repository;

import com.project.cabinet.Model.RendezVous;
import com.project.cabinet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, String>  {
    List<RendezVous> findByPatientId(Long patientId);

}
