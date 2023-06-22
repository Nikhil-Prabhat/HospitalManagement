package com.securityservice.entity;

public enum Roles {
	
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER");
	
	private String userRole;
	
	private Roles(String userRole) {
		this.setUserRole(userRole);
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
