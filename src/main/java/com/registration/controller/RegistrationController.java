package com.registration.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.dto.RegistrationDetails;
import com.registration.service.RegistrationService;
@RestController
@CrossOrigin("*")
@RequestMapping("/menu")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	
    @PostMapping(value="/test",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testRequest (@RequestBody String data){
    	String msg = "";
    	System.out.println(data.toString());
    	
    	try{
    		JSONObject jsonData = new JSONObject(data);
    		String name =jsonData.getString("name");
    		Integer roll = jsonData.getInt("roll");
    		String address =jsonData.getString("address");
    		String phone =jsonData.getString("phone");
    		String adhaar =jsonData.getString("adhaar");
    		
    		RegistrationDetails lastRecord = null;
    		try {
    			lastRecord = registrationService.findLastObject();
    		}catch(Exception e){
    			 System.out.println("exception occured");
    		}
    		if(lastRecord ==null) {
    			lastRecord =new RegistrationDetails();
    			lastRecord.setSlNo(0);
    		
    		}
    		System.out.println(lastRecord);
    		
    		RegistrationDetails  details =new RegistrationDetails();
    		
			System.out.println(details.toString());
			
    		details.setSlNo(lastRecord.getSlNo()+1);
    		details.setName(name);
    		details.setRoll(roll);
    		details.setAddress(address);
    		details.setPhone(phone);
    		details.setAdhaar(adhaar);
    	
    		System.out.println(details.toString());
    		
    		RegistrationDetails savedDetails =registrationService.saveDetails(details);
    		msg="success";
    		
    	} catch(Exception e) {
    		
    		e.printStackTrace();
    		msg="error";
    	}
    	JSONObject response =new JSONObject();
    	response.put("data",msg);
    	
		return ResponseEntity.ok(response.toString());
    }
    @GetMapping("/registrationDetails")
    public ResponseEntity<?>getResgistrationDetails(){
    	List<RegistrationDetails>regList = new ArrayList<>();
     regList = registrationService.getAllRegistrationDetails();
    	return ResponseEntity.ok(regList);
    }
    @GetMapping("/deleteHardRecord")
	public ResponseEntity<?> deleteHardRecord(@RequestParam("rid") Integer rid){
		System.out.println(rid);
		String msg;
		try {
			RegistrationDetails tobeDeletedObj = registrationService.findRegistrationById(rid);
			
			RegistrationDetails deleteObj = registrationService.tobeDeletedObj(tobeDeletedObj);
		} catch (Exception e) {
			msg = "successfully deleted with id :"+rid;
		}
		JSONObject response = new JSONObject();
		response.put("msg", "success");
		 
		return ResponseEntity.ok(response);
	}
	@PostMapping("/softDelete")
	public ResponseEntity<?> softDelete(@RequestBody String data){
		System.out.println(data);
		JSONObject jsonData = new JSONObject(data);
		Integer rid = jsonData.getInt("id");
		String msg = "";
		JSONObject response = new JSONObject();
		
		try {
			RegistrationDetails tobeDeletedObj = registrationService.findRegistrationById(rid);
			RegistrationDetails deleteObj = registrationService.softDeleteRegistration(tobeDeletedObj);
			msg = "successfully deleted with id :"+deleteObj.getSlNo()+"and with name: "+deleteObj.getName();
			response.put("msg", "success");
		} catch (Exception e) {
			msg = "Record not available with the provided id:"+rid;
			response.put("msg", "error");
		}
	return ResponseEntity.ok(response.toString());
	}
	@PostMapping("/register")
	public ResponseEntity<?>updateRegister(@RequestBody String data){
		JSONObject jsonData =null;
		String msg ="";
		try {
			jsonData=new JSONObject(data);
			Integer id =jsonData.getInt("slNo");
			RegistrationDetails toBeupdateObject=registrationService.findRegistrationById(id);
			toBeupdateObject.setName(jsonData.getString("name"));
			toBeupdateObject.setRoll((int)jsonData.get("roll"));
			toBeupdateObject.setAddress(jsonData.getString("address"));
			toBeupdateObject.setAdhaar(jsonData.getString("adress"));
			toBeupdateObject.setPhone(jsonData.getString("phone"));
			
			RegistrationDetails updateRegister = registrationService.saveDetails(toBeupdateObject);
			
			msg ="success";
		}catch(Exception e) {
		   System.out.println("pls check");
		   e.printStackTrace();
		   msg ="error is here";
		}
		return ResponseEntity.ok(msg);
	}
	@PostMapping(value="/getUserById",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserById(@RequestBody String data){
		
		System.out.println(data);
		JSONObject jsonData = new JSONObject(data);
		Integer id = jsonData.getInt(data);
		
		RegistrationDetails registredUser = registrationService.findRegistrationById(id);
		
		JSONObject response = new JSONObject();
		response.put("slno", registredUser.getSlNo());
		response.put("name", registredUser.getName());
		response.put("roll", registredUser.getRoll());
		response.put("address", registredUser.getAddress());
		response.put("phone", registredUser.getPhone());
		response.put("adhaar", registredUser.getAdhaar());
		
		//System.out.println(response);
		
		return ResponseEntity.ok(response.toString());
		
	}	

}

