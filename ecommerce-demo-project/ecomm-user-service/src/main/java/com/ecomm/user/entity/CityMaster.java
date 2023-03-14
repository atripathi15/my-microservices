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
@Table(name = "city_master")
public class CityMaster implements Serializable {

	private static final long serialVersionUID = -837863992234899994L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "city_name", nullable = false)
	private String cityName;
	@Column(name = "city_code", nullable = false, unique = true)
	private String cityCode;

}
