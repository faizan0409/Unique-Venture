<!-- Created By: Siddhi Suryawanshi on Mar 04, 2021-->
<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
body {
	margin: 0;
	padding: 0;
	background-color: #ffffff;;
	height: 100vh;
}

#login .container #login-row #login-column #login-box {
	margin-top: 20%;
	max-width: auto;
	height: 20%;
	border: 1px solid #9C9C9C;
	background-color: #ffffff;
	border: 1px solid transparent;
	border-radius: 4px;
	box-shadow: 0px 0px 10px 05px rgb(32 45 135/ 47%);
}

#login .container #login-row #login-column #login-box #login-form {
	padding: 5%;
}

#login .container #login-row #login-column #login-box #login-form #register-link
	{
	margin-top: auto;
}

.text-info {
	color: #000000 !important;
}

#div1 {
	background-color: #e9ecef;
}
</style>
<script>
	function myFunction() {
		var x = document.getElementById("Npassword");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}

	function myFunctionP() {
		var x = document.getElementById("password");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}

	function Validate() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("Npassword").value;
		if (password != confirmPassword) {
			alert("Passwords do not match.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="login">
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<form id="login-form" class="form" action="login" method="post">
							<h3 class="text-center text-info" style="">Enter Password</h3>
							<div id="div1">
								<div class="form-group">
									<label for="password" class="text-info"><b> New
											Password</b></label><br> <input type="password" name="password"
										id="Npassword" class="form-control"
										onkeypress="return (event.charCode > 32 && event.charCode < 127)"
										  minlength="8"  
										class="login password-field form-control"> 
										<input type="checkbox" onclick="myFunction()" >show
										password
										
									<!-- 	<a
										class="fa fa-eye" onclick="myFunction()">&nbsp;show
										password</a>
 -->
								</div>
								<div class="form-group">
									<label for="password" class="text-info"> <b>confirm
											Password</b></label><br> <input type="password" name="password"
										id="password" class="form-control"
										onkeypress="return (event.charCode > 32 && event.charCode < 127)"
									  minlength="8" 
										class="login password-field form-control"> 
										<input type="checkbox" onclick="myFunctionP()" >show
										password
										
									
										<!-- <a
										class="fa fa-eye" onclick="myFunctionP()">&nbsp;show
										password</a>
 -->
								</div>
								<div class="form-group">

									<input type="submit" name="submit" class="btn btn-info btn-md"
										onclick="return Validate()" value="submit">
									<a href="login" class="btn btn-info">Cancel</a>	
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