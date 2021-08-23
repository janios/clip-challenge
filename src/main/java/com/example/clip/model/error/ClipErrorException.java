package com.example.clip.model.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClipErrorException  extends Exception {

	private static final long serialVersionUID = 5971585368758423354L;
	
	private ClipErrorMessage errorMessage;
	
	public ClipErrorException(String message, String developerMessage, int httpStatus) {
		super(message);
		this.errorMessage = new ClipErrorMessage(message, developerMessage, httpStatus);
	}
	
	
}
