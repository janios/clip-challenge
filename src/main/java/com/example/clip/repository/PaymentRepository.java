package com.example.clip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.clip.model.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT DISTINCT userId FROM Payment")
	List<Long> findDistinctUserId();
	
	List<Payment> findByStatus(String status);
	
	List<Payment> findByUserId(Long id);
	
}
