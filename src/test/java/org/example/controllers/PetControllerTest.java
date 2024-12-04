package org.example.controllers;

import org.example.entities.Guardian;

import org.example.entities.Pet;
import org.example.repositories.GuardianRepository;
import org.example.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class PetControllerTest {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private GuardianRepository guardianRepository;


    @Autowired
    MockMvc mockMvc;

    @Test
    void givenAValidPet_whenPostRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);

        String request = """
                {
                  "name": "Buddy",
                  "specie": "dog",
                  "breed": "Labrador Retriever",
                  "age": 4,
                  "guardianId": 1
                }
                """;

        String response = """
                {
                    "id": 1,
                    "name": "Buddy",
                    "specie": "dog",
                    "breed": "Labrador Retriever",
                    "age": 4,
                    "guardian": {
                        "id": 1,
                        "name": "Alice Johnson",
                        "email": "alice.johnson@email.com",
                        "phone": "987654321",
                        "address": "123 Meadow Lane"
                    }
                }
                """;

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void givenValidGuardian_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        Guardian guardian2 = new Guardian("Emma Brown", "emma.brown@email.com", "987654322", "456 Oak Street");
        guardianRepository.save(guardian1);
        guardianRepository.save(guardian2);

        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        Pet pet2 = new Pet("Luna", "cat", "Siamese", 3, guardian2);
        petRepository.save(pet1);
        petRepository.save(pet2);

        String jsonreponse =
                """
                        [
                           {
                             "id": 1,
                             "name": "Buddy",
                             "specie": "dog",
                             "breed": "Labrador Retriever",
                             "age": 4,
                             "guardian": {
                               "id": 1,
                               "name": "Alice Johnson",
                               "email": "alice.johnson@email.com",
                               "phone": "987654321",
                               "address": "123 Meadow Lane"
                             }
                           },
                           {
                             "id": 2,
                             "name": "Luna",
                             "specie": "cat",
                             "breed": "Siamese",
                             "age": 3,
                             "guardian": {
                               "id": 2,
                               "name": "Emma Brown",
                               "email": "emma.brown@email.com",
                               "phone": "987654322",
                               "address": "456 Oak Street"
                             }
                           }
                         ]
                        """;

        mockMvc.perform(get("/pets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenValidIdPet_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);

        String jsonresponse =
                """
                        
                         {
                                   "id": 1,
                                   "name": "Buddy",
                                   "specie": "dog",
                                   "breed": "Labrador Retriever",
                                   "age": 4,
                                   "guardian": {
                                     "id": 1,
                                     "name": "Alice Johnson",
                                     "email": "alice.johnson@email.com",
                                     "phone": "987654321",
                                     "address": "123 Meadow Lane"
                                   }
                        }
                        
                        """;


        mockMvc.perform(get("/pets/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));
    }

    @Test
    void givenPatientById_whenDelete_theReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);
        assertEquals(1, petRepository.count());


        mockMvc.perform(delete("/pets/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(0, petRepository.count());
    }

    @Test
    void givenPatientById_whenUpdate_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        Guardian guardian2 = new Guardian("Emma Brown", "emma.brown@email.com", "987654322", "456 Oak Street");
        guardianRepository.save(guardian1);
        guardianRepository.save(guardian2);

        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);

        String jsonrequest =
                """
                                {
                                                       "name": "Buddy Updated",
                                                       "specie": "dog",
                                                       "breed": "Golden Retriever",
                                                       "age": 5,
                                                       "guardianId": 2
                                }
                        
                        """;

        String jsonresponse =
                """
                        
                          {
                            "id": 1,
                            "name": "Buddy Updated",
                            "specie": "dog",
                            "breed": "Golden Retriever",
                            "age": 5,
                            "guardian": {
                              "id": 2,
                              "name": "Emma Brown",
                              "email": "emma.brown@email.com",
                              "phone": "987654322",
                              "address": "456 Oak Street"
                            }
                          }
                        
                        """;

        mockMvc.perform(put("/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));

    }

}
