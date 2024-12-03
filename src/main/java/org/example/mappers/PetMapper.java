package org.example.mappers;

import org.example.dtos.PetRequest;
import org.example.entities.Guardian;
import org.example.entities.Pet;

public class PetMapper {
    public static Pet fromRequest(PetRequest petRequest, Guardian guardian) {
        return new Pet( petRequest.name(), petRequest.specie(),
                petRequest.breed(), petRequest.age(), guardian
        );
    }
}
