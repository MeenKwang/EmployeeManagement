package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reason")
    private String reason;
    @ManyToOne
    @JoinColumn(name = "absence_type_off_id")
    private AbsenceTypeOff typeOff;
    @Column(name = "date_request")
    private LocalDate dateRequest;
    @Column(name = "time_off")
    private Integer timeOff;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Absence() {
    }

    public Absence(Integer id, String reason, AbsenceTypeOff typeOff, LocalDate dateRequest, Integer timeOff, Employee employee) {
        this.id = id;
        this.reason = reason;
        this.typeOff = typeOff;
        this.dateRequest = dateRequest;
        this.timeOff = timeOff;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AbsenceTypeOff getTypeOff() {
        return typeOff;
    }

    public void setTypeOff(AbsenceTypeOff typeOff) {
        this.typeOff = typeOff;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Integer getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Integer timeOff) {
        this.timeOff = timeOff;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
