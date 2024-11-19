package com.assessment.assessment.repository;

import com.assessment.assessment.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorId(Long doctorId);

}
