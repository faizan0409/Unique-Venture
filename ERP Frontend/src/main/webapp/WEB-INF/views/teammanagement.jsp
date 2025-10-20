
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
						<h2 class="title">Add Team</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form action="${pageContext.request.contextPath}/deleteteam"
								method="post" name="deletform" id="deletform">
								<input type="text" id="name" name="name">
							</form:form>
						</div>
						<div id="teamFormId" style="display: none">
							<form:form method="POST" name="teamform" action="saveteam"
								modelAttribute="myTeam">
								<%--  <form:hidden path="companyId" id="companyId"/>  --%>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Team Name*</label>
											<form:input class="input--style-4" path="teamName" autocomplete="off"
												id="teamName" onclick="cleardata()" 
												onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									
									<div class="col-2">
										<div class="input-group">
											<label class="label">Description*</label>
											<form:input class="input--style-4" path="description" 
												id="description" onclick="cleardata()" autocomplete="off"
												onkeypress="cleardata()" onkeyup="cleardata()"
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									
								</div>

								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="">Status*</label>
											<div class="row">
												<form:radiobutton path="teamStatus" name="teamStatus" value="Y" checked="checked" id="active"/>
												&nbsp;Active &nbsp; &nbsp;
												<form:radiobutton path="teamStatus" name="teamStatus" value="N" id="inActive"/>
												&nbsp;In Active &nbsp; &nbsp;
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
					<div class="" id="teamTableDivId">
						<button onclick="addteam()"
							class="btn btn-primary btn-xm pull-right">Add</button>
						<table class="table table-striped custab" id="teamTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Sr No</th>
									<th>Team Name</th>
									<th>Description</th>
									<th>CompanyID</th>
									<th>Status</th>
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

						var data = eval('${teamlist}');

						var table = $('#teamTable')
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
														"mData" : "teamName",
														"visible" : true,
													},
													
													
													{
														"mData" : "description",
														"visible" : true,

													},
													{
														"mData" : "companyId",
														"visible" : false,
													},
													{
														"mData" : "teamStatus",
														"visible" : true,
													},
													{
														"defaultContent" : "<div class='row' style='width:200px'><a id='editBtn' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-edit'></span> Edit</a>  &nbsp; <a class='btn btn-danger btn-xs' id='deleteBtn'><span class='glyphicon glyphicon-remove'></span> Delete</a></div>"
													} ],
													"createdRow": function ( row, data, index ) {
														
														 if(data.teamStatus == 'true') {
															option="<a href=''  value='"+data.teamStatus+"'/> Active	";
															 $('td', row).eq(3).html(option);
														
														 }else{
																option="<a href=''   'value='"+data.teamStatus+"' />Inactive	";
																 $('td', row).eq(3).html(option);
														 }
														},

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
						$('#teamTable tbody').on('click', '#btn-view',
								function(e) {

								});
						$('#teamTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete The Team!");
											if (result) {
												var data = table.row(
														$(this).closest('tr'))
														.data();
												var name = data[Object
														.keys(data)[0]];

												document.getElementById("name").value = name;
												$("#deletform").submit();
											}
										});

						$('#teamTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#teamFormId").show();
											$("#teamTableDivId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();
											document.getElementById("teamName").value = data[Object.keys(data)[0]];
											$("#teamName").prop("readonly", true);
											
											document
													.getElementById("description").value = data[Object
													.keys(data)[1]];
											


											var status = data[Object.keys(data)[3]];
											if (status == "true") {
												document
														.getElementById("active").checked = "checked";
											} else {
												document
														.getElementById("inActive").checked = "checked";
											}
										});

					});

	function addteam() {
		$("#teamFormId").show();
		$("#teamTableDivId").hide();
	}

	function cancel() {
		$("#teamFormId").hide();
		$("#teamTableDivId").show();
	}

	function validate() {
		var name =/^[0-9*#+@+&+$_+%+!+]+$/;  
		
		if (document.getElementById("teamName").value == "") {
			alert("Team Name should not be empty..!");
			document.getElementById("teamName").focus();
		}else if (document.getElementById("teamName").value.match(name)) {
			alert ("Team name should not be special characters..!");
			document.getElementById("teamName").focus();
		} else if (document.getElementById("description").value == "") {
			alert("Team Description should not be empty..!");
			document.getElementById("description").focus();
		}else {
			document.teamform.submit();
		}
		
	}

	
	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
