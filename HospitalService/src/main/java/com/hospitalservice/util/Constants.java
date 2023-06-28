package com.hospitalservice.util;

public interface Constants {

	String VALIDATE = "/validate";
	String AUTHORIZATION = "Authorization";
	String ID = "id";
	String DOCTOR_ID = "doctorId";
	String PATIENT_ID = "patientId";
	String STATUS = "status";
	String HOSPITAL_APP = "/hospitalapp";
	String SAVE_DOCTOR = "/savedoctors";
	String SAVE_PATIENT = "/savepatients";
	String GET_PATIENT_BY_ID = "/getpatientbyid/{" + ID + "}";
	String GET_DOCTOR_BY_ID = "/getdoctorbyid/{" + ID + "}";
	String ASSIGN_PATIENT_TO_DOCTOR = "/assignpatienttodoctor/{" + PATIENT_ID + "}/{" + DOCTOR_ID + "}";
	String UPDATE_DOCTOR = "/updatedoctor/{" + ID + "}";
	String UPDATE_PATIENT = "/updatepatient/{" + ID + "}";
	String DELETE_DOCTOR = "/deletedoctor/{" + ID + "}";
	String DELETE_PATIENT = "/deletepatient/{" + ID + "}";
	String SAVE_APPOINTMENT = "/saveappointment";
	String GET_APPOINTMENT_BY_ID = "/getappointmentbyid/{" + ID + "}";
	String UPDATE_APPOINTMENT_STATUS = "/updateappointmentstatus/{"+ ID +"}/" + STATUS + "}";
	String DELETE_APPOINTMENT = "/deleteappointment/{" + ID + "}";
	String PATIENT_SAVED_SUCCESSFULLY = "Patient Saved Successfully";
	String DOCTOR_SAVED_SUCCESSFULLY = "Doctor saved successfully";
	String ASSIGN_PATIENT_TO_DOCTOR_SUCCESSFULLY = "Patient Assigned To Doctor Successfully";
	String DOCTOR_UPDATED_SUCCESSFULLY = "Doctor Updated Successfully";
	String PATIENT_UPDATED_SUCCESSFULLY = "Patient Updated Successfully";
	String DOCTOR_DELETED_SUCCESSFULLY = "Doctor Deleted Successfully";
	String PATIENT_DELETED_SUCCESFULLY = "Patient Deleted Successfully";
	String APPOINTMENT_SAVED_SUCCESSFULLY = "Appointment Saved Successfully";
	String APPOINTMENT_UPDATED_SUCCESSFULLY = "Appointment Updated Successfully";
	String APPOINTMENT_DELETED_SUCCESSFULLY = "Appointment Deleted Successfully";

}
