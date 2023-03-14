package com.ecomm.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityMasterVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5928159717345180895L;
	private int id;
	private String cityName;
	private String cityCode;
}
