package com.healthcaremanagement.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


@Entity
@Table(name ="Offices")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "doctor")

public class Office {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="OfficeID")
    private int officeId;

    @Column
    private String location;

    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "DoctorID", nullable = true)
    private Doctor doctor;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Office that = (Office) o;
        return officeId == that.officeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId);
    }

@Override
public String toString() {
    return "Office{" +
            "officeId=" + officeId +
            ", location='" + location + '\'' +
            ", phone='" + phone + '\'' +
            ", doctor=" + (doctor != null ? doctor.getDoctorId() : "N/A") +
            '}';
}


}
