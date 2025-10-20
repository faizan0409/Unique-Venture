<!--  Author: Faizan
Title: User Profile
Date: 25 MARCH 202 -->
<%@page isELIgnored="false" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

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
function onlyNumberKey(evt) { 
    
    // Only ASCII charactar in that range allowed 
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) 
        return false; 
    return true; 
} 

	function myFunctionP() {
		var x = document.getElementById("s_email_password");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
	function isAlpha(e,id) {
		document.getElementById(id).innerHTML = "";
		if (e.charCode != 0) {
			 var regex = /^[a-zA-Z ]*$/;
		    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		    if (!regex.test(key)) {
		      e.preventDefault();
		      return false;
		    }
		  }
	}
	

</script>
<script type="text/javascript">
	function EnableDisableTextBox(btnedit) {
		var name = document.getElementById("name");
		if (btnedit.value == "Edit") {
			name.removeAttribute("disabled");
			document.getElementById('owner').removeAttribute("disabled");
			document.getElementById('regDate').removeAttribute("disabled");
			document.getElementById('website').removeAttribute("disabled", "disabled");
			document.getElementById('address').removeAttribute("disabled", "disabled");
			document.getElementById('contactno').removeAttribute("disabled", "disabled");
			document.getElementById('supportContact').removeAttribute("disabled", "disabled");
			document.getElementById('s_email').removeAttribute("disabled", "disabled");
			document.getElementById('s_email_password').removeAttribute("disabled", "disabled");
			document.getElementById('gst').removeAttribute("disabled", "disabled");
			document.getElementById('Companytype').removeAttribute("disabled", "disabled");
			document.getElementById('category').removeAttribute("disabled","disabled");
			document.getElementById('category1').removeAttribute("disabled","disabled");
			document.getElementById('email').removeAttribute("disabled","disabled");
		}

		else {
			name.setAttribute("disabled", "false");
			document.getElementById('owner').setAttribute("disabled", "false");
			document.getElementById('regDate').setAttribute("disabled", "false");
			document.getElementById('website').setAttribute("disabled", "false");
			document.getElementById('address').setAttribute("disabled", "false");
			document.getElementById('contactno').setAttribute("disabled", "false");
			document.getElementById('supportContact').setAttribute("disabled", "false");
			document.getElementById('s_email').setAttribute("disabled", "false");
			document.getElementById('s_email_password').setAttribute("disabled", "false");
			document.getElementById('gst').setAttribute("disabled", "false");
			document.getElementById('Companytype').setAttribute("disabled", "false");
			document.getElementById('category').setAttribute("disabled", "false");
			document.getElementById('category1').setAttribute("disabled", "false");
			document.getElementById('email').setAttribute("disabled", "false");
			
		}
	}
	
	
	function validate() {
		
		var email=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var mob=/^[1-9]{1}[0-9]{9}$/;
				
		if (document.getElementById("email").value == "") {
			alert("Company Email Id should not be empty..!");
			document.getElementById("email").focus();
		}else if (email.test(document.getElementById("email").value) == false) {
			alert("Invalid  Company Email Id ..!");
			document.getElementById("email").focus();
		}else if(document.getElementById("contactno").value == "") {
			alert(" Contact No should not be empty..!");
			document.getElementById("contactno").focus();
		}else if(document.getElementById("name").value == "") {
			alert("Company Name should not be empty..!");
			document.getElementById("name").focus();
		}else if(document.getElementById("owner").value == "") {
			alert("Owner Name Id should not be empty..!");
			document.getElementById("owner").focus();
		}else if(document.getElementById("regDate").value == "") {
			alert("Registration date should not be empty..!");
			document.getElementById("regDate").focus();
		}else if(document.getElementById("address").value == "") {
			alert("Address should not be empty..!");
			document.getElementById("address").focus();
		}else if(document.getElementById("Companytype").value == "") {
			alert("Select Company Type..!");
			document.getElementById("Companytype").focus();
		}else if (email.test(document.getElementById("s_email").value) == false) {
			alert("Invalid Supprot Email Id ..!");
			document.getElementById("s_email").focus();
		}else if (mob.test(document.getElementById("contactno").value) == false) {
			alert("Invalid Contact Number..!");
			document.getElementById("contactno").focus();
		}else if (mob.test(document.getElementById("supportContact").value) == false) {
			alert("Invalid Support Contact Number..!");
			document.getElementById("supportContact").focus();
		}
		 else {
			document.companyform.submit();
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
					<li class="breadcrumb-item"><a href="companydashboard"
						style="text-decoration: none">Home</a></li>
					<li class="breadcrumb-item active" aria-current="page">User
						Profile</li>
				</ol>
			</nav>
			<!-- /Breadcrumb -->

			<div class="row gutters-sm">
			<form:form method="POST" name="companyform" action="updatecompanyprofile"
								modelAttribute="myrole">
				<div class="col-md-4 mb-3">
					<div class="card">

						<div class="card-body">
							<div class="d-flex flex-column align-items-center text-center">

								<img src="https://bootdey.com/img/Content/avatar/avatar7.png"
									alt="Admin" class="rounded-circle" width="150">
								<div class="mt-3">
                  


									<h4>@Ozone System Solution.</h4>

									<p class="text-muted font-size-sm">
										<input type="button" class="btn btn-info" value="Save" id=save
											 onclick="validate()"  /> &nbsp;&nbsp;
										<button name="btnedit"  type="button" value="Edit"
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
								<h6 class="mb-0">
									<i class="material-icons text-info mr-2">Contact Info</i> <br>
									<i class="fa fa-envelope fa-2x"></i>&nbsp; &nbsp; Company Email
								</h6> <span class="text-secondary"><form:input  path="email" 
									name="email" id="email" autocomplete="off" class="form-control"
									pattern="[a-z0-9.-]+@[a-z0-9.-]+\.[a-z]{2,}$" disabled="true"/></span>
							</li>
							<li
								class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
								
									<i class="fa fa-phone fa-2x"></i>&nbsp; &nbsp; Contact Number
								</h6> <span class="text-secondary"><form:input type="text"
									name="contact" id="contactno" autocomplete="off" maxlength="10"
									onkeypress="return onlyNumberKey(event)" pattern="^\d{10}$" path="contactNumber"
									class="form-control" disabled="true"/></span>
							</li>
							<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
								<i class="fa fa-phone fa-2x"></i>&nbsp; &nbsp;Support Contact Number
								</h6> <span class="text-secondary"><form:input type="text" autocomplete="off"
									name="s_contact" id="supportContact" autocomplete="off" path="supportContactNumber"
									onkeypress="return onlyNumberKey(event)" pattern="^\d{10}$" maxlength="10"
									class="form-control" disabled="true"/></span>

							</li>
							<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
									<i class="fa fa-envelope fa-2x"></i>&nbsp; &nbsp; Support Email ID
								</h6> <span class="text-secondary"><form:input type="email" 
									name="s_email" id="s_email" autocomplete="off" path="supportEmailId"
									class="form-control" disabled="true"/> </span>

							</li>
							<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
								<h6 class="mb-0">
							    <i class="fa fa-key fa-2x"></i>&nbsp; &nbsp; Support Email
									Password
								</h6> <span class="text-secondary"><form:input type="password" maxlength="8"
									name="s_email_password" id="s_email_password" path="supportEmailPassword" autocomplete="off"
									onkeypress="return (event.charCode > 32 && 
                        			 event.charCode < 127)" 
									class="form-control" style="width: 100%" autocomplete="off"
									 disabled="true" />
									<span class="col-sm-6 form-group"
									style="margin-left: 85%; margin-top: -9%;"> <a 
									class="fa fa-eye" onclick="myFunctionP()"></a>
								</span> </span>
							</li>
						</ul>
					
					</div>
				</div>
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
						
							<div class="row">
								&nbsp;&nbsp; &nbsp; <i class="material-icons text-info mr-2">Company
									Info</i><br>
								<div class="col-sm-3">
								
									<h6 class="mb-0">Company Name</h6>
								</div>
							
								<div class="col-sm-9 text-secondary">
										<form:input type="text" id="name" name="name" path="name" autocomplete="off"
										class="form-control" disabled="true" />
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Owner Name</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<form:input type="text" name="owner" id="owner" autocomplete="off"
										pattern="^\S+(\s\S+)+$" path="ownerName"
										onkeypress="return (event.charCode > 64 && 
                          event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode > 31 && event.charCode < 33)"
										class="form-control" disabled="true"/>

								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Registration Date</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<form:input type="date" name="date" id="regDate" autocomplete="off" path="registrationDate"
										onkeypress="return validation(event)" class="form-control"
										 disabled="true"/>

								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Website</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<form:input type="text" name="website" id="website" path="website"
										class="form-control" autocomplete="off"
										pattern="[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$"
										disabled="true"/>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Company Address</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<form:textarea name="address" id="address" rows="3" path="address" autocomplete="off"
										class="form-control" disabled="true"/>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">GST Number</h6>
								</div>
								<div class="col-sm-9 text-secondary">
										<form:input type="text" name="gst" id="gst" minlength="15"
										maxlength="15" path="gstNumber"
										onkeypress="return (event.charCode > 47 &&
                          event.charCode < 58) || (event.charCode > 64 && event.charCode < 91) || (event.charCode > 96 && event.charCode < 123)"
										autocomplete="off" class="form-control" disabled="true"/>
								</div>
							</div>

							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Company</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<label><form:radiobutton name="category" id="category" path="category" disabled="true"/>
										&nbsp;GOV</label> &nbsp; &nbsp; <label><form:radiobutton 
										name="category" id="category1" path="category"  disabled="true"/> &nbsp;NON GOV</label>
								</div>
							</div>
						
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Company Type</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<form:select name="companytype" id="Companytype" path="type"
										class="form-control"  disabled="true">
										<form:option value="">Select</form:option>
										<form:option value="Small">Small(1-99)</form:option>
										<form:option value="Large">Large(100-999)</form:option>
										<form:option value="VeryLarge">Very Large(1000 Above)</form:option>
									</form:select>
								</div>
							</div>

					
						</div>
					</div>
					
				</div>
				</form:form>
			</div>
			
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function() {

	var data = eval('${companylist}');
	console.log(data);
	document.getElementById('name').value = data[0].name;
	document.getElementById('owner').value =data[0].ownerName ;
	document.getElementById('regDate').value =data[0].registrationDate ;
	document.getElementById('website').value =data[0].website ;
	document.getElementById('address').value =data[0].address ;
	document.getElementById('gst').value =data[0].gstNumber ;
	document.getElementById('category').value =data[0].category ;
	document.getElementById('Companytype').value =data[0].type ;
	document.getElementById('email').value =data[0].email ;
	document.getElementById('contactno').value =data[0].contactNumber ;
	document.getElementById('supportContact').value =data[0].supportContactNumber ;
	document.getElementById('s_email').value =data[0].supportEmailId ;
	document.getElementById('s_email_password').value =data[0].supportEmailPassword ;
	
 });
</script>
</html>