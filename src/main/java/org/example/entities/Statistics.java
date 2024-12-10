package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Statistics {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private Long totalAppointments;
    
    @Column
    private Long totalPets;
    
    @Column
    private Long totalGuardians;
    
    
    public Statistics(Long id, Long totalAppointmets, Long totalPets, Long totalGuardians) {
        this.id = id;
        this.totalAppointments = totalAppointmets;
        this.totalPets = totalPets;
        this.totalGuardians = totalGuardians;
    }
    
    public Statistics() {
    }
    
 
}
