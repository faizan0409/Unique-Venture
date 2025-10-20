package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_asset_details")
public class AssetDetails extends BaseModel{

	private String assetType;
	private String assetDescription;
	private String employeeId;
	
	public AssetDetails() {
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
