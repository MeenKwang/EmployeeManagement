package com.manage.employee.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.manage.employeemanagementmodel.entity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmployeeFormDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate birthDate;
	private LocalDate hiringDate;
	private boolean enabled;
	private String photo;
	private Integer buddyId;
	private Integer departmentId;
	private Integer accountId;
	private Integer jobDepartmentId;

	public EmployeeFormDto() {
	}

	public EmployeeFormDto(Integer id, String email, String firstName, String lastName, Gender gender, LocalDate birthDate, LocalDate hiringDate, boolean enabled, String photo, Integer buddyId, Integer departmentId, Integer accountId, Integer jobDepartmentId) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.hiringDate = hiringDate;
		this.enabled = enabled;
		this.photo = photo;
		this.buddyId = buddyId;
		this.departmentId = departmentId;
		this.accountId = accountId;
		this.jobDepartmentId = jobDepartmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getBuddyId() {
		return buddyId;
	}

	public void setBuddyId(Integer buddyId) {
		this.buddyId = buddyId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getJobDepartmentId() {
		return jobDepartmentId;
	}

	public void setJobDepartmentId(Integer jobDepartmentId) {
		this.jobDepartmentId = jobDepartmentId;
	}

	public String photosImagePath() {
		if(id == null || photo == null) {
			return "/images/default-user.png";
		}
		return "/employee-photos/" + this.id + "/" + this.photo;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
