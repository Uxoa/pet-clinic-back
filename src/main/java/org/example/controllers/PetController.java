package org.example.controllers;

import org.example.entities.Pet;
import org.example.repositories.PetRepository;
import org.example.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {
    /*   private final PetService petService;

       public PetController(PetService petService) {
           this.petService = petService;
       }

        @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet){
        Pet pet = petService.save(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);

    }
   */
    private final PetRepository petRepository;

    public PetController(PetService petService, PetRepository petRepository) {
        this.petRepository = petRepository;
    }





    @GetMapping
    public List<Pet> showAllPets(){
      return this.petRepository.findAll();
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
        petRepository.save(petToUpdate);

        return new ResponseEntity<>(petToUpdate, HttpStatus.OK);
    }
}
