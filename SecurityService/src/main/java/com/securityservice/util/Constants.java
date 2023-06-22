package com.securityservice.util;

public interface Constants {
	String ROLE_ADMIN = "ROLE_ADMIN";
	String ROLE_USER = "ROLE_USER";
	String IS_ADMIN = "isAdmin";
	String IS_USER = "isUser";
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

}
