package com.ecomm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.StateMaster;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster, Integer>{
	
}
