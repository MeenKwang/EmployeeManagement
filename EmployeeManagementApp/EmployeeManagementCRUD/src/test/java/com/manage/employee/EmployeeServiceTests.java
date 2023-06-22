package com.manage.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.manage.employee.dto.EmployeeFormDto;
import com.manage.employee.dto.mapper.employee.EmployeeFormMapper;
import com.manage.employeemanagementmodel.entity.Department;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.manage.employee.repository.EmployeeRepository;
import com.manage.employee.service.EmployeeService;
import com.manage.employee.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTests {
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private EmployeeFormMapper employeeFormMapper;
	
	private EmployeeService employeeService;
	
	@BeforeEach
	void setup() {
		employeeService = new EmployeeServiceImpl(employeeRepository, employeeFormMapper);
	}
	
	@Test
	public void testCheckEmailUniqueWhenUpdateEmployeeWithEmailNoneExist() {
		String email = "noneexistemail@gmail.com";
		Integer existId = 7;
		Mockito.when(employeeRepository.getEmployeeByEmail(email)).thenReturn(null);
		
		boolean result = employeeService.checkEmailUnique(existId, email);
		
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	public void testCheckEmailUniqueWhenCreateEmployeeWithEmailNoneExist() {
		String email = "noneexistemail@gmail.com";
		Integer existId = null;
		Mockito.when(employeeRepository.getEmployeeByEmail(email)).thenReturn(null);
		
		boolean result = employeeService.checkEmailUnique(existId, email);
		
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	public void testCheckEmailUniqueWhenCreateEmployeeWithEmailExist() {
		String email = "test7@gmail.com";
		Integer existId = null;
		Employee employee = new Employee();
		employee.setId(19);
		employee.setEmail(email);
		Mockito.when(employeeRepository.getEmployeeByEmail(email)).thenReturn(employee);
		
		boolean result = employeeService.checkEmailUnique(existId, email);
		
		assertThat(result).isEqualTo(false);
	}
	
	@Test
	public void testCheckEmailUniqueWhenUpdateEmployeeWithEmailRemainTheSame() {
		String email = "test7@gmail.com";
		Integer existId = 19;
		Employee employee = new Employee();
		employee.setId(existId);
		employee.setEmail(email);
		Mockito.when(employeeRepository.getEmployeeByEmail(email)).thenReturn(employee);
		
		boolean result = employeeService.checkEmailUnique(existId, email);
		
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	public void testCheckEmailUniqueWhenUpdateEmployeeWithEmailChange() {
		String email = "test8@gmail.com";
		Integer existId = 19;
		Employee employee = new Employee();
		employee.setId(20);
		employee.setEmail(email);
		Mockito.when(employeeRepository.getEmployeeByEmail(email)).thenReturn(employee);
		
		boolean result = employeeService.checkEmailUnique(existId, email);
		
		assertThat(result).isEqualTo(false);
	}
	
	@Test
	public void testFindEmployeeFormById() {
		Integer employeeId = 19;
		Employee employee = new Employee();
		employee.setId(19);
		employee.setFirstName("Test7");
		employee.setLastName("Test7");
		employee.setGender(Gender.FEMALE);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String hiringDateString = "2023-06-17";
		LocalDate hiringDate = LocalDate.parse(hiringDateString, formatter);
		employee.setHiringDate(hiringDate);
		employee.setPhoto("Screenshot 2023-03-11 103546.png");
		employee.setEnabled(true);
		employee.setEmail("test7@gmail.com");
		String birthDateString = "2023-05-29";
		LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
		employee.setBirthDate(birthDate);
		employee.setAccount(null);
		Employee buddy = new Employee();
		buddy.setId(1);
		employee.setBuddy(buddy);
		employee.setDepartment(new Department(1, null));
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		EmployeeFormDto employeeFormDto = new EmployeeFormDto();
		employeeFormDto.setId(19);
		employeeFormDto.setFirstName("Test7");
		employeeFormDto.setLastName("Test7");
		employeeFormDto.setGender(Gender.FEMALE);
		employeeFormDto.setHiringDate(hiringDate);
		employeeFormDto.setPhoto("Screenshot 2023-03-11 103546.png");
		employeeFormDto.setEnabled(true);
		employeeFormDto.setEmail("test7@gmail.com");
		employeeFormDto.setBirthDate(birthDate);
		employeeFormDto.setAccountId(null);
		employeeFormDto.setBuddyId(1);
		employeeFormDto.setDepartmentId(1);
		Mockito.when(employeeFormMapper.employeeToEmployeeFormDto(employee)).thenReturn(employeeFormDto);
		try {
			assertThat(employeeService.findEmployeeFormById(employeeId)).isEqualTo(employeeFormDto);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListByPage() {
		Integer pageNum = 1;
		String sortField = "firstName";
		String sortDir = "ASC";
//		String keyword = null;
		String keyword = "Test";
		Sort sort  = Sort.by(sortField);
		if(sortDir.equals("ASC")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		Pageable page = PageRequest.of(pageNum - 1, 5, sort);
		List<Employee> employeesList = List.of(new Employee(), new Employee(), new Employee(), new Employee(), new Employee());
		Page<Employee> employeesPage = new PageImpl<>(employeesList);
		Mockito.when(employeeRepository.findAll(page)).thenReturn(employeesPage);
		List<Employee> employeesList2 = List.of(new Employee(), new Employee(), new Employee());
		Page<Employee> employeesPage2 = new PageImpl<>(employeesList2);
		Mockito.when(employeeRepository.findAll("Test", page)).thenReturn(employeesPage2);
//		assertThat(employeeService.listByPage(pageNum, sortField, sortDir, keyword).getContent().size()).isEqualTo(5);
		assertThat(employeeService.listByPage(pageNum, sortField, sortDir, keyword).getContent().size()).isEqualTo(3);
	}
	
	@Test
	public void testDeleteEmployee() {
		Integer employeeId = 24;
		when(employeeRepository.findById(employeeId)).thenCallRealMethod();
		
	}
}


