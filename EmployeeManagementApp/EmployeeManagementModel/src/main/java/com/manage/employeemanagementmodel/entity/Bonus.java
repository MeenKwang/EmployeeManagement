package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bonus")
public class Bonus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "bonus_amount", nullable = false)
    private Long bonusAmount;
    @OneToMany(mappedBy = "bonus")
    private List<EmployeeBonus> employeeBonuses;

    public Bonus() {
    }

    public Bonus(Integer id, String name, String description, Long bonusAmount, List<EmployeeBonus> employeeBonuses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.bonusAmount = bonusAmount;
        this.employeeBonuses = employeeBonuses;
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

    public Long getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Long bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public List<EmployeeBonus> getEmployeeBonuses() {
        return employeeBonuses;
    }

    public void setEmployeeBonuses(List<EmployeeBonus> employeeBonuses) {
        this.employeeBonuses = employeeBonuses;
    }
}
