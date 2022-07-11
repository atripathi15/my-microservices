package com.ecomm.user.model;


public class UserConfiguration {
	private int maximum;
	private int minimum;

	protected UserConfiguration() {

	}

	public UserConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getMinimum() {
		return minimum;
	}

}
