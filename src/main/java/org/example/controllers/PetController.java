package org.example.controllers;

import org.example.dtos.PetRequest;
import org.example.entities.Pet;
import org.example.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetRequest petRequest) {
        Pet pet = petService.createPet(petRequest);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Pet> showAllPets(){
        return this.petService.findAll();
    }


    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id){
        return petService.findByIdPets(id);
    }


    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable int id){
        this.petService.deletePetById(id);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody PetRequest petRequest){
        Pet pet = petService.updatePetById(id, petRequest);

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
}
