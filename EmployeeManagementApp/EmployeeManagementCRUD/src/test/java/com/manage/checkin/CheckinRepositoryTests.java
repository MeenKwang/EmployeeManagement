package com.manage.checkin;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employee.repository.CheckinRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CheckinRepositoryTests {

    @Autowired
    private CheckinRepository checkinRepository;

    @Test
    public void createCheckinForEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        CheckIn checkIn = new CheckIn();
        checkIn.setEmployee(employee);
        checkIn.setCheckInTime(LocalDateTime.of(2023, 6, 19, 8, 30, 0));
        checkIn.setCheckOutTime(LocalDateTime.of(2023, 6, 19, 17, 30, 0));

        CheckIn savedCheckIn = checkinRepository.save(checkIn);

        assertThat(savedCheckIn.getId()).isGreaterThan(0);

    }

}
