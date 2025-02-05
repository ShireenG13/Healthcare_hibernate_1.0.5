package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Objects;

@Entity
@Table(name = "Appointments")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"patient", "doctor"})

public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "DoctorID")
    private Doctor doctor;

    @Column(name="AppointmentDate")
    private String appointmentDate;

    @Column(name="Notes")
    private String notes;


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" +  (patient != null ? patient.getPatientId() : "N/A") +
                ", doctorId=" + (doctor != null ? doctor.getDoctorId() : "N/A") +
                ", appointmentDate='" + appointmentDate + "' +" +
                " notes='" + notes  + '}';
    }

}
