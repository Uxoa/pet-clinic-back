package org.example.controllers;

import org.example.logic.Mentor;
import org.example.logic.MentorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentors")
public class MentorController {
    private final MentorRepository mentorRepository;

    public MentorController(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @GetMapping
    public List<Mentor> showAllMentors(){
        return this.mentorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor){

        Mentor savedMentor = mentorRepository.save(mentor);
        return new ResponseEntity<>(savedMentor, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable int id ){
        Optional<Mentor> optionalMentor = mentorRepository.findById(id);

        if (optionalMentor.isPresent()) {
            return new ResponseEntity<>(optionalMentor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Mentor> getMentorByName(@PathVariable String name ){
        Optional<Mentor> optionalMentor = mentorRepository.findByName(name);

        if (optionalMentor.isPresent()) {
            return new ResponseEntity<>(optionalMentor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
}
