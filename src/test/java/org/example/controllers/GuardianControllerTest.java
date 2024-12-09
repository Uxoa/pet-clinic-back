package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;
import org.example.repositories.GuardianRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void givenValidGuardian_whenPostRequestIsMade_thenReturnSuccess() throws Exception {
        String guardianRequest = """
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
                        .content(guardianRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void givenValidGuardian_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
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

        mockMvc.perform(get("/guardians")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }@Test
    void givenValidGuardianName_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);

        String jsonreponse =
                """
                                [
                                	{
                                	  "id": 1,
                                	  "name": "Alice",
                                	  "email": "alice.johnson@email.com",
                                	  "phone": "987654321",
                                	  "address": "123 Meadow Lane"
                                	}
                                ]
                        """;

        mockMvc.perform(get("/guardians?name=Alice").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenValidGuardianId_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);


        String jsonreponse =
                """
                                	{
                                	  "id": 1,
                                	  "name": "Alice Johnson",
                                	  "email": "alice.johnson@email.com",
                                	  "phone": "987654321",
                                	  "address": "123 Meadow Lane"
                                	}
                        """;
        mockMvc.perform(get("/guardians/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenValidGuardianId_whenDeleteRequestIsMade_thenReturnSuccessAndGuardianRepositoryEmpty() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);

        String jsonreponse =
                """
                                	{
                                	  "id": 1,
                                	  "name": "Alice Johnson",
                                	  "email": "alice.johnson@email.com",
                                	  "phone": "987654321",
                                	  "address": "123 Meadow Lane"
                                	}
                        """;

        mockMvc.perform(delete("/guardians/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));
        assertEquals(0, guardianRepository.count());
    }

    @Test
    void givenValidGuardianIdAndValidGuardian_whenPutRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);

        String jsonrequest =
                """
                                 {
                                 "name": "Alice Johnson",
                                 "email": "alice.johnson@email.com",
                                 "phone": "123456789",
                                 "address": "123 Meadow Lane"
                                 }
                     """;

        String jsonreponse =
                """
                               {
                                 "id": 1,
                                 "name": "Alice Johnson",
                                 "email": "alice.johnson@email.com",
                                 "phone": "123456789",
                                 "address": "123 Meadow Lane"
                               }
                   """;

        mockMvc.perform(put("/guardians/1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));
    }

}