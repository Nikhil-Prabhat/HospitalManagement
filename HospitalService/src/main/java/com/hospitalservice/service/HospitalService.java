package com.hospitalservice.service;

import java.util.List;
import java.util.UUID;

import com.hospitalservice.dto.AppointmentDTO;
import com.hospitalservice.dto.BillDTO;
import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.dto.TreatmentHistoryDTO;
import com.hospitalservice.entity.Appointment;
import com.hospitalservice.entity.Bill;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.entity.TreatmentHistory;

public interface HospitalService {

	public void saveDoctor(DoctorDTO doctorDTO);

	public Doctor getDoctorById(UUID idOfDoctor);
	
	public List<Doctor> getAllDoctors();

	public void updateDoctor(UUID idOfDoctor, DoctorDTO doctorDTO);

	public void deleteDoctor(UUID idOfDoctor);

	public void savePatient(PatientDTO patientDTO);

	public Patient getPatientById(UUID idOfPatient);
	
	public List<Patient> getAllPatients();

	public void updatePatient(UUID idOfPatient, PatientDTO patientDTO);

	public void deletePatient(UUID idOfPatient);

	public void assignPatientToDoctor(UUID idOfDoctor, UUID idOfPatient);

	public void saveAppointment(AppointmentDTO appointmentDTO);

	public Appointment getAppointmentById(UUID idOfAppointment);
	
	public List<Appointment> getAllAppointments();

	public void updateAppointmentWithStatus(UUID idOfAppointment, String status);

	public void deleteAppointment(UUID idOfAppointment);

	public void saveTreatmentHistory(TreatmentHistoryDTO treatmentHistoryDTO);
	
	public List<TreatmentHistory> getAllTreatmentHistories();

	public TreatmentHistory getTreatmentHistoryById(UUID idOfTreatmentHistory);

	public void updateTreatmentInTreatmentHistory(UUID idOfTreatmentHistory, String treatment);

	public void deleteTreatment(UUID idOfTreatmentHistory);

	public void saveBill(BillDTO billDTO);

	public Bill getBillById(UUID idOfBill);
	
	public List<Bill> getAllBills();

	public void updateBill(UUID idOfBill, BillDTO billDTO);

	public void deleteBill(UUID idOfBill);
	
	public double getTotalBillAmountOfHospital();

}
