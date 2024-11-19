package com.assessment.assessment.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User doctor;

    private LocalDateTime dateTime;
    private String reason;
    private String status; // "SCHEDULED", "CANCELLED"



}