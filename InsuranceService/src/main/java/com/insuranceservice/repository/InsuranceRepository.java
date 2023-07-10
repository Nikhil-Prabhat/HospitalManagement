package com.insuranceservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceservice.entity.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {

}
