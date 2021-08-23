package com.example.clip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clip.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
