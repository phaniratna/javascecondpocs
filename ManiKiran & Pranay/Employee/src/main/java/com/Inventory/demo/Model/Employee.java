package com.Inventory.demo.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
/* @Embeddable */
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employee_id;
	private String employeeName;
	private String employee_email;
	private String employee_mobileno;
	private String employee_password;
	private String employee_role;
	@OneToMany(targetEntity = Assect.class, mappedBy = "employee", fetch = FetchType.LAZY)
	private List<Assect> assect;

	public Employee(long employee_id, String employeeName, String employee_email, String employee_mobileno,
			String employee_password, String employee_role, List<Assect> assect) {
		super();
		this.employee_id = employee_id;
		this.employeeName = employeeName;
		this.employee_email = employee_email;
		this.employee_mobileno = employee_mobileno;
		this.employee_password = employee_password;
		this.employee_role = employee_role;
		this.assect = assect;
	}

	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public String getEmployee_mobileno() {
		return employee_mobileno;
	}

	public void setEmployee_mobileno(String employee_mobileno) {
		this.employee_mobileno = employee_mobileno;
	}

	public String getEmployee_password() {
		return employee_password;
	}

	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}

	public String getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}

	public List<Assect> getAssect() {
		return assect;
	}

	public void setAssect(List<Assect> assect) {
		this.assect = assect;
	}

	public Employee() {
		super();
	}

}
