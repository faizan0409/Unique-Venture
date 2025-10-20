
<!-- Author: Faizan
Title: Role Management
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
body {
 
  overflow-x: hidden; /* Hide horizontal scrollbar */
}
input {
  outline: none;
  margin: 0;
  border: none;
  -webkit-box-shadow: none;
  -moz-box-shadow: none;
  box-shadow: none;
  width:12%;
  font-size: 14px;
  font-family: inherit;
}
.dataTables_wrapper {
	width: 115%;
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

.btn-xm {
	width: 60px;
	font-size: 12px;
	size: 15px;
	border-radius: 7px;
}

.well {
	min-height: 19px;
	margin-left: 33%;
	padding: 19px;
	margin-bottom: 8px;
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	border-radius: 18px;
	-webkit-box-shadow: inset 0 1px 1px rgb(0 0 0/ 5%);
	box-shadow: inset 0 1px 30px rgb(0 0 0/ 5%);
}

@media ( min-width : 1200px) .col-lg-12 {
	width:145%;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row col-md-12 col-md-offset-2 custyle">
				<div class="" style="margin-top: 7%; margin-left: 4%;">
					<div class="">
						<h1 class="title">
							<i class="fa fa-user-circle  fa-1x "></i> &nbsp; &nbsp; Role
							Management
							<button onclick="addRole()"
								class="btn btn-primary btn-xm pull-right" id="addBtnId">&nbsp;
								&nbsp;Add</button>
						</h1>
						<p align="center" id="show" class="error_msg"
							>${msg}</p>
						<div style="display: none">
							<form:form action="${pageContext.request.contextPath}/deleterole"
								method="post" name="deletform" id="deletform">
								<input type="text" id="name" name="name">
							</form:form>
						</div>
						<div id="roleFormId" style="display: none">
							<form:form method="POST" name="roleform" action="saverole"
								modelAttribute="myrole">
								<%--  <form:hidden path="companyId" id="companyId"/>  --%>
								<div class="modal-body" style="">
									<div class="col-lg-12 well">
										<div class="row">

											<div class="col-sm-12">
												<div class="row">
													<div class="input-group">
														<label for="roleName">Role Name*<span id=rerror
															class=error_msg></span></label>
														<form:input class="input--style-4" path="name"
															id="roleName" onclick="cleardata()" autocomplete="off"
														onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')"  style="width: 160%;" />


														<div class="col-12 ">
															<div class="input-group">
																<label for="description">Description*<span
																	id=derror class=error_msg></span></label>
																<form:input class="input--style-4" path="description"
																	id="description" onclick="cleardata()" autocomplete="off"
																	onkeypress="cleardata()" onkeyup="cleardata()"
																	maxlength="100" style="width: 160%;" />

															</div>
														</div>
													</div>
												</div>
											</div>



											<div class="col-12">
												<div class="input-group">
													<label for="roleStatus">Status*</label>
													&nbsp;&nbsp;
													<form:radiobutton path="roleStatus" name="roleStatus"
														value="Y" checked="checked" id="active" />
													Active &nbsp;&nbsp;&nbsp;&nbsp;
													<form:radiobutton path="roleStatus" name="roleStatus"
														value="N" id="inActive" />
													In Active


												</div>

											</div>
											
										</div>

										<div class="p-t-15">
											<button class="btn btn--radius-2 btn--blue" type="button"
												onclick="validate()">Submit</button>
											<button class="btn btn--radius-2 btn--blue" type="button"
												onclick="cancel()">Cancel</button>
										</div>
									</div>

								</div>
							</form:form>
						</div>
					</div>
					<div class="" id="roleTableDivId">

						<table class="table table-striped custab" id="roleTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Sr No</th>
									<th>Role Name</th>
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

						var data = eval('${rolelist}');

						var table = $('#roleTable')
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
														"mData" : "name",
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
														"mData" : "roleStatus",
														"visible" : true,
													},
													{
														"defaultContent" : "<div class='row' style='width:200px'><a id='editBtn' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-edit'></span> Edit</a>  &nbsp; <a class='btn btn-danger btn-xs' id='deleteBtn'><span class='glyphicon glyphicon-remove'></span> Delete</a></div>"
													} ],
													"createdRow": function ( row, data, index ) {
														
														 if(data.roleStatus == 'true') {
															option="<a href=''  value='"+data.roleStatus+"'/> Active	";
															 $('td', row).eq(3).html(option);
														
														 }else{
																option="<a href=''   'value='"+data.roleStatus+"' />Inactive	";
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
						$('#roleTable tbody').on('click', '#btn-view',
								function(e) {

								});
						$('#roleTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete The Role!");
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

						$('#roleTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#roleFormId").show();
											$("#roleTableDivId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();

											document.getElementById("roleName").value = data[Object
													.keys(data)[0]];
											$("#roleName").prop("readonly",
													true);
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

	function addRole() {
		$("#roleFormId").show();
		$("#roleTableDivId").hide();
		$("#addBtnId").hide();
	}

	function cancel() {
		$("#roleFormId").hide();
		$("#roleTableDivId").show();
		$("#addBtnId").show();
	}

	function validate() {
		 var name = /^[0-9*#+@+&+$_+%+!+]+$/; 
		 
		if (document.getElementById("roleName").value == "") {
			alert("Role Name should not be empty..!");
			document.getElementById("roleName").focus();
		} else if (document.getElementById("roleName").value.match(name)) {
			alert ("Role name should not be special characters..!");
			document.getElementById("roleName").focus();
		}else if (document.getElementById("description").value == "") {
			alert("Role Description should not be empty..!");
			document.getElementById("description").focus();
		}else {
			document.roleform.submit();
		}
		
	}

	
	
	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
