package org.example;

import org.example.logic.Patient;
import org.example.logic.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientAcceptanceTest {
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

        //then


    }

}
