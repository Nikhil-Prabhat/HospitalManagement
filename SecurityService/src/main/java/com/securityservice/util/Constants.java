package com.securityservice.util;

public interface Constants {
	String ROLE_ADMIN = "ROLE_ADMIN";
	String ROLE_USER = "ROLE_USER";
	String ROLE_DOCTOR = "ROLE_DOCTOR";
	String IS_ADMIN = "isAdmin";
	String IS_USER = "isUser";
	String IS_DOCTOR = "isDoctor";
	String USER_NOT_FOUND_WITH_NAME = "User Not Found With Name : ";
	String AUTHORIZATION = "Authorization";
	String BEARER = "Bearer ";
	String AUTHAPP = "/authapp";
	String SAVE_USER = "/saveuser";
	String LOGIN = "/login";
	String VALIDATE = "/validate";
	String SAVE_USER_URL = AUTHAPP + SAVE_USER;
	String LOGIN_URL = AUTHAPP + LOGIN;
	String USER_SAVED_SUCCESSFULLY = "User Saved Successfully";
	String ROLE_CHECK = ROLE_ADMIN + "|" + ROLE_USER + "|" + ROLE_DOCTOR;
	String NOT_EMPTY = "Must Not Be Empty!";
	String NOT_NULL = "Must Not Be Null!";
	String CORRECT_ROLE_NOT_SUPPLIED = "Correct Role Not Provided, It should be either ROLE_ADMIN or ROLE_USER or ROLE_DOCTOR";
	String ERROR_MESSAGE = "errorMessage";
	String ERROR_CLASS = "errorClass";
	String METHOD_INVOKED = "Method Invoked :- ";
	String ARGUMENTS = "Arguments :- ";
	String RESPONSE = "Response :- ";
	String INCORRECT_CREDENTIALS = "Incorrect Credentials For the User : ";

}
