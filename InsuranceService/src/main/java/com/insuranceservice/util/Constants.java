package com.insuranceservice.util;

public interface Constants {

	String INDIAN = "INDIAN";
	String INTERNATIONAL = "INTERNATIONAL";
	String ID = "id";
	String AUTHORIZATION = "Authorization";
	String PATIENT_ID = "patientid";
	String INSURANCE_ID = "insuranceid";
	String VALIDATE = "/validate";
	String INSURANCEAPP = "/insuranceapp";
	String SAVE_INSURER_DETAILS = "/saveinsurerdetails";
	String GET_ALL_INSURER_DETAILS = "/findallinsurerdetails";
	String GET_INSURER_BY_ID = "/findinsurerdetailsbyid/{" + ID + "}";
	String DELETE_INSURER_BY_ID = "/deleteinsurerbyid/{" + ID + "}";
	String SAVE_PATIENT_CLAIM = "/savepatientclaim";
	String GET_ALL_PATIENT_CLAIMS = "/getallpatientclaims";
	String GET_PATIENT_CLAIM_BY_ID = "/getpatientclaimbyid/{" + ID + "}";
	String UPDATE_PATIENT_CLAIM = "/updatepatientclaim/{" + ID + "}";
	String DELETE_PATIENT_CLAIM_BY_ID = "/deletepatientclaimbyid/{" + ID + "}";
	String GET_PATIENT_BY_ID = "/getpatientbyid/{" + ID + "}";
	String GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT = "/getalltreatmenthistoryforapatient/{" + ID + "}";
	String GET_BILL_BY_PATIENT_ID = "/getbillbypatientid/{"+ID+"}";
	String FINAL_COST_CALCULATION = "/calculatefinalamount/{" + PATIENT_ID+ "}/{" + INSURANCE_ID +"}";
	String INSURER_SAVED_SUCCESSFULLY = "Insurer Details Saved Successfully";
	String INSURER_DELETED_SUCCESSFULLY = "Insurer Deleted Successfully";
	String PATIENT_CLAIM_SAVED_SUCCESSFULLY = "Patient Claim Saved Successfully";
	String PATIENT_CLAIM_UPDATED_SUCCESSFULLY = "Patient Claim Updated Successfully";
	String PATIENT_CLAIM_DELETED_SUCCESSFULLY = "Patient Claim Deleted Successfully";
	String NOT_EMPTY = "Must Not Be Empty!";
	String NOT_NULL = "Must Not Be Null!";
	String INSURANCE_TYPE_CHECK = INDIAN + "|" + INTERNATIONAL;
	String INSURANCE_TYPE_NOT_PROVIDED_CORRECTLY = "Insurance Type Not Provided Correctly, It should be either INDIAN or INTERNATIONAL";
	String METHOD_INVOKED = "Method Invoked :- ";
	String ARGUMENTS = "Arguments :- ";
	String RESPONSE = "Response :- ";
	String ERROR_MESSAGE = "errorMessage";
	String ERROR_CLASS = "errorClass";
	String INSURER_NOT_FOUND_WITH_ID = "Insurer Not Found With Id : ";
	String PATIENT_CLAIM_NOT_FOUND_WITH_ID = "Patient Claim Not Found With Id : ";
	String FALLBACK_METHOD_MESSAGE = "Fallback Method For Insurance Service Invoked";
	String CIRCUIT_BREAKER_NAME = "insuranceCircuitBreaker";
	String RETRY_NAME = "insuranceRetry";
	String FALLBACK_METHOD_NAME = "insuranceFallbackMethod";
	

}
