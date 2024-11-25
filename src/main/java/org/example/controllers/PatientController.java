package org.example.controllers;

import jakarta.persistence.Entity;
import org.example.logic.Patient;
import org.example.logic.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> showAll(){
        return this.patientRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        if (patient.getRace() == null || patient.getRace().isEmpty() || !patient.getRace().equals(".*[^a-zA-Z].*")){
            patient.setRace("unknown");
        }
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id){
        this.patientRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Patient> searchById(@PathVariable int id){
        return this.patientRepository.findById(id);
    }




}