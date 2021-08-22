package com.example.clip.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClipConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}
