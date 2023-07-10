package com.insuranceservice.util;

public interface Constants {

	String INDIAN = "INDIAN";
	String ID = "id";
	String INSURANCEAPP = "/insuranceapp";
	String SAVE_INSURER_DETAILS = "/saveinsurerdetails";
	String GET_ALL_INSURER_DETAILS = "/findallinsurerdetails";
	String GET_INSURER_BY_ID = "/findinsurerdetailsbyid/{" + ID + "}";
	String DELETE_INSURER_BY_ID = "/deleteinsurerbyid/{" + ID + "}";
	String INSURER_SAVED_SUCCESSFULLY = "Insurer Details Saved Successfully";
	String INSURER_DELETED_SUCCESSFULLY = "Insurer Deleted Successfully";

}
