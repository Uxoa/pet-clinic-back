package org.example.dtos;


import jakarta.validation.constraints.NotNull;

public record PetRequest(
        @NotNull (message = "The name cannot be null.")
        String name,

        @NotNull (message = "The specie cannot be null")
        String specie,

        String breed,

        int age,

        int guardianId
) {
}
