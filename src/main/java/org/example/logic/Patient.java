package org.example.logic;

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
    @Column(name="species")
    private String species;
    @Column(name="race")
    private String race;
    @Column(name="age")
    private int age;

    public Patient(String name, String species, String race, int age) {
        this.name = name;
        this.species = species;
        this.race = race;
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
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
