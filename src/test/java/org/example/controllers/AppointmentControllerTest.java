package org.example.controllers;

import org.example.entities.Appointment;
import org.example.entities.Guardian;
import org.example.entities.Pet;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.GuardianRepository;
import org.example.repositories.PetRepository;
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
class AppointmentControllerTest {


    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenAValidAppointment_whenPostRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian);
        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian);
        petRepository.save(pet1);


        String request = """
                {
                  "date": "2024-04-15",
                  "time": "15:30",
                  "reason": "Vaccination",
                  "petId": 1
                }
                """;

        String response = """
                {
                  "id": 1,
                  "date": "2024-04-15",
                  "time": "15:30",
                  "reason": "Vaccination",
                  "pet": {
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
                }
                """;

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        Guardian guardian2 = new Guardian("Emma Brown", "emma.brown@email.com", "987654322", "456 Oak Street");
        guardianRepository.save(guardian1);
        guardianRepository.save(guardian2);

        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        Pet pet2 = new Pet("Luna", "cat", "Siamese", 3, guardian2);
        petRepository.save(pet1);
        petRepository.save(pet2);

        Appointment appointment1 = new Appointment("2024-04-15", "15:30", "Vaccination", pet1);
        appointmentRepository.save(appointment1);
        Appointment appointment2 = new Appointment("2024-04-16", "10:00", "Annual Checkup", pet2);
        appointmentRepository.save(appointment2);


        String jsonreponse =
                """
                        [
                          {
                            "id": 1,
                            "date": "2024-04-15",
                            "time": "15:30",
                            "reason": "Vaccination",
                            "pet": {
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
                          },
                          {
                            "id": 2,
                            "date": "2024-04-16",
                            "time": "10:00",
                            "reason": "Annual Checkup",
                            "pet": {
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
                          }
                        ]
                        """;

        mockMvc.perform(get("/appointments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenValidIdAppointment_whenGetRequestIsMade_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian1);

        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);

        Appointment appointment1 = new Appointment("2024-04-15", "15:30", "Vaccination", pet1);
        appointmentRepository.save(appointment1);


        String jsonreponse =
                """
                        {
                          "id": 1,
                          "date": "2024-04-15",
                          "time": "15:30",
                          "reason": "Vaccination",
                          "pet": {
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
                        }
                        """;

        mockMvc.perform(get("/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenAppointmentById_whenDelete_theReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);
        Appointment appointment1 = new Appointment("2024-04-15", "15:30", "Vaccination", pet1);
        appointmentRepository.save(appointment1);

        assertEquals(1, appointmentRepository.count());


        mockMvc.perform(delete("/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(0, appointmentRepository.count());
    }

    @Test
    void givenPatientById_whenUpdate_thenReturnSuccess() throws Exception {
        Guardian guardian1 = new Guardian("Alice Johnson", "alice.johnson@email.com", "987654321", "123 Meadow Lane");
        guardianRepository.save(guardian1);

        Pet pet1 = new Pet("Buddy", "dog", "Labrador Retriever", 4, guardian1);
        petRepository.save(pet1);

        Appointment appointment1 = new Appointment("2024-04-15", "15:30", "Vaccination", pet1);
        appointmentRepository.save(appointment1);

        String jsonrequest =
                """
                                {
                                  "date": "2024-04-16",
                                  "time": "14:00",
                                  "reason": "Follow-up",
                                  "petId": 1
                                }
                        
                        """;

        String jsonresponse =
                """
                        
                          {
                            "id": 1,
                            "date": "2024-04-16",
                            "time": "14:00",
                            "reason": "Follow-up",
                            "pet": {
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
                          }
                        
                        """;

        mockMvc.perform(put("/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));

    }
}