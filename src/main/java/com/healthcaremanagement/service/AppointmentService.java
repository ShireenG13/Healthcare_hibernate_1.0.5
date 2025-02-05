package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;

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

        Appointment appointment = appointmentRepository.getAppointmentById(id);
        appointmentRepository.deleteAppointment(id);

    }
        /*if(appointment != null) {

            /*Doctor doctor = doctorRepository.getDoctorById(id);
            Patient patient = patientRepository.getPatientById(id);
            appointmentRepository.deleteAppointment(id);
            boolean hasOtherAppointments = appointmentRepository.hasOtherAppointmentsBetween(doctor.getDoctorId(), patient.getPatientId());
            if(!hasOtherAppointments){
                doctor.getPatients().remove(patient);
                patient.getDoctors().remove(doctor);
            }
            doctorRepository.updateDoctor(doctor);
            patientRepository.updatePatient(patient);
        }*/
       /* public static void manageAppointments(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService)

        case 4:

        System.out.print(“Enter Appointment Id: “);
        appointmentId = scanner.nextInt();
        appointment = appointmentService.getAppointmentById(appointmentId);
        appointment = appointmentService.getAppointmentById(appointmentId);
        if (!appointmentService.hasOtherAppointmentsBetween(
                doctorToCheck.getDoctorId(),
                patientToCheck.getPatientId()
        )) {
            doctorService.removePatientFromDoctor(
                    doctorToCheck.getDoctorId(),
                    patientToCheck
            );
            patientService.removeDoctorFromPatient(
                    patientToCheck.getPatientId(),
                    doctorToCheck
            );
        }*/

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        return appointmentRepository.hasOtherAppointmentsBetween(doctorId, patientId);
    }
}
