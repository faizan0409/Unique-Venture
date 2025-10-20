<!--  Author: Faizan
Title: User Profile
Date: 25 MARCH 202 -->
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet'>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<style>
body {
	margin-top: 20px;
	color: #1a202c;
	text-align: left;
	background-color: #e2e8f0;
}

.main-body {
	padding: 15px;
}

.card {
	box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0
		rgba(0, 0, 0, .06);
}

.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 0 solid rgba(0, 0, 0, .125);
	border-radius: .25rem;
}

.card-body {
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1rem;
}

.gutters-sm {
	margin-right: -8px;
	margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
	padding-right: 8px;
	padding-left: 8px;
}

.mb-3, .my-3 {
	margin-bottom: 1rem !important;
}

.bg-gray-300 {
	background-color: #e2e8f0;
}

.h-100 {
	height: 100% !important;
}

.shadow-none {
	box-shadow: none !important;
}

</style>
<script>
	function yesnoCheck() {
		if (document.getElementById('yesCheck').checked) {
			document.getElementById('ifYes').style.visibility = 'visible';
		} else
			document.getElementById('ifYes').style.visibility = 'hidden';

	}
</script>
<script type="text/javascript">
$(document).ready(function() {
	var data = eval('${employeelist}');
	console.log(data);
	document.getElementById("txtPassportNumber").value = data[0].fullName;
	document.getElementById("skillSet").value = data[0].skillSet;
	document.getElementById("dateOfBirth").value = data[0].dateOfBirth;
	document.getElementById("gender").value = data[0].gender;
	document.getElementById("bloodGroup").value = data[0].bloodGroup;
	document.getElementById("contactNumber").value = data[0].contactNumber;
	document.getElementById("personalEmailId").value = data[0].personalEmailId;
	document.getElementById("organizationEmailId").value = data[0].organizationEmailId;
	document.getElementById("emergencyContactNo").value = data[0].emergencyContactNo;
	document.getElementById("highestQualification").value = data[0].highestQualification;
	document.getElementById("experienceInMonth").value = data[0].experienceInMonth;
	document.getElementById("roleId").value = data[0].roleId;
	document.getElementById("departmentId").value = data[0].departmentId;
	document.getElementById("joiningDate").value = data[0].joiningDate;
	document.getElementById("contractBased").value = data[0].contractBased;
	document.getElementById("pincode").value = data[0].pincode;
	document.getElementById("city").value = data[0].city;
	document.getElementById("state").value = data[0].state;
	document.getElementById("country").value = data[0].country;
	document.getElementById("medicalStatus").value = data[0].medicalStatus;
	document.getElementById("yesno").value = data[0].pfAccount;	
	
	
});

function validate() {
	document.employeeform.submit();
}

	function EnableDisableTextBox(btnPassport) {
		var txtPassportNumber = document.getElementById("txtPassportNumber");
		if (btnPassport.value == "Edit") {
			txtPassportNumber.removeAttribute("disabled");
			skillSet.removeAttribute("disabled");
			dateOfBirth.removeAttribute("disabled");
			gender.removeAttribute("disabled", "disabled");
			address.removeAttribute("disabled", "disabled");
			emergencyContactNo.removeAttribute("disabled", "disabled");
			contactNumber.removeAttribute("disabled", "disabled");
			personalEmailId.removeAttribute("disabled", "disabled");
			emergencyContactNo.removeAttribute("disabled", "disabled");
			bloodGroup.removeAttribute("disabled", "disabled");
			highestQualification.removeAttribute("disabled", "disabled");
			highestQualification.removeAttribute("disabled", "disabled");
			experienceInMonth.removeAttribute("disabled", "disabled");
			pincode.removeAttribute("disabled", "disabled");
			city.removeAttribute("disabled", "disabled");
			state.removeAttribute("disabled", "disabled");
			country.removeAttribute("disabled", "disabled");
			medicalStatus.removeAttribute("disabled", "disabled");
			contractBased.removeAttribute("disabled", "disabled");
			yesCheck.removeAttribute("disabled", "disabled");
			noCheck.removeAttribute("disabled", "disabled");
		}

		else {
			txtPassportNumber.setAttribute("disabled", "disabled");
			skillSet.setAttribute("disabled", "disabled");
			dateOfBirth.setAttribute("disabled", "disabled");
			gender.setAttribute("disabled", "disabled");
			address.setAttribute("disabled", "disabled");
			emergencyContactNo.setAttribute("disabled", "disabled");
			contactNumber.setAttribute("disabled", "disabled");
			personalEmailId.setAttribute("disabled", "disabled");
			emergencyContactNo.setAttribute("disabled", "disabled");
			bloodGroup.setAttribute("disabled", "disabled");
			highestQualification.setAttribute("disabled", "disabled");
			experienceInMonth.setAttribute("disabled", "disabled");
			pincode.setAttribute("disabled", "disabled");
			city.setAttribute("disabled", "disabled");
			state.setAttribute("disabled", "disabled");
			country.setAttribute("disabled", "disabled");
			medicalStatus.setAttribute("disabled", "disabled");
			contractBased.setAttribute("disabled", "disabled");
			yesCheck.setAttribute("disabled", "disabled");
			noCheck.setAttribute("disabled", "disabled");

		}
	}
	
