package org.example.controllers;

import org.example.entities.Guardian;

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

        String response ="""
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
/*
    @Test
    void givenValidUser_whenSaving_thenReturnSuccess() throws Exception {

        Pet pet1 = new Pet("nombre", "gato", "raza1", 3);
        petRepository.save(pet1);

        String jsonresponse = String.format(
                """
                [
                    {
                        "id": %d,
                        "name": "nombre",
                        "specie": "gato",
                        "race": "raza1",
                        "age": 3
                    }
                ]
                """,
                pet1.getId()
        );

        //when
        mockMvc.perform(get("/pets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse))

        ;

    }

    @Test
    void givenPatientById_whenSearch_thenReturnSuccess() throws Exception {
        Pet pet = new Pet("humita", "gato", "domestico", 2);
        petRepository.save(pet);

        String jsonresponse =
                """

                    {
                        "id": 1,
                        "name": "humita",
                        "specie": "gato",
                        "race": "domestico",
                        "age": 2
                    }

                """;


        mockMvc.perform(get("/patients/1" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));
    }

    @Test
    void givenPatientById_whenDelete_theReturnSuccess() throws Exception {
        Pet pet = new Pet("humita", "gato", "domestica", 2);
        petRepository.save(pet);
        assertEquals(1, petRepository.count());

        String jsonresponse1 =
                """
                [
                    {
                        "id": 1,
                        "name": "humita",
                        "specie": "gato",
                        "race": "domestico",
                        "age": "2"
                    }
                ]
                """;

        mockMvc.perform(delete("/pets/" + pet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                assertEquals(0, petRepository.count());
    }

    @Test
    void givenPatientById_whenUpdate_thenReturnSuccess() throws Exception {
        Pet pet = new Pet("umi", "gato", "domestica", 2);
        petRepository.save(pet);

        String jsonrequest =
                """

                    {
                        "id": 1,
                        "name": "humita",
                        "specie": "gato",
                        "race": "domestico",
                        "age": 2
                    }

                """;

        String jsonresponse1 =
                """

                    {
                        "id": 1,
                        "name": "humita",
                        "specie": "gato",
                        "race": "domestico",
                        "age": 2
                    }

                """;

        mockMvc.perform(put("/pets/1", pet.getId())
                . contentType(MediaType.APPLICATION_JSON)
                .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse1));

    }

*/
}
