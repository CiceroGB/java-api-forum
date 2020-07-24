package com.cicerogb.forum.controller.dto;

import java.time.LocalDateTime;

import com.cicerogb.forum.model.Answer;

public class AnswerDto {

	private Long id;
	private String message;
	private LocalDateTime createDate;
	private String authorName;

	public AnswerDto(Answer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.createDate = answer.getCreateDate();
		this.authorName = answer.getAuthor().getName();
	}

	public Long getId() {
		return id;
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

	
	
}
