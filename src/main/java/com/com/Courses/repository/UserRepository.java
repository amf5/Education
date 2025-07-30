package com.com.Courses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.com.Courses.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u.token FROM User u WHERE u.email = :email")
	String findTokenByEmail(@Param("email") String email);
}
