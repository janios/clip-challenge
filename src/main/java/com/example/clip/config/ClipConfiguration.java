package com.example.clip.config;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.entities.Payment;

@Configuration
public class ClipConfiguration {

	BigDecimal disbursmentFee = BigDecimal.valueOf(1.035);
	
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mm = new ModelMapper();

	    PropertyMap<Payment,PaymentDto> propertyMap = new PropertyMap<Payment,PaymentDto> (){
	        protected void configure() {
	            map().setId(source.getId());
	            map().setUserId(source.getUserId());
	            
	        }
	    };

	    mm.addMappings(propertyMap);
	    return mm;
	}
	

	public BigDecimal getDisbursmentFee() {
		return this.disbursmentFee;
		
	}
	
}
