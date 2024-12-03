package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "specie")
    private String specie;
    @Column(name = "race")
    private String race = "unknown";
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "pet")
    private List<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    @JsonIgnoreProperties(value = "pets")
    private Guardian guardian;

    public List<Appointment> getAppointment() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Pet(String name, String specie, String race, int age) {
        this.name = name;
        this.specie = specie;
        this.race = race;
        this.age = age;

    }

    public Guardian getGuardian() {
        return guardian;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setName(String name) {
        if (name == null) {
            return;
        }
        this.name = name;
    }

    public void setSpecie(String specie) {
        if (specie == null) {
            return;
        }
        this.specie = specie;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGuardian(Guardian guardian) {
        if (guardian == null) {
            return;
        }
        this.guardian = guardian;
    }

    public Pet() {
    }
}
