package org.example.repositories;

import org.example.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
    List<Guardian> findByName(String name);
}
