package org.example.repositories;

import org.example.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
    List<Guardian> findByName(String name);
    @Query(value = "SELECT g FROM Guardian g WHERE LOWER(g.name) LIKE LOWER(CONCAT ('%',:name, '%'))")
    List<Guardian> findLikeName(String name);
}
