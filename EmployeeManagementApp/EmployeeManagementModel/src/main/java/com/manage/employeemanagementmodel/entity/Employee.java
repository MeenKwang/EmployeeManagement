package com.manage.employeemanagementmodel.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.employeemanagementmodel.entity.enums.Gender;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "first_name", nullable = false, length = 255)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 255)
	private String lastName;
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	@Column(name = "hiring_date", nullable = false)
	private LocalDate hiringDate;
	@Column(name = "email", nullable = false, length = 255)
	private String email;
	@Column(name = "photo", length = 255, nullable = true)
	private String photo;
	@Column(name = "enabled")
	private boolean enabled;
	@ManyToOne
	@JoinColumn(name = "buddy_id")
	private Employee buddy;
	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;
	//Remember to initialize the collection, if not, it can't be got (maybe hibernate can't manipulate an un-initialized collection.)
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Note> notes = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "job_department_id")
	private JobDepartment jobDepartment;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<EmployeeBonus> employeeBonuses;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CheckIn> checkIns = new ArrayList<>();
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Absence> absences = new ArrayList<>();
	public Employee() {
	}

	public Employee(Integer id, String firstName, String lastName, Gender gender, LocalDate birthDate, LocalDate hiringDate, String email, String photo, boolean enabled, Employee buddy, Department department, List<Note> notes, Account account, JobDepartment jobDepartment, List<EmployeeBonus> employeeBonuses, List<CheckIn> checkIns, List<Absence> absences) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.hiringDate = hiringDate;
		this.email = email;
		this.photo = photo;
		this.enabled = enabled;
		this.buddy = buddy;
		this.department = department;
		this.notes = notes;
		this.account = account;
		this.jobDepartment = jobDepartment;
		this.employeeBonuses = employeeBonuses;
		this.checkIns = checkIns;
		this.absences = absences;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Employee getBuddy() {
		return buddy;
	}

	public void setBuddy(Employee buddy) {
		this.buddy = buddy;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public JobDepartment getJobDepartment() {
		return jobDepartment;
	}

	public void setJobDepartment(JobDepartment jobDepartment) {
		this.jobDepartment = jobDepartment;
	}

	public List<EmployeeBonus> getEmployeeBonuses() {
		return employeeBonuses;
	}

	public void setEmployeeBonuses(List<EmployeeBonus> employeeBonuses) {
		this.employeeBonuses = employeeBonuses;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<CheckIn> getCheckIns() {
		return checkIns;
	}

	public void setCheckIns(List<CheckIn> checkIns) {
		this.checkIns = checkIns;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	@Transient
	public String getPhotosImagePath() {
		if(id == null || photo == null) {
			return "/images/default-user.png";
		}
		return "/employee-photos/" + this.id + "/" + this.photo;
	}
	
	@Transient
	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", gender=" + gender +
				", birthDate=" + birthDate +
				", hiringDate=" + hiringDate +
				", email='" + email + '\'' +
				", photo='" + photo + '\'' +
				", enabled=" + enabled +
				", buddy=" + buddy +
				", department=" + department +
				", notes=" + notes +
				", account=" + account +
				", jobDepartment=" + jobDepartment +
				", employeeBonuses=" + employeeBonuses +
				", checkIns=" + checkIns +
				", absences=" + absences +
				'}';
	}
}
