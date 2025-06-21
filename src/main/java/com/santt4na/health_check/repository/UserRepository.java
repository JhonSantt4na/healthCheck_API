package com.santt4na.health_check.repository;

import com.santt4na.health_check.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User findByUsername(@Param("userName") String userName);
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	Boolean existsByUserName(@Param("userName") String userName);
}