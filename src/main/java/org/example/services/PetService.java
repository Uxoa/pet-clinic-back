package org.example.services;

import org.example.dtos.PetRequest;
import org.example.entities.Guardian;
import org.example.entities.Pet;

import org.example.exeptions.PetNotFoundException;
import org.example.mappers.PetMapper;
import org.example.repositories.PetRepository;

import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Pet> findAll() {
        return petRepository.findAll();
    }
    public Pet findByIdPets(int id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet Not Found");
        }

        return optionalPet.get();
    }


    public void deletePetById(int id) {
        Optional<Pet> petToDelete = petRepository.findById(id);
        if (petToDelete.isEmpty()) {
            throw new PetNotFoundException("Pet not found");
        }
        petRepository.deleteById(id);
    }

    public Pet updatePetById(int id, PetRequest petRequest) {
        Pet petToUpdate = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));


        Guardian guardian = petToUpdate.getGuardian();
        if (petRequest.guardianId() != guardian.getId()) {
            guardian = guardianRepository.findById(petRequest.guardianId());
        }

        petToUpdate.setName(petRequest.name());
        petToUpdate.setSpecie(petRequest.specie());
        petToUpdate.setBreed(petRequest.breed());
        petToUpdate.setAge(petRequest.age());
        petToUpdate.setGuardian(guardian);

        return petRepository.save(petToUpdate);


    }


}
