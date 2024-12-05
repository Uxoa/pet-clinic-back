package org.example.mappers;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
import org.example.entities.Pet;

public class AppointmentMapper {

    public static Appointment fromRequest(AppointmentRequest appointmentRequest, Pet pet){
        return new Appointment(appointmentRequest.date(), appointmentRequest.time(), appointmentRequest.reason(), pet);
    }

}