</script>
</head>
<body>
	<div class="container">
		<div class="main-body">

			<!-- Breadcrumb -->
			<nav aria-label="breadcrumb" class="main-breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="employeemanagement"
						style="text-decoration: none">Home</a></li>
					<li class="breadcrumb-item active" aria-current="page">User
						Profile</li>
				</ol>
			</nav>
			<!-- /Breadcrumb -->

			<div class="row gutters-sm">
				<div class="col-md-4 mb-3">
					<div class="card">

						<div class="card-body">
							<div class="d-flex flex-column align-items-center text-center">

								<img src="https://bootdey.com/img/Content/avatar/avatar7.png"
									alt="Admin" class="rounded-circle" width="150">
								<div class="mt-3">



									<h4>Sumit Aher</h4>
									<p class="text-secondary mb-1">Director</p>
									<p class="text-muted font-size-sm">
										@Ozone Systme Solution. <br> <input type="button"
											class="btn btn-info" value="Save"
											onclick="EnableDisableTextBox(this)" onclick="validate()" /> &nbsp;&nbsp;
										<button name="btnPassport" type="button" value="Edit"
											onclick="EnableDisableTextBox(this)" class="btn btn-primary">
											<i class="fa fa-edit"></i>
										</button>
								</div>
							</div>
						</div>
					</div>
					<div class="card mt-3">
						<ul class="list-group list-group-flush">
						<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<i class="material-icons text-info mr-2">Contact Info</i> <br><h6 class="mb-0">
								
									<!-- <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round"
										class="feather feather-instagram mr-2 icon-inline text-danger">
										<rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect>
										<path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path>
										<line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>
								 -->
									<i class="fa fa-phone fa-2x"></i>&nbsp; &nbsp; Contact Number
								</h6> <span class="text-secondary"><input type="text"
									name="contactNumber" id="contactNumber" autocomplete="off"
									onkeypress="return onlyNumberKey(event)" pattern="^\d{10}$"
									class="form-control" required disabled="disabled"></span>
							</li>
							<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
									<!-- <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round"
										class="feather feather-twitter mr-2 icon-inline text-info">
										<path
											d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path></svg>
									 -->
									<i class="fa fa-envelope fa-2x"></i>&nbsp; &nbsp; Personal
									Email
								</h6> <span class="text-secondary"><input type="email"
									name="personalEmailId" id="personalEmailId" autocomplete="off"
									class="form-control" disabled="disabled"
									pattern="[a-z0-9.-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
							</span>
							</li>
							<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
									
									<i class="fa fa-envelope fa-2x"></i>&nbsp; &nbsp; Company Email
								</h6> <span class="text-secondary"> <input type="email"
									name="organizationEmailId" autocomplete="off"
									class="form-control"
									pattern="[a-z0-9.-]+@[a-z0-9.-]+\.[a-z]{2,}$" required
									disabled="disabled"></span>
							</li>
							<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
									<!-- <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round"
										class="feather feather-github mr-2 icon-inline">
										<path
											d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>
									 -->
									<i class="fa fa-phone fa-2x"></i>&nbsp; &nbsp; Emergency
									Contact
								</h6> <span class="text-secondary"><input type="text"
									name="emergencyContactNo" id="emergencyContactNo"
									autocomplete="off" onkeypress="return onlyNumberKey(event)"
									pattern="^\d{10}$" class="form-control" disabled="disabled"></span>
							</li>
							
							
							<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
									<!-- <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round"
										class="feather feather-facebook mr-2 icon-inline text-primary">
										<path
											d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>
									 -->
									<i class="fa fa-map-marker fa-2x"></i>&nbsp; &nbsp; Location
								</h6> <span class="text-secondary"><textarea name="address"
										id="address" class="form-control" required disabled="disabled"></textarea>
							</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
							<div class="row">
								&nbsp;&nbsp; &nbsp; <i class="material-icons text-info mr-2">Personal
									Info</i><br>
								<div class="col-sm-3">

									<h6 class="mb-0">Full Name</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" id="txtPassportNumber"  name="txtPassportNumber" class="form-control"
										disabled="disabled" />
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Skill Sets</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<textarea name="skillSet" id="skillSet" disabled="disabled"
										required class="form-control"> </textarea>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Date Of Birth</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="date" name="dateOfBirth" id="dateOfBirth"
										autocomplete="off" onkeypress="return validation(event)"
										class="form-control" required disabled="disabled" />
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Gender</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<label><input type="radio" name="gender" id=""gender"">
										&nbsp;Male</label> &nbsp; &nbsp; <label><input type="radio"
										name="gender" id="gender"> &nbsp;Female</label>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Blood Group</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" name="bloodGroup" id="bloodGroup"
										autocomplete="off" class="form-control" disabled="disabled">
								</div>
							</div>
						</div>
					</div>
					<div class="row gutters-sm">
						<div class="col-sm-6 mb-3">
							<div class="card h-100">
								<div class="card-body">
									<h6 class="d-flex align-items-center mb-3">
										<i class="material-icons text-info mr-2">Educational Info</i>
									</h6>
									<small>Highest Qualification<input type="text"
										name="highestQualification" id="highestQualification"
										autocomplete="off" class="form-control" required
										disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 80%" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Experience<input type="text"
										name="experienceInMonth" id="experienceInMonth"
										autocomplete="off" class="form-control" disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 72%" aria-valuenow="72" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Role<input type="text" name="roleId"
										name="roleId" id=" Highest Qualification" autocomplete="off"
										class="form-control" disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 89%" aria-valuenow="89" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Department<input type="text" name="departmentId"
										id="departmentId" autocomplete="off" class="form-control"
										required disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 55%" aria-valuenow="55" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Joining Date<input type="text"
										name="joiningDate" id="joiningDate" autocomplete="off"
										class="form-control" required disabled="disabled"></small> <br>
									<small>Contract Base &nbsp;<label><input
											type="radio" name="contractBased" id="contractBased"
											disabled="disabled"> &nbsp;Yes</label> &nbsp; &nbsp; <label><input
											type="radio" name="contractBased" id="contractBased"
											disabled="disabled"> &nbsp;No</label>
									</small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 66%" aria-valuenow="66" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
								</div>
							</div>
						</div>
						<div class="col-sm-6 mb-3">
							<div class="card h-100">
								<div class="card-body">
									<h6 class="d-flex align-items-center mb-3">
										<i class="material-icons text-info mr-2">Native Info</i>

									</h6>
									<small> Zip Code<input type="text" name="pincode"
										id="pincode" autocomplete="off" class="form-control" required
										disabled="disabled" onkeypress="return onlyNumberKey(event)"
										pattern="^\d{10}$">
									</small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 80%" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>City<input type="text" name="city" id="city"
										autocomplete="off" class="form-control" required
										disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 72%" aria-valuenow="72" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>State<input type="text" name="state" id="state"
										autocomplete="off" class="form-control" required
										disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 89%" aria-valuenow="89" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Country<input type="text" name="country"
										id="country" autocomplete="off" class="form-control" required
										disabled="disabled"></small>
									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 55%" aria-valuenow="55" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
									<small>Medical Status<select name="medicalStatus"
										id="medicalStatus" style="width: 100%" class="form-control"
										required disabled="disabled">
											<option value="normal">Normal</option>
											<option value="handicap">Handicap</option>

									</select></small>  <small>PF Account Eligibility &nbsp; <input
										type="radio" onclick="javascript:yesnoCheck();" name="yesno"
										id="yesCheck" > &nbsp;&nbsp; Yes
										&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
										onclick="javascript:yesnoCheck();" name="yesno" id="noCheck"
										> &nbsp;&nbsp; No<br>
										<div id="ifYes" style="visibility: hidden">
											Enter PF Number&nbsp;&nbsp;<input type='text' id='yes'
												name='yes'>
										</div>



									</small>


									<!-- <div class="progress mb-3" style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar"
											style="width: 66%" aria-valuenow="66" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div> -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>