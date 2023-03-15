package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employee;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pet;
    private LocalDate date;

}
