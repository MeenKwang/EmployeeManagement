package com.manage.employee;

import com.manage.employee.controller.EmployeeController;
import com.manage.employee.controller.EmployeeRestController;
import com.manage.employee.dto.mapper.employee.EmployeeFormMapper;
import com.manage.employee.entity.Account;
import com.manage.employee.entity.Department;
import com.manage.employee.entity.Employee;
import com.manage.employee.entity.enums.Gender;
import com.manage.employee.service.DepartmentService;
import com.manage.employee.service.EmailService;
import com.manage.employee.service.EmployeeService;
import com.manage.employee.service.NoteService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {EmployeeController.class, EmployeeRestController.class})
@MockBeans({@MockBean(EmployeeService.class), @MockBean(DepartmentService.class), @MockBean(EmployeeFormMapper.class),
        @MockBean(NoteService.class), @MockBean(EmailService.class)})
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    //Controller test
    @Test
    public void myFirstPageTest() throws Exception {
        // create some sample employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Test", "Test", Gender.MALE, null, null, "test@gmail.com", "photo", true, new Employee(), new Department(), null, new Account(), null, null),
                new Employee(2, "Test2", "Test2", Gender.MALE, null, null, "test2@gmail.com", "photo2", true, new Employee(), new Department(), null, new Account(), null, null)
        );

        // mock the employee service to return the sample employees
        Page<Employee> page = new PageImpl<>(employees);
        when(employeeService.listByPage(1, "firstName", "ASC", "test")).thenReturn(page);

        // perform the GET request
        mockMvc.perform(get("/employees/page/{pageNum}", 1)
                        .param("sortField", "firstName")
                        .param("sortDir", "ASC")
                        .param("keyword", "test"))
                .andExpect(status().isOk())
//                .andExpect(view().name("employees/employees"))
                .andExpect(model().attribute("employees", hasSize(2)))
                .andExpect(model().attribute("totalPages", CoreMatchers.equalTo(1)))
                .andExpect(model().attribute("currentPage", CoreMatchers.equalTo(1)))
                .andExpect(model().attribute("startCount", CoreMatchers.equalTo(1L)))
                .andExpect(model().attribute("endCount", CoreMatchers.equalTo(2L)))
                .andExpect(model().attribute("totalElements", CoreMatchers.equalTo(2L)))
                .andExpect(model().attribute("sortField", CoreMatchers.equalTo("firstName")))
                .andExpect(model().attribute("sortDir", CoreMatchers.equalTo("ASC")))
                .andExpect(model().attribute("keyword", CoreMatchers.equalTo("test")))
                .andExpect(model().attribute("reverseSortDir", CoreMatchers.equalTo("DESC")));

        // verify that the employee service was called with the correct parameters
        verify(employeeService, times(1)).listByPage(eq(1), eq("firstName"), eq("ASC"), eq("test"));
    }
}
