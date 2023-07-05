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
import com.hospitalservice.exception.RecordNotFound;

public interface HospitalService {

	public void saveDoctor(DoctorDTO doctorDTO);

	public Doctor getDoctorById(UUID idOfDoctor) throws RecordNotFound;
	
	public List<Doctor> getAllDoctors();

	public void updateDoctor(UUID idOfDoctor, DoctorDTO doctorDTO) throws RecordNotFound;

	public void deleteDoctor(UUID idOfDoctor) throws RecordNotFound;
	
	public List<Patient> getAllPatientsForADoctor(UUID idOfDoctor) throws RecordNotFound;
	
	public List<TreatmentHistory> getAllTreatmentHistoryForADoctor(UUID idOfDoctor) throws RecordNotFound;
	
	public List<Appointment> getAllAppointmentsForADoctor(String doctorAssignedName);

	public void savePatient(PatientDTO patientDTO);

	public Patient getPatientById(UUID idOfPatient) throws RecordNotFound;
	
	public List<Patient> getAllPatients();
	
	public List<TreatmentHistory> getAllTreatmentHistoriesByPatientId(UUID idOfPatient) throws RecordNotFound;
	
	public List<Appointment> getAllAppointmentsForAPatient(String patientName);
	
	public List<Doctor> getAllDoctorsForAPatient(UUID idOfPatient) throws RecordNotFound;
	
	public Bill getBillByPatientId(UUID idOfPatient) throws RecordNotFound;

	public void updatePatient(UUID idOfPatient, PatientDTO patientDTO) throws RecordNotFound;

	public void deletePatient(UUID idOfPatient) throws RecordNotFound;

	public void assignPatientToDoctor(UUID idOfDoctor, UUID idOfPatient) throws RecordNotFound;

	public void saveAppointment(AppointmentDTO appointmentDTO);

	public Appointment getAppointmentById(UUID idOfAppointment) throws RecordNotFound;
	
	public List<Appointment> getAllAppointments();

	public void updateAppointmentWithStatus(UUID idOfAppointment, String status) throws RecordNotFound;

	public void deleteAppointment(UUID idOfAppointment) throws RecordNotFound;

	public void saveTreatmentHistory(TreatmentHistoryDTO treatmentHistoryDTO);
	
	public List<TreatmentHistory> getAllTreatmentHistories();

	public TreatmentHistory getTreatmentHistoryById(UUID idOfTreatmentHistory) throws RecordNotFound;

	public void updateTreatmentInTreatmentHistory(UUID idOfTreatmentHistory, String treatment) throws RecordNotFound;

	public void deleteTreatment(UUID idOfTreatmentHistory) throws RecordNotFound;

	public void saveBill(BillDTO billDTO);

	public Bill getBillById(UUID idOfBill) throws RecordNotFound;
	
	public List<Bill> getAllBills();

	public void updateBill(UUID idOfBill, BillDTO billDTO) throws RecordNotFound;

	public void deleteBill(UUID idOfBill) throws RecordNotFound;
	
	public double getTotalBillAmountOfHospital();

}
