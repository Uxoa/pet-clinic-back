package org.example.services;

import org.example.dtos.PetRequest;
import org.example.entities.Guardian;
import org.example.entities.Pet;
import org.example.exeptions.GuardianNotFoundException;
import org.example.mappers.PetMapper;
import org.example.repositories.PetRepository;
import org.example.services.GuardianService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {
    private PetRepository petRepository;
    private GuardianService guardianRepository;

    public PetService(PetRepository petRepository, GuardianService guardianRepository) {
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
    }

    public Pet createPet(PetRequest petRequest) {

        Guardian guardian = guardianRepository.findById(petRequest.guardianId());
        Pet pet = PetMapper.fromRequest(petRequest, guardian);
        return petRepository.save(pet);

    }

}
