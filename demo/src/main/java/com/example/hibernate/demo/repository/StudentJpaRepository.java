package com.example.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Passport;
import com.example.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentJpaRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	/*
	 * Find student by id
	 */
	public Student findById(int id) {
		return entityManager.find(Student.class, id);
	}

	/*
	 * Delete student by id
	 */
	public void deleteById(int id) {
		Student student = this.findById(id);
		entityManager.remove(student);
	}

	/*
	 * Insert and update student
	 */
	public void save(Student student) {
		if(this.findById(student.getId()) == null ) {
			entityManager.persist(student);
		}else {
			entityManager.merge(student);
		}
	}
	
	/*
	 * Insert and update student
	 */
	public void saveStudentWithPassport() {
		Passport passport = new Passport("K919122");
		entityManager.persist(passport);
		
		Student student2 = new Student("Jack");
		student2.setPassport(passport);
		entityManager.persist(student2);	
	}
	
	/*
	 * find Student and Passport Details with Eager Fetch
	 */
	public void findStudentAndPassportDetails(int id) {
		Student student = entityManager.find(Student.class, id);
		logger.info("Student -> {}", student);
		logger.info("Passport -> {}", student.getPassport());
	}
	
	/*
	 * find Student and Passport Details with Lazy Fetch
	 */
	public void findStudentAndPassportDetailsLazyFetch(int id) {
		Student student = entityManager.find(Student.class, id);
		logger.info("Student -> {}", student);
		logger.info("Passport -> {}", student.getPassport());
	}
	
	/*
	 * find Student and Passport Details (Bidirectional)
	 */
	public void findPassportAndStudentDetails(int id) {
		Passport passport = entityManager.find(Passport.class, id);
		logger.info("Passport -> {}", passport);
		logger.info("Student -> {}", passport.getStudent());
	}


	/*
	 * flush and persist, track entity and pushes changes in the DB
	 */
	public void persistenceWithEntityManager() {
//		Student student1 = new Student("ML");
//		entityManager.persist(student1);
//		entityManager.flush();
//		
//		student1.setName("ML updated");
//		entityManager.flush();
//		
//		Student student2 = new Student("AI");
//		entityManager.persist(student2);
//		entityManager.flush();
//		
//		
//		//detach() does not track the entity and not pushes changes in db.
//		entityManager.detach(student2);
//		//entityManager.clear();     //this method is used to clear the EM of all the entities it is tracking. 
//									 //Can be used in place of detach on each entity. 
//		
//		
//		entityManager.refresh(student1); //would fetch the value from the DB.
//		
//		student2.setName("AI updated");
//		entityManager.flush();	
	}
	
	/*
	 * ManyToMany Mapping
	 */
	public void retrieveStudentandCourses(int id) {
		Student student = entityManager.find(Student.class, id);
		logger.info("Student :: {}" , student);
		logger.info("Courses :: {}" , student.getCourses());
	}
	
	/*
	 * ManyToMany Mapping
	 */
	public void retrieveCoursesAndStudent(int id) {
		Course course = entityManager.find(Course.class, id);
		logger.info("Course :: {}" , course);
		logger.info("Students :: {}" , course.getStudents());
	}
	
	/*
	 * ManyToMany Mapping
	 */
	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourses(course);
		course.addStudents(student);
		
		entityManager.persist(student);
		entityManager.persist(course);
	}
}
