package com.assessment.assessment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private Long id;
    private Long patientId;
    private String reason;
    private LocalDateTime dateTime;
    private String status;

}
