package com.example.clip.model.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "disbursment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Disbursement {

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "disbursment_sequence", sequenceName = "disbursment_sequence")
	@GeneratedValue(generator = "disbursment_sequence")
	private Long id;

	@Column(name = "amount")
	@Positive
	@NotNull
	private BigDecimal amount;

	@NotNull
	@Column(name = "user_id")
	private Long userId;

	@NotNull
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "create_ts")
	private Date createTs;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "last_ts")
	private Date lastTs;

}
