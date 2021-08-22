package com.example.clip.model.error;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ClipErrorMessage implements Serializable {

	
	private static final long serialVersionUID = 3966059804760243906L;
	
	private String developerMessage;
	private String message;
	private int httpStatus;
	
}
