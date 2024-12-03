package org.example.services;

import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;
import org.example.mappers.GuardianMapper;
import org.example.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

@Service
public class GuardianServices {
    private final GuardianRepository guardianRepository;

    public GuardianServices(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }
    public Guardian createGuardian(GuardianRequest guardianRequest) {
        Guardian guardian = GuardianMapper.fromRequest(guardianRequest);
        return guardianRepository.save(guardian);
    };

}
