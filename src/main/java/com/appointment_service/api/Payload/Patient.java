package com.appointment_service.api.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Patient {

    private Long id;
    private String name;
    private String email;

}
