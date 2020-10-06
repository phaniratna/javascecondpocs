	package com.Inventory.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assect")
public class Assect {

	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */	private long assect_id;
	private String assect_type;
	private boolean assect_status;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid", referencedColumnName =  "employee_id")
	private Employee employee;

	public Assect() {
		super();
	}

	public Assect(long assect_id, String assect_type, boolean assect_status, Employee employee) {
		super();
		this.assect_id = assect_id;
		this.assect_type = assect_type;
		this.assect_status = assect_status;
		this.employee = employee;
	}

	public long getAssect_id() {
		return assect_id;
	}

	public void setAssect_id(long assect_id) {
		this.assect_id = assect_id;
	}

	public String getAssect_type() {
		return assect_type;
	}

	public void setAssect_type(String assect_type) {
		this.assect_type = assect_type;
	}

	public boolean getAssect_status() {
		return assect_status;
	}

	public void setAssect_status(boolean assect_status) {
		this.assect_status = assect_status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Assect [assect_id=" + assect_id + ", assect_type=" + assect_type + ", assect_status=" + assect_status
				+ ", employee=" + employee + "]";
	}

}
