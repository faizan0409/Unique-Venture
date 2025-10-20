
<!-- Author: Faizan
Title: Team Management
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
						<h2 class="title">Project Management</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form action="${pageContext.request.contextPath}/deleteproject"
								method="post" name="deleteform" id="deleteform">
								<input type="text" id="projectId" name="projectId">
							</form:form>
						</div>
						<div id="projectFormId" style="display: none">
							<form:form method="POST" name="projectform" action="saveproject"
								modelAttribute="myproject">
								<%--  <form:hidden path="companyId" id="companyId"/>  --%>
								<div class="col-2" style="display: none">
										<div class="input-group">
											<label class="label">Project Id</label>
											<form:input class="input--style-4" path="projectId"
												id="projectInputId" value="0" onclick="cleardata()"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
								<div class="col-2">
										<div class="input-group">
											<label class="label">Project Title *</label>
											<form:input class="input--style-4" path="projectTitle"
												id="projectTitleId" autocomplete="off"
											onclick="cleardata()" onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">project Description </label>
											<form:input class="input--style-4" path="projectDescription"
												id="projectDescription" onclick="cleardata()" autocomplete="off"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									</div>
									<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Client *</label>
											<form:select path="clientId" name="clientId" id="clientId">
												<form:option value="" >Select</form:option >
											<c:forEach items="${clients}" var="client">
												<option value="${client.clientId}">${client.clientName}</option>
											</c:forEach>
										</form:select>
											<span id=derror class=error_msg></span>
										</div>
									</div>
								</div>	
								
								<div class="row row-space">
								<div class="col-2">
										<div class="input-group">
											<label class="label">Project Type *</label>
											<form:select path="projectType" name="projectType" id="projectType">
												<form:option value="">Select</form:option>
												<form:option value="Cost">Cost</form:option>
												<form:option value="Revenue">Revenue</form:option>
												</form:select>

											<span id=derror class=error_msg></span>
										</div>
									</div>
									</div>
									<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Select Manager </label>
											<form:select path="managerId" name="managerId" id="managerId">
												<form:option value="" >Select</form:option >
											<c:forEach items="${managers}" var="manager">
												<option value="${manager.employeeId}">${manager.fullName}</option>
											</c:forEach>
										</form:select>
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									</div>
									<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Start Date </label>
											<form:input type="date" class="input--style-4" path="projectStartDate"
												id="projectStartDate" onclick="cleardata()"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>

								</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">End Date </label>
											<form:input type="date" class="input--style-4" path="projectEndDate"
												id="projectEndDate" onclick="cleardata()"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>

								</div>
								<%-- <div class="row row-space">
								<div class="col-2">
										<div class="input-group">
											<label class="label">Assign to*</label>
											<form:select path="clientName" name="clientName">
												<form:option value="0" >Select</form:option >
											<c:forEach items="${employees}" var="emp">
												<option value="${emp.employeeId}">${emp.fullName }</option>
											</c:forEach>
										</form:select>
											<span id=derror class=error_msg></span>
										</div>
									</div>
									</div> --%>
									<%-- <div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Project Documents</label>
											<form:input class="input--style-4" path="doc"
												id="doc" onclick="cleardata()"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>

								</div> --%>


								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="">Project Status*</label>
											<div class="row">
											<form:select path="projectStatus" name="projectStatus" id="projectStatus">
												<form:option value="">Select</form:option>
												<form:option value="notstarted">NotStarted</form:option>
												<form:option value="inprogress">InProgress</form:option>
												<form:option value="halted">Halted</form:option>
												<form:option value="completed">Completed</form:option>
											</form:select>
											</div>
										</div>
									</div>
								</div>


								<div class="p-t-15">
									<button class="btn btn--radius-2 btn--blue" type="button"
										onclick="validate()">Submit</button>
									<button class="btn btn--radius-2 btn--blue" type="button"
										onclick="cancel()">Cancel</button>
								</div>
							</form:form>
						</div>
					</div>
					<div class="" id="projectTableDivId">
						<button onclick="addproject()"
							class="btn btn-primary btn-xm pull-right">Add Project</button>
						<table class="table table-striped custab" id="projectTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Id</th>
									<th>Project Title</th>
									<th>Client Id</th>
									<th>project Type</th>
									<th>Start Date</th>
									<th>End Date</th>
									<!-- <th>Project Status</th> -->
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

						var data = eval('${projectlist}');

						var table = $('#projectTable')
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
														"mData" : null,
															
													},

													{
														"mData" : "projectTitle",
														"visible" : true,
													},
													
													
													{
														"mData" : "clientId",
														"visible" : true,

													},
													{
														"mData" : "projectType",
														"visible" : true,
													},
													{
														"mData" : "projectStartDate",
														"visible" : true,
													},
													{
														"mData" : "projectEndDate",
														"visible" : true,
													},
													/* {
														"mData" : "projectStatus",
														"visible" : true,
													}, */
													
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
						$('#projectTable tbody').on('click', '#btn-view',
								function(e) {

								});
						$('#projectTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete the Project!");
											if (result) {
												var data = table.row(
														$(this).closest('tr'))
														.data();
												var projectId = data[Object
														.keys(data)[0]];

												document.getElementById("projectId").value = projectId;
												$("#deleteform").submit();
											}
										});

							 $('#projectTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#projectFormId").show();
											$("#projectTableDivId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();
											document.getElementById("projectInputId").value = data[Object.keys(data)[0]];
											document.getElementById("projectTitleId").value = data[Object.keys(data)[1]];
											$("#projectTitleId").prop("readonly", true);
											document.getElementById("projectDescription").value = data[Object.keys(data)[2]];
											document.getElementById("clientId").value = data[Object.keys(data)[6]];
											 $('#projectType option[value='+data[Object.keys(data)[7]]+']').prop('selected', true);
											document.getElementById("managerId").value = data[Object.keys(data)[3]];
											document.getElementById("projectStartDate").value = data[Object.keys(data)[4]];
											document.getElementById("projectEndDate").value = data[Object.keys(data)[5]];
											 $('#projectStatus option[value='+data[Object.keys(data)[8]]+']').prop('selected', true);						
										});

					});

	function addproject() {
		$("#projectFormId").show();
		$("#projectTableDivId").hide();
	}

	function cancel() {
		$("#projectFormId").hide();
		$("#projectTableDivId").show();
	}
	function validate() {
		var name =  /^[0-9*#+@+&+$_+%+!+]+$/; 
		
		
		if (document.getElementById("projectTitleId").value == "") {
			alert("Project Title should not be empty..!");
			document.getElementById("projectTitleId").focus();
		}else if (document.getElementById("projectTitleId").value.match(name)) {
			alert ("Project Title should not be special characters..!");
			document.getElementById("projectTitleId").focus();
		} else if (document.getElementById("clientId").value == "") {
			alert("Select Client ..!");
			document.getElementById("clientId").focus();
		}else if (document.getElementById("projectType").value == "") {
			alert("Select Project Type..!");
			document.getElementById("projectType").focus();
		}else if (document.getElementById("managerId").value == "") {
			alert("Select Manager ..!");
			document.getElementById("managerId").focus();
		}else if (document.getElementById("projectStatus").value == "") {
			alert("Select Project Status ..!");
			document.getElementById("projectStatus").focus();
		}else if (document.getElementById("projectStartDate").value == "") {
			alert("Select Start Date ..!");
			document.getElementById("projectStartDate").focus();
		} else if (document.getElementById("projectEndDate").value == "") {
			alert("Select end Date ..!");
			document.getElementById("projectEndDate").focus();
		}else {
			document.projectform.submit();
		}
		
	}
	
	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>
</html>
