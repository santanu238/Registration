package com.registration.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.dao.RegistrationDao;
import com.registration.dto.RegistrationDetails;
import com.registration.service.RegistrationService;


@Service
public class RegistrationServiceImpl implements RegistrationService {
 @Autowired
 RegistrationDao registrationDao;
 
 
@Override
public RegistrationDetails saveDetails(RegistrationDetails details) {
	return registrationDao.save(details);
}


@Override
public RegistrationDetails findLastObject() {
	RegistrationDetails lastRecord =registrationDao.getlastRecord();
	return lastRecord;

}


@Override
public List<RegistrationDetails> getAllRegistrationDetails() {
	List<RegistrationDetails> regList= registrationDao.findAll();
	return regList;
}
@Override
public RegistrationDetails findRegistrationById(Integer rid) {
	return registrationDao.findById(rid).get();
}

@Override
public RegistrationDetails tobeDeletedObj(RegistrationDetails tobeDeletedObj) {
	registrationDao.delete(tobeDeletedObj);
	return tobeDeletedObj;
}


@Override
public RegistrationDetails softDeleteRegistration(RegistrationDetails tobeDeletedObj) {
	tobeDeletedObj.setIsdeleted(1);
	return registrationDao.save(tobeDeletedObj);
}


}


