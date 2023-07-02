package com.hospitalservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospitalservice.entity.Bill;
import com.hospitalservice.util.Constants;

import jakarta.websocket.server.PathParam;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID>, Constants {
	
	@Query(name = "select bt.* from hosptial.bill_table bt\r\n"
			+ "inner join hospital.patient_table pt\r\n"
			+ "on bt.patient_id_fk = pt.id\r\n"
			+ "where pt.id = :id", nativeQuery = true)
	public Bill getBillByPatientId(@PathParam(ID) UUID id);

}
