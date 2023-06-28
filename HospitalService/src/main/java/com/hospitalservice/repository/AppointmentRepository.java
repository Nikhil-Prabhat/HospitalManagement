package com.hospitalservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitalservice.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

}
