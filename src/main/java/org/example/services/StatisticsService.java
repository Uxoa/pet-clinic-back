package org.example.services;

import org.example.repositories.AppointmentRepository;
import org.example.repositories.GuardianRepository;
import org.example.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {
    
    private AppointmentRepository appointmentRepository;
    private PetRepository petRepository;
    private GuardianRepository guardianRepository;
    
    public StatisticsService(AppointmentRepository appointmentRepository, PetRepository petRepository, GuardianRepository guardianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
    }
    
    public Map<String, Integer> getGlobalStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("appointments", (int) appointmentRepository.count());
        statistics.put("pets", (int) petRepository.count());
        statistics.put("guardians", (int) guardianRepository.count());
        return statistics;
    }
}