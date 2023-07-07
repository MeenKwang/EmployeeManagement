package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "absence_type_off")
public class AbsenceTypeOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "default_time_off")
    private Integer defaultTimeOff;
    @ManyToOne
    @JoinColumn(name = "absence_type_id")
    private AbsenceType absenceType;

    public AbsenceTypeOff() {
    }

    public AbsenceTypeOff(Integer id, String name, Integer defaultTimeOff, AbsenceType absenceType) {
        this.id = id;
        this.name = name;
        this.defaultTimeOff = defaultTimeOff;
        this.absenceType = absenceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultTimeOff() {
        return defaultTimeOff;
    }

    public void setDefaultTimeOff(Integer defaultTimeOff) {
        this.defaultTimeOff = defaultTimeOff;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }
}
