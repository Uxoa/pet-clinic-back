package org.example.dtos;


import jakarta.validation.constraints.NotNull;

public record PetRequest(
        @NotNull
        String name,
        String specie,
        String race,
        int age,
        int guardianId
) {
}
