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
    private String age;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnoreProperties(value = "patients")
    private Mentor mentor;

    public Patient(String name, String specie, String race, String age) {
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

    public String getAge() {
        return age;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setName(String name) {
        if (name == null){
            return;
        }
        this.name = name;
    }

    public void setSpecie(String specie) {
        if (specie == null){
            return;
        }
        this.specie = specie;
    }

    public void setAge(String age) {
        if (age == null){
            return;
        }
        this.age = age;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Patient() {
    }
}
