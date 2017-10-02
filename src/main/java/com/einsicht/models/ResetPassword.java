package com.einsicht.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetPassword {
	
	@NotEmpty(message = "*email is mandatory")
	@Email(message = "*Please provide a valid email")
	private String email;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
