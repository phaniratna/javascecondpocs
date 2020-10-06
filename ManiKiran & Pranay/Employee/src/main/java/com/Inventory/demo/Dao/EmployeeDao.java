package com.Inventory.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Inventory.demo.Model.Employee;

@Repository


public interface EmployeeDao extends JpaRepository<Employee, Long> {
	  @Query("select u from Employee u where u.employeeName = ?1")
	public List<Employee> findbByEmployeeName(String employeeName);

}
