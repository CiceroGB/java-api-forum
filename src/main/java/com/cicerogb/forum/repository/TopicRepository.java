package com.cicerogb.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cicerogb.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	List<Topic> findByCourseName(String courseName);
	
}
