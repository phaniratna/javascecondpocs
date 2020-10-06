package com.ojas.obs.employmentdetails.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.employmentdetails.controller.EmploymentDetailsController;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.mapper.EmploymentDetailsRowMapper;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.util.QueryUtil;

@Repository
public class EmploymentDetailsDAOImpl implements EmploymentDetailsDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentDetailsController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Autowired
	private QueryUtil queryUtil;

	@Override
	@Transactional(rollbackFor = DataNotInsertedException.class)
	public boolean saveEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {

		
		int savedRows = 0;

		for (EmploymentDetails employmentObject : employmentDetailsList) {
		
			savedRows = jdbcTemplate.update(queryUtil.getQuery("INSERT_EMPLOYMENT_DETAILS_STMT"),
					employmentObject.getEmpId(),
					employmentObject.getEmpName(),
					employmentObject.getEmpSal(),employmentObject.getAddres(), employmentObject.getMobileNumber()); 
					
			
		if (savedRows > 0) {
			LOGGER.debug("Employee details record inserted succefully ");
			return true;
		}
		LOGGER.debug("Employee details record not inserted succefully ");
		
	}
		return false;
	}
	

	@Override
	public boolean updateEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {
		
		int updatedRows=0;

		for (EmploymentDetails employmentObject : employmentDetailsList) {
			updatedRows = jdbcTemplate.update(queryUtil.getQuery("UPDATE_EMPLOYMENT_DETAILS_STMT"),
					employmentObject.getEmpName(),
					employmentObject.getEmpSal(),employmentObject.getAddres(), employmentObject.getMobileNumber(),employmentObject.getEmpId()); 
					
		}
		if (updatedRows > 0) {
			return true;
		}
		return false; 
	}

	@Override
	public boolean deleteEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {
		int deletedRows = 0;

		for (EmploymentDetails employmentObject : employmentDetailsList) {
			deletedRows = jdbcTemplate.update(queryUtil.getQuery("DELETE_EMPLOYMENT_DETAILS_STMT"), 
					employmentObject.getEmpId());
		}
		if (deletedRows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<EmploymentDetails> getAllEmploymentDetails() {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate
				.query(queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_STMT"), rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;
	}
	@Override
	public List<EmploymentDetails> getEmploymentDetailsById(Integer id) {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate
				.query(queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_ID_STMT"), new Object[] { id }, rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;
	}



	
}
