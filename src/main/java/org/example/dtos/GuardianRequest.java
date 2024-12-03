package org.example.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GuardianRequest(
        @NotNull(message = "The name cannot be null.")
        String name,
        @Email
        String email,
        @NotNull
        @Size(min = 8, message = "The phone cannot be less than 8 numbers.")
        String phone,
        String address
){
}
