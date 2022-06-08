package com.ecomm.user.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleMasterVO implements Serializable {
	private static final long serialVersionUID = -7326325251805709081L;
	@NotNull(message = "role id is mandatory")
	private Integer id;
	private String name;
}
