package org.example.logic;

import jakarta.persistence.*;

@Entity
@Table(name="Mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mentor", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="phone")
    private int phone;

    public Mentor(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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
