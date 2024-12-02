package org.example.dtos;

import org.example.entities.Guardian;
import org.example.entities.Pet;

import java.util.List;

public record GuardianRequest(
        String name, String surname, String email, String phone, String address, List<Pet> pets
) {
    public static Guardian toEntity(String name, String surname, String email, String phone, String address, List<Pet> pets) {
       return new Guardian(name, surname, email, phone, address, pets);
    }
}
