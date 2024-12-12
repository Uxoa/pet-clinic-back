package org.example.config;

import org.example.entities.Guardian;
import org.example.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("!test")
public class InitFakeData {
    
    @Autowired
    GuardianRepository guardianRepository;
    
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            List<Guardian> guardianList = List.of(
                  new Guardian("Paloma", "655014845", "palomita@gmail.com", "mi direccion1"),
                  new Guardian("nombre2", "677876545", "ggg@gmail.com", "mi direccion2"));
            guardianRepository.saveAll(guardianList);
            
        };
    }
    
}

