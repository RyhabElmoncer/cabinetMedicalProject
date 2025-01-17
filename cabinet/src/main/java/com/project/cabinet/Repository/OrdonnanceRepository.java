package com.project.cabinet.Repository;

import com.project.cabinet.Model.Ordonnance;
import com.project.cabinet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, String> {
}
