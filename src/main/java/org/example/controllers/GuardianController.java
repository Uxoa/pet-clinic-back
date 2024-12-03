package org.example.controllers;

import org.example.entities.Guardian;
import org.example.services.GuardianServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Guard;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    private final GuardianServices guardianServices;

    public GuardianController(GuardianServices guardianServices) {
        this.guardianServices = guardianServices;
    }

    /*private final GuardianRepository guardianRepository;
    private final PetRepository petRepository;

    public GuardianController(GuardianRepository guardianRepository, PetRepository petRepository) {
        this.guardianRepository = guardianRepository;
        this.petRepository = petRepository;
    }

    @PostMapping
    public ResponseEntity<Guardian> createMentor(@RequestBody Guardian mentor){
        Guardian savedGuardian = guardianRepository.save(mentor);
        return new ResponseEntity<>(savedGuardian, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Guardian> showAllGuardians(){
        return this.guardianRepository.findAll();
    }*/

<<<<<<< HEAD
=======


/*
>>>>>>> 23787cf35f2ccef482a2356e980298320b6aa455
    @GetMapping("/{id}")
    public ResponseEntity<Guardian> getGuardianById(@PathVariable int id ){
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if (optionalGuardian.isPresent()) {
            return new ResponseEntity<>(optionalGuardian.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Guardian> getGuardianByName(@PathVariable String name ){
        Optional<Guardian> optionalGuardian = guardianRepository.findByName(name);

        if (optionalGuardian.isPresent()) {
            return new ResponseEntity<>(optionalGuardian.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Guardian> deleteGuardian(@PathVariable int id){
        Optional<Guardian> guardianToDelete = guardianRepository.findById(id);
        if( guardianToDelete.get().getPet().isEmpty()){
            guardianRepository.deleteById(id);
            return new ResponseEntity<>(guardianToDelete.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<Guardian> updateGuardian(@PathVariable int id, @RequestBody Guardian guardian){
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);
        if(optionalGuardian.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Guardian guardianToUpdate = optionalGuardian.get();
        guardianToUpdate.setName(guardian.getName());
        guardianToUpdate.setSurname(guardian.getSurname());
        guardianToUpdate.setPhone(guardian.getPhone());
        guardianRepository.save(guardianToUpdate);

        return new ResponseEntity<>(guardianToUpdate, HttpStatus.OK);
    }

    
  */
}
