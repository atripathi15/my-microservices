package com.ecomm.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.UserAddress;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Integer> {

	List<UserAddress> findByUserId(int userId);
	
	List<UserAddress> findByUserRoleMasterName(String name);

}
