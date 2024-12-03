package org.example.controllers;

import org.example.entities.Guardian;
import org.example.repositories.GuardianRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GuardianControllerTest {

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createAGuardian() throws Exception {
        String request = """
                {
                  "name": "Alice Johnson",
                  "email": "alice.johnson@email.com",
                  "phone": "987654321",
                  "address": "123 Meadow Lane"
                }
                """;
        String response = """
                {
                  "name": "Alice Johnson",
                  "email": "alice.johnson@email.com",
                  "phone": "987654321",
                  "address": "123 Meadow Lane",
                  "pets": null
                }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void givenAValidMentor_whenSaving_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);

        String jsonreponse =
                """
                                [
                                	{
                                	  "id": 1,
                                	  "name": "Alice Johnson",
                                	  "email": "alice.johnson@email.com",
                                	  "phone": "987654321",
                                	  "address": "123 Meadow Lane"
                                	}
                                ]
                        """;

        mockMvc.perform(get("/guardians").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }
/*
    @Test
    void givenMentorById_whenSearch_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Paloma", "Perez", "paloma@email.com", "987654321", "calle 123");
        Guardian guardian1 = new Guardian("Palom", "Perez","paloma@email.com", "987654321", "calle 123");
        Guardian guardian2 = new Guardian("Palo", "Perez", "paloma@email.com", "987654321", "calle 123");
        guardianRepository.save(guardian);
        guardianRepository.save(guardian1);
        guardianRepository.save(guardian2);

        String jsonreponse =
                """
                        
                                        {
                                            "id": 1,
                                            "name": "Paloma",
                                            "surname": "Perez",
                                            "email": "paloma@email.com",
                                            "phone": "987654321",
                                            "address": "calle 123"
                                        }
                        
                        """;
        mockMvc.perform(get("/guardians/1", guardian1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenMentorById_whenDelete_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Ivan", "Perez", "ivan@email.com","987654321", "street 123");
        guardianRepository.save(guardian);

        String jsonreponse =
                """
                        
                                        {
                                            "id": 1,
                                            "name": "Ivan",
                                            "surname": "Perez",
                                            "email": "ivan@email.com",
                                            "phone": "987654321",
                                            "address": "street 123"
                                        }
                        
                        """;

        mockMvc.perform(delete("/guardians/1", guardian.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));
    }

    @Test
    void givenMentorById_whenUpdate_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Layla", "Perez", "layla@email.com", "987654321", "calle 123");
        guardianRepository.save(guardian);

        String jsonreponse =
                """
                        
                                        {
                                            "id": 1,
                                            "name": "Layl",
                                            "surname": "Pere",
                                            "email": "layla@email.com",
                                            "phone": "987654321",
                                            "address": "calle 123"
                                        }
                        
                        """;

        String jsonreponse1 =
                """
                        
                                        {
                                            "id": 1,
                                            "name": "Layl",
                                            "surname": "Pere",
                                            "phone": "987654322"
                                            "email": "layla@email.com",
                                            "phone": "987654321",
                                            "address": "calle 123"
                                        }
                        
                        """;

        mockMvc.perform(put("/guardians/1", guardian.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(jsonreponse))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse1));
    }*/

}