package com.healthcaremanagement.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.util.HashSet;
@Data
@ToString(exclude = "doctors")
@Getter
@Setter
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private int patientId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(mappedBy = "patients", cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Patient that = (Patient) o;
        return patientId == that.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }





}

