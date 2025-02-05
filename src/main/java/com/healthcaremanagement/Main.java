package com.healthcaremanagement;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import com.healthcaremanagement.service.PatientService;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Scanner scanner = new Scanner(System.in);

        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);

        AppointmentRepositoryImpl appointmentRespository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRespository);

        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);
        OfficeService officeService = new OfficeService(officeRepository);


        int makeChoice = 1;
         while (makeChoice == 1){
             System.out.println("Main Menu:");
             System.out.println("1.Manage Patients");
             System.out.println("2.Manage Doctors");
             System.out.println("3.Manage Appointments");
             System.out.println("4.Manage Offices");
             System.out.println("5.Exit");
             int choice = scanner.nextInt();
             scanner.nextLine();
             switch (choice) {
                 case 1:
                     managePatients(patientService, scanner);
                     break;
                 case 2:
                     manageDoctors(doctorService, scanner);
                     break;
                 case 3:
                     manageAppointments(appointmentService, patientService, doctorService, scanner);
                     break;
                 case 4:
                     manageOffices(officeService, doctorService, scanner);
                     break;
                 case 5:
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Invalid choice");
             }
             System.out.println("Press 1 for Main Menu. Press 0 to Exit");
             makeChoice = scanner.nextInt();
             scanner.nextLine();
             }
            sessionFactory.close();
            scanner.close();
            System.exit(0);
         }


    //PATIENT CONTROLLER
    public static void managePatients( PatientService patientService, Scanner scanner) {


        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    //CREATE PATIENT
                    // Application calls the service layer, not the repository directly
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientService.createPatient(newPatient);  // Use service here
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    //GET PATIENT BY ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                        //System.out.println("Doctors: " + patient.getDoctors());
                        //System.out.println("Appointments: " + patient.getAppointments());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    //UPDATE PATIENT
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(patient);  // Use service here
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    //DELETE PATIENT
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    patientService.deletePatient(patientId);  // Use service here
                    System.out.println("Patient deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
        }
    }

    //DOCTOR CONTROLLER
    public static void manageDoctors( DoctorService doctorService, Scanner scanner) {


        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    //CREATE DOCTOR
                    // Application calls the service layer, not the repository directly
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Specialty: ");
                    newDoctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());
                    doctorService.createDoctor(newDoctor);  // Use service here
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:
                    //READ DOCTOR
                    // Application calls the service layer, not the repository directly
                      System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    Doctor doctor = doctorService.getDoctorById(doctorId);  // Use service here
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Specialty: " + doctor.getSpecialty());
                        System.out.println("Email: " + doctor.getEmail());
                        //System.out.println("Patients: " + doctor.getPatients());
                        //System.out.println("Appointments: " + doctor.getAppointments());
                    } else {

                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3:
                    //UPDATE DOCTOR
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    doctor = doctorService.getDoctorById(doctorId);  // Use service here
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Specialty: ");
                        doctor.setSpecialty(scanner.nextLine());

                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());
                        doctorService.updateDoctor(doctor);  // Use service here
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    //DELETE DOCTOR
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    doctorService.deleteDoctor(doctorId);  // Use service here
                    System.out.println("Doctor deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
        }
    }

