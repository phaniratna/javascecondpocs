package com.ojas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	Page<Employee> findByNameContaining(String search, Pageable paging);

}
