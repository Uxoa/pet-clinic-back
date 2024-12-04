package org.example.controllers;

import org.example.dtos.AppointmentRequest;
import org.example.entities.Appointment;
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

    @GetMapping
    public List<Appointment> showAllAppointments() {
        return this.appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            return new ResponseEntity<>(optionalAppointment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        this.appointmentRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment Appointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Appointment appointmentToUpdate = optionalAppointment.get();
        appointmentToUpdate.setDate(Appointment.getDate());
        appointmentToUpdate.setTime(Appointment.getTime());
        appointmentToUpdate.setReason(Appointment.getReason());
        appointmentRepository.save(appointmentToUpdate);

        return new ResponseEntity<>(appointmentToUpdate, HttpStatus.OK);
    }

}