//APPOINTMENT CONTROLLER

    private static void manageAppointments(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService, Scanner scanner) {
        System.out.println("\nManage Appointments");
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. List All Appointments");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                //CREATE APPOINTMENT
                System.out.print("Enter patient ID: ");
                int patientId = scanner.nextInt();
                scanner.nextLine();
                Patient patient = patientService.getPatientById(patientId);

                System.out.print("Enter doctor ID: ");
                int doctorId = scanner.nextInt();
                scanner.nextLine();
                Doctor doctor = doctorService.getDoctorById(doctorId);

                if (patient == null || doctor == null) {
                    System.out.println("Patient or Doctor not found.");
                    break;
                }

                // Establish relationship using service methods
                doctorService.addPatientToDoctor(doctorId, patient);
                patientService.addDoctorToPatient(patientId, doctor);

                Appointment newAppointment = new Appointment();
                newAppointment.setPatient(patient);
                newAppointment.setDoctor(doctor);

                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                newAppointment.setAppointmentDate(scanner.nextLine());
                System.out.print("Enter notes: ");
                newAppointment.setNotes(scanner.nextLine());

                appointmentService.createAppointment(newAppointment);
                System.out.println("Appointment created successfully.");
                break;

            case 2:
                //GET APPOINTMENT BY ID
                System.out.print("Enter Appointment ID: ");
                int appointmentId = scanner.nextInt();
                scanner.nextLine();
                Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.println("Appointment ID: " + appointment.getAppointmentId());
                    System.out.println("Patient ID: " + appointment.getPatient().getPatientId());
                    System.out.println("Doctor ID: " + appointment.getDoctor().getDoctorId());
                    System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                    System.out.println("Notes: " + appointment.getNotes());


                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 3:
                //UPDATE APPOINTMENT

                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                scanner.nextLine();
                appointment = appointmentService.getAppointmentById(appointmentId);
                if (appointment != null) {
                    // Store original relationships
                    Doctor originalDoctor = appointment.getDoctor();
                    Patient originalPatient = appointment.getPatient();

                    System.out.print("Enter new patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();
                    Patient updatedPatient = patientService.getPatientById(patientId);

                    System.out.print("Enter new doctor ID: ");
                    doctorId = scanner.nextInt();
                    scanner.nextLine();
                    Doctor updatedDoctor = doctorService.getDoctorById(doctorId);

                    if (updatedPatient == null || updatedDoctor == null) {
                        System.out.println("Patient or Doctor not found.");
                        break;
                    }

                    // Update relationships
                    doctorService.addPatientToDoctor(doctorId, updatedPatient);
                    patientService.addDoctorToPatient(patientId, updatedDoctor);

                    // Remove old relationships if no other appointments exist
                    if (!appointmentService.hasOtherAppointmentsBetween(
                            originalDoctor.getDoctorId(),
                            originalPatient.getPatientId()
                    )) {
                        doctorService.removePatientFromDoctor(
                                originalDoctor.getDoctorId(),
                                originalPatient
                        );
                        patientService.removeDoctorFromPatient(
                                originalPatient.getPatientId(),
                                originalDoctor
                        );
                    }

                    // Update appointment details
                    appointment.setPatient(updatedPatient);
                    appointment.setDoctor(updatedDoctor);
                    System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                    appointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());

                    appointmentService.updateAppointment(appointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 4:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                scanner.nextLine();
                appointment = appointmentService.getAppointmentById(appointmentId);
                if (appointment != null) {
                    Doctor doctorToCheck = appointment.getDoctor();
                    Patient patientToCheck = appointment.getPatient();

                    // Delete the appointment first
                    appointmentService.deleteAppointment(appointmentId);

                    // Check if relationship should be removed
                    if (!appointmentService.hasOtherAppointmentsBetween(doctorToCheck.getDoctorId(), patientToCheck.getPatientId())) {
                        doctorService.removePatientFromDoctor(
                                doctorToCheck.getDoctorId(),
                                patientToCheck
                        );
                        patientService.removeDoctorFromPatient(patientToCheck.getPatientId(), doctorToCheck);
                    }
                    System.out.println("Appointment deleted successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 5:
                System.out.println("Listing All Appointments:");
                for (Appointment a : appointmentService.getAllAppointments()) {
                    System.out.println(a);
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    //OFFICE CONTROLLER

    public static void manageOffices(OfficeService officeService,DoctorService doctorService, Scanner scanner) {


        System.out.println("1. Create Office");
        System.out.println("2. Read Office");
        System.out.println("3. Update Office");
        System.out.println("4. Delete Office");
        System.out.println("5. List All Offices");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Office newOffice = new Office();
                    System.out.print("Enter Office Location: ");
                    newOffice.setLocation(scanner.nextLine());
                    System.out.print("Enter Office Phone Number: ");
                    newOffice.setPhone(scanner.nextLine());

                    System.out.print("Enter Doctor ID: ");
                    Doctor doctor = new Doctor();
                    doctor.setDoctorId(scanner.nextInt());
                    newOffice.setDoctor(doctor);
                    officeService.createOffice(newOffice);
                    System.out.println("Office created successfully.");
                    break;

                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    int officeId = scanner.nextInt();
                    Office office = officeService.getOfficeById(officeId);  // Use service here
                    if (office != null) {
                        System.out.println("Office Id: " + office.getOfficeId());
                        System.out.println("Office Location " + office.getLocation());
                        System.out.println("Office Phone No: " + office.getPhone());
                        System.out.println("Doctor : " + office.getDoctor());
                    } else {
                        System.out.println("Office not found.");
                    }
                    break;
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    officeId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    office = officeService.getOfficeById(officeId);  // Use service here

                    if (office != null) {
                        System.out.print("Enter new location: ");
                        office.setLocation(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        office.setPhone(scanner.nextLine());

                        System.out.print("Enter new Doctor ID(or 0 to keep current): ");
                        int newDoctorId = scanner.nextInt();
                        scanner.nextLine();  // consume newline

                        if (newDoctorId != 0) {
                            Doctor newDoctor = doctorService.getDoctorById(newDoctorId);
                            if (newDoctor != null) {
                                office.setDoctor(newDoctor);
                            } else {
                                System.out.println("Doctor not found. Keeping the existing doctor.");
                            }
                        }
                        officeService.updateOffice(office);
                        System.out.println("Office updated successfully.");
                        } else {
                        System.out.println("Office not found.");
                        }

                        break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    officeId = scanner.nextInt();
                    officeService.deleteOffice(officeId);  // Use service here
                    System.out.println("Office deleted successfully.");
                    break;
                case 5:
                    System.out.println("Listing All Offices:");
                    for (Office off : officeService.getAllOffices()) {
                        System.out.println(off);
                    }
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
        }
    }



}





