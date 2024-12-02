package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name="Guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_guardian", nullable = false)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name = "surname")
    private String surname;

    @NotNull
    /*@Email*/
    private String email;

    @Column(name="phone")
    private String phone;
    private String address;


    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "guardian")
    private List<Pet> pets;



    public List<Pet> getPet() {
        return pets;
    }
    
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Guardian(String name, String surname, String email, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Guardian() {
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
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
