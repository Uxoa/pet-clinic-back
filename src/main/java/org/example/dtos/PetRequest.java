package org.example.dtos;


import jakarta.validation.constraints.NotNull;
import org.example.entities.Appointment;

import java.util.List;

public record PetRequest(
       @NotNull
        String name,
        String specie,
        String breed,
        int age,
        int guardianId

) {
}
