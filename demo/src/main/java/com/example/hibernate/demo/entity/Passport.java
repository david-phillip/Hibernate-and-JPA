package com.example.hibernate.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String passport_number;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Passport(String passport_number) {
		super();
		this.passport_number = passport_number;
	}
	
	public Passport() {
		super();
	}
	
	public String getPassport_number() {
		return passport_number;
	}
	
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", passport_number=" + passport_number + "]";
	}
	
	
}
