package com.manage.employee.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.manage.employee.dto.EmployeeBasicInfoDto;
import com.manage.employee.dto.mapper.employee.EmployeeBasicInfoInterfaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manage.employee.dto.EmployeeProfileDto;
import com.manage.employeemanagementmodel.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findAll();
	Optional<Employee> findById(Integer id);
	
	@Query("SELECT em FROM Employee em ORDER BY em.firstName DESC")
	List<Employee> findAllAndSortByName();
	
	List<Employee> findByIdNot(Integer id);
	
	@Query("SELECT em FROM Employee em WHERE em.buddy.id = ?1")
	Set<Employee> findSubEmployeeByParentEmployee(Integer parentId);
	
	@Query("SELECT em FROM Employee em WHERE CONCAT(em.id, ' ', em.email, ' ', em.firstName, ' ', em.lastName) LIKE %?1%")
	public Page<Employee> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT em FROM Employee em WHERE CONCAT(em.id, ' ', em.email, ' ', em.firstName, ' ', em.lastName) LIKE %?1%")
	public Slice<Employee> findAllWithSlice(String keyword, Pageable pageable);
	
	@Query("SELECT em FROM Employee em WHERE em.email = :email")
	Employee getEmployeeByEmail(@Param("email") String email);
	
	boolean existsById(Integer id);
	//if one field is null, whole object return null
	@Query("SELECT NEW com.manage.employee.dto.EmployeeProfileDto(CONCAT(em.firstName, ' ', em.lastName), "
			+ "em.gender, em.hiringDate, em.department.name, "
			+ "CASE WHEN em.buddy IS NOT NULL THEN CONCAT(em.firstName, ' ', em.lastName) "
			+ "ELSE 'Has not have any buddy' "
			+ "END, "
			+ "CASE WHEN em.account IS NOT NULL THEN em.account.username "
			+ "ELSE 'Employee does not have account' "
			+ "END) "
			+ "FROM Employee em WHERE em.id = :employeeId")
	EmployeeProfileDto findEmployeeProfile(@Param("employeeId") Integer employeeId);

	EmployeeBasicInfoInterfaceDto findEmployeeById(Integer id);

	EmployeeBasicInfoDto findEmployeeByEmail(String email);
	
}
