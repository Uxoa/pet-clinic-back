package org.example.dtos;


import jakarta.validation.constraints.NotNull;

public record PetRequest(
        String name,
        String specie,
        int age,
        int guardianId
) {
}
