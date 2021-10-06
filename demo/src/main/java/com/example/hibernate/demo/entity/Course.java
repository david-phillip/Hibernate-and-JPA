package com.example.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries(value = { 
						@NamedQuery(name = "query_all_courses", query = "SELECT c FROM Course c"),
						@NamedQuery(name = "query_all_where_courses", query = "SELECT c FROM Course c WHERE name like '%Com%' ") 
})

public class Course {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=true)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>(); 
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Course() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}
	
	public void removeReview(Review review) {
		reviews.remove(review);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
}
