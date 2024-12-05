package org.example.controllers;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
import org.example.entities.Guardian;
import org.example.entities.Pet;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.PetRepository;
import org.example.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {
   private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest appointmentRequest){

        Appointment savedAppointment = appointmentService.createAppointment(appointmentRequest);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById (@PathVariable Long id){
        return appointmentService.findById(id);
    }

    @GetMapping
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        this.appointmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment  = appointmentService.updateAppointment(id, appointmentRequest);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
