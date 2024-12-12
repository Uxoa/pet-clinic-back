package org.example.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment", nullable = false)
    private Long id;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    
    @Column(name = "reason")
    private String reason;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonIgnoreProperties(value = "appointments")
    private Pet pet;
    
    
    
    public Appointment(String date, String time, String reason, Pet pet) {
        
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.pet = pet;
    }
    
}