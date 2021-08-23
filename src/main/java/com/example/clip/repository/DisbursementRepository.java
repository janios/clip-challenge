package com.example.clip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clip.model.entities.Disbursement;

public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {
	
}
