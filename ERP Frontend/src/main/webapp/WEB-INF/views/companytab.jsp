
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.css">
<script>
function myFunctionP() {
	var x = document.getElementById("supportEmailPassword");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

 function setvar(data)
{
	document.getElementById("name").value=data.name;
	document.getElementById("ownerName").value = data.ownerName;
	document.getElementById("registrationDate").value = data.registrationDate;
	document.getElementById("gstNumber").value = data.gstNumber;
	document.getElementById("address").value = data.address;
	document.getElementById("email").value = data.email;
	document.getElementById("contactNumber").value = data.contactNumber;	
	document.getElementById("website").value = data.website;
	document.getElementById("supportContactNumber").value = data.supportContactNumber;
	document.getElementById("supportEmailId").value = data.supportEmailId;
	document.getElementById("supportEmailPassword").value = data.supportEmailPassword;
	document.getElementById("website").value = data.website;
	document.getElementById("category").value = data.category;
	document.getElementById("type").value = data.type;
	

} 
</script>

<style>
.box_1{
	background: #eee;
}

input[type="checkbox"].switch_1{
	font-size: 15px;
	-webkit-appearance: none;
	   -moz-appearance: none;
	        appearance: none;
	width: 3.5em;
	height: 1.5em;
	background: #ddd;
	border-radius: 3em;
	position: relative;
	cursor: pointer;
	outline: none;
	-webkit-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
  }
  
  input[type="checkbox"].switch_1:checked{
	background: #0ebeff;
  }
  
  input[type="checkbox"].switch_1:after{
	position: absolute;
	content: "";
	width: 1.5em;
	height: 1.5em;
	border-radius: 50%;
	background: #fff;
	-webkit-box-shadow: 0 0 .25em rgba(0,0,0,.3);
	        box-shadow: 0 0 .25em rgba(0,0,0,.3);
	-webkit-transform: scale(.7);
	        transform: scale(.7);
	left: 0;
	-webkit-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
  }
  
  input[type="checkbox"].switch_1:checked:after{
	left: calc(100% - 1.5em);
  }
  
  
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
	<script type="text/javascript">

		
	</script>
	<div>
		<jsp:include page="productheader.jsp"></jsp:include>
		<div class="container">
			<div class="row col-md-10.9 col-md-offset-2 custyle">
				<div class="" style="margin-top: 10%">
					<div class="">
						<h2 class="title">Company</h2>
						<p align="center" id="show" class="error_msg">${msg}</p>
						<div style="display: none">
						<form:form
								action="${pageContext.request.contextPath}/changestatus"
								method="post" name="updatestatus" id="updatestatus" modelAttribute="myCompany">
								<input type="text" id="companyEmailId" name="companyEmailId">
								<input type="text" id="companyStatus" name="companyStatus">
							</form:form>
						</div>
						
					</div>
					<div class="" id="companyTableDivId">
						<table class="table table-striped custab" id="companyTable"
							style="margin-top: 3%;">
							
							<thead>

								<tr>
									<th>ID</th>
									<th>Company Name</th>
									<th>Owner name</th>
									<th>Status</th>
									<!-- <th>Action</th> -->
								</tr>
							</thead>

						</table>

					</div>
						
		<div class="row">
			<form role="form"  id="companyFormId" style="display: none">	
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="name">Company Name </label> 
							<input type="text" name="name" autocomplete="off" class="form-control" id="name">
						</div>
						<div class="col-sm-6 form-group">
							<label for="owner">Company Owner Name</label> <input
								type="text" name="ownerName" id="ownerName" class="form-control" autocomplete="off"  >
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="text">Registration Date </label> <input type="date"
								name="registrationDate" id="registrationDate"  class="form-control" id="">
						</div>
						<div class="col-sm-6 form-group">
							<label for="gst">GST Number</label> <input type="text" name="gstNumber" id="gstNumber" class="form-control" autocomplete="off" >
						</div>
					</div>
					<div class="form-group">
						<label for="address">Company Address</label>
						<textarea name="address" id="address" class="form-control" autocomplete="off"  ></textarea>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="email">Email <span class="required">* </span></label> <input type="email"
								name="email" id="email" autocomplete="off" >
						</div> 
						<div class="col-sm-6 form-group">
							<label for="contactNumber"> Contact Number </label> <input type="text"
								name="contactNumber" id="contactNumber" class="form-control"autocomplete="off"  >
						</div>
					</div>
					<div class="row">
				       				
							<div class="col-sm-6 form-group">
							<label for="site">Website</label> <input type="text" name="website" id="website" autocomplete="off" >
						</div>
						<div class="col-sm-6 form-group">
							<label for="supportContactNumber">Support Contact Number</label> <input
								type="text" name="supportContactNumber" id="supportContactNumber"
								class="form-control" autocomplete="off" >
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="supportEmailId">Support Email Id</label> <input type="text" name="supportEmailId" id="supportEmailId"
								class="form-control" >
						</div>
						<div class="col-sm-6 form-group">
							<label for="supportEmailPassword">Support Email Password</label> <input
								type="password" id="supportEmailPassword" class="form-control" autocomplete="off" >
								<span class="col-sm-6 form-group" style=" margin-left: 82%;margin-top:-13%;">
												<a class="fa fa-eye" onclick="myFunctionP()"></a>
											</span>
						</div>
					</div>
					<div class="row">
						
						<div class="col-sm-6 form-group">
							<label for="category">Company</label><br><input
								type="text" name="category" id="category"> 
								
						</div>
						<div class="row">
						<div class="col-sm-6 form-group">
							<label for="type">Company Type</label><br>
							<input
								type="text" name="type" id="type">  
							<button class="btn btn--radius-2 btn--blue" type="button" onclick="cancel()">Cancel</button>

						</div>
						
					</div>
					
				</div>
				</div>
			</form>
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

						var data = eval('${companylist}');

						var table = $('#companyTable')
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
														"mData" : "ownerName",
														"visible" : true,

													},
													 {
														"mData" : "registrationDate",
														"visible" : false,
													},
													
													
													{
														"mData" : "gstNumber",
														"visible" : false,

													},
													{
														"mData" : "address",
														"visible" : false,
													},
													
													
													{
														"mData" : "email",
														"visible" : false,

													},
													{
														"mData" : "contactNumber",
														"visible" : false,
													},
													
													
													{
														"mData" : "website",
														"visible" : false,

													},
													{
														"mData" : "supportContactNumber",
														"visible" : false,
													},
													
													
													{
														"mData" : "supportEmailId",
														"visible" : false,

													},
													{
														"mData" : "supportEmailPassword",
														"visible" : false,
													},
													
													
													{
														"mData" : "website",
														"visible" : false,

													},
													{
														"mData" : "category",
														"visible" : false,
													},
													{
														"mData" : "type",
														"visible" : false,
													},
													 
													{
														"mData" : "deleteStatus",
														"visible" : true,
													},
													 {
														"defaultContent" : " <a id='viewBtn' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-eye-open'></span> View</a>"
													}, 
													
													],
													"createdRow": function ( row, data, index ) {
														var option = "";
														if(data.deleteStatus == true){
															option = "<input type= 'checkbox' class='switch_1'  id='deleteStatus' data-toggle='toggle' data-on='false' data-off='true' data-onstyle='success' data-offstyle='danger' />";
														}else{
															option = "<input type= 'checkbox' class='switch_1'  id='deleteStatus' checked data-toggle='toggle' data-on='false' data-off='true' data-onstyle='success' data-offstyle='danger'/>";
														}
														 
														 $('td', row).eq(3).html(option);

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
						
						$('#companyTable tbody').on('click', '#viewBtn', function (e) {
							$("#companyFormId").show();
							$("#companyTableDivId").hide();
							  var data = table.row($(this).closest('tr')).data();
							  setvar(data); 
							 $("#name").prop("readonly", true);
							 $("#ownerName").prop("readonly", true);
							 $("#registrationDate").prop("readonly", true);
							 $("#gstNumber").prop("readonly", true);
							 $("#address").prop("readonly", true);
							 $("#email").prop("readonly", true);
							 $("#contactNumber").prop("readonly", true);
							 $("#website").prop("readonly", true);
							 $("#supportContactNumber").prop("readonly", true);
							 $("#supportEmailId").prop("readonly", true);
							 $("#supportEmailPassword").prop("readonly", true);
							 $("#website").prop("readonly", true);
							 $("#category").prop("readonly", true);
							 $("#type").prop("readonly", true);
							 
							  
			  			});
						
						 $('#companyTable tbody').on('click', '#deleteStatus', function(e) {
							var data = table.row($(this).closest('tr')).data();
							var emailId = data.email;
							document.getElementById("companyEmailId").value = emailId;
							var deleteStatus=document.getElementById("deleteStatus").value;
							if ($('input[type="checkbox"]').prop("checked") == true){
								document.getElementById("companyStatus").value= false;
							} else {
								document.getElementById("companyStatus").value= true;
							}
							
							document.updatestatus.submit();
						}); 
						
						
					});

	function addcompany() {
		$("#companyFormId").show();
		$("#companyTableDivId").hide();
	}

	function cancel() {
		$("#companyFormId").hide();
		$("#companyTableDivId").show();
	}

	function validate() {
		document.companyform.submit();
	}

	function cleardata() {
		document.getElementById("rerror").innerHTML = "";
		document.getElementById("derror").innerHTML = "";

	}
</script>


</html>
