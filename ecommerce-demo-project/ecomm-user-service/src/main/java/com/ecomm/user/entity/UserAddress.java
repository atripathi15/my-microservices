package com.ecomm.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "address_line1", nullable = false)
	private String addressLine1;
	@Column(name = "address_line2", nullable = false, unique = true)
	private String addressLine2;
	@Column(name = "pincode", nullable = false)
	private String pinCode;
	@OneToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private CityMaster city;
	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private StateMaster state;
	@OneToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private CountryMaster country;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserDetail user;
}
