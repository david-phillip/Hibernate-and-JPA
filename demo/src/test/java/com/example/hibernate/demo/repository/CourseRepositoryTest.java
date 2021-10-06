package com.example.hibernate.demo.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.hibernate.demo.DemoApplication;
import com.example.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseJpaRepository courseJpaRepository;

	@Test
	public void find_by_id_test() {
		Course course = courseJpaRepository.findById(101);
		assertEquals("Commerce", course.getName());
	}

	@Test
	@DirtiesContext
	public void delete_by_id_test() {
		courseJpaRepository.deleteById(102);
		assertNull(courseJpaRepository.findById(102));
	}

	@Test
	@DirtiesContext
	public void save_test() {
		Course course = courseJpaRepository.findById(101);
		assertEquals("Commerce", course.getName());

		course.setName("IOT");
		courseJpaRepository.save(course);

		Course course2 = courseJpaRepository.findById(101);
		assertEquals("IOT", course2.getName());

	}
}
