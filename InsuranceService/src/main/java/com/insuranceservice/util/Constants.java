package com.insuranceservice.util;

public interface Constants {

	String INDIAN = "INDIAN";
	String INTERNATIONAL = "INTERNATIONAL";
	String ID = "id";
	String AUTHORIZATION = "Authorization";
	String TOTAL_EXPENSE = "totalexpense";
	String INSURANCE_AMOUNT = "insuranceamount";
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
	String FINAL_COST_CALCULATION = "/calculatefinalamount/{" + TOTAL_EXPENSE+ "}/{" + INSURANCE_AMOUNT +"}";
	String INSURER_SAVED_SUCCESSFULLY = "Insurer Details Saved Successfully";
	String INSURER_DELETED_SUCCESSFULLY = "Insurer Deleted Successfully";
	String PATIENT_CLAIM_SAVED_SUCCESSFULLY = "Patient Claim Saved Successfully";
	String PATIENT_CLAIM_UPDATED_SUCCESSFULLY = "Patient Claim Updated Successfully";
	String PATIENT_CLAIM_DELETED_SUCCESSFULLY = "Patient Claim Deleted Successfully";

}
