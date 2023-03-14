package com.ecomm.user.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="user_detail")
public class UserDetail implements Serializable{
	
	private static final long serialVersionUID = 8468924511515575309L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable=false, unique=true)
	private int id;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="email_id", nullable=false, unique=true)
	private String emailId;
	@Column(name="mobile_number", nullable=false)
	private String mobileNumber;	
	@OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleMaster roleMaster;
	@OneToMany(mappedBy="user", cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    private Set<UserAddress> userAddress;

}
