package com.cicerogb.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cicerogb.forum.controller.dto.DetailsTopicDto;
import com.cicerogb.forum.controller.dto.TopicDto;
import com.cicerogb.forum.controller.form.TopicForm;
import com.cicerogb.forum.controller.form.UpdateTopicForm;
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
	public ResponseEntity<DetailsTopicDto> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailsTopicDto(topic.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
