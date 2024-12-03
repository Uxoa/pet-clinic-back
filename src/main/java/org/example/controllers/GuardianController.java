package org.example.controllers;

import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;
import org.example.services.GuardianServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    @Autowired
    private final GuardianServices guardianServices;

    public GuardianController(GuardianServices guardianServices) {
        this.guardianServices = guardianServices;
    }
    @GetMapping
    public List<Guardian> getGuardians(@RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return guardianServices.findByName(name);
        }
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


    @DeleteMapping("/{id}")
    public ResponseEntity<Guardian> deleteGuardian(@PathVariable int id){
        Guardian guardianToDelete = guardianServices.deleteById(id);
        return new ResponseEntity<>(guardianToDelete, HttpStatus.OK);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<Guardian> updateGuardian(@PathVariable int id, @RequestBody GuardianRequest guardianRequest){
        Guardian guardian =guardianServices.updateGuardian(id, guardianRequest);
        return new ResponseEntity<>(guardian, HttpStatus.OK);
    }

    

}
