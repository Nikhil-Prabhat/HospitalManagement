package com.insuranceservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceservice.entity.PatientClaim;

@Repository
public interface PatientClaimRepository extends JpaRepository<PatientClaim, UUID>{

}
