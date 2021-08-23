package com.example.clip.model.error;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClipErrorMessage implements Serializable {

	
	private static final long serialVersionUID = 3966059804760243906L;
	
	private String developerMessage;
	private String message;
	private int httpStatus;

	
	public ClipErrorMessage() {}
	
	public ClipErrorMessage(String developerMessage, String message, int httpStatus) {
		this.developerMessage= developerMessage;
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
