package com.appointment_service.api.Repository;

import com.appointment_service.api.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
