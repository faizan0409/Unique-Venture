
<!-- Author: Faizan
Title: Department Management
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
						<h2 class="title">Task Management</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form action="${pageContext.request.contextPath}/deletetask"
								method="post" name="deleteform" id="deleteform">
								<input type="text" id="taskName" name="taskName">
							</form:form>
						</div>
						<div id="taskFormId" style="display: none">
							<form:form method="POST" name="taskform" action="savetask"
								modelAttribute="mytask">
								
								<div class="row row-space">
								<div class="col-2" style="display: none;">
										<div class="input-group">
											<label class="label">Company Id*</label>
											<form:input class="input--style-4" path="companyId" id="companyId"
												onclick="cleardata()" onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Task Name*</label>
											<form:input class="input--style-4" path="taskName" id="taskNameId" autocomplete="off"
												onclick="cleardata()" onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')"  />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">Task Description</label>
											<form:input class="input--style-4" path="taskDescription"
												id="taskDescription" onclick="cleardata()" autocomplete="off"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									
									<div class="col-2">
										<div class="input-group">
											<label class="label">Project*</label>
											<form:select path="projectId" name="projectId" id="projectId">
												<form:option value="" >Select</form:option >
											<c:forEach items="${projects}" var="project">
												<option value="${project.projectId}">${project.projectTitle}</option>
											</c:forEach>
										</form:select>
											<span id=derror class=error_msg></span>
										</div>
									</div>

							
									<div class="col-2">
										<div class="input-group">
											<label class="label">Priority*</label>
											<form:select path="priority" name="priority" id="priority">
											<form:option value="">Select</form:option>
												<form:option value="low">Low</form:option>
												<form:option value="medium">Medium</form:option>
												<form:option value="high">High</form:option>
											</form:select>
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group">
											<label class="label">start date</label>
											<form:input type="date" class="input--style-4" path="startDate"
												id="startDate" onclick="cleardata()"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									
									<div class="col-2">
										<div class="input-group">
											<label class="label">End Date*</label>
											<form:input type="date" class="input--style-4" path="endDate"
												id="endDate" onclick="cleardata()"
												onkeypress="cleardata()"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Task Status*</label>
											<form:select path="taskStatus" name="taskStatus" id="taskStatus">
												<form:option value="">Select</form:option>
												<form:option value="notstarted">NotStarted</form:option>
												<form:option value="inprogress">InProgress</form:option>
												<form:option value="halted">Halted</form:option>
												<form:option value="completed">Completed</form:option>
											</form:select>
											<span id=derror class=error_msg></span>
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
					<div class="" id="taskTableDivId">
						<button onclick="addtask()"
							class="btn btn-primary btn-xm pull-right">Add</button>
						<table class="table table-striped custab" id="tasktable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Sr No</th>
									<th>Task Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Status</th>
									<th>Priority</th>
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

						var data = eval('${tasklist}');

						var table = $('#tasktable')
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
														"mData" : "taskName",
														"visible" : true,

													},
													{
														"mData" : "startDate",
														"visible" :true,

													},
													{
														"mData" : "endDate",
														"visible" : true,

													},
													{
														"mData" : "taskStatus",
														"visible" : true,
													},
													{
														"mData" : "priority",
														"visible" : true	,
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
						$('#tasktable tbody').on('click', '#btn-view',
								function(e) {

								});
						$('#tasktable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete The Task!");
											if (result) {
												var data = table.row(
														$(this).closest('tr'))
														.data();
												var taskName = data[Object
														.keys(data)[0]];

												document.getElementById("taskName").value = taskName;
												$("#deleteform").submit();
											}
										});
						
						$('#tasktable tbody').on('click', '#editBtn', function (e) {
							 $("#taskFormId").show();
							 $("#taskTableDivId").hide();
					    	 var data = table.row($(this).closest('tr')).data();
					         document.getElementById("taskNameId").value=data[Object.keys(data)[0]];
					    	 $("#taskNameId").prop("readonly", true);
					    	 document.getElementById("taskDescription").value=data[Object.keys(data)[1]];
					    	 document.getElementById("projectId").value=data[Object.keys(data)[2]];
					    	 document.getElementById("startDate").value=data[Object.keys(data)[4]];
					    	 document.getElementById("endDate").value=data[Object.keys(data)[5]];
					    	
					    	 $('#priority option[value='+data[Object.keys(data)[3]]+']').prop('selected', true);
					    	 $('#taskStatus option[value='+data[Object.keys(data)[6]]+']').prop('selected', true);
					   	 
						 });

					});

	function addtask() {
		$("#taskFormId").show();
		$("#taskTableDivId").hide();
	}
	
	function cancel() {
		$("#taskFormId").hide();
		$("#taskTableDivId").show();
	}

	function validate() {
		var name =  /^[0-9*#+@+&+$_+%+!+]+$/; 
		
		
		if (document.getElementById("taskNameId").value == "") {
			alert("Task Name should not be empty..!");
			document.getElementById("taskNameId").focus();
		}else if (document.getElementById("taskNameId").value.match(name)) {
			alert ("Task name should not be special characters..!");
			document.getElementById("taskNameId").focus();
		} else if (document.getElementById("startDate").value == "") {
			alert("Select Start Date ..!");
			document.getElementById("startDate").focus();
		} else if (document.getElementById("endDate").value == "") {
			alert("Select end Date ..!");
			document.getElementById("endDate").focus();
		}else if (document.getElementById("projectId").value == "") {
			alert("Select project ..!");
			document.getElementById("projectId").focus();
		}else if (document.getElementById("taskStatus").value == "") {
			alert("Select Task Status ..!");
			document.getElementById("taskStatus").focus();
		}else if (document.getElementById("priority").value == "") {
			alert("Select Priority ..!");
			document.getElementById("priority").focus();
		}
		else {
			document.taskform.submit();
		}
		
	}
	
	function cleardata() {
	    document.getElementById("rerror").innerHTML = "";
	    document.getElementById("derror").innerHTML = "";
	   
	}

</script>


</html>
