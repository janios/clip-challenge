package com.example.clip.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ReportDto implements Serializable {

	private static final long serialVersionUID = 5071474716914612092L;
	
	private Long idUsuario;
	private String name;
	private int payments_sum;
	private long new_payments;
	private BigDecimal new_payments_amount;
	
}
