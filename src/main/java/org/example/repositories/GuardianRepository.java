package org.example.repositories;

import org.example.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
    Optional<Guardian> findByName(String name);
}
