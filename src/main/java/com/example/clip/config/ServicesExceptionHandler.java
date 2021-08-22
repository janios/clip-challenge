package com.example.clip.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.clip.model.error.ClipErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ServicesExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	private ResponseEntity<ClipErrorMessage> handleException(Exception ex, WebRequest request) {
		log.error("Unhandled exception ", ex);
		return new ResponseEntity<>(
				new ClipErrorMessage(String.format("Unhandled exception. Details: %s", ex.getMessage()),
						ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = TransactionSystemException.class)
	private ResponseEntity<ClipErrorMessage> handleValidationException(TransactionSystemException e) {
		log.error("TransactionSystemException", e);
		if (e.getRootCause() instanceof ConstraintViolationException) {
			var constraintViolationException = (ConstraintViolationException) e.getRootCause();

			Map<String, String> validationMap = new HashMap<>();
			constraintViolationException.getConstraintViolations().stream()
					.forEach(val -> validationMap.put(val.getPropertyPath().toString(), val.getMessage()));

			return new ResponseEntity<>(
					new ClipErrorMessage(String.format("Validation Error. Details: %s", validationMap.toString()),
							e.getLocalizedMessage(), HttpStatus.CONFLICT.value()),
					HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(
				new ClipErrorMessage(String.format("Transaction Exception Details: %s", e.getMessage()), e.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR.value()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
