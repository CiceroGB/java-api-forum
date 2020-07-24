package com.cicerogb.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cicerogb.forum.model.Topic;
import com.cicerogb.forum.model.TopicStatus;

public class DetailsTopicDto {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime createDate;
	private String authorName;
	private TopicStatus status;
	private List<AnswerDto> answer;
	
	
	public DetailsTopicDto (Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message =topic.getMessage();
		this.createDate = topic.getCreateDate();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.answer = new ArrayList<>();
		this.answer.addAll(topic.getAnswers().stream().map(AnswerDto:: new).collect(Collectors.toList()));
		}


	public Long getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getMessage() {
		return message;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}


	public String getAuthorName() {
		return authorName;
	}


	public TopicStatus getStatus() {
		return status;
	}


	public List<AnswerDto> getAnswer() {
		return answer;
	}

	
}
