package com.ecomm.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryMasterVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3538214360244345352L;
	private int id;
	private String countryName;
	private String countryCode;

}
