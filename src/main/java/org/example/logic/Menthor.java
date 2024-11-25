package org.example.logic;

import jakarta.persistence.*;

@Entity
@Table(name="Menthor")
public class Menthor{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_menthor", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="phone")
    private int phone;

    public Menthor(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }


}
