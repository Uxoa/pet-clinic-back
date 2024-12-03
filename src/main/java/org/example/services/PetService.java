package org.example.services;

import org.example.repositories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


}
