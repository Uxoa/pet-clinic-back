package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;
import org.example.services.GuardianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @GetMapping
    public List<Guardian> getGuardians(@RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return guardianService.searchByName(name);
        }
        return guardianService.findAll();
    }


    @PostMapping
    public ResponseEntity<Guardian> createGuardian(@Valid @RequestBody GuardianRequest guardianRequest){

        Guardian guardian = guardianService.createGuardian(guardianRequest);
        return new ResponseEntity<>(guardian, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public Guardian getGuardianById (@PathVariable int id){
        return guardianService.findById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Guardian> deleteGuardian(@PathVariable int id){
        Guardian guardianToDelete = guardianService.deleteById(id);
        return new ResponseEntity<>(guardianToDelete, HttpStatus.OK);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<Guardian> updateGuardian(@PathVariable int id, @RequestBody GuardianRequest guardianRequest){
        Guardian guardian = guardianService.updateGuardian(id, guardianRequest);
        return new ResponseEntity<>(guardian, HttpStatus.OK);
    }



}

