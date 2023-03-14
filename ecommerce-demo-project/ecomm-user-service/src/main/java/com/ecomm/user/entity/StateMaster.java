package com.ecomm.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state_master")
public class StateMaster implements Serializable {

	private static final long serialVersionUID = -4211822338983061143L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "state_name", nullable = false)
	private String stateName;
	@Column(name = "state_code", nullable = false, unique = true)
	private String stateCode;

}
