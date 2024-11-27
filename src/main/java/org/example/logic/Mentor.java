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
    private String phone;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "mentor")
    private List<Patient> patients;
    
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
    
    public Mentor(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
    
    public Mentor() {
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

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        if(name==null){
            return;
        }
        this.name = name;

    }

    public void setSurname(String surname) {
        if (surname == null){
            return;
        }
        this.surname = surname;
    }

    public void setPhone(String phone) {
        if (phone == null){
            return;
        }

        this.phone = phone;
    }
}
