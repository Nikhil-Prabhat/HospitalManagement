package com.hospitalservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitalservice.entity.TreatmentHistory;

@Repository
public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, UUID> {

}
