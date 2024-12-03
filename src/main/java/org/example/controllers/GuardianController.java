package org.example.controllers;

import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;
import org.example.services.GuardianServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    private final GuardianServices guardianServices;

    public GuardianController(GuardianServices guardianServices) {
        this.guardianServices = guardianServices;
    }

    @GetMapping
    public List<Guardian> showAllGuardians(){
        return guardianServices.findAll();
    }

    @PostMapping
    public ResponseEntity<Guardian> createGuardian(@RequestBody GuardianRequest guardianRequest){

        Guardian guardian = guardianServices.createGuardian(guardianRequest);
        return new ResponseEntity<>(guardian, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public Guardian getGuardianById (@PathVariable int id){
        return guardianServices.findById(id);
    }


    @GetMapping("/name/{name}")
    public List<Guardian> getGuardianByName(@PathVariable String name) {
        return guardianServices.findByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guardian> deleteGuardian(@PathVariable int id){
        Guardian guardianToDelete = guardianServices.deleteById(id);
        return new ResponseEntity<>(guardianToDelete, HttpStatus.OK);

    }
/*
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
