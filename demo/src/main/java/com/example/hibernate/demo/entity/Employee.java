package com.example.hibernate.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="EmployeeType")
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	
	protected String name;
	protected String company_code;

	protected Employee(String name, String company_code) {
		super();
		this.name = name;
		this.company_code = company_code;
	}

	protected Employee() {
		super();
	}	
}
