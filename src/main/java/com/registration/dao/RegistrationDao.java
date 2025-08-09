package com.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.registration.dto.RegistrationDetails;
@Repository
public interface RegistrationDao extends JpaRepository<RegistrationDetails,Integer>{

	@Query(value = "select * from registration order by sl_no desc limit 1",nativeQuery=true)
	RegistrationDetails getlastRecord();
	@Query(value ="select * from registration where is_deleted = ?1",nativeQuery=true)
	List<RegistrationDetails>findByIsDeleted(int i);
}
