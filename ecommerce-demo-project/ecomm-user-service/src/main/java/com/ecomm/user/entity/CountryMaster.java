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
@Table(name = "country_master")
public class CountryMaster implements Serializable {

	private static final long serialVersionUID = 136688911648980854L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "country_name", nullable = false)
	private String countryName;
	@Column(name = "country_code", nullable = false, unique = true)
	private String countryCode;

}
