package com.layermark.interviewtask.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.layermark.interviewtask.patient.Patient;
import com.layermark.interviewtask.vaccine.Vaccine;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Doctor")
@Table(
        name = "doctor",
        uniqueConstraints = @UniqueConstraint(name = "doctor_email_unique", columnNames = "email")
)
public class Doctor {

    // properties
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Vaccine> vaccines = new HashSet<>();

    // constructors
    public Doctor() {} //default constructor

    public Doctor(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    } // constructor with all properties

    public Doctor(String name, String email) {
        this.name = name;
        this.email = email;
    } // constructor without id since it will be generated by the database

    // accessor methods
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Set<Patient> getPatients() { return patients; }

    public void assignPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }


    public void assignVaccine(Vaccine vaccine) {
        if (!vaccines.contains(vaccine)) {
            vaccines.add(vaccine);
        }
    }

    //overridden toString method
    @Override
    public String toString() {
        return "Student {" + "id = "+ id + ", name = \"" + name + '"' + ", email = \"" + email + '"' + "}";
    }
}
