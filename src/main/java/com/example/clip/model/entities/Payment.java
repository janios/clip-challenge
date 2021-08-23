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
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {

	@Id
	@SequenceGenerator(initialValue = 3, allocationSize = 1, name = "payment_sequence", sequenceName = "payment_sequence")
	@GeneratedValue(generator = "payment_sequence")
	private Long id;

	@Column(name = "amount")
	@Positive
	@NotNull
	private BigDecimal amount;

	@NotNull
	@Column(name = "user_id")
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "create_ts")
	private Date createTs;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "last_ts")
	private Date lastTs;

	@Column(name = "status")
	private String status;

}
