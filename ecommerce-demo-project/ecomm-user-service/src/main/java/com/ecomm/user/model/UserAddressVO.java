package com.ecomm.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8484642559125110518L;
	private int id;
	private String addressLine1;
	private String addressLine2;
	private String pinCode;
	private CityMasterVO city;
	private StateMasterVO state;
	private CountryMasterVO country;

}

