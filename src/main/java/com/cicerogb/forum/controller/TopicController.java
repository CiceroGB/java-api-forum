package com.cicerogb.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cicerogb.forum.controller.dto.DetailsTopicDto;
import com.cicerogb.forum.controller.dto.TopicDto;
import com.cicerogb.forum.controller.form.TopicForm;
import com.cicerogb.forum.model.Topic;
import com.cicerogb.forum.repository.CourseRepository;
import com.cicerogb.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public List<TopicDto> list(String courseName) {

		if (courseName == null) {

			List<Topic> topics = topicRepository.findAll();
			return TopicDto.converter(topics);
		} else {

			List<Topic> topics = topicRepository.findByCourseName(courseName);
			return TopicDto.converter(topics);
		}

	}

	@PostMapping
	public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));

	}

	@GetMapping("/{id}")
	public DetailsTopicDto detail(@PathVariable Long id) {
		Topic topic = topicRepository.getOne(id);
		return new DetailsTopicDto(topic);

	}
}
