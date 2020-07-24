package com.cicerogb.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cicerogb.forum.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findByName(String name);
	
}
