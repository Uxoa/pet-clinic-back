package org.example.controllers;

import org.example.logic.Patient;
import org.example.logic.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenValidUser_whenSaving_thenReturnSuccess() throws Exception {

        Patient patient1 = new Patient("nombre", "gato", "raza1", "3");
        patientRepository.save(patient1);

        String jsonreponse =
                """
                                    [
                                        {
                                            "id": 1,
                                            "name": "nombre",
                                            "species": "gato",
                                            "race": "raza1",
                                            "age": 3
                                        }
                                    ]
                        """;
        //when
        mockMvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse))

        ;

    }

    @Test
    void givenPatientById_whenSearch_thenReturnSuccess() throws Exception {
        Patient patient1 = new Patient("humita", "gato", "domestico", "2");
        Patient patient2 = new Patient("humit", "gato", "domestico", "2");
        Patient patient3 = new Patient("humi", "gato", "domestico", "2");
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        String jsonresponse =
                """
                        {
                            "id":1,
                            "name": "humita",
                            "specie": "gato",
                            "race": "domestico",
                            "age": "2"
                        }
                        
                        """;

        mockMvc.perform(get("/patients/1", patient1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));
    }

    @Test
    void givenPatientById_whenDelete_theReturnSuccess() throws Exception {
        Patient patient = new Patient("humita", "gato", "domestica", "2");
        patientRepository.save(patient);
        assertEquals(1, patientRepository.count());

        String jsonresponse1 =
                """
                        {
                            "id": 1,
                            "name": "humita",
                            "specie": "gato",
                            "race": "domestico",
                            "age": "2"
                        }
                        """;
        mockMvc.perform(delete("/patients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(0, patientRepository.count());
    }

    @Test
    void givenPatientById_whenUpdate_thenReturnSuccess() throws Exception {
        Patient patient = new Patient("umi", "gato", "domestica", "2");
        patientRepository.save(patient);

        String jsonresponse =
                """
                         {
                             "id":1,
                             "name": "humita",
                             "specie": "gato",
                             "race": "domestico",
                             "age": "2"
                         }
                         
                         """;

        String jsonresponse1 =
                """
                         {
                             "id":1,
                             "name": "humita",
                             "specie": "gato",
                             "race": "domestico",
                             "age": "2"
                         }
                         
                         """;
        mockMvc.perform(put("/patients/1", patient.getId())
                . contentType(MediaType.APPLICATION_JSON)
                .content(jsonresponse))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse1));
    }


}
