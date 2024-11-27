package org.example.controllers;

import org.example.logic.Mentor;
import org.example.logic.MentorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MentorControllerTest {
    
    @Autowired
    private MentorRepository mentorRepository;
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    void givenAValidMentor_whenSaving_thenReturnSuccess() throws Exception {
        Mentor mentor1 = new Mentor("Fran", "Perez", 123456789);
        mentorRepository.save(mentor1);
        
        String jsonreponse =
              """
                          [
                              {
                                  "id": 1,
                                  "name": "Fran",
                                  "surname": "Perez",
                                  "phone": 123456789
                              }
                          ]
              """;
        
        mockMvc.perform(get("/mentors").contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(content().json(jsonreponse));
        
    }

}