
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
function isNumberKey(evt,id){
	document.getElementById(id).innerHTML = "";
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
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
						<h2 class="title">Client Management</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
							<form:form action="${pageContext.request.contextPath}/deleteclient"
								method="post" name="deletform" id="deletform">
								<input type="text" id="emailId" name="emailId">
							</form:form>
						</div>
						<div id="clientFormId" style="display: none">
							<form:form method="POST" name="clientform" action="saveclient"
								modelAttribute="myclient">
								<%--  <form:hidden path="companyId" id="companyId"/>  --%>
								<div class="col-2" style="display: none">
										<div class="input-group">
											<label class="label">Client Id</label>
											<form:input class="input--style-4" path="clientId"
												id="clientId" onclick="cleardata()"
												onkeypress="cleardata()" />
											<span id=rerror class=error_msg></span>
										</div>
									</div>
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Client Name*</label>
											<form:input class="input--style-4" path="clientName"
												id="clientName" onclick="cleardata()" autocomplete="off"
												onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')"/>
											<span id=rerror class=error_msg></span>
										</div>
									</div>
									</div>
									<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Company Name</label>
											<form:input class="input--style-4" path="companyName"
												id="companyName" onclick="cleardata()" autocomplete="off"
												onkeypress="return isAlpha(event,'rerror')" onkeyup="remove_error('rerror')" 
												maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
								</div>	
								
								<div class="row row-space">
								<div class="col-2">
										<div class="input-group">
											<label class="label">Email*</label>
											<form:input class="input--style-4" path="emailId"
												id="emailInputId" onclick="cleardata()"
												onkeypress="cleardata()" autocomplete="off"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									</div>
									<div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Contact No*</label>
											<form:input class="input--style-4" path="contactNumber"
												id="contactNumber" onclick="cleardata()"
												onkeypress="return isNumberKey(event,'derror')" autocomplete="off"
												onkeyup="cleardata()" maxlength="10" />
											<span id=derror class=error_msg></span>
										</div>
									</div>

								</div>
								<div class="row row-space">
								<div class="col-2">
										<div class="input-group">
											<label class="label">Address*</label>
											<form:textarea class="input--style-4" path="address"
												id="address" onclick="cleardata()"
												onkeypress="cleardata()" autocomplete="off"
												onkeyup="cleardata()" maxlength="100" />
											<span id=derror class=error_msg></span>
										</div>
									</div>
									</div>
									<%-- <div class="row row-space">
									<div class="col-2">
										<div class="input-group">
											<label class="label">Documents</label>
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
											<label class="">Status*</label>
											<div class="row">
												<form:radiobutton path="clientStatus" name="clientStatus" value="Y" checked="checked" id="active"/>
												&nbsp;Active &nbsp; &nbsp;
												<form:radiobutton path="clientStatus" name="clientStatus" value="N" id="inActive"/>
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
					<div class="" id="clientTableDivId">
						<button onclick="addclient()"
							class="btn btn-primary btn-xm pull-right">Add Client</button>
						<table class="table table-striped custab" id="clientTable"
							style="margin-top: 3%;">
							<thead>

								<tr>
									<th>Id</th>
									<th>Client Name</th>
									<th>Company Name</th>
									<th>Email</th>
									<th>Contact</th>
									<th>Address</th>
									<th>Status</th>
									<th>Client Id</th>
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

						var data = eval('${clientlist}');

						var table = $('#clientTable')
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
														"mData" : "clientName",
														"visible" : true,
													},
													
													
													{
														"mData" : "companyName",
														"visible" : true,

													},
													{
														"mData" : "emailId",
														"visible" : true,
													},
													{
														"mData" : "contactNumber",
														"visible" : false,
													},
													{
														"mData" : "address",
														"visible" : false,
													},
													{
														"mData" : "clientStatus",
														"visible" : true,
													},
													{
														"mData" : "clientId",
														"visible" : true,
													},
													{
														"defaultContent" : "<div class='row' style='width:200px'><a id='editBtn' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-edit'></span> Edit</a>  &nbsp; <a class='btn btn-danger btn-xs' id='deleteBtn'><span class='glyphicon glyphicon-remove'></span> Delete</a></div>"
													} ],
													"createdRow": function ( row, data, index ) {
														
														 if(data.clientStatus == 'true') {
															option="<a href=''  value='"+data.clientStatus+"'/> Active	";
															 $('td', row).eq(4).html(option);
														
														 }else{
																option="<a href=''   'value='"+data.clientStatus+"' />Inactive	";
																 $('td', row).eq(4).html(option);
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
						$('#clientTable tbody').on('click', '#btn-view',
								function(e) {

								});
						$('#clientTable tbody')
								.on(
										'click',
										'#deleteBtn',
										function(e) {

											var result = confirm("Do You Want To Delete the client!");
											if (result) {
												var data = table.row(
														$(this).closest('tr'))
														.data();
												var emailId = data[Object
														.keys(data)[5]];

												document.getElementById("emailId").value = emailId;
												$("#deletform").submit();
											}
										});

						$('#clientTable tbody')
								.on(
										'click',
										'#editBtn',
										function(e) {
											$("#clientFormId").show();
											$("#clientTableDivId").hide();
											var data = table.row(
													$(this).closest('tr'))
													.data();
											//document.getElementById("clientId").value = data[Object.keys(data)[0]];
											document.getElementById("clientName").value = data[Object.keys(data)[1]];
											document.getElementById("companyName").value = data[Object.keys(data)[2]];
											document.getElementById("emailInputId").value = data[Object.keys(data)[5]];
										//	$("#emailId").prop("readonly", true);
											document.getElementById("contactNumber").value = data[Object.keys(data)[4]];
											document.getElementById("address").value = data[Object.keys(data)[3]];
											document.getElementById("clientId").value = data[Object.keys(data)[0]];
																	
											
											var status = data[Object.keys(data)[6]];
											if (status == "true") {
												document.getElementById("active").checked = "checked";
											} else {
												document.getElementById("inActive").checked = "checked";
											}
										});

					});

	function addclient() {
		$("#clientFormId").show();
		$("#clientTableDivId").hide();
	}

	function cancel() {
		$("#clientFormId").hide();
		$("#clientTableDivId").show();
	}
	function validate() {
		var name =/^[0-9*#+@+&+$_+%+!+]+$/;  
		var email=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{0,9})$/;
		var mob=/^[1-9]{1}[0-9]{9}$/;
		
		if (document.getElementById("clientName").value == "") {
			alert("Client Name should not be empty..!");
			document.getElementById("clientName").focus();
		}else if (document.getElementById("clientName").value.match(name)) {
			alert ("Client name should not be special characters..!");
			document.getElementById("clientName").focus();
		}else if (document.getElementById("emailInputId").value == "") {
			alert("Email Id should not be empty..!");
			document.getElementById("emailInputId").focus();
		}else if (email.test(document.getElementById("emailInputId").value) == false) {
			alert("Invalid Email Id ..!");
			document.getElementById("emailInputId").focus();
		}else if (document.getElementById("contactNumber").value == "") {
			alert("Contact No should not be empty..!");
			document.getElementById("contactNumber").focus();
		}else if (mob.test(document.getElementById("contactNumber").value) == false) {
			alert("Invalid Contact Number..!");
			document.getElementById("contactNumber").focus();
		}else if (document.getElementById("address").value == "") {
			alert("Address should not be empty..!");
			document.getElementById("address").focus();
		}else {
			document.clientform.submit();
		}
	}

	
	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
