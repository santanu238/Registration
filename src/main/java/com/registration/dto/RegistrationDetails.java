package com.registration.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration")
public class RegistrationDetails {
@Id

@Column(name = "sl_no")
private Integer slNo;

@Column(name = "name")
private String name;

@Column(name ="roll")
private Integer roll;

@Column(name ="address")
private String address;

@Column(name ="phone")
private String phone;

@Column(name ="adhaar")
private String adhaar;

@Column(name="is_deleted")
private Integer isDelete;

public Integer getSlNo() {
	return slNo;
	
}


public void setSlNo(Integer slNo) {
	this.slNo = slNo;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public Integer getRoll() {
	return roll;
}


public void setRoll(Integer roll) {
	this.roll = roll;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}


public String getAdhaar() {
	return adhaar;
}


public void setAdhaar(String adhaar) {
	this.adhaar = adhaar;
}


@Override
public String toString() {
	return "RegistrationDetails [slNo=" + slNo + ", name=" + name + ", roll=" + roll + ", address=" + address
			+ ", phone=" + phone + ", adhaar=" + adhaar + "]";
}


public void setIsdeleted(int i) {
	
	
}

}
