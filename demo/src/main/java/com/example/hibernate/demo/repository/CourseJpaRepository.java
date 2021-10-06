package com.example.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseJpaRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	CourseRepository courseRepository;
	/*
	 * Find course by id
	 */
	public Course findById(int id) {
		return entityManager.find(Course.class, id);
	}

	/*
	 * Delete course by id
	 */
	public void deleteById(int id) {
		Course course = this.findById(id);
		entityManager.remove(course);
	}

	/*
	 * Insert and update course
	 */
	public void save(Course course) {
		if(this.findById(course.getId()) == null ) {
			entityManager.persist(course);
		}else {
			entityManager.merge(course);
		}
	}

	/*
	 * flush and persist, track entity and pushes changes in the DB
	 */
	public void persistenceWithEntityManager() {
		Course course1 = new Course("ML");
		entityManager.persist(course1);
		entityManager.flush();
		
		course1.setName("ML updated");
		entityManager.flush();
		
		Course course2 = new Course("AI");
		entityManager.persist(course2);
		entityManager.flush();
		
		
		//detach() does not track the entity and not pushes changes in db.
		entityManager.detach(course2);
		//entityManager.clear();     //this method is used to clear the EM of all the entities it is tracking. 
									 //Can be used in place of detach on each entity. 
		
		
		entityManager.refresh(course1); //would fetch the value from the DB.
		
		course2.setName("AI updated");
		entityManager.flush();	
	}
	
	public void createUpdateWithEntityManager() {
		Course course1 = findById(102);
		course1.setName("Engineering updated");
	}
	
	public void jpql_query() {
		TypedQuery<Course> query = entityManager.createQuery("Select c From Course c", Course.class);
		List<Course> courses = query.getResultList();
		logger.info("JPQLQuery : Select c from course : {}", courses);
	}
	
	public void jpql_named_query() {
		TypedQuery<Course> query = entityManager.createNamedQuery("query_all_courses", Course.class);
		List<Course> courses = query.getResultList();
		logger.info("NamedQuery: Select c from course : {}", courses);
	}
	
	public void jpql_where_named_query() {
		TypedQuery<Course> query = entityManager.createNamedQuery("query_all_where_courses", Course.class);
		List<Course> courses = query.getResultList();
		logger.info("NamedQueryWhereClause: Select c from course where : {}", courses);
	}
	
	public void native_query() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List courses = query.getResultList();
		logger.info("NativeQuery: Select c from course : {}", courses);
	}
	
	public void native_query_with_param() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 101);
		List courses = query.getResultList();
		logger.info("NativeQueryWithParam: Select c from course WHERE id : {}", courses);
	}
	
	public void native_query_with_param2() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		query.setParameter("id", 102);
		List courses = query.getResultList();
		logger.info("NativeQueryWithParam: Select c from course WHERE id : {}", courses);
	}
	
	public void update_native_query() {
		Query query = entityManager.createNativeQuery("UPDATE COURSE SET Last_updated_date = sysdate()", Course.class);
		int noOfRows = query.executeUpdate();
		logger.info("Update NativeQuery: noOfRows : {}", noOfRows);
	}
	
	public void addReviewsToCourse(int id) {
		Course course = entityManager.find(Course.class, id);
		logger.info("Course: {}", course.getReviews());
		
		Review review1 = new Review(4, "Nice");
		Review review2 = new Review(5, "Excellent Course");
		
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		entityManager.persist(review1);
		entityManager.persist(review2);
	}
	
	public void genericAddReviewsToCourse(int id, List<Review> reviews) {
		Course course = entityManager.find(Course.class, id);
		logger.info("Course: {}", course.getReviews());
		
		for(Review review:reviews) {
			course.addReview(review);
			review.setCourse(course);
			
			entityManager.persist(review);
		}
	}
	
	public void retrieveReviewToCourse(int id) {
		Course course = entityManager.find(Course.class, id);
		logger.info("Reviews :: {}", course.getReviews());	
	}
	
	public void retrieveCourseToReview(int id) {
		Review review = entityManager.find(Review.class, id);
		logger.info("Course :: {}", review.getCourse());	
	}
	
	//JPQL - ENTITIES
	
	public void getCourseWithNoStudents() {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.students is empty", Course.class);
		logger.info("Result : {}", query.getResultList());
	}
	
	public void getCourseWithMoreThan2Students() {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2", Course.class);
		logger.info("Result2 : {}", query.getResultList());
	}
	
	public void getCourseOrderByStudents() {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c order by size(c.students)", Course.class);
		logger.info("Result3 : {}", query.getResultList());
	}
	
	//JOINS - JPQL
	
	public void join() {
		Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> results = query.getResultList();
		for(Object[] result: results) {
			logger.info("Course : {} , Students : {}", result[0], result[1]);
		}
	}
	
	public void leftJoin() {
		Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> results = query.getResultList();
		for(Object[] result: results) {
			logger.info("Course : {} , Students : {}", result[0], result[1]);
		}
	}
	
	public void crossJoin() {
		Query query = entityManager.createQuery("Select c, s from Course c, Student s");
		List<Object[]> results = query.getResultList();
		for(Object[] result: results) {
			logger.info("Course : {} , Students : {}", result[0], result[1]);
		}
	}
	
	
	//CRITERIA QUERIES
	
	public void getAllCourses() {
		//Select c from Course c
		
		/*
		 * 1. Use CriteriaBuilder to create a Criteria query returing the expected result object 
		 */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		/*
		 * 2. Define roots for tables which are involved in the query
		 */
		Root<Course> courseRoot = cq.from(Course.class);
		
		/*
		 * 3.Define predicates etc using Criteria Builder
		 */
		
		/*
		 * 4.Add predicates etc to the Criteria Query
		 */
		
		/*
		 * 5.Build the TypedQuery using the Entity Manager and Criteria Query
		 */
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query: {} ", resultList);
	}
	
	public void getAllCoursesHavingCom() {
		//Select c from Course c
		
		/*
		 * 1. Use CriteriaBuilder to create a Criteria query returing the expected result object 
		 */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		/*
		 * 2. Define roots for tables which are involved in the query
		 */
		Root<Course> courseRoot = cq.from(Course.class);
		
		/*
		 * 3.Define predicates etc using Criteria Builder
		 */
		Predicate predicateCondition = cb.like(courseRoot.<String> get("name"), "%Com%");
		
		/*
		 * 4.Add predicates etc to the Criteria Query
		 */
		cq.where(predicateCondition);
		
		/*
		 * 5.Build the TypedQuery using the Entity Manager and Criteria Query
		 */
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query2 : {} ", resultList);
	}
	
	public void getCourseWhereStudentEmpty() {
		//Select c from Course c
		
		/*
		 * 1. Use CriteriaBuilder to create a Criteria query returing the expected result object 
		 */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		/*
		 * 2. Define roots for tables which are involved in the query
		 */
		Root<Course> courseRoot = cq.from(Course.class);
		
		/*
		 * 3.Define predicates etc using Criteria Builder
		 */
		Predicate predicateCondition = cb.isEmpty(courseRoot.<List> get("students"));
		
		/*
		 * 4.Add predicates etc to the Criteria Query
		 */
		cq.where(predicateCondition);
		
		/*
		 * 5.Build the TypedQuery using the Entity Manager and Criteria Query
		 */
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query3 : {} ", resultList);
	}
	
	public void getCourseStudentJoin() {
		//Select c from Course c JOIN c.students;
		
		/*
		 * 1. Use CriteriaBuilder to create a Criteria query returing the expected result object 
		 */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		/*
		 * 2. Define roots for tables which are involved in the query
		 */
		Root<Course> courseRoot = cq.from(Course.class);
		
		/*
		 * 3.Define predicates etc using Criteria Builder
		 */
		Join<Object, Object> join = courseRoot.join("students");
		
		/*
		 * 4.Add predicates etc to the Criteria Query
		 */
		
		/*
		 * 5.Build the TypedQuery using the Entity Manager and Criteria Query
		 */
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query4 : {} ", resultList);
	}
	
	public void getCourseStudentLeftJoin() {
		//Select c from Course c JOIN c.students;
		
		/*
		 * 1. Use CriteriaBuilder to create a Criteria query returing the expected result object 
		 */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		/*
		 * 2. Define roots for tables which are involved in the query
		 */
		Root<Course> courseRoot = cq.from(Course.class);
		
		/*
		 * 3.Define predicates etc using Criteria Builder
		 */
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		/*
		 * 4.Add predicates etc to the Criteria Query
		 */
		
		/*
		 * 5.Build the TypedQuery using the Entity Manager and Criteria Query
		 */
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query5 : {} ", resultList);
	}
	
	
	/*
	 * Spring Data JPA
	 */
	
	public void sortCourses() {
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		logger.info("Courses info :: {}", courseRepository.findAll(sort));
		
	}
	
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = courseRepository.findAll(pageRequest);
		logger.info("First Page :: {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = courseRepository.findAll(secondPageable);
		logger.info("Second Page :: {}", secondPage.getContent());
	}
	
	public void courseWithComInName(){
		logger.info("Course with Com :: {}", courseRepository.courseWithComInName());
	}
	
	public void courseWithNativeQuery(){
		logger.info("Course with Native Query :: {}", courseRepository.courseWithComInNameWithNativeQuery());
	}
	
	public void courseWithNamedQuery(){
		logger.info("Course with Named Query :: {}", courseRepository.courseWithNamedQuery());
	}
	
}
