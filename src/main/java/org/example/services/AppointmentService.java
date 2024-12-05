package org.example.services;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
import org.example.entities.Pet;
import org.example.exeptions.AppointmentNotFoundException;
import org.example.mappers.AppointmentMapper;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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

    public Appointment findById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        return optionalAppointment.get();
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public void deleteById(Long id) {
        Optional<Appointment> appointmentToDelete = appointmentRepository.findById(id);
        if (appointmentToDelete.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id)
                .orElseThrow(()-> new AppointmentNotFoundException("Appointment not found"));
        Optional<Pet> pet = Optional.ofNullable(appointmentToUpdate.getPet());
        if (appointmentRequest.petId()!= pet.get().getId()){
            pet = petRepository.findById(appointmentRequest.petId());
        }

        appointmentToUpdate.setDate(appointmentRequest.date());
        appointmentToUpdate.setTime(appointmentRequest.time());
        appointmentToUpdate.setReason(appointmentRequest.reason());
        appointmentToUpdate.setPet(pet.get());
        appointmentRepository.save(appointmentToUpdate);

        return appointmentRepository.save(appointmentToUpdate);
    }
}
