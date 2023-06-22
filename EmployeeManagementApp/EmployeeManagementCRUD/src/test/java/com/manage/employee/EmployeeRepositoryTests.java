package com.manage.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import com.manage.employee.entity.Account;
import com.manage.employee.entity.JobDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.manage.employee.entity.Department;
import com.manage.employee.entity.Employee;
import com.manage.employee.entity.enums.Gender;
import com.manage.employee.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTests {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TestEntityManager testEntityManager;
	
//	public EmployeeRepositoryTests(EmployeeRepository employeeRepository,
//			TestEntityManager testEntityManager) {
//		this.employeeRepository = employeeRepository;
//		this.testEntityManager = testEntityManager;
//	}
	
	@Test
	public void testCreateEmployee() {
		Department department = testEntityManager.find(Department.class, 1);
		Employee buddy = testEntityManager.find(Employee.class, 1);
		Account account = new Account();
		account.setId(5);
		JobDepartment jobDepartment = new JobDepartment();
		jobDepartment.setId(3);
		Employee employee = new Employee(null, "HR", "HR", Gender.FEMALE, LocalDate.now(), LocalDate.now(), "hr@gmail.com", "test_repo_photo.png", true, null, department, null, null, null, null);
		
		Employee savedEmployee = employeeRepository.save(employee);
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getEmployeeById() {
		Integer employeeId = 7;
		Employee employee = employeeRepository.getReferenceById(employeeId);
		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo("Test3");
	}
	
	@Test
	public void getEmployeesListByPage() {
		Integer pageNum = 1;
		Integer pageSize = 5;
		String sortField = "firstName";
		String searchKeyword = "Test";
		Sort sort = Sort.by(sortField).ascending();
		Pageable page = PageRequest.of(pageNum - 1, pageSize, sort);
		Page<Employee> employeesPage = employeeRepository.findAll(searchKeyword, page);
		
		System.out.println(employeesPage);
		
		assertThat(employeesPage.getSize()).isGreaterThan(0);
		assertThat(employeesPage.getSize()).isEqualTo(5);
	}
	
	@Test
	public void testUpdateEmployee() {
		Integer employeeId = 24;
		Employee employee = employeeRepository.getReferenceById(employeeId);
		employee.setEmail("testrepo2@gmail.com");
		Employee savedEmployee = employeeRepository.save(employee);
		assertThat(savedEmployee.getEmail()).isEqualTo("testrepo2@gmail.com");
	}
	
	@Test
	public void testDeleteEmployee() {
		Integer employeeId = 24;
		employeeRepository.deleteById(employeeId);
		assertThat(employeeRepository.getReferenceById(employeeId)).isNull();
	}

}
