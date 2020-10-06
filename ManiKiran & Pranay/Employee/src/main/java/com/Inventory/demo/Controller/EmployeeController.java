package com.Inventory.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Inventory.demo.Model.Employee;
import com.Inventory.demo.Service.EmployeeServiceImplementation;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeServiceImplementation employeeServiceImplementation;

	

	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		return employeeServiceImplementation.addEmployee(employee);
		
		 
	}

	@GetMapping("/deleteEmployeeByid")
	public ResponseEntity<?> deleteEmployee(@RequestParam("employee_id") long employee_id) {
		return employeeServiceImplementation.deleteEmployeeByid(employee_id);
	}

	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAllEmployees() {
		return employeeServiceImplementation.getAllEmployees();

	}

	@GetMapping("/findEmployeeByid")
	public ResponseEntity<?> findEmployeeByid(@RequestParam("employee_id") long employee_id) {
		System.out.println("hello");
		return employeeServiceImplementation.findEmployeeById(employee_id);
	}

	@PutMapping("/updateEmployeeByid")
	public ResponseEntity<?> updateEmployeeByid(@RequestParam("employee_id") long employee_id,
			@RequestBody Employee employee) {
		return employeeServiceImplementation.upadateEmployeeByid(employee_id, employee);
	}

	@GetMapping("/findByEmployeename")
	public ResponseEntity<?> findByEmployeename(@RequestParam("employee_name") String employee_name) {
		System.out.println("hello");
		return employeeServiceImplementation.findbByEmployeename(employee_name);

	}

}
