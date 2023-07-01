package com.hospitalservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospitalservice.entity.TreatmentHistory;
import com.hospitalservice.util.Constants;

import jakarta.websocket.server.PathParam;

@Repository
public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, UUID>, Constants {

	@Query(name = "select tht.* from hospital.treatment_history_table tht\r\n"
			+ "inner join hospital.doctor_table dt\r\n" + "on tht.doctor_id_fk = dt.id\r\n"
			+ "where dt.id = :id", nativeQuery = true)
	public List<TreatmentHistory> findAllTreatmentHistoriesByDoctorId(@PathParam(ID) UUID id);

}
