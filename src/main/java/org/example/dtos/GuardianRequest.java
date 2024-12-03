package org.example.dtos;

import jakarta.persistence.Entity;
import org.example.entities.Guardian;
import org.example.entities.Pet;

import java.util.List;


public record GuardianRequest(
        String name,
        String surname,
        String email,
        String phone,
        String address
){
}
