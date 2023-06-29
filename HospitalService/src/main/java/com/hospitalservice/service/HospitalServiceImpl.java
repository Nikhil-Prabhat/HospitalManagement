package com.hospitalservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hospitalservice.repository.AppointmentRepository;
import com.hospitalservice.repository.BillRepository;
import com.hospitalservice.repository.DoctorRepository;
import com.hospitalservice.repository.PatientRepository;
import com.hospitalservice.repository.TreatmentHistoryRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

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
	public void assignPatientToDoctor(UUID idOfDoctor, UUID idOfPatient) {
		Doctor doctorById = getDoctorById(idOfDoctor);
		Patient patientById = getPatientById(idOfPatient);

		if (Objects.nonNull(doctorById) && Objects.nonNull(patientById)) {
			doctorById.getPatients().add(patientById);
			doctorRepository.save(doctorById);
		}

	}

	@Override
	public Doctor getDoctorById(UUID idOfDoctor) {
		return doctorRepository.findById(idOfDoctor).orElse(null);
	}

	@Override
	public Patient getPatientById(UUID idOfPatient) {
		return patientRepository.findById(idOfPatient).orElse(null);
	}

	@Override
	public void updateDoctor(UUID idOfDoctor, DoctorDTO doctorDTO) {
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
	public void updatePatient(UUID idOfPatient, PatientDTO patientDTO) {
		Patient patientById = getPatientById(idOfPatient);

		if (Objects.nonNull(patientById)) {
			patientById.setName(patientDTO.getName());
			patientById.setMobileNo(patientDTO.getMobileNo());
			patientById.setAddress(patientDTO.getAddress());
			patientById.setBriefProblemDescription(patientDTO.getBriefProblemDescription());
			patientRepository.save(patientById);
		}

	}

	@Override
	public void deleteDoctor(UUID idOfDoctor) {
		doctorRepository.deleteById(idOfDoctor);

	}

	@Override
	public void deletePatient(UUID idOfPatient) {
		patientRepository.deleteById(idOfPatient);

	}

	@Override
	public void saveAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		appointment.setPatientName(appointmentDTO.getPatientName());
		appointment.setPatientMobileNo(appointmentDTO.getPatientMobileNo());
		appointment.setDoctorAssigned(appointmentDTO.getDoctorAssigned());
		appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
		appointment.setAppointmentStatus(appointmentDTO.getAppointmentStatus());

		appointmentRepository.save(appointment);

	}

	@Override
	public Appointment getAppointmentById(UUID idOfAppointment) {
		return appointmentRepository.findById(idOfAppointment).orElse(null);
	}

	@Override
	public void updateAppointmentWithStatus(UUID idOfAppointment, String status) {
		Appointment appointmentById = getAppointmentById(idOfAppointment);

		if (Objects.nonNull(appointmentById)) {
			appointmentById.setAppointmentStatus(status);
			appointmentRepository.save(appointmentById);
		}

	}

	@Override
	public void deleteAppointment(UUID idOfAppointment) {
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
	public TreatmentHistory getTreatmentHistoryById(UUID idOfTreatmentHistory) {
		return treatmentHistoryRepository.findById(idOfTreatmentHistory).orElse(null);
	}

	@Override
	public void updateTreatmentInTreatmentHistory(UUID idOfTreatmentHistory, String treatment) {
		TreatmentHistory treatmentHistoryById = getTreatmentHistoryById(idOfTreatmentHistory);

		if (Objects.nonNull(treatmentHistoryById)) {
			treatmentHistoryById.setTreatment(treatment);

			treatmentHistoryRepository.save(treatmentHistoryById);
		}

	}

	@Override
	public void deleteTreatment(UUID idOfTreatmentHistory) {
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
	public Bill getBillById(UUID idOfBill) {
		return billRepository.findById(idOfBill).orElse(null);
	}

	@Override
	public void updateBill(UUID idOfBill, BillDTO billDTO) {
		Bill billById = getBillById(idOfBill);

		if (Objects.nonNull(billById)) {
			billById.setHospitalizationFee(billDTO.getHospitalizationFee());
			billById.setConsultationFee(billDTO.getConsultationFee());
			billById.setPharmacyFee(billDTO.getPharmacyFee());

			billRepository.save(billById);
		}

	}

	@Override
	public void deleteBill(UUID idOfBill) {
		billRepository.deleteById(idOfBill);

	}

	@Override
	public double getTotalBillAmountOfHospital() {
		List<Bill> listOfBillsOfHospital = billRepository.findAll();
		Double totalBillOfHospital = listOfBillsOfHospital.stream()
				.filter(bill -> Objects.nonNull(bill))
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

}
