package com.example.clip.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DisbursementDto implements Serializable {

	private static final long serialVersionUID = 8316920319257045674L;
	
	private long id;
	private BigDecimal amount;
	private Long userId;
	private BigDecimal payment;	
	private Date createTs;
	private Date lastTs;
	private String status;
}
