package com.example.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hibernate.demo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	List<Course> findAll();
	
	//JPA Query
	@Query("Select c from Course c where name like '%Com%'")
	List<Course> courseWithComInName();
	
	//Native Query
	@Query(value="select * from Course c where c.name like '%Com%'", nativeQuery=true)
	List<Course> courseWithComInNameWithNativeQuery();
	
	//Named Query
	@Query(name="query_all_where_courses")
	List<Course> courseWithNamedQuery();
}
