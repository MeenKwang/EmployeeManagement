package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "punishment_type")
public class PunishmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "punishment_money")
    private Double punishmentMoney;

    public PunishmentType() {
    }

    public PunishmentType(Integer id, String name, String description, Double punishmentMoney) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.punishmentMoney = punishmentMoney;
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

    public Double getPunishmentMoney() {
        return punishmentMoney;
    }

    public void setPunishmentMoney(Double punishmentMoney) {
        this.punishmentMoney = punishmentMoney;
    }
}
