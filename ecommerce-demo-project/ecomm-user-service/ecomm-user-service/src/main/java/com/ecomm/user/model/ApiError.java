package com.ecomm.user.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private List<String> errors;
    public ApiError() {}

	public ApiError(List<String> errors) {
        this.errors = errors;
    }
}
