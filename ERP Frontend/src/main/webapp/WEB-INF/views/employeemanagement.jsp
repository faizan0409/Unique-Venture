
<!-- Author: Faizan
Title: Employee Management
Date:15 MARCH 2021  -->
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMP System</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="resources/js/jquery-1.12.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet'>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<link href="resources/css/companydashboard.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/jquery.dataTables.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/main1.css" rel="stylesheet" type="text/css">
<script src="resources/js/jquery.dataTables.js"></script>



<style>
.modal-dialog, .modal-content {
	height: 75%;
	width: 70%;
	margin-left: -12%;
	margin-top: 3%;
}

body {
	font-family: 'Roboto';
}

.btn-lg {
	width: 165px;
	padding: 300px 150px;
	size: 10px;
	border-radius: 15px;
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
<script>
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
function isNumberKey(evt,id){
	document.getElementById(id).innerHTML = "";
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row col-md-10.9 col-md-offset-2 custyle">
				<div class="" style="margin-top: 10%">
					<div class="">
						<h2 class="title">Employee Management</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form
								action="${pageContext.request.contextPath}/delete"
								method="post" name="deletform" id="deletform">
								<input type="text" id="employeeId" name="employeeId">
							</form:form>
						</div>
						<div id="employeeFormId" style="display: none">
							<form:form method="POST" name="employeeform"
								action="saveemployee" modelAttribute="myrole">
								<%--  <form:hidden path="companyId" id="companyId"/>  --%>
								<div class="col-2">
										<div class="input-group" style="display: none">
											<label class="label">Employee Id</label>
											<form:input class="input--style-4" path="employeeId"
												id="employeeInputId" onclick="cleardata()"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Employee Name*</label>
											<form:input class="input--style-4" path="fullName"
												id="fullName" onclick="cleardata()" autocomplete="off"
												onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2" style=" display: none;">
										<div class="input-group" >
											<label class="label">Company ID*</label>
											<form:input class="input--style-4" path="companyId"
												id="companyId" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Date Of Birth*</label>
											<form:input type="date" class="input--style-4" path="dateOfBirth"
												id="dateOfBirth" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="">Gender*</label>
											<div class="row">
												<form:radiobutton path="gender" name="male" value="male"
													checked="checked" id="male" />
												&nbsp;Male &nbsp; &nbsp;
												<form:radiobutton path="gender" name="female"
													value="female" id="female" />
												&nbsp; Female &nbsp; &nbsp;
											</div>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label"> Blood Group*</label>
											<form:input class="input--style-4" path="bloodGroup"
												id="bloodGroup" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Experience(InMonth)*</label>
											<form:input class="input--style-4" path="experienceInMonth"
												id="experienceInMonth" onclick="cleardata()"  autocomplete="off"
												onkeypress="return isNumberKey(event,'derror')" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Address*</label>
											<form:input class="input--style-4" path="address"  autocomplete="off"
												id="address" onclick="cleardata()" onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Company Email*</label>
											<form:input class="input--style-4" path="organizationEmailId"
												id="organizationEmailId" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Personal Email*</label>
											<form:input class="input--style-4" path="personalEmailId"
												id="personalEmailId" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Contact No*</label>
											<form:input class="input--style-4" path="contactNumber"
												id="contactNumber" onclick="cleardata()"  autocomplete="off"
											onkeypress="return isNumberKey(event,'derror')" onkeyup="cleardata()"
												maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Emergency Contact No</label>
											<form:input class="input--style-4" path="emergencyContactNo"
												id="emergencyContactNo" onclick="cleardata()"  autocomplete="off"
											onkeypress="return isNumberKey(event,'derror')" onkeyup="cleardata()"
												maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Pincode*</label>
											<form:input class="input--style-4" path="pincode"  autocomplete="off"
												id="pincode" onclick="cleardata()" onkeypress="return isNumberKey(event,'derror')"
												onkeyup="cleardata()" maxlength="6" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">City*</label>
											<form:input class="input--style-4" path="city" id="city"  autocomplete="off"
												onclick="cleardata()" onkeypress="return isAlpha(event,'derror')" onkeyup="remove_error('derror')" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">State*</label>
											<form:input class="input--style-4" path="state" id="state"  autocomplete="off"
												onclick="cleardata()" onkeypress="return isAlpha(event,'derror')" onkeyup="remove_error('derror')"  maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Country*</label>
											<form:input class="input--style-4" path="country"  autocomplete="off"
												id="country" onclick="cleardata()" onkeypress="return isAlpha(event,'derror')" onkeyup="remove_error('derror')" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Highest Qualification*</label>
											<form:input class="input--style-4"
												path="highestQualification" id="highestQualification"  autocomplete="off"
												onclick="cleardata()" onkeypress="return isAlpha(event,'derror')" onkeyup="remove_error('derror')"  maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Joining Date*</label>
											<form:input type="date" class="input--style-4" path="joiningDate"
												id="joiningDate" onclick="cleardata()"  autocomplete="off"
												onkeypress="cleardata()" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>

									<div class="col-2">
										<div class="input-group">
											<label class="">PF Account*</label>
											<div class="row">
												<form:radiobutton path="pfAccount" name="pfAccount"
													value="Y"  id="yesCheck"
													onclick="javascript:yesnoCheck();" />
												&nbsp;Yes &nbsp; &nbsp;
												<form:radiobutton path="pfAccount" name="contractBased" checked="checked"
													value="N" id="noCheck" onclick="javascript:yesnoCheck();" />
												&nbsp; No &nbsp; &nbsp;
												<div id="ifYes" style="visibility: hidden">
													Enter PF Number&nbsp;&nbsp;
													<form:input class="input--style-4" path="pfAccount"
														id="pfAccount" onclick="cleardata()" 
														onkeypress="cleardata()" onkeyup="cleardata()"
														maxlength="100" />
												</div>
											</div>
										</div>
									</div>

								</div>



								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="">Contract Base*</label>
											<div class="row">
												<form:radiobutton path="contractBased" name="contractBased"
													value="Y" id="yes" />
												&nbsp;Yes &nbsp; &nbsp;
												<form:radiobutton path="contractBased" name="contractBased"
													value="N" checked="checked" id="no" />
												&nbsp; No &nbsp; &nbsp;
											</div>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Department ID*</label>
											<form:select path="departmentId" name="departmentId" id="departmentId">
												<form:option value="0" >Select</form:option >
											<c:forEach items="${departments}" var="department">
												<option value="${department.id}">${department.name}</option>
											</c:forEach>
										</form:select>
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Role ID*</label>
										<form:select path="roleId" name="roleId" id="roleId">
												<form:option value="0" >Select</form:option >
											<c:forEach items="${roles}" var="role">
												<option value="${role.id}">${role.name}</option>
											</c:forEach>
										</form:select>	
										<span id=derror class=error_msg></span>
										</div>
									</div> 
									<div class="col-2">
										<div class="input-group">
											<label class="">Medical Status*</label>
											<div class="row">
												<form:select path="medicalStatus" name="medicalStatus" id="medicalStatus">
												<form:option value="">Select</form:option>
												<form:option value="normal">Normal</form:option>
												<form:option value="handicap">Handicap</form:option>
											</form:select>
											</div>
										</div>
									</div>

								</div>


								<div class="p-t-15">
									<button class="btn btn--radius-2 btn--blue" type="button"
										onclick="validate()" id="submitbtn">Submit</button>
									<button class="btn btn--radius-2 btn--blue" type="button"
										onclick="cancel()">Cancel</button>
								</div>
							</form:form>
						</div>
					</div>
					<div class="" id="employeeTableDivId">
						<button onclick="addemployee()"
							class="btn btn-primary btn-xm pull-right">Add Employee</button>
						<table class="table table-striped custab" id="employeeTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Sr No</th>
									<th>Employee Name</th>		
									<th>Company Email</th>
									<th>Contact No</th>
									<th>Experience</th>
									<th>Department ID</th>
									<th>Role ID</th>
									<th>Action</th>
									
									<!-- <th>CompanyID</th>
									<th>Employee ID</th>
									<th>Date Of Birth</th>
									<th>Gender</th>
									<th>Blood Group</th>
									
									<th>Address</th>	
									
									<th>Personal Email</th>
									
									<th>Emergency Contact No</th>
									<th>Pincode</th>
									<th>City</th>
									<th>State</th>
									<th>Country</th>
									<th>Highest Qualification</th>
									<th>Joining Date</th>	
									
									<th>PF Account</th>
									<th>Contract Based</th>
									<th>Medical Status</th> -->
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<script>
	$(document)
			.ready(
					function() {
						$('#menu_icon')
								.click(
										function() {
											if ($('.page-sidebar').hasClass(
													'expandit')) {
												$('.page-sidebar').addClass(
														'collapseit');
												$('.page-sidebar').removeClass(
														'expandit');
												$('.profile-info').addClass(
														'short-profile');
												$('.logo-area').addClass(
														'logo-icon');
												$('#main-content').addClass(
														'sidebar_shift');
												$('.menu-title').css("display",
														"none");
											} else {
												$('.page-sidebar').addClass(
														'expandit');
												$('.page-sidebar').removeClass(
														'collapseit');
												$('.profile-info').removeClass(
														'short-profile');
												$('.logo-area').removeClass(
														'logo-icon');
												$('#main-content').removeClass(
														'sidebar_shift');
												$('.menu-title').css("display",
														"inline-block");
											}
										});

						var data = eval('${employeelist}');

						var table = $('#employeeTable')
								.DataTable(
										{
											"dom" : '<lf<"table-responsive table_res_width"t>ip>',
											"autoWidth" : false,

											"aaData" : data,
											"columnDefs" : [ {
												"searchable" : true,
												"orderable" : false,
												"targets" : [ 0 ]
											}, ],

											"aoColumns" : [
													{
														"mData" : null
													},

													{
														"mData" : "fullName",
														"visible" : true,

													},
													{												
														"mData" : "organizationEmailId",
														"visible" : true,
													},
													{
														"mData" : "contactNumber",
														"visible" : true,
													},
													{
														"mData" : "experienceInMonth",
														"visible" : true,
													},
													{
														"mData" : "departmentId",
														"visible" : false,
													},
													{
														"mData" : "roleId",
														"visible" : false,
													},
													/* {
														"mData" : "companyId",
														"visible" : false

													},
													{
														"mData" : "dateOfBirth",
														"visible" : false

													},
													{
														"mData" : "gender",
														"visible" : true

													},
													{
														"mData" : "bloodGroup",
														"visible" : false,

													},
													
													{
														"mData" : "address",
														"visible" : false,
													},
													{
														"mData" : "personalEmailId",
														"visible" : false,
													},

													
													{
														"mData" : "emergencyContactNo",
														"visible" : false,
													},
													{
														"mData" : "pincode",
														"visible" : false,
													},
													{
														"mData" : "city",
														"visible" : false,
													},
													{
														"mData" : "state",
														"visible" : false,
													},
													{
														"mData" : "country",
														"visible" : false,
													},
													{
														"mData" : "highestQualification",
														"visible" : false,
													},
													{
														"mData" : "joiningDate",
														"visible" : false,
													},
													
													{
														"mData" : "pfAccount",
														"visible" : false,
													},
													{
														"mData" : "contractBased",
														"visible" : false,
													},
													{
														"mData" : "medicalStatus",
														"visible" : false,
													},
 */
													{
														"defaultContent" : "<div class='row' style='width:200px'><a id='editBtn' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-edit'></span> Edit</a>  &nbsp; <a class='btn btn-danger btn-xs' id='deleteBtn'><span class='glyphicon glyphicon-remove'></span> Delete</a></div>"
													} ],

											"paging" : true
										});
						// Here we create the index column in jquery datatable
						table.on('order.dt search.dt', function() {
							table.column(0, {
								search : 'applied',
								order : 'applied'
							}).nodes().each(function(cell, i) {
								cell.innerHTML = i + 1;
							});
						}).draw();
						/* $('#employeeTable tbody')
								.on(
										'click',
										'#viewBtn',
										function(e) {
											$("#employeeFormId").show();
											$("#employeeTableDivId").hide();
											$("#submitbtn").hide();
											
											var data = table.row(
													$(this).closest('tr'))
													.data();
											document
													.getElementById("employeeName").value = data[Object
													.keys(data)[10]];
											$("#employeeName").prop("readonly",
													true);
											document
													.getElementById("companyId").value = data[Object
													.keys(data)[1]];
											$("#companyId").prop("readonly",
													true);
											document
													.getElementById("employeeId").value = data[Object
													.keys(data)[7]];
											$("#employeeId").prop("readonly",
													true);
											document
													.getElementById("dateOfBirth").value = data[Object
													.keys(data)[21]];
											$("#dateOfBirth").prop("readonly",
													true);
											var gender = data[Object.keys(data)[22]];
											if (status == "true") {
												document.getElementById("male").checked = "checked";
											} else {
												document.getElementById("female").checked = "checked";

											}
											$("#gender").prop("readonly", true);
											document
													.getElementById("bloodGroup").value = data[Object
													.keys(data)[24]];
											$("#bloodGroup").prop("readonly",
													true);
											document
											.getElementById("skillSet").value = data[Object
											.keys(data)[28]];
									$("#skillSet").prop("readonly",
											true);
											document
													.getElementById("experienceInMonth").value = data[Object
													.keys(data)[27]];
											$("#experienceInMonth").prop(
													"readonly", true);
											document.getElementById("address").value = data[Object
													.keys(data)[15]];
											$("#address")
													.prop("readonly", true);
											document
													.getElementById("organizationEmailId").value = data[Object
													.keys(data)[11]];
											$("#organizationEmailId").prop(
													"readonly", true);
											document
													.getElementById("personalEmailId").value = data[Object
													.keys(data)[12]];
											$("#personalEmailId").prop(
													"readonly", true);
											document
													.getElementById("contactNumber").value = data[Object
													.keys(data)[13]];
											$("#contactNumber").prop(
													"readonly", true);
											document
													.getElementById("emergencyContactNo").value = data[Object
													.keys(data)[25]];
											$("#emergencyContactNo").prop(
													"readonly", true);

											document.getElementById("pincode").value = data[Object
													.keys(data)[16]];
											$("#pincode")
													.prop("readonly", true);
											document.getElementById("city").value = data[Object
													.keys(data)[18]];
											$("#city").prop("readonly", true);
											document.getElementById("state").value = data[Object
													.keys(data)[17]];
											$("#state").prop("readonly", true);
											document.getElementById("country").value = data[Object
													.keys(data)[19]];
											$("#country")
													.prop("readonly", true);
											document
													.getElementById("highestQualification").value = data[Object
													.keys(data)[23]];
											$("#highestQualification").prop(
													"readonly", true);
											document
													.getElementById("joiningDate").value = data[Object
													.keys(data)[26]];
											$("#joiningDate").prop("readonly",
													true);
											document
													.getElementById("departmentId").value = data[Object
													.keys(data)[29]];
											$("#departmentId").prop("readonly",
													true);
											document.getElementById("roleId").value = data[Object
													.keys(data)[30]];
											$("#roleId").prop("readonly", true);
											var pfAccount = data[Object.keys(data)[31]];
											if (status == "true") {
												document.getElementById("yesCheck").checked = "checked";
											} else {
												document.getElementById("nocheck").checked = "checked";

											}
											$("#pfAccount").prop("readonly",
													true);
											var contractBased = data[Object.keys(data)[2]];
											if (status == "true") {
												document.getElementById("yes").checked = "checked";
											} else {
												document.getElementById("no").checked = "checked";

											}
											$("#contractBased").prop(
													"readonly", true);
											var medicalStatus = data[Object.keys(data)[32]];
											if (status == "true") {
												document.getElementById("normal").checked = "checked";
											} else {
												document.getElementById("handicap").checked = "checked";

											}
											$("#medicalStatus").prop(
													"readonly", true);

										}); */
						$('#employeeTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete The Employee!");
											if (result) {
												var data = table.row(
														$(this).closest('tr'))
														.data();
												var employeeId = data.employeeId;

												document
														.getElementById("employeeId").value = employeeId;
												$("#deletform").submit();
											}
										});

						$('#employeeTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#employeeFormId").show();
											$("#employeeTableDivId").hide();
											$("#employeeId").hide();
											$("#companyId").hide();
											//$("#roleId").hide();
											//$("#departmentId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();
											document.getElementById("employeeInputId").value = data.employeeId;
											document.getElementById("fullName").value = data.fullName;
											$("#employeeName").prop("readonly",true);
											document.getElementById("dateOfBirth").value = data.dateOfBirth;
											
											var gender = data.gender;
											if (gender == "true") {
												document.getElementById("male").checked = "checked";
											} else {
												document.getElementById("female").checked = "checked";

											}
											
											document.getElementById("experienceInMonth").value = data.experienceInMonth;
											
											document.getElementById("address").value = data.address;
											
											document.getElementById("bloodGroup").value = data.bloodGroup;
											
											document.getElementById("organizationEmailId").value = data.organizationEmailId;
											
											document.getElementById("personalEmailId").value = data.personalEmailId;
											
											document.getElementById("contactNumber").value = data.contactNumber;
											
											document.getElementById("emergencyContactNo").value = data.emergencyContactNo;
											
											document.getElementById("pincode").value = data.pincode
											
											document.getElementById("city").value = data.city;
											
											document.getElementById("state").value = data.state;
											
											document.getElementById("country").value = data.country;
											
											document.getElementById("highestQualification").value = data.highestQualification;
											
											document.getElementById("joiningDate").value = data.joiningDate;
											var pfAccount = data.pfAccount;
											if (status == "Y") {
												document.getElementById("yes").checked = "checked";
											} else {
												document.getElementById("no").checked = "checked";

											}
											var contractBased = data.contractBased;
											
											/* var medicalStatus = data.medicalStatus;
											if (medicalStatus == "normal") {
												document.getElementById("normal").checked = "checked";
											} else {
												document.getElementById("handicap").checked = "checked";

											} */
											$('#departmentId option[value='+data.departmentId+']').prop('selected', true);
											$('#roleId option[value='+data.roleId+']').prop('selected', true);
											$('#medicalStatus option[value='+data.medicalStatus+']').prop('selected', true);
											
										});

					});

	function addemployee() {
		$("#employeeFormId").show();
		$("#employeeTableDivId").hide();
	}

	function cancel() {
		$("#employeeFormId").hide();
		$("#employeeTableDivId").show();
	}

	function validate() {
		var name =/^[0-9*#+@+&+$_+%+!+]+$/;  
		var email=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{0,9})$/;
		var mob=/^[1-9]{1}[0-9]{9}$/;
		
		if (document.getElementById("fullName").value == "") {
			alert("Employee Name should not be empty..!");
			document.getElementById("fullName").focus();
		}else if (document.getElementById("fullName").value.match(name)) {
			alert ("Employee name should not be special characters..!");
			document.getElementById("fullName").focus();
		}else if (document.getElementById("organizationEmailId").value == "") {
			alert(" Company Email Id should not be empty..!");
			document.getElementById("organizationEmailId").focus();
		}else if (email.test(document.getElementById("organizationEmailId").value) == false) {
			alert("Invalid Company Email Id ..!");
			document.getElementById("organizationEmailId").focus();
		}else if (document.getElementById("contactNumber").value == "") {
			alert("Contact No should not be empty..!");
			document.getElementById("contactNumber").focus();
		}else if (mob.test(document.getElementById("contactNumber").value) == false) {
			alert("Invalid Contact Number..!");
			document.getElementById("contactNumber").focus();
		}else if (document.getElementById("experienceInMonth").value == "") {
			alert("Experience in months field should not be empty..!");
			document.getElementById("experienceInMonth").focus();
		}else if (document.getElementById("dateOfBirth").value == "") {
			alert("Select Date Of Birth..!");
			document.getElementById("dateOfBirth").focus();
		}else if (document.getElementById("bloodGroup").value == "") {
			alert("Blood Group field should not be empty..!");
			document.getElementById("bloodGroup").focus();
		}else if (document.getElementById("address").value == "") {
			alert("Address should not be empty..!");
			document.getElementById("address").focus();
		}else if (document.getElementById("personalEmailId").value == "") {
			alert(" Personal Email Id should not be empty..!");
			document.getElementById("personalEmailId").focus();
		}else if (email.test(document.getElementById("personalEmailId").value) == false) {
			alert("Invalid Personal Email Id ..!");
			document.getElementById("personalEmailId").focus();
		}else if (mob.test(document.getElementById("emergencyContactNo").value) == false) {
			alert("Invalid Emergency Contact Number..!");
			document.getElementById("emergencyContactNo").focus();
		}else if (document.getElementById("pincode").value == "") {
			alert("Pincode should not be empty..!");
			document.getElementById("pincode").focus();
		}else if (document.getElementById("state").value == "") {
			alert("State should not be empty..!");
			document.getElementById("state").focus();
		}else if (document.getElementById("country").value == "") {
			alert("Country should not be empty..!");
			document.getElementById("country").focus();
		}else if (document.getElementById("state").value == "") {
			alert("State should not be empty..!");
			document.getElementById("state").focus();
		}else if (document.getElementById("highestQualification").value == "") {
			alert("Hightest Qualification field should not be empty..!");
			document.getElementById("highestQualification").focus();
		}else if (document.getElementById("joiningDate").value == "") {
			alert("Select date of joining..!");
			document.getElementById("joiningDate").focus();
		}else if (document.getElementById("medicalStatus").value == "") {
			alert("Select Medical Status..!");
			document.getElementById("medicalStatus").focus();
		}else if (document.getElementById("departmentId").value == "0") {
			alert("Select department Id..!");
			document.getElementById("departmentId").focus();
		}else if (document.getElementById("roleId").value == "0") {
			alert("Select role Id..!");
			document.getElementById("roleId").focus();
		}else {
			document.employeeform.submit();
		}
	}	
	

	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
