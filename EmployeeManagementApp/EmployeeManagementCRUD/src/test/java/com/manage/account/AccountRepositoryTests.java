package com.manage.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employee.entity.Account;
import com.manage.employee.entity.Role;
import com.manage.employee.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void createAccountForEmployee() {
        Account account = new Account();
        account.setEnabled(true);
        account.setUsername("hr@ncc.asia");
        String password = "hr123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        account.setPassword(encodePassword);
        List<Role> roles = List.of(new Role(2), new Role(3));
        account.setRoles(roles);

        Account savedAccount = accountRepository.save(account);

        assertThat(savedAccount.getId()).isGreaterThan(0);
    }

}
