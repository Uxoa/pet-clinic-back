package org.example.services;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
import org.example.repositories.AppointmentRepository;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    /*public Appointment createAppointment(AppointmentRequest appointmentRequest) {
    Appointment appointment =
        return AppointmentRepository.save(appointmentRequest);
    }*/
}
