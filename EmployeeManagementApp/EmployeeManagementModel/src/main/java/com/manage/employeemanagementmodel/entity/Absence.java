package com.manage.employeemanagementmodel.entity;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
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
    @JoinColumn(name = "absence_type_id")
    private AbsenceType absenceType;
    @ManyToOne
    @JoinColumn(name = "absence_type_off_id")
    private AbsenceTypeOff typeOff;
    @Column(name = "date_request")
    private LocalDate dateRequest;
    @Column(name = "date_submit")
    private LocalDate dateSubmit;
    @Column(name = "type_time_off")
    @Enumerated(EnumType.STRING)
    private TypeTimeOff typeTimeOff;
    @Column(name = "time_off")
    private Double timeOff;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AbsenceStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Absence() {
    }

    public Absence(Integer id, String reason, AbsenceType absenceType, AbsenceTypeOff typeOff, LocalDate dateRequest, LocalDate dateSubmit, TypeTimeOff typeTimeOff, Double timeOff, Employee employee) {
        this.id = id;
        this.reason = reason;
        this.absenceType = absenceType;
        this.typeOff = typeOff;
        this.dateRequest = dateRequest;
        this.dateSubmit = dateSubmit;
        this.typeTimeOff = typeTimeOff;
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

    public Double getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Double timeOff) {
        this.timeOff = timeOff;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TypeTimeOff getTypeTimeOff() {
        return typeTimeOff;
    }
    public void setTypeTimeOff(TypeTimeOff typeTimeOff) {
        this.typeTimeOff = typeTimeOff;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    public void setStatus(AbsenceStatus status) {
        this.status = status;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", absenceType=" + absenceType +
                ", typeOff=" + typeOff +
                ", dateRequest=" + dateRequest +
                ", dateSubmit=" + dateSubmit +
                ", typeTimeOff=" + typeTimeOff +
                ", timeOff=" + timeOff +
                ", status=" + status +
                ", employee=" + employee +
                '}';
    }
}
