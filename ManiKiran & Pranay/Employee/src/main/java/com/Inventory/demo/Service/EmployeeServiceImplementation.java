package com.Inventory.demo.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Inventory.demo.Dao.EmployeeDao;
import com.Inventory.demo.Model.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	private static final Logger log = LogManager.getLogger(EmployeeServiceImplementation.class);

	@Autowired
	private EmployeeDao employeedao;

	@Override
	public ResponseEntity<?> addEmployee(Employee employee ) {

		if (employee.getEmployeeName().equals(null)
				|| employee.getEmployee_email().equals(null) || employee.getEmployee_mobileno().equals(null)) {
			String string = new String("No Data Found to Insert");
			log.error("Please don not Enter Null values");
			return new ResponseEntity<String>(string, HttpStatus.NOT_FOUND);

		}
		Employee employee2 = employeedao.save(employee);
		log.info("Employee Inserted SuccessFully");
		return new ResponseEntity<Employee>(employee2, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> deleteEmployeeByid(long employee_id) {
		Optional<Employee> optional = employeedao.findById(employee_id);
		if (optional.isPresent()) {
			employeedao.deleteById(employee_id);
			String string = new String("Employee Object Deleted Successfully");
			log.info("Deleted Employee Object");
			return new ResponseEntity<String>(string, HttpStatus.OK);
		}
		String string = new String("No Data Found with this employee_id : " + employee_id);
		log.error("No object Found");
		return new ResponseEntity<String>(string, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> list = employeedao.findAll();

		if (list != null) {
			log.info("List of Employees are Returned");
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		} else {
			String string = new String("No Data Found");
			log.error("No Employees Data Found");
			return new ResponseEntity<String>(string, HttpStatus.NO_CONTENT);
		}

	}

	@Override
	public ResponseEntity<?> findEmployeeById(long employee_id) {
		Optional<Employee> optional = employeedao.findById(employee_id);
		System.out.println("find bi id");
		if (optional.isPresent()) {
			log.info("Here the Data of Employee");
			return new ResponseEntity<>(optional, HttpStatus.FOUND);
		} else {
			String string = new String("No Data found with this Employee_id : " + employee_id);
			log.error("No Employee found with this id");
			new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
		}

		return null;
	}

	@Override
	public ResponseEntity<?> upadateEmployeeByid(long employee_id, Employee employee) {

		Optional<Employee> optional = employeedao.findById(employee_id);
		if (optional.isPresent()) {
			Employee employee1 = new Employee();
			employee1.setEmployee_id(employee_id);
			employee1.setEmployeeName(employee.getEmployeeName());
			employee1.setEmployee_password(employee.getEmployee_password());
			employee1.setEmployee_mobileno(employee.getEmployee_mobileno());
			employee1.setEmployee_email(employee.getEmployee_email());
			employee1.setEmployee_role(employee.getEmployee_role());
			Employee save = employeedao.save(employee1);
			log.info("Employee Updated successfully");
			return new ResponseEntity<Employee>(save, HttpStatus.UPGRADE_REQUIRED);
		} else {
			String string = new String("No Employee Found with this employee_id : " + employee_id);
			log.error("No Employee found with this employee_id");
			return new ResponseEntity<String>(string, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> findbByEmployeename(String employee_name) {
	List<Employee> employeeName = employeedao.findbByEmployeeName(employee_name);
	if(employeeName!=null) {
		return new ResponseEntity<>(employeeName,HttpStatus.FOUND);
	}else {
		String string = new String("No Employee Found with this Name : "+employee_name);
		return new ResponseEntity<String>(string,HttpStatus.NOT_FOUND);
		
	}
	
		
		
		 
	}
}
