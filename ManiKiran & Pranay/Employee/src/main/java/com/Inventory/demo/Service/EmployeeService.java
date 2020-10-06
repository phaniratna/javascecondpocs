package com.Inventory.demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Inventory.demo.Model.Employee;
@Service
public interface EmployeeService {
	
	public ResponseEntity<?> addEmployee(Employee employee);
	
	public ResponseEntity<?> deleteEmployeeByid(long employee_id);
	
	public ResponseEntity<?> getAllEmployees();
	
	public ResponseEntity<?> findEmployeeById(long employee_id);
	
	public ResponseEntity<?> upadateEmployeeByid(long employee_id,Employee employee);

	public ResponseEntity<?> findbByEmployeename(String employee_name);
}
