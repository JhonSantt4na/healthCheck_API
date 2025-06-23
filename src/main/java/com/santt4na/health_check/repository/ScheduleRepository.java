package com.santt4na.health_check.repository;

import com.santt4na.health_check.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	@Query("SELECT s FROM Schedule s JOIN FETCH s.doctor")
	List<Schedule> findAllWithDoctor();
	
	@Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.doctor WHERE s.id = :id")
	Optional<Schedule> findByIdWithDoctor(@Param("id") Long id);
	
	List<Schedule> findByDoctorIdAndAvailableTrue(Long doctorId);
}
