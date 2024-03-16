package com.appointment_service.api.Services;

// AppointmentService.java
import com.appointment_service.api.Entity.Appointment;
import com.appointment_service.api.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    private List<String> collect;
    

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
   public void Bookanappoiontment(Appointment appointment, LocalDateTime appointmentDateTime){
       List<String> availabletimeslots=new ArrayList<>();
       availabletimeslots.add("10:15 AM");
       availabletimeslots.add("11:15 AM");
       availabletimeslots.add("12:15 PM");
       availabletimeslots.add("02:15 PM");
     List<String> timeslots = availabletimeslots.stream().filter(slot->slot.equals(appointmentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a")))). collect(Collectors.toList());
        if(!timeslots.isEmpty()){
        String selectedTimeSlot =  timeslots.get(0);
        availabletimeslots.remove(selectedTimeSlot);
        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDateTime(appointmentDateTime);
        appointment2.setDoctorId(appointment.getDoctorId());
        appointment2.setPatientId(appointment.getPatientId());
        appointmentRepository.save(appointment2);
        System.out.println("Time slots is booked for time "+appointmentDateTime.format(DateTimeFormatter.ofPattern("hh:mm:a")));
        }else{
            System.out.println("slotes are not available");
        }
    }
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepository.findAll();}
   //  List<String> timeslots = availabletimeslots.stream().filter(slot->slot.equals(appointmentDateTime.format(DateTimeFormatter.ofPattern("hh:mm:aa")))).collect(Collectors.toList());
     
// if (!timeslots.isEmpty()) {
//     String Selectedtimeslots=timeslots.get(0);
//     availabletimeslots.remove(Selectedtimeslots);
//     Appointment appointment2 = new Appointment();
//     appointment2.setAppointmentDateTime(appointmentDateTime);
//     appointment2.setDoctorId(appointment.getDoctorId());
//     appointment2.setPatientId(appointment.getPatientId());
//     appointmentRepository.save(appointment2);
//     System.out.println("Slots is booked for your time:"+appointmentDateTime.format(DateTimeFormatter.ofPattern("hh:mm:aa")));
    
// } else {
//     System.out.println("slotes is not booked for your time its been alloted:"+appointmentDateTime.format(DateTimeFormatter.ofPattern("hh:mm:aa")));
// }
// }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

//    public void updateAppointment(Long id, Appointment updatedAppointment) {
//    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}

