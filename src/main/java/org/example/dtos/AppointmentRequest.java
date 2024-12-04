package org.example.dtos;

import jakarta.validation.constraints.NotNull;

public record AppointmentRequest(
        @NotNull
        String date,
        String time,
        String reason,
        int petId) {

}
