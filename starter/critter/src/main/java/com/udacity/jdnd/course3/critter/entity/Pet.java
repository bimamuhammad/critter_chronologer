package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(
        name = "get.PetById",
        query = "SELECT p from Pet p WHERE p.id = :petid"
)

@NamedQuery(
        name = "get.ByOwerId",
        query = "SELECT c FROM Pet c WHERE owner_id = :owner_id"
)
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private long id;
    @Nationalized
    private PetType type;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Customer customer;
    private LocalDate birthDate;
    private String notes;

    public Pet(){}

    public Pet(long id, PetType type, String name, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
