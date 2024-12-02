package org.example.controllers;

import org.example.entities.Pet;
import org.example.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping
    public List<Pet> showAllPets(){
      return this.petRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet){
        Pet savedPet = petRepository.save(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable int id){
        this.petRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> searchById(@PathVariable int id){
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalPet.get(), HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet petRequest){
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Pet petToUpdate = optionalPet.get();
        petToUpdate.setName(petRequest.getName());
        petToUpdate.setSpecie(petRequest.getSpecie());
        petToUpdate.setRace(petRequest.getRace());
        petToUpdate.setAge(petRequest.getAge());
        petToUpdate.setGuardian(petRequest.getGuardian());
        petRepository.save(petToUpdate);

        return new ResponseEntity<>(petToUpdate, HttpStatus.OK);
    }
}
