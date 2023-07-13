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
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "absence_type_id")
    private AbsenceType absenceType;

    public AbsenceTypeOff() {
    }

    public AbsenceTypeOff(Integer id, String name, String description, AbsenceType absenceType) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }

    @Override
    public String toString() {
        return "AbsenceTypeOff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", absenceType=" + absenceType +
                '}';
    }
}
