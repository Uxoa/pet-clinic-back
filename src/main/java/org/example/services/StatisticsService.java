package org.example.services;

import org.example.repositories.AppointmentRepository;
import org.example.repositories.GuardianRepository;
import org.example.repositories.PetRepository;
import org.example.repositories.StatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private StatisticsRepository statisticsRepository;
    private AppointmentRepository appointmentRepository;
    private GuardianRepository guardianRepository;
    private PetRepository petRepository;
    
    
    public StatisticsService(StatisticsRepository statisticsRepository, AppointmentRepository appointmentRepository, GuardianRepository guardianRepository, PetRepository petRepository) {
        this.statisticsRepository = statisticsRepository;
        this.appointmentRepository = appointmentRepository;
        this.guardianRepository = guardianRepository;
        this.petRepository = petRepository;
    }
    
    public StatisticsService() {
    }
}
