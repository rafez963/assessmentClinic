package com.assessment.assessment.service;

import com.assessment.assessment.model.Availability;
import com.assessment.assessment.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * Guerdar una nueva diponibilidad
     * @param availability  la disponibilidad para guardar
     * @return la disponibilidad para guardar
     */
    public Availability save(Availability availability){
        return availabilityRepository.save(availability);
    }

    /**
     * la disponibilidad de un medico
     * @param doctorId id del medico disponible
     * @return la lista disponible de medicos
     */
    public List<Availability> findByDoctorId(Long doctorId){
        return availabilityRepository.findByDoctorId(doctorId);
    }

    /**
     * Verifica si un medico tiene disponibilidad horaria
     * @param doctorId id del medico disponible
     * @param startTime hora de inicio de la cita medica
     * @param endTime hora de finalizacion de la cita medica
     */
    public boolean isAvailability(Long doctorId, LocalDateTime startTime, LocalDateTime endTime){
        List<Availability> availabilities = findByDoctorId(doctorId);
        return availabilities.stream().anyMatch(avail ->
                (startTime.isAfter(avail.getStartTime()) || startTime.isEqual(avail.getStartTime())) &&
                        (endTime.isBefore(avail.getEndTime()) || endTime.isEqual(avail.getEndTime()))
        );
    }
}
