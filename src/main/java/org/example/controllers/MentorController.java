package org.example.controllers;

import org.example.logic.Mentor;
import org.example.logic.MentorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
