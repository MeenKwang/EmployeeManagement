package com.manage.job.service.impl;

import com.manage.employeemanagementmodel.entity.MailConfig;
import com.manage.employeemanagementmodel.entity.enums.MailType;
import com.manage.job.dto.EmployeeNotifyDto;
import com.manage.job.repository.EmployeeRepository;
import com.manage.job.repository.MailConfigRepository;
import com.manage.job.service.DailyJobService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
@Service
public class DailyJobServiceImpl implements DailyJobService {
    private final EmployeeRepository employeeRepository;
    private final MailConfigRepository mailConfigRepository;
    private final TemplateEngine templateEngine;

    public DailyJobServiceImpl(EmployeeRepository employeeRepository, MailConfigRepository mailConfigRepository, TemplateEngine templateEngine) {
        this.employeeRepository = employeeRepository;
        this.mailConfigRepository = mailConfigRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMailNotifyDailyTask() throws MessagingException, UnsupportedEncodingException {
        List<EmployeeNotifyDto> lst = employeeRepository.findAllToNotify();
        MailConfig mailConfig = mailConfigRepository.findByType(MailType.NOTIFY_EMPLOYEE);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(Integer.parseInt(mailConfig.getPort()));

        mailSender.setUsername(mailConfig.getUserName());
        mailSender.setPassword(mailConfig.getPassword());

        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", mailConfig.isSmtpAuth() ? "true" : "false");
        mailProperties.setProperty("mail.smtp.starttls.enable", mailConfig.isSmtpSecured() ? "true" : "false");
        mailProperties.setProperty("mail.mime.address.strict", "false");
        mailSender.setJavaMailProperties(mailProperties);
        for(EmployeeNotifyDto employee : lst) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(mailConfig.getMailFrom(), mailConfig.getSenderName());
            helper.setTo(employee.getEmail());
            helper.setSubject("NOTIFY DAILY!");
            final Context ctx = new Context();
            ctx.setVariable("employeeName", employee.getFullName());
            final String htmlContent = templateEngine.process("email/mail_notify_employee_daily.html", ctx);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        }
    }
}
