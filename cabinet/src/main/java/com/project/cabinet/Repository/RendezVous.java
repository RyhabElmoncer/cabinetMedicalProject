package com.project.cabinet.Repository;

import com.project.cabinet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVous extends JpaRepository<User, String>  {
}
