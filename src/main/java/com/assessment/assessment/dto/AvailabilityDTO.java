package com.assessment.assessment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvailabilityDTO {
    private Long id;
    private Long doctorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
