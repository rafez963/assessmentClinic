package com.assessment.assessment.controller;

import com.assessment.assessment.model.Availability;
import com.assessment.assessment.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    /**
     * crear una nueva disponibilidad para un medico
     * @param availability datos de la disponibilidad
     * @return la disponibilidad creada
     */
    @PostMapping
    public Availability createAvailability(@RequestBody Availability availability){
        return availabilityService.save(availability);
    }

    /**
     * obtiene la disponibilidad de un medico
     * @param doctorId id del doctor
     * @return lista de la disponibilidad del medico
     */
    @GetMapping("/doctor/{doctorId}")
    public List<Availability> getAvailabilitiesByDoctor(@PathVariable Long doctorId){
        return availabilityService.findByDoctorId(doctorId);
    }

}

