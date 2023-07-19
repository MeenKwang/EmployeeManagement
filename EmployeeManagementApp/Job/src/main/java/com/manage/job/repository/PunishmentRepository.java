package com.manage.job.repository;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.manage.employeemanagementmodel.entity.enums.PunishmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PunishmentRepository extends JpaRepository<Punishment, Integer> {
}
