<!-- Created By: Siddhi Suryawanshi on Mar 04, 2021-->
<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<title>EMS</title>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>	

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
<link href="resources/css/login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.css">
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script>
	$(function() {

		$("#productAdminBtn").css("background-color", "#8f959b"); // Added by sumit for heighlight company as default selection
		$('#login-form-link').click(function(e) {
			$("#login-form").delay(100).fadeIn(100);
			$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
			$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});

	});

	function myFunction() {
		var x = document.getElementById("companyPassword");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}

	function myFunctionP() {
		var x = document.getElementById("productPassword");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}

	function setConfForProductAdmin() {
		$("#productAdminBtn").css("background-color", "#66b3ff");
		$("#companyBtn").css("background-color", "#8f959b");
		$("#login-form").attr('action', 'productdashboard');
	}

	function setConfForCompany() {
		$("#companyBtn").css("background-color", "#66b3ff");
		$("#productAdminBtn").css("background-color", "#8f959b");
		$("#login-form").attr('action', 'companydashboard');
	}
	$(function(){
	       setTimeout(function(){
	           $("#sleep").hide();
	           }, 5000);
	         });
</script>

</head>

<body class="h-100">
	<div class="container h-100">
		<div class="row align-items-center h-100">
			<div class="col-md-4 mx-auto">
				<div class="tf_login_wrapper mb-3">
					<div class="tf_login_head">
						<a href="/Employee_Management_System"> <img
							src="resources/img/unique Venture logo.png" class="img-fluid"
							class="img-responsive" />
						</a>
					</div>
				</div>

				<div class="card">
					<div class="card-header">
						<h4 align="left">
							<strong>Login</strong>
						</h4>
						 <span id="sleep" style="color: red;" >
							${msg}
						</span> 
						<p>Please provide your details</p>
						<div  id="sleep" ><h3 align="center"style="color: red">${exist}</h3></div>
						
						<div class="row">
							<div class="input-group mb-3">
								<!-- <div class="input-group-prepend">  -->
								<div class="row" style="padding:auto;margin:auto"  class="text-center">
									<a href="#" class="active" id="login-form-link"> <input
										type="button" id="productAdminBtn" name="product" value="Product Admin"
										onclick="setConfForProductAdmin()"
										style="background-color: #66b3ff; color: #ffffff; border-color: #ffffff; margin-left: -10%"></a>
									<a href="#" id="register-form-link"> <input type="button"
										id="companyBtn" value="Company" name="company" onclick="setConfForCompany()" 
										style="background-color: #66b3ff; color: #ffffff; border-color: #ffffff; margin-left: 12%; padding-left: 30%; padding-right: 20%;"
										autofocus>

									</a>
								</div>
							</div>
						</div>

						<form id="login-form" action="productdashboard" method="post" role="form"
							style="display: none;">
							<div class="form-group" style="width: 100%;">

								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"><i
											class="fa fa-user"></i></span>
									</div>
									<input type="text" id="username" name="username"
										placeholder="Username" required="required"
										"onkeypress="return (event.charCode > 32 && event.charCode < 127)"
										class="login username-field form-control" />
								</div>

								<div class="input-group mb-3">
									<div class="form-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"><i
												class="fa fa-lock"></i></span> <input type="password"
												id="productPassword" name="password" placeholder="Password"
												required="required"
												class="login password-field form-control" onkeypress="return (event.charCode > 33 && event.charCode < 127)"
												  /> <span class="input-group-text"> <a class="fa fa-eye"
												onclick="myFunctionP()"></a>
											</span>

										</div>
									</div>
								</div>


								<div class="login-actions d-flex mt-4 align-items-right">
									&nbsp;
									<button class="btn btn-info btn-sm " type="submit">Log
										In</button>
								</div>
								<br>
							</div>
						</form>

						<form id="register-form" action="companydashboard" method="post"
							role="form" style="display: block;">
							<div class="form-group">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"><i
											class="fa fa-envelope"></i></span>
									</div>
									<input type="email" id="email" name="email" placeholder="Email"
										class="form-control"
										pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required
										class="login username-field form-control">

								</div>

								<div class="input-group mb-3">
									<div class="form-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"><i
												class="fa fa-lock"></i></span> <input type="password"
												id="companyPassword" name="password" placeholder="Password"
												required="required"
												onkeypress="return (event.charCode > 32 && event.charCode < 127)"
												class="login password-field form-control" /> <span
												class="input-group-text"> <a class="fa fa-eye"
												onclick="myFunction()"></a>
											</span>

										</div>
									</div>
								</div>

								<div class="control-group d-flex align-items-center">
									<label class="control-label mr-3 mb-0"><b>User Type</b></label>
									<div class="controls">
										<div class="form-check form-check-inline tf_form_radio">
											<input type="radio" id="A" class="form-check-input"
												name="usertype" value="A" onclick="showforgot(this.value);"
												required> <label class="form-check-label"
												for="inlineRadio1">Admin</label>
										</div>
										<div class="form-check form-check-inline tf_form_radio">
											<input type="radio" id="S" class="form-check-input"
												name="usertype" value="E" onclick="showforgot(this.value);"
												required checked> <label class="form-check-label"
												for="inlineRadio1">Employee</label>
										</div>
									</div>
								</div>

								<div class="login-actions d-flex mt-4 align-items-right">
									<button class="btn btn-info btn-sm" type="submit">Log
										In</button>
									&nbsp;&nbsp;<a href="forgotpassword"
										style="margin-left: 8%; margin-top: 1%;">Forgot Password</a>

								</div>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


