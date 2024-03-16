package com.appointment_service.api.Payload;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class Doctor {

    private Long id;
    private String name;
    private String specialization;

}
