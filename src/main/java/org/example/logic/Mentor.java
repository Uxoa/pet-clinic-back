package org.example.logic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mentor", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name="phone")
    private int phone;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "mentor")
    private List<Patient> patients;
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
    
    public Mentor(int id, String name, String surname, int phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public Mentor() {
    }
}
