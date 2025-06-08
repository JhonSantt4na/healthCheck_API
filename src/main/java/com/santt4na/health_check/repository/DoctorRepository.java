package com.santt4na.health_check.repository;

import com.santt4na.health_check.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Optional<Doctor> findByMedicalLicense(String medicalLicense);
	boolean existsByMedicalLicense(String medicalLicense);
}