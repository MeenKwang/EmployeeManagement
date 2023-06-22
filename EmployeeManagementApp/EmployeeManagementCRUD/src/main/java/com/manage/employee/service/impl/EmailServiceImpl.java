package com.manage.employee.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.manage.employeemanagementmodel.entity.Department;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.MailConfig;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.entity.enums.MailType;
import com.manage.employee.repository.DepartmentRepository;
import com.manage.employee.repository.EmployeeRepository;
import com.manage.employee.repository.MailConfigRepository;
import com.manage.employee.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	
	private final MailConfigRepository mailConfigRepository;
	private final TemplateEngine templateEngine;
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	
	public EmailServiceImpl(MailConfigRepository mailConfigRepository, TemplateEngine templateEngine, EmployeeRepository employeeRepository,
			DepartmentRepository departmentRepository) {
		this.mailConfigRepository = mailConfigRepository;
		this.templateEngine = templateEngine;
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public void sendEmailToPM(Employee employee, String type) {
		System.out.println("Email test:" + employee);
		MailConfig mailConfig = mailConfigRepository.findByType(MailType.NOTIFY_EMPLOYEE);
		Employee buddy = null;
		if(employee.getBuddy() != null) {
			buddy = employeeRepository.findById(employee.getBuddy().getId()).get();
		}
		
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		employee.setBuddy(buddy);
		employee.setDepartment(department);
		if(buddy != null) {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			
			mailSender.setHost(mailConfig.getHost());
			mailSender.setPort(Integer.parseInt(mailConfig.getPort()));
			mailSender.setUsername(mailConfig.getUserName());
			mailSender.setPassword(mailConfig.getPassword());
			
			Properties mailProperties = new Properties();
			mailProperties.setProperty("mail.smtp.auth", mailConfig.isSmtpAuth() ? "true" : "false");
			mailProperties.setProperty("mail.smtp.starttls.enable", mailConfig.isSmtpSecured() ? "true" : "false");
			
			mailSender.setJavaMailProperties(mailProperties);
			
			MimeMessage message = mailSender.createMimeMessage();
			
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setFrom(mailConfig.getMailFrom(), mailConfig.getSenderName());
				helper.setTo(buddy.getEmail());
				helper.setSubject("Notify about details information of your employee");	
				final Context ctx = new Context();
				ctx.setVariable("genderCall", buddy.getGender() == Gender.MALE ? "Mr." : "Mrs.");
				ctx.setVariable("name", buddy.getFullName());
				ctx.setVariable("imageResourceName", "myPhoto");
				if(type.equals("NEW")) {
					ctx.setVariable("notifyText", "Notify to PM that he has new member!");
					System.out.println("Notify to PM that he has new member!");
				} else {
					ctx.setVariable("notifyText", "Notify to PM that HR has changed his member's information!");
					System.out.println("Notify to PM that HR has changed his member's information!");
				}
				ctx.setVariable("employee", employee);
				// Create the HTML body using Thymeleaf
				final String htmlContent = templateEngine.process("email/mail_notify_employee_details.html", ctx);
				helper.setText(htmlContent, true);
				// Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
				InputStreamSource imageReSource = new FileSystemResource("employee-photos/" + employee.getId() + "/" + employee.getPhoto());
				helper.addInline("myPhoto", imageReSource, "image/png");
				//Send mail
				mailSender.send(message);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
