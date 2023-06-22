package com.manage.employee;

import com.manage.employee.controller.EmployeeController;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employee.service.EmployeeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTests {
    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;


    //smokeTest
    @Test
    public void contextLoads() {
        assertThat(employeeController).isNotNull();
    }

    //Integration Test
    @Test
    public void returnMyFirstPageTest() throws Exception {
        this.mockMvc.perform(
                get("/employees/page/{pageNum}", 1)
                        .param("sortField", "firstName")
                        .param("sortDir", "ASC")
                        .param("keyword", "Test")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("employees", hasSize(5)))
                .andExpect(model().attribute("totalPages", CoreMatchers.equalTo(3)))
                .andExpect(model().attribute("currentPage", CoreMatchers.equalTo(1)))
                .andExpect(model().attribute("startCount", CoreMatchers.equalTo(1L)))
                .andExpect(model().attribute("endCount", CoreMatchers.equalTo(5L)))
                .andExpect(model().attribute("totalElements", CoreMatchers.equalTo(13l)))
                .andExpect(model().attribute("sortField", CoreMatchers.equalTo("firstName")))
                .andExpect(model().attribute("sortDir", CoreMatchers.equalTo("ASC")))
                .andExpect(model().attribute("keyword", CoreMatchers.equalTo("Test")))
                .andExpect(model().attribute("reverseSortDir", CoreMatchers.equalTo("DESC")));;
    }
}
