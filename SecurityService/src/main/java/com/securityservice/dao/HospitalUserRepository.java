package com.securityservice.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securityservice.entity.HospitalUser;

@Repository
public interface HospitalUserRepository extends JpaRepository<HospitalUser, UUID> {
	
	public HospitalUser findByUsername(String username);
}
