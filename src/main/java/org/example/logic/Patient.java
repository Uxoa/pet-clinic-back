package org.example.logic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="specie")
    private String specie;
    @Column(name="race")
    private String race = "unknown";
    @Column(name="age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnoreProperties(value = "patients")
    private Mentor mentor;

    public Patient(String name, String specie, String race, int age) {
        this.name = name;
        this.specie = specie;
        this.race = race;
        this.age = age;

    }

    public Mentor getMentor() {
        return mentor;
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

    public Patient() {
    }
}
