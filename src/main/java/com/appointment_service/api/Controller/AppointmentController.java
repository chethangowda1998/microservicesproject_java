package com.appointment_service.api.Controller;


import com.appointment_service.api.Entity.Appointment;
import com.appointment_service.api.Payload.Doctor;
import com.appointment_service.api.Payload.Patient;
import com.appointment_service.api.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentController(RestTemplate restTemplate, AppointmentService appointmentService) {
        this.restTemplate = restTemplate;
        this.appointmentService = appointmentService;
    }
    // @PostMapping("/create")
    // ResponseEntity<Appointment> createappointment(@RequestBody Appointment appointment) {
    //     ResponseEntity<Patient> patientresponse = restTemplate.getForEntity
    //             ("http://localhost:8080/patients/" + appointment.getPatientId(),
    //                     Patient.class);


    //     if (patientresponse.getStatusCode() != HttpStatus.OK || patientresponse.getBody() == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }
    //     patientresponse.getBody();
    //     appointmentService.addAppointment(appointment);
       
    @PostMapping("/")
    ResponseEntity<Appointment> createapoointment(@RequestBody Appointment appointment){
        ResponseEntity<Patient> patientresponse = restTemplate.getForEntity(
            "http://localhost:8080/patients/" + appointment.getPatientId(),Patient.class
        );

        if(patientresponse.getStatusCode()!=HttpStatus.OK || patientresponse.getBody() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       patientresponse.getBody();
       appointmentService.addAppointment(appointment);
        ResponseEntity<Doctor> doctorResponse = restTemplate.getForEntity(
                "http://localhost:8081/doctors/" + appointment.getDoctorId(), Doctor.class);
        if (doctorResponse.getStatusCode() != HttpStatus.OK || doctorResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        doctorResponse.getBody();
        Appointment appointment1 = appointmentService.addAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment1);
    }
}