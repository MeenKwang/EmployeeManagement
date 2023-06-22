package com.manage.employee_bonus;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employeemanagementmodel.entity.Bonus;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.manage.employee.repository.EmployeeBonusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeBonusRepositoryTests {

    @Autowired
    private EmployeeBonusRepository employeeBonusRepository;

    @Test
    public void createEmployeeBonus() {
        Employee employee = new Employee();
        employee.setId(1);
        Bonus bonus = new Bonus();
        bonus.setId(1);
        EmployeeBonus employeeBonus = new EmployeeBonus();
        employeeBonus.setBonus(bonus);
        employeeBonus.setReason("Reason 1");
        employeeBonus.setEmployee(employee);
        String dateString = "13-06-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        employeeBonus.setDateBonus(date);

        EmployeeBonus savedEmployeeBonus = employeeBonusRepository.save(employeeBonus);

        assertThat(savedEmployeeBonus.getId()).isGreaterThan(0);
    }
}
