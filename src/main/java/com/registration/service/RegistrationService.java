package com.registration.service;
import java.util.List;

import com.registration.dto.RegistrationDetails;
public interface RegistrationService {
	

	RegistrationDetails saveDetails(RegistrationDetails details);

	RegistrationDetails findLastObject();

	List<RegistrationDetails> getAllRegistrationDetails();

	RegistrationDetails findRegistrationById(Integer rid);

	RegistrationDetails tobeDeletedObj(RegistrationDetails tobeDeletedObj);

	RegistrationDetails softDeleteRegistration(RegistrationDetails tobeDeletedObj);


}
