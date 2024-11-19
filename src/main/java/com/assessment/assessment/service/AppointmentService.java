package com.assessment.assessment.service;

import com.assessment.assessment.model.Appointment;
import com.assessment.assessment.model.Availability;
import com.assessment.assessment.repository.AppointmentRepository;
import com.assessment.assessment.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * cracion de una nueva cita despues de validar la disponibiliad y conflicto de horario
     * @param appointment la cita que se pretende guardar
     * @return la cita ya guardada
     */
    public Appointment save(Appointment appointment){
       //cerificar si ya existe una cita en el mismo horario para el medico
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorId(appointment.getDoctor().getId());
        for(Appointment existing : existingAppointments){
            if(existing.getDateTime().isEqual(appointment.getDateTime())){
                throw new IllegalArgumentException("The doctor is already booked for this time slot.");
            }
        }

        //verificar diponibilidad del medico
        List<Availability> availabilities = availabilityRepository.findByDoctorId(appointment.getDoctor().getId());
        boolean isAvailable = availabilities.stream().anyMatch(availability ->
                        (appointment.getDateTime().isAfter(availability.getStartTime()) ||
                                appointment.getDateTime().isEqual(availability.getStartTime())) &&
                                (appointment.getDateTime().plusMinutes(30).isBefore(availability.getEndTime()) ||
                                        appointment.getDateTime().plusMinutes(30).isEqual(availability.getEndTime()))
                );

        if(!isAvailable){
            throw new IllegalArgumentException("The doctor is not available for the selected time slot");
        }
        //guardar la cita
        return appointmentRepository.save(appointment);
    }

    /**
     *
     * @param patientId id del paciente
     * @return lista de citas
     */
    public List<Appointment> findByPatientId(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }

    /**
     *
     * @param doctorId id del medico
     * @return lista de citas
     */
    public List<Appointment> findByDoctorId(Long doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
