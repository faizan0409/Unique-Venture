package com.ozone.model;

public class Salary extends BaseModel {

	private int companyId;
	private String ctc;
	private String netSalary;
	private String basicSalary;
	private String houseRentAllowance;
	private String specialBenefits;
	private String monthlyBonus;
	private String otherCompensation;
	private String pf;
	private String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public String getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getHouseRentAllowance() {
		return houseRentAllowance;
	}

	public void setHouseRentAllowance(String houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}

	public String getSpecialBenefits() {
		return specialBenefits;
	}

	public void setSpecialBenefits(String specialBenefits) {
		this.specialBenefits = specialBenefits;
	}

	public String getMonthlyBonus() {
		return monthlyBonus;
	}

	public void setMonthlyBonus(String monthlyBonus) {
		this.monthlyBonus = monthlyBonus;
	}

	public String getOtherCompensation() {
		return otherCompensation;
	}

	public void setOtherCompensation(String otherCompensation) {
		this.otherCompensation = otherCompensation;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	private String grossSalary;

	@Override
	public String toString() {
		return "Salary [companyId=" + companyId + ",employeeId=" + employeeId + ", ctc=" + ctc + ", netSalary="
				+ netSalary + ", basicSalary=" + basicSalary + ", houseRentAllowance=" + houseRentAllowance
				+ ", specialBenefits=" + specialBenefits + ", monthlyBonus=" + monthlyBonus + ", otherCompensation="
				+ otherCompensation + ", pf=" + pf + ", grossSalary=" + grossSalary + "]";
	}

}
