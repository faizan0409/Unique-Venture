<!-- Author: Faizan
Title: Salary Management
Date: 22 April 2021  -->
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
<script>
function IsAlphaNumeric(e,id) {
	document.getElementById(id).innerHTML=" ";

	if (e.charCode != 0) {
	    var regex = /^[a-zA-Z0-9]+$/
	    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (!regex.test(key)) {
	      e.preventDefault();
	      return false;
	    }
	  }
}
</script>


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
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row col-md-10.9 col-md-offset-2 custyle">
				<div class="" style="margin-top: 10%">
					<div class="">
						<h2 class="title">Salary Management</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form
								action="${pageContext.request.contextPath}/deletesalary"
								method="post" name="deletform" id="deletform">
								<input type="text" id="employeeId" name="employeeId">
							</form:form>
						</div>
						<div id="salaryFormId" style="display: none">
							<form:form method="POST" name="salaryform" action="savesalary"
								modelAttribute="mysalary">
								<div class="col-2">
									<div class="input-group">
										<label class="label">Employee Id*</label>
										<form:select path="employeeId" name="employeeId"
											id="employeeInputId">
											<form:option value="">Select</form:option>
											<c:forEach items="${employees}" var="emp">
												<option value="${emp.employeeId}">${emp.fullName}</option>
											</c:forEach>
										</form:select>
										<span id=rerror class=error_msg></span>
									</div>
								</div>
								<div class="col-2" style="display: none;">
									<div class="input-group">
										<label class="label">Company ID*</label>
										<form:input class="input--style-4" path="companyId"
											id="companyId" onclick="cleardata()" onkeypress="cleardata()" />
										<span id=rerror class=error_msg></span>
									</div>
								</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">CTC*</label>
											<form:input class="input--style-4" path="ctc" id="ctc"
												onclick="cleardata()" 	onkeypress="return IsAlphaNumeric(event,'rerror')" onkeyup="remove_error('rerror')"  />
											<span id=rerror class=error_msg></span>
										</div>
									</div>

									<div class="col-2">
										<div class="input-group">
											<label class="label">Net Salary*</label>
											<form:input class="input--style-4" path="netSalary"
												id="netSalary" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'rerror')" onkeyup="remove_error('rerror')" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Basic Salary*</label>
											<form:input class="input--style-4" path="basicSalary"
												id="basicSalary" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'rerror')" onkeyup="remove_error('rerror')" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>

									<div class="col-2">
										<div class="input-group">
											<label class="label"> House Rent Allowance</label>
											<form:input class="input--style-4" path="houseRentAllowance"
												id="houseRentAllowance" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'derror')" onkeyup="remove_error('derror')"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Special Benefits </label>
											<form:input class="input--style-4" path="specialBenefits"
												id="specialBenefits" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'derror')" onkeyup="remove_error('derror')"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Monthly Bonus</label>
											<form:input class="input--style-4" path="monthlyBonus"
												id="monthlyBonus" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'derror')" onkeyup="remove_error('derror')"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Other Compensation</label>
											<form:input class="input--style-4" path="otherCompensation"
												id="otherCompensation" onclick="cleardata()"
												onkeypress="return IsAlphaNumeric(event,'derror')" onkeyup="remove_error('derror')"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">PF</label>
											<form:input class="input--style-4" path="pf" id="pf"
												onclick="cleardata()" onkeypress="return IsAlphaNumeric(event,'derror')"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
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
					<div class="" id="salaryTableDivId">
						<button onclick="addsalary()"
							class="btn btn-primary btn-xm pull-right">Add Salary</button>
						<table class="table table-striped custab" id="salaryTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Sr No</th>
									<th>Employee ID</th>
									<th>CTC</th>
									<th>Net Salary</th>
									<th>Basic Salary</th>
									<th>House Rent Allowance</th>
									<th>Special Benefits</th>
									<th>Monthly Bonus</th>
									<th>Other Compensation</th>
									<th>PF</th>
									<th>Action</th>
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

						var data = eval('${salarylist}');

						var table = $('#salaryTable')
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
														"mData" : "employeeId",
														"visible" : true,

													},

													{
														"mData" : "ctc",
														"visible" : true,

													},
													{
														"mData" : "netSalary",
														"visible" : true,
													},
													{
														"mData" : "basicSalary",
														"visible" : true,
													},
													{
														"mData" : "houseRentAllowance",
														"visible" : true,
													},
													{
														"mData" : "specialBenefits",
														"visible" : true,
													},
													{
														"mData" : "monthlyBonus",
														"visible" : true,
													},
													{
														"mData" : "otherCompensation",
														"visible" : true,
													},
													{
														"mData" : "pf",
														"visible" : true,
													},
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
						$('#salaryTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete The Salary Records!");
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

						$('#salaryTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#salaryFormId").show();
											$("#salaryTableDivId").hide();
											$("#employeeId").hide();
											$("#companyId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();
											$('#employeeInputId option[value='+data.employeeId+']').prop('selected', true);
											document.getElementById("ctc").value = data.ctc;
											document
													.getElementById("netSalary").value = data.netSalary;
											document
													.getElementById("basicSalary").value = data.basicSalary;

											document
													.getElementById("houseRentAllowance").value = data.houseRentAllowance;
											document
													.getElementById("specialBenefits").value = data.specialBenefits;
											document
													.getElementById("monthlyBonus").value = data.monthlyBonus;
											document
													.getElementById("otherCompensation").value = data.otherCompensation;
											document.getElementById("pf").value = data.pf;
										});

					});

	function addsalary() {
		$("#salaryFormId").show();
		$("#salaryTableDivId").hide();
	}

	function cancel() {
		$("#salaryFormId").hide();
		$("#salaryTableDivId").show();
	}

	function validate() {
		  
		if (document.getElementById("employeeInputId").value == "") {
			alert("select Employee Id..!");
			document.getElementById("employeeInputId").focus();
		} else if (document.getElementById("ctc").value == "") {
			alert("Enter CTC..!");
			document.getElementById("ctc").focus();
		}else if (document.getElementById("netSalary").value == "") {
			alert("Enter Net Salary..!");
			document.getElementById("netSalary").focus();
		}else if (document.getElementById("basicSalary").value == "") {
			alert("Enter Basic Salary ..!");
			document.getElementById("basicSalary").focus();
		}else {
			document.salaryform.submit();
		}
		
	}

	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
