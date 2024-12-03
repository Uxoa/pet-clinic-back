package org.example.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guardian", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "guardian")
    private List<Pet> pets;


    public Guardian(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


}
