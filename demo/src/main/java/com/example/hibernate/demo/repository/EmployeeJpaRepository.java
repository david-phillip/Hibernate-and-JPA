package com.example.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.demo.entity.Employee;
import com.example.hibernate.demo.entity.FullTimeEmployee;
import com.example.hibernate.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeJpaRepository {
	
	@Autowired
	EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Only for inheritance strategy based(single, joined, table per class)
//	public List<Employee> retriveAllEmployees() {
//		List<Employee> empList = entityManager.createQuery("Select e from Employee e", Employee.class).getResultList();
//		logger.info("empList : {} ", empList);
//		return empList;
//	}
	
	public List<FullTimeEmployee> retriveFullTimeEmployees() {
		List<FullTimeEmployee> empList = entityManager.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
		logger.info("empList : {} ", empList);
		return empList;
	}
	
	public List<PartTimeEmployee> retrivePartTimeEmployees() {
		List<PartTimeEmployee> empList = entityManager.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
		logger.info("empList : {} ", empList);
		return empList;
	}
	
	public void insertEmployee(Employee employee) {
		entityManager.persist(employee);
	}

}
