package org.example.services;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
import org.example.entities.Pet;
import org.example.mappers.AppointmentMapper;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private PetRepository petRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PetRepository petRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
    }

    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        Optional<Pet> pet = petRepository.findById(appointmentRequest.petId());
        Appointment appointment = AppointmentMapper.fromRequest(appointmentRequest, pet.get());
        return appointmentRepository.save(appointment);
    }
}
