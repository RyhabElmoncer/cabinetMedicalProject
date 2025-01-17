package com.project.cabinet.Repository;

import com.project.cabinet.Model.User;
import com.project.cabinet.Enum.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

   
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findByRole(Role role);

    Optional<User> findByEmail(String email);}
