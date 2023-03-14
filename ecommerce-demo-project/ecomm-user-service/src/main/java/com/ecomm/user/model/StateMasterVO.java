package com.ecomm.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateMasterVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8469465456186235874L;
	private int id;
	private String stateName;
	private String stateCode;


}
