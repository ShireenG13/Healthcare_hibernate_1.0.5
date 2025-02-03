package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;

import java.util.List;

public class AppointmentService {

    private final AppointmentRepositoryImpl appointmentRepository;

    public AppointmentService(AppointmentRepositoryImpl appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.updateAppointment(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteAppointment(id);
    }

    public Doctor getDoctor(int id){ return appointmentRepository.getDoctor(id);}

    public Patient getPatient(int id){ return appointmentRepository.getPatient(id);}

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        return appointmentRepository.hasOtherAppointmentsBetween(doctorId, patientId);
    }
}
