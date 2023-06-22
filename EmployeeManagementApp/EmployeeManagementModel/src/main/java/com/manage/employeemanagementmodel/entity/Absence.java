package com.manage.employeemanagementmodel.entity;

import com.manage.employeemanagementmodel.entity.enums.AbsenceType;
import com.manage.employeemanagementmodel.entity.enums.AbsenceTypeOff;
import jakarta.persistence.*;

@Entity
@Table(name = "absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "absence_type")
    @Enumerated(EnumType.STRING)
    private AbsenceType absenceType;
    @Column(name = "reason")
    private String reason;
    @Column(name = "type_off")
    @Enumerated(EnumType.STRING)
    private AbsenceTypeOff typeOff;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
