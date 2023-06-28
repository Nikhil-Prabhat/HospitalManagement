package com.hospitalservice.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalservice.dto.AppointmentDTO;
import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.entity.Appointment;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.repository.AppointmentRepository;
import com.hospitalservice.repository.DoctorRepository;
import com.hospitalservice.repository.PatientRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

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

}
