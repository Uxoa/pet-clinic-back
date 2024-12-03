package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pet", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "specie")
    private String specie;

    @Column(name = "breed")
    private String breed = "unknown";

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "pets")
    private List<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    @JsonIgnoreProperties(value = "pets")
    private Guardian guardian;

    public Pet(String name, String specie,String breed, int age, Guardian guardian) {
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.breed = breed;
        this.guardian = guardian;
    }
}
