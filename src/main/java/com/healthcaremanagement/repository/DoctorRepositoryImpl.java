package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class DoctorRepositoryImpl {
    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(doctor);
            transaction.commit();
        }
    }

    public Doctor getDoctorById(int doctorId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, doctorId);
        }
    }

    public void updateDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(doctor);
            transaction.commit();
        }
    }

    public void deleteDoctor(int doctorId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null) {
                    if (doctor.getOffice() != null) {
                        doctor.getOffice().setDoctor(null);
                        session.merge(doctor.getOffice());
                    }
                    session.remove(doctor);
            }
            transaction.commit();
        }
    }

    public List<Doctor> getAllDoctors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }
    public void addPatientToDoctor(int doctorId, Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null && !doctor.getPatients().contains(patient)) {
                doctor.getPatients().add(patient);
                session.merge(doctor);
            }
            transaction.commit();
        }
    }

    public void removePatientFromDoctor(int doctorId, Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null && doctor.getPatients().contains(patient)) {
                doctor.getPatients().remove(patient);
                session.merge(doctor);
            }
            transaction.commit();
        }
    }

}
