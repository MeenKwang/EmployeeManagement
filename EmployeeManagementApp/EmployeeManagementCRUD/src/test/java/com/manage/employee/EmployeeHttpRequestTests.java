package com.manage.employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeHttpRequestTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    public void testCheckUniqueEmail() {
        String url = "http://localhost:8080/Manager/employees/check_email_unique?id={id}&email={email}";
        Map<String, String> params = new HashMap<>();
        params.put("id", "7");
        params.put("email", "emailtest@gmail.com");
        Assertions.assertThat(this.testRestTemplate
                .postForObject(url, null, String.class, params))
                .contains("Unique");
    }
}
