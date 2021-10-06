package com.example.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee{
	
	private BigDecimal hourlyWages;
	
	public PartTimeEmployee(String name, String company_code, BigDecimal hourlyWages) {
		super(name, company_code);
		this.hourlyWages = hourlyWages;
	}
	
	public PartTimeEmployee() {	
		super();
	}

	@Override
	public String toString() {
		return "PartTimeEmployee [name=" + name + ", company_code=" + company_code + ", hourlyWages=" + hourlyWages + "]";
	}
}
