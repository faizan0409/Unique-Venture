package com.ozone.model;

public class Employee extends BaseModel {
	private int companyId;
	private String employeeId;
	private String imageData;
	private String imageContentType;
	private String fullName;
	private String personalEmailId;
	private String organizationEmailId;
	private String contactNumber;
	private String password;
	private String address;
	private String pincode;
	private String state;
	private String city;
	private String country;
	private String contractBased;
	private String dateOfBirth;
	private String gender;
	private String highestQualification;
	private String bloodGroup;
	private String emergencyContactNo;
	private String joiningDate;
	private String experienceInMonth;
	private String skillSet;
	private int departmentId;
	private int roleId;
	private String pfAccount;
	private String medicalStatus;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", imageData=" + imageData + ", companyId=" + companyId
				+ ", imageContentType=" + imageContentType + ", fullName=" + fullName + ", personalEmailId="
				+ personalEmailId + ", organizationEmailId=" + organizationEmailId + ", contactNumber=" + contactNumber
				+ ", password=" + password + ", address=" + address + ", pincode=" + pincode + ", state=" + state
				+ ", city=" + city + ", country=" + country + ", contractBased=" + contractBased + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", highestQualification=" + highestQualification
				+ ", bloodGroup=" + bloodGroup + ", emergencyContactNo=" + emergencyContactNo + ", joiningDate="
				+ joiningDate + ", experienceInMonth=" + experienceInMonth + ", skillSet=" + skillSet
				+ ", departmentId=" + departmentId + ", roleId=" + roleId + ", pfAccount=" + pfAccount
				+ ", medicalStatus=" + medicalStatus + "]";
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getOrganizationEmailId() {
		return organizationEmailId;
	}

	public void setOrganizationEmailId(String organizationEmailId) {
		this.organizationEmailId = organizationEmailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContractBased() {
		return contractBased;
	}

	public void setContractBased(String contractBased) {
		this.contractBased = contractBased;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getExperienceInMonth() {
		return experienceInMonth;
	}

	public void setExperienceInMonth(String experienceInMonth) {
		this.experienceInMonth = experienceInMonth;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getPfAccount() {
		return pfAccount;
	}

	public void setPfAccount(String pfAccount) {
		this.pfAccount = pfAccount;
	}

	public String getMedicalStatus() {
		return medicalStatus;
	}

	public void setMedicalStatus(String medicalStatus) {
		this.medicalStatus = medicalStatus;
	}

}
