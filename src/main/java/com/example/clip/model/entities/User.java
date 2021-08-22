package com.example.clip.model.entities;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@SequenceGenerator(initialValue = 5, allocationSize = 1, name = "user_sequence", sequenceName = "user_sequence")
	@GeneratedValue(generator = "user_sequence")
	@Column(name = "id")
	private long id;
	@Column(name="name")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "create_ts")
	private Date createTs;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "last_ts")
	private Date lastTs;

}
