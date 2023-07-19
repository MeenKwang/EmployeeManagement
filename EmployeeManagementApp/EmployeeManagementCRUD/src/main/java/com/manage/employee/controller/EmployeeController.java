package com.manage.employee.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.manage.employee.service.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manage.employee.dto.EmployeeFormDto;
import com.manage.employee.dto.mapper.employee.EmployeeFormMapper;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.manage.employee.ultilities.FileUploadUtil;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	private final DepartmentService departmentService;
	private final EmployeeFormMapper employeeFormMapper;
	private final NoteService noteService;
	private final EmailService emailService;
	private final JobDepartmentService jobDepartmentService;
	
	public EmployeeController(EmployeeService employeeService, DepartmentService departmentService,
							  EmployeeFormMapper employeeFormMapper, NoteService noteService, EmailService emailService, JobDepartmentService jobDepartmentService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.employeeFormMapper = employeeFormMapper;
		this.noteService = noteService;
		this.emailService = emailService;
		this.jobDepartmentService = jobDepartmentService;
	}
	
	@GetMapping("")
	public String listAll(Model model) {
		return listByPage(1, "firstName", "ASC", null, model);
	}
	
	@GetMapping("page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") Integer pageNum, @RequestParam(name = "sortField") String sortField,
			@RequestParam("sortDir") String sortDir, @RequestParam(name = "keyword") String keyword, Model model) {
		Page<Employee> page = employeeService.listByPage(pageNum, sortField, sortDir, keyword);
		long startCount = (pageNum - 1) * EmployeeService.EMPLOYEES_PER_PAGE + 1;
		long endCount = startCount + EmployeeService.EMPLOYEES_PER_PAGE - 1;
		if(endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		String reverseSortDir = "";
		if(sortDir.equals("ASC")) {
			reverseSortDir = "DESC";
		} else {
			reverseSortDir = "ASC";
		}
		
		model.addAttribute("employees", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("reverseSortDir", reverseSortDir);
		
		return "employees/employees";
	}
	
	@GetMapping("new")
	public String createNewEmployee(Model model) {
		model.addAttribute("employee", new EmployeeFormDto());
		model.addAttribute("pageTitle", "Create New Employee");
		model.addAttribute("genders", Gender.values());
		model.addAttribute("listDepartments", departmentService.findAll());
		model.addAttribute("listBuddy", employeeService.findAll());
		model.addAttribute("listJobDepartments", jobDepartmentService.findAll());
		return "employees/employees_form";
	}
	
	@GetMapping("edit/{id}")
	public String editEmployee(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			EmployeeFormDto employee =  employeeService.findEmployeeFormById(id);
			model.addAttribute("employee", employee);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("listDepartments", departmentService.findAll());
			model.addAttribute("listBuddy", employeeService.findByIdNot(employee.getId()));
			model.addAttribute("listJobDepartments", jobDepartmentService.findAll());
			model.addAttribute("pageTitle", "Update Employee (ID: " + id + ")");
			return "employees/employees_form";
		} catch (EmployeeNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/employees";
		}
	}
	
	@PostMapping("save")
	public String saveEmployee(EmployeeFormDto employeeFormDto, Model model, RedirectAttributes redirectAttributes, 
		@RequestParam(name = "image") MultipartFile photoFile) throws IOException, MessagingException {
		String type = "NEW";
		if(employeeFormDto.getId() != null) {
			type = "UPDATE";
		}
		Employee employee = employeeFormMapper.employeeFormDtoToEmployee(employeeFormDto);
		if(employee.getBuddy().getId() == null) {
			employee.setBuddy(null);
		}

		if(!photoFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(photoFile.getOriginalFilename()));
			employee.setPhoto(fileName);
			//They are reference to the same object, the object after saving only merged with id. WTF ?
			Employee savedEmployee = employeeService.save(employee);
			String uploadDirectory = "employee-photos/" + savedEmployee.getId();
			FileUploadUtil.cleanDir(uploadDirectory);
			FileUploadUtil.saveFile(uploadDirectory, fileName, photoFile);
			emailService.sendEmailToPM(employee, type);
		} else {
			if(employee.getPhoto().isEmpty()) {
				employee.setPhoto(null);
			}
			employeeService.save(employee);
			emailService.sendEmailToPM(employee, type);
		}
		
		redirectAttributes.addFlashAttribute("message", "The employee has been save successfully.");
		return "redirect:/employees";
	}
	
	@GetMapping("delete/{id}")
	public String deleteEmployee(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			employeeService.detete(id);
			redirectAttributes.addFlashAttribute("message", "The employee with ID "  + id + " has been delete successfully.");
		} catch (EmployeeNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", "Could not find any employee to delete.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something is wrong! Please try again.");
		}
		System.out.println("DELETE EMPLOYEE");
		String employeeImagesDir = "employee-photos/" + id;
		FileUploadUtil.removeDir(employeeImagesDir);
		return "redirect:/employees";
	}
	
	@GetMapping("{id}/notes")
	public String getAllNotesForEmployee(@PathVariable("id") Integer id, Model model) {
		List<Note> noteList = noteService.listAllNoteByEmployee(id);
		model.addAttribute("nodeList", noteList);
		return "notes/notes";
	}
	
	@GetMapping("{employeeId}delete/{id}")
	public String deleteNotes(@PathVariable("id") Integer id, @PathVariable("employeeId") Integer employeeId) {
		return "redirect:/{employeeId}/notes";
	}
}
