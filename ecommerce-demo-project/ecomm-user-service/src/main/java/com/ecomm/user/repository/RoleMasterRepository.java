package com.ecomm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.RoleMaster;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer>{

}
