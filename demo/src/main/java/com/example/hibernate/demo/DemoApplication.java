package com.example.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hibernate.demo.repository.CourseJpaRepository;
import com.example.hibernate.demo.repository.CourseRepository;
import com.example.hibernate.demo.repository.EmployeeJpaRepository;
import com.example.hibernate.demo.repository.StudentJpaRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseJpaRepository courseJpaRepository;
	
	@Autowired
	StudentJpaRepository studentJpaRepository;
	
	@Autowired
	EmployeeJpaRepository employeeJpaRepository;
	
	@Autowired
	CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		Course course = courseJpaRepository.findById(101);
//		logger.info("Course 101 => " + course);
//		courseJpaRepository.save(new Course("Machine Learning"));
		
		// Persist(), Flush(), Clear(), Deattach() Persistance with EM
		//courseJpaRepository.persistenceWithEntityManager();
		//courseJpaRepository.createUpdateWithEntityManager();
		
		// JPQL Named QUERIES
		//courseJpaRepository.jpql_query();
		//courseJpaRepository.jpql_named_query();
		//courseJpaRepository.jpql_where_named_query();
		
		//JPQL Native Queries
		//courseJpaRepository.native_query();
		//courseJpaRepository.native_query_with_param();
		//courseJpaRepository.native_query_with_param2();
		//courseJpaRepository.update_native_query();
		
		//OneToOneMapping methods
		//studentJpaRepository.saveStudentWithPassport();
		//studentJpaRepository.findStudentAndPassportDetails(301);
		//studentJpaRepository.findStudentAndPassportDetailsLazyFetch(301);
		
		//bidirectional fetch
		//studentJpaRepository.findPassportAndStudentDetails(202);
		
		//ManyToOne Mapping
		//courseJpaRepository.addReviewsToCourse(101);
		
//		List<Review> reviews = new ArrayList<Review>();
//		reviews.add(new Review(4, "Nice"));
//		reviews.add(new Review(5, "Excellent Course"));
//		
//		courseJpaRepository.genericAddReviewsToCourse(103, reviews);
		
		//courseJpaRepository.retrieveReviewToCourse(102);
		//courseJpaRepository.retrieveCourseToReview(403);
		
		//ManyToMany Mapping
		//studentJpaRepository.retrieveStudentandCourses(301);
		//studentJpaRepository.retrieveCoursesAndStudent(101);
		//studentJpaRepository.insertStudentAndCourse(new Student("Robin"), new Course("Microservices"));
		
		
		//JPA Inheritance Hiearchies Mapping
//		employeeJpaRepository.insertEmployee(new PartTimeEmployee("Jack", "Altran", new BigDecimal(50)));
//		employeeJpaRepository.insertEmployee(new FullTimeEmployee("Jill", "Altran", new BigDecimal(10000)));
		//employeeJpaRepository.retriveAllEmployees();
//		employeeJpaRepository.retriveFullTimeEmployees();
//		employeeJpaRepository.retrivePartTimeEmployees();
		
		//JPQL queries with entities
//		courseJpaRepository.getCourseWithNoStudents();
//		courseJpaRepository.getCourseWithMoreThan2Students();
//		courseJpaRepository.getCourseOrderByStudents();
		
		//JPQL-JOINs
//		courseJpaRepository.join();
//		courseJpaRepository.leftJoin();
//		courseJpaRepository.crossJoin();
		
		//CRITERIA Queries
//		courseJpaRepository.getAllCourses();
//		courseJpaRepository.getAllCoursesHavingCom();
//		courseJpaRepository.getCourseWhereStudentEmpty();
//		courseJpaRepository.getCourseStudentJoin();
//		courseJpaRepository.getCourseStudentLeftJoin();
		
		
		//Spring Data JPA
		//courseJpaRepository.sortCourses();
		//courseJpaRepository.pagination();
		//courseJpaRepository.courseWithComInName();
		courseJpaRepository.courseWithNativeQuery();
		courseJpaRepository.courseWithNamedQuery();
	}

}
