package com.cicerogb.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorDto> handle(MethodArgumentNotValidException exception) {
		List<ErrorDto> dto = new ArrayList<>();

		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorDto error = new ErrorDto(e.getField(), message);
			dto.add(error);
		});
		return dto;
	}

}
