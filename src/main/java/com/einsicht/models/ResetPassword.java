package com.einsicht.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ResetPassword {	
	@NotEmpty(message = "*oldPassword is mandatory")
	@Size(min=8)
	private String oldPassword;
	
	@NotEmpty(message = "*newPassword is mandatory")
	@Size(min=8)
	private String newPassword;
	
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}	
}
