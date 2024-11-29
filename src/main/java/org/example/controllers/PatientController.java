package org.example.controllers;

import org.example.logic.Patient;
import org.example.logic.PatientRepository;
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
    public List<Patient> showAllPatients(){
      return this.patientRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id){
        this.patientRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> searchById(@PathVariable int id){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalPatient.get(), HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patientRequest){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Patient patientToUpdate = optionalPatient.get();
        patientToUpdate.setName(patientRequest.getName());
        patientToUpdate.setSpecie(patientRequest.getSpecie());
        patientToUpdate.setRace(patientRequest.getRace());
        patientToUpdate.setAge(patientRequest.getAge());
        patientToUpdate.setMentor(patientRequest.getMentor());
        patientRepository.save(patientToUpdate);

        return new ResponseEntity<>(patientToUpdate, HttpStatus.OK);
    }
}
