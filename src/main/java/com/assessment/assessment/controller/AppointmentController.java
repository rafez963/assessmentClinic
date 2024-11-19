package com.assessment.assessment.controller;

import com.assessment.assessment.model.Appointment;
import com.assessment.assessment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * crear una nueva cita medica despues de validar la disponibilidad
     * @param appointment datos de la cita
     * @return la cita creada exitosamente
     */
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.save(appointment);
    }

    /**
     * obtiene las citas que tiene agregada un paciente
     * @param patientId Id del paciente
     * @return Lista de las citas medicas del paciente
     */
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointment(@PathVariable Long patientId){
        return appointmentService.findByPatientId(patientId);
    }

    /**
     * obtiene las citas medicas que tiene agregado un doctor
     * @param doctorId id del doctor
     * @return listade citas del medico
     */
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentByDoctor(@PathVariable Long doctorId){
        return appointmentService.findByDoctorId(doctorId);
    }
}
