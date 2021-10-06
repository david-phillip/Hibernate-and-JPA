package com.example.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee{
	
	private BigDecimal salary;
	
	public FullTimeEmployee(String name, String company_code, BigDecimal salary) {
		super(name, company_code);
		this.salary = salary;
	}

	protected FullTimeEmployee() {
		super();
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [name=" + name + ", company_code=" + company_code + ", salary=" + salary + "]";
	}	
}
