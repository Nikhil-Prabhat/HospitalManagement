package com.hospitalservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalservice.dto.AppointmentDTO;
import com.hospitalservice.dto.BillDTO;
import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.dto.TreatmentHistoryDTO;
import com.hospitalservice.entity.Appointment;
import com.hospitalservice.entity.AppointmentStatus;
import com.hospitalservice.entity.Bill;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.entity.TreatmentHistory;
import com.hospitalservice.exception.RecordNotFound;
import com.hospitalservice.repository.AppointmentRepository;
import com.hospitalservice.repository.BillRepository;
import com.hospitalservice.repository.DoctorRepository;
import com.hospitalservice.repository.PatientRepository;
import com.hospitalservice.repository.TreatmentHistoryRepository;
import com.hospitalservice.util.Constants;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService, Constants {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private TreatmentHistoryRepository treatmentHistoryRepository;

	@Autowired
	private BillRepository billRepository;

	@Override
	public void saveDoctor(DoctorDTO doctorDTO) {

		Doctor doctor = new Doctor();
		doctor.setName(doctorDTO.getName());
		doctor.setSpecialisation(doctorDTO.getSpecialisation());
		doctor.setMobileNo(doctorDTO.getMobileNo());
		doctor.setAddress(doctorDTO.getAddress());
		doctor.setPatients(new HashSet<>());
		doctorRepository.save(doctor);

	}

	@Override
	public void savePatient(PatientDTO patientDTO) {

		Patient patient = new Patient();
		patient.setName(patientDTO.getName());
		patient.setMobileNo(patientDTO.getMobileNo());
		patient.setAddress(patientDTO.getAddress());
		patient.setBriefProblemDescription(patientDTO.getBriefProblemDescription());
		patient.setDoctors(new HashSet<>());
		patient.setTreatmentStatus(patientDTO.getTreatmentStatus());
		patientRepository.save(patient);

	}

	@Override
	public void assignPatientToDoctor(UUID idOfDoctor, UUID idOfPatient) throws RecordNotFound {
		Doctor doctorById = getDoctorById(idOfDoctor);
		Patient patientById = getPatientById(idOfPatient);

		if (Objects.nonNull(doctorById) && Objects.nonNull(patientById)) {
			doctorById.getPatients().add(patientById);
			doctorRepository.save(doctorById);
		}

	}

	@Override
	public Doctor getDoctorById(UUID idOfDoctor) throws RecordNotFound {
		Doctor doctorById = doctorRepository.findById(idOfDoctor).orElse(null);
		
		if(Objects.nonNull(doctorById))
			return doctorById;
		else 
			throw new RecordNotFound(DOCTOR_NOT_FOUND_WITH_ID + idOfDoctor);
	}

	@Override
	public Patient getPatientById(UUID idOfPatient) throws RecordNotFound {
		Patient patientById = patientRepository.findById(idOfPatient).orElse(null); 
		
		 if(Objects.nonNull(patientById))
			 return patientById;
		 else
			 throw new RecordNotFound(PATIENT_NOT_FOUND_WITH_ID + idOfPatient);
					 
	}

	@Override
	public void updateDoctor(UUID idOfDoctor, DoctorDTO doctorDTO) throws RecordNotFound {
		Doctor doctorById = getDoctorById(idOfDoctor);
		
		if (Objects.nonNull(doctorById)) {
			doctorById.setName(doctorDTO.getName());
			doctorById.setMobileNo(doctorDTO.getMobileNo());
			doctorById.setAddress(doctorDTO.getAddress());
			doctorById.setSpecialisation(doctorDTO.getSpecialisation());
			doctorRepository.save(doctorById);
		}
	}

	@Override
	public void updatePatient(UUID idOfPatient, PatientDTO patientDTO) throws RecordNotFound {
		Patient patientById = getPatientById(idOfPatient);
		
		if (Objects.nonNull(patientById)) {
			patientById.setName(patientDTO.getName());
			patientById.setMobileNo(patientDTO.getMobileNo());
			patientById.setAddress(patientDTO.getAddress());
			patientById.setBriefProblemDescription(patientDTO.getBriefProblemDescription());
			patientById.setTreatmentStatus(patientDTO.getTreatmentStatus());
			patientRepository.save(patientById);
		}

	}

	@Override
	public void deleteDoctor(UUID idOfDoctor) throws RecordNotFound {
		Doctor doctorById = getDoctorById(idOfDoctor);
		
		if(Objects.nonNull(doctorById))
			doctorRepository.deleteById(idOfDoctor);

	}

	@Override
	public void deletePatient(UUID idOfPatient) throws RecordNotFound {
		Patient patientById = getPatientById(idOfPatient);
		
		if(Objects.nonNull(patientById))
			patientRepository.deleteById(idOfPatient);

	}

	@Override
	public void saveAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		appointment.setPatientName(appointmentDTO.getPatientName());
		appointment.setPatientMobileNo(appointmentDTO.getPatientMobileNo());
		appointment.setDoctorAssignedName(appointmentDTO.getDoctorAssignedName());
		appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
		appointment.setAppointmentStatus(appointmentDTO.getAppointmentStatus());

		appointmentRepository.save(appointment);

	}

	@Override
	public Appointment getAppointmentById(UUID idOfAppointment) throws RecordNotFound {
		 Appointment appointmentById = appointmentRepository.findById(idOfAppointment).orElse(null);
		 
		 if(Objects.nonNull(appointmentById))
			 return appointmentById;
		 else
			 throw new RecordNotFound(APPOINTMENT_NOT_FOUND_WITH_ID + idOfAppointment);
	}

	@Override
	public void updateAppointmentWithStatus(UUID idOfAppointment, String status) throws RecordNotFound {
		Appointment appointmentById = getAppointmentById(idOfAppointment);

		if (Objects.nonNull(appointmentById)) {
			AppointmentStatus appointmentStatus = status.equals(AppointmentStatus.CONFIRMED.getAppointmentStatus()) 
					? AppointmentStatus.CONFIRMED : AppointmentStatus.NOT_ACCEPTED;
			appointmentById.setAppointmentStatus(appointmentStatus);
			appointmentRepository.save(appointmentById);
		}

	}

	@Override
	public void deleteAppointment(UUID idOfAppointment) throws RecordNotFound {
		Appointment appointmentById = getAppointmentById(idOfAppointment);
		
		if(Objects.nonNull(appointmentById))
			appointmentRepository.deleteById(idOfAppointment);

	}

	@Override
	public void saveTreatmentHistory(TreatmentHistoryDTO treatmentHistoryDTO) {
		TreatmentHistory treatmentHistory = new TreatmentHistory();
		treatmentHistory.setPatient(treatmentHistoryDTO.getPatient());
		treatmentHistory.setDoctor(treatmentHistoryDTO.getDoctor()); 
		treatmentHistory.setSymptoms(treatmentHistoryDTO.getSymptoms());
		treatmentHistory.setBriefDescription(treatmentHistoryDTO.getBriefDescription());
		treatmentHistory.setTreatment(treatmentHistoryDTO.getTreatment());

		treatmentHistoryRepository.save(treatmentHistory);

	}

	@Override
	public TreatmentHistory getTreatmentHistoryById(UUID idOfTreatmentHistory) throws RecordNotFound {
		 TreatmentHistory treatmentHistoryById = treatmentHistoryRepository.findById(idOfTreatmentHistory).orElse(null);
		 
		 if(Objects.nonNull(treatmentHistoryById))
			 return treatmentHistoryById;
		 else
			 throw new RecordNotFound(TREATMENT_HISTORY_NOT_FOUND_WITH_ID + idOfTreatmentHistory);
	}

	@Override
	public void updateTreatmentInTreatmentHistory(UUID idOfTreatmentHistory, String treatment) throws RecordNotFound {
		TreatmentHistory treatmentHistoryById = getTreatmentHistoryById(idOfTreatmentHistory);

		if (Objects.nonNull(treatmentHistoryById)) {
			treatmentHistoryById.setTreatment(treatment);
			treatmentHistoryRepository.save(treatmentHistoryById);
		}

	}

	@Override
	public void deleteTreatment(UUID idOfTreatmentHistory) throws RecordNotFound {
		TreatmentHistory treatmentHistoryById = getTreatmentHistoryById(idOfTreatmentHistory);
		
		if(Objects.nonNull(treatmentHistoryById))
			treatmentHistoryRepository.deleteById(idOfTreatmentHistory);

	}

	@Override
	public void saveBill(BillDTO billDTO) {
		Bill bill = new Bill();
		bill.setPatient(billDTO.getPatient());
		bill.setPharmacyFee(billDTO.getPharmacyFee());
		bill.setHospitalizationFee(billDTO.getHospitalizationFee());
		bill.setConsultationFee(billDTO.getConsultationFee());

		billRepository.save(bill);

	}

	@Override
	public Bill getBillById(UUID idOfBill) throws RecordNotFound {
		 Bill billWithId = billRepository.findById(idOfBill).orElse(null);
		 
		 if(Objects.nonNull(billWithId))
			 return billWithId;
		 else
			 throw new RecordNotFound(BILL_NOT_FOUND_WITH_ID + idOfBill);
	}

	@Override
	public void updateBill(UUID idOfBill, BillDTO billDTO) throws RecordNotFound {
		Bill billById = getBillById(idOfBill);

		if (Objects.nonNull(billById)) {
			billById.setHospitalizationFee(billDTO.getHospitalizationFee());
			billById.setConsultationFee(billDTO.getConsultationFee());
			billById.setPharmacyFee(billDTO.getPharmacyFee());

			billRepository.save(billById);
		}

	}

	@Override
	public void deleteBill(UUID idOfBill) throws RecordNotFound {
		Bill billById = getBillById(idOfBill);
		
		if(Objects.nonNull(billById))
			billRepository.deleteById(idOfBill);

	}

	@Override
	public double getTotalBillAmountOfHospital() {
		List<Bill> listOfBillsOfHospital = billRepository.findAll();
		Double totalBillOfHospital = listOfBillsOfHospital.stream()
				.filter(bill -> Objects.nonNull(bill) && Objects.nonNull(bill.getTotalAmountOfBill()))
				.map(Bill::getTotalAmountOfBill)
				.reduce(0.0, Double::sum);
		return totalBillOfHospital;

	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@Override
	public List<TreatmentHistory> getAllTreatmentHistories() {
		return treatmentHistoryRepository.findAll();
		
	}

	@Override
	public List<Patient> getAllPatientsForADoctor(UUID idOfDoctor) throws RecordNotFound {
		List<Patient> listOfPatients = new ArrayList<>();
		Doctor doctorById = getDoctorById(idOfDoctor);
		doctorById.getPatients()
				.stream()
				.forEach(patient -> listOfPatients.add(patient));
		
		return listOfPatients;
	}

	@Override
	public List<TreatmentHistory> getAllTreatmentHistoryForADoctor(UUID idOfDoctor) throws RecordNotFound {
		Doctor doctorById = getDoctorById(idOfDoctor);
		
		if(Objects.nonNull(doctorById)) {
			List<TreatmentHistory> allTreatmentHistoriesByDoctorId = treatmentHistoryRepository.findAllTreatmentHistoriesByDoctorId(idOfDoctor);
			return allTreatmentHistoriesByDoctorId;
		}
		
		return null;
	}

	@Override
	public List<Appointment> getAllAppointmentsForADoctor(String doctorAssignedName) {
		List<Appointment> allAppointmentsByDoctorAssignedName = appointmentRepository.findByDoctorAssignedName(doctorAssignedName);
		return allAppointmentsByDoctorAssignedName;
	}

	@Override
	public List<TreatmentHistory> getAllTreatmentHistoriesByPatientId(UUID idOfPatient) throws RecordNotFound {
		Patient patientById = getPatientById(idOfPatient);
		
		if(Objects.nonNull(patientById)) {
			List<TreatmentHistory> allTreatmentHistoriesByPatientId = treatmentHistoryRepository.findAllTreatmentHistoriesByPatientId(idOfPatient);
			return allTreatmentHistoriesByPatientId;
		}
		
		return null;
	}

	@Override
	public Bill getBillByPatientId(UUID idOfPatient) throws RecordNotFound {
		Patient patientById = getPatientById(idOfPatient);
		
		if(Objects.nonNull(patientById)) {
			Bill billByPatientId = billRepository.getBillByPatientId(idOfPatient);
			
			if(Objects.nonNull(billByPatientId)) 
				return billByPatientId;
			else 
				throw new RecordNotFound(BILL_NOT_FOUND_WITH_PATIENT_ID + idOfPatient);
		}
		
		return null;
	}

	@Override
	public List<Appointment> getAllAppointmentsForAPatient(String patientName) {
		List<Appointment> appointmentsByPatientName = appointmentRepository.findByPatientName(patientName);
		return appointmentsByPatientName;
	}

	@Override
	public List<Doctor> getAllDoctorsForAPatient(UUID idOfPatient) throws RecordNotFound {
		List<Doctor> doctorslistForAPatient = new ArrayList<>();
		Patient patientById = getPatientById(idOfPatient);
		patientById.getDoctors()
		 			.stream()
		 			.forEach(doctor -> doctorslistForAPatient.add(doctor));
		return doctorslistForAPatient;
	}

}
