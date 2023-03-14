package com.ecomm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.CountryMaster;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer>{
	
}
