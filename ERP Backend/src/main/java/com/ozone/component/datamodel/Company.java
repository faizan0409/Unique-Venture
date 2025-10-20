package com.ozone.component.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Author: Sumit Aher Date: 06-Mar-2021
 */

@Entity
@Table(name = "ozone_company")
public class Company extends BaseModel {

	private String name;
	private String ownerName;
	private String registrationDate;
	private String gstNumber;
	private String address;
	private String email;
	private String contactNumber;
	private String website;
	private String supportContactNumber;
	private String supportEmailId;
	private String supportEmailPassword;
	private String category;
	private String type;
	private String password;
	private String otp;
	private boolean otpValidationStatus;
	private Date otpTimestamp; 

	@Lob
	private String imageData;
	private String imageContentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSupportContactNumber() {
		return supportContactNumber;
	}

	public void setSupportContactNumber(String supportContactNumber) {
		this.supportContactNumber = supportContactNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSupportEmailId() {
		return supportEmailId;
	}

	public void setSupportEmailId(String supportEmailId) {
		this.supportEmailId = supportEmailId;
	}

	public String getSupportEmailPassword() {
		return supportEmailPassword;
	}

	public void setSupportEmailPassword(String supportEmailPassword) {
		this.supportEmailPassword = supportEmailPassword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean setOtpValidationStatus() {
		return otpValidationStatus;
	}

	public void setOtpValidationStatus(boolean otpValidationStatus) {
		this.otpValidationStatus = otpValidationStatus;
	}

	public Date getOtpTimestamp() {
		return otpTimestamp;
	}

	public void setOtpTimestamp(Date otpTimestamp) {
		this.otpTimestamp = otpTimestamp;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", ownerName=" + ownerName + ", registrationDate=" + registrationDate
				+ ", gstNumber=" + gstNumber + ", address=" + address + ", email=" + email + ", contactNumber="
				+ contactNumber + ", website=" + website + ", supportContactNumber=" + supportContactNumber
				+ ", supportEmailId=" + supportEmailId + ", supportEmailPassword=" + supportEmailPassword
				+ ", category=" + category + ", type=" + type + ", password=" + password + ", otp=" + otp
				+ ", otpValidationStatus=" + otpValidationStatus + ", otpTimestamp=" + otpTimestamp + ", imageData="
				+ imageData + ", imageContentType=" + imageContentType + "]";
	}
}
