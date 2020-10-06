package com.ojas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ojas.dao.EmployeeDao;
import com.ojas.exception.RecordNotFoundException;
import com.ojas.model.Employee;
import com.ojas.response.EmployeeResponseData;
import com.ojas.service.EmployeeService;

public class EmployeeServiceTestCases {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeDao employeeRepository;

	/*
	 * @Autowired private MockMvc mockMvc;
	 */

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createEmployee() throws SQLException {
		Employee employee = new Employee();
		employee.setName("nikhitha");
		employee.setCity("hyderabad");
		employee.setPhone(70328713);
		employee.setSalary(10000d);

		when(employeeRepository.save(employee)).thenReturn(employee);

		Employee createEmployee = employeeService.saveEmployee(employee);

		assertEquals(createEmployee, employee);

	}

	@Test
	public void getEmployee() throws SQLException, RecordNotFoundException {
		Employee employee = new Employee();
		employee.setId(20);
		employee.setName("nikhitha");
		employee.setCity("hyderabad");
		employee.setPhone(70328713);
		employee.setSalary(10000d);

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(employee));

		Employee getEmployee = employeeService.getEmployeeById(10);

		assertEquals(getEmployee, employee);

	}

	@Test
	public void getEmployee_throwRecordNotFoundException() throws SQLException, RecordNotFoundException {

		when(employeeRepository.findById(20)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.getEmployeeById(20));

	}

	@Test
	public void updateEmployee_NoRecordFoundException() throws SQLException, RecordNotFoundException {

		Employee employee1 = new Employee();
		employee1.setId(20);
		employee1.setName("shreshta");
		employee1.setCity("hyderabad");
		employee1.setPhone(70328713);
		employee1.setSalary(10000d);

		
		when(employeeRepository.findById(20)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.updateEmployee(employee1));

		
	}

	@Test
	public void updateEmployee() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(20);
		employee.setName("shreshta");
		employee.setCity("hyderabad");
		employee.setPhone(70328713);
		employee.setSalary(10000d);

		Optional<Employee> of = Optional.of(employee);

		when(employeeRepository.findById(20)).thenReturn(of);

		when(employeeRepository.save(employee)).thenReturn(employee);

		Employee updateEmployee = employeeService.updateEmployee(employee);

		assertEquals(updateEmployee, employee);

	}

	@Test
	public void deleteEmployee() throws SQLException, RecordNotFoundException {
		Employee employee = new Employee();
		employee.setId(20);
		employee.setName("nikhitha");
		employee.setCity("hyderabad");
		employee.setPhone(70328713);
		employee.setSalary(10000d);

		when(employeeRepository.findById(20)).thenReturn(Optional.ofNullable(employee));

		boolean deleteEmployeeById = employeeService.deleteEmployeeById(20);

		assertEquals(deleteEmployeeById, true);

	}

	@Test
	public void deleteEmployee_throwRecordNotFoundException() throws SQLException, RecordNotFoundException {

		when(employeeRepository.findById(20)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.deleteEmployeeById(20));

	}

	@Test
	public void getAllEmployeeDetails_PageNumber_PageSize() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(2l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "nikhitha", 2500d, "hyderabad", 703287));
		arrayList.add(new Employee(1, "nikhitha", 3500d, "hyderabad", 703287));
		employeeResponseData.setList(arrayList);

		Pageable paging = PageRequest.of(1, 3);

		Page<Employee> page = new PageImpl<>(arrayList);

		when(employeeRepository.findAll(paging)).thenReturn(page);

		EmployeeResponseData allEmployeeDetails = employeeService.getAllEmployeeDetails(1, 3);

		assertEquals(allEmployeeDetails.getList(), employeeResponseData.getList());

	}
	
	@Test
	public void getAllEmployeeDetails_PageNumber_PageSize_search() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(2l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "nikhitha", 2500d, "hyderabad", 70328));
		arrayList.add(new Employee(1, "nikhitha", 3500d, "hyderabad", 70328));
		employeeResponseData.setList(arrayList);

		Pageable paging = PageRequest.of(1, 3);

		Page<Employee> page = new PageImpl<>(arrayList);

		when(employeeRepository.findByNameContaining("nikhitha", paging)).thenReturn(page);

		EmployeeResponseData allEmployeeDetails = employeeService.getAllEmployeeDetails("nikhitha",1, 3);

		assertEquals(allEmployeeDetails.getList(), employeeResponseData.getList());

	}
	
	

}
