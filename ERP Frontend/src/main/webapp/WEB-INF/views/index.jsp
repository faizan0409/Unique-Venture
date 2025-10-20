<!--  Author: Faizan 
Title: Product Page Views of Employee Management System
Date: 17 Feb 2021 
 -->
<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<script>

$( document ).ready(function(){
    $('#message').fadeIn('slow', function(){
      $('#message').delay(5000).fadeOut();
   });
});</script>
<script>
function ValidateEmail(mail) 
{
 if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(myForm.emailAddr.value))
  {
    return (true)
  }
    alert("You have entered an invalid email address!")
    return (false)
}</script>
<link href="resources/css/index.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet'>
<script>
	$(document).on('click', 'a[href^="#"]', function(event) {
		event.preventDefault();
		$('html, body').animate({
			scrollTop : $($.attr(this, 'href')).offset().top - 60
		}, 500);
	});
</script>
<style>
.msg
{
    -webkit-animation: fadeOut 1s;
    animation: fadeOut 1s;
    animation-fill-mode: forwards;
    }
.header {
	margin: auto;
	overflow: hidden;
	background-color: #DAF7A6;
	padding: 3px 5px;
	border: 0px lightgray;
	box-shadow: 0px 0px 10px 08px rgb(32 45 135/ 47%);
}
.ftr {
	text-decoration:none;
	color: black;
	padding: 5px;
	font-size: 25px;
	cursor: pointer;
	line-height: 25px;
}

.ftr:hover {
	border: 3px dotted green;
	color: green;
	background: light gray;
}
/* CSS FOR ROBOTO FONT FAMILY IN OVERALL PAGE......Remaining All CSS is in index.css  */
body {
	font-family: 'Roboto';
}

/* Hide horizontal scrollbar */
body {
	overflow-x: hidden;
}
</style>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</head>
<body>
	<!-- This DIV is for Main Header -->
	<div class=" responsive header">
		<a href="#Home" class="logo"><img
			src="resources/img/unique Venture logo.png"
			style="width: 15%; height: 7%;"logo"></a>
		<div class="header-right">
			<a href="#Products" class="ftr"><b>Products</b></a> <a href="#Features"
				class="ftr"><b>Features</b></a>
			<!-- <a href="#2" class="ftr"><b>Acknowledgement</b></a> -->
			<a href="#ContactUs" class="ftr"> <b> Contact Us</b></a> <a
				href="login" class="btn login" type="button">LOGIN</a> <a
				href="registration" class="btn signup" type="button">SIGN UP</a>
		</div>
	</div>
	<br>

	<!-- This DIV is for Middle and Desktop Image Section -->
	<div class="mid">
	<section class="Products" id="Products">
		<div class="row">
			<div class="column" style="width: 45%">
				<div class="card">
					<h1 style="font-size: 280%;">Employee Management,</h1>
					<h1 style="font-size: 280%;">Robust Security,</h1>
					<h1 style="font-size: 280%;">Professionalism.</h1>
					<h3 style="font-size: 175%;">Shield your System and make it
						Unique.</h3>
				</div>
			</div>
			<div class="column" style="width: 45%">
				<div class="card">
					<img src="resources/img/Desktop Image.png" alt="product"
						class="responsive">
				</div>
			</div>
		</div>
		</section>
	</div>


	<!-- This div is for Features Section -->
	
	<section class="Features" id="Features">
	<br>
	<h1 style="text-align: center; font-size: 250%;">FEATURES</h1>
	<br>
		<div class="row">

			<!-- <div class="column">
				<div class="card">
					<img src="resources/img/Left Arrow.png" style="width: 98.5%">
				</div>
			</div> -->
			<div class="column">
				<div class="card">
					<img src="resources/img/Employee Management.png"
						style="width: 98.5%">
					<div class="container">
						<h5 style="text-align: center">Smart Employee Management</h5>
					</div>
				</div>
			</div>

			<div class="column">
				<div class="card">
					<img src="resources/img/Task Management1.png" style="width: 105%">
					<div class="container">
						<h5 style="text-align: center">
							Smart <br> Task Management
						</h5>

					</div>
				</div>
			</div>

			<div class="column">
				<div class="card">
					<img src="resources/img/Company Managemnt.png" style="width: 110%">
					<div class="container">
						<h5 style="text-align: center">Smart Company Management</h5>

					</div>
				</div>
			</div>
			<div class="column">
				<div class="card">
					<img src="resources/img/Rohbust Security.png" style="width: 115%">
					<div class="container">
						<h5 style="text-align: center">Robust Security</h5>

					</div>
				</div>
			</div>

			<div class="column">
				<div class="card">
					<img src="resources/img/Project Management.png" style="width: 115%">
					<div class="container">
						<h5 style="text-align: center">Smart Project Management</h5>
					</div>
				</div>
			</div>

			<div class="column">
				<div class="card">
					<img src="resources/img/Live Management.png" style="width: 115%">
					<div class="container">
						<h5 style="text-align: center">
							Smart <br>Leave Management
						</h5>
					</div>
				</div>
			</div>

			<div class="column">
				<div class="card">
					<img src="resources/img/Notification.png" style="width: 115%">
					<div class="container">
						<h5 style="text-align: center">Smart Notification</h5>
					</div>
				</div>
			</div>

			<!-- <div class="column">
				<div class="card">
					<img src="resources/img/Right Arrow.png" style="width: 115%">
				</div>
			</div> -->
		</div>



		<!--  This div is for Knowledge Base -->
		<!-- <br>
	<section class="Knowledge Base" id="2">
		<div style="text-align: center">
			<h1>KNOWLEDGE BASE</h1>
			<select size="2" style="width: 75%;">
				<option value="FAQ"><a
						href="resources/documents/FAQ of EMS.pdf">About US </a></option>

			</select>
	</section> -->
		</div>
		<br>
	</section>

	<!-- This div is for Contact Form -->
	<div class="contact" style="text-align: center">
		<section class="Contact Us" id="ContactUs">
			<h1 style="font-size: 250%;">GET IN TOUCH WITH US</h1>
			<form action="sendEnquiry" method="post">
				<div class="ne">

					<input type="text" id="name" name="name"
						placeholder="Your Name Here"
						onkeypress="return (event.charCode > 64 &&
                           event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode > 31 && event.charCode < 33)"
						class="form-control" required> <input type="email"
						id="email" name="email" placeholder="Your Email Here"
						pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
				</div>

				<div class="sub">
					<input type="text" id="sub" name="sub" placeholder="Title"
						style="width: 63%" required>
				</div>
				<div class="dsc">
					<textarea id="desc" name="desc" placeholder="Enter Message Here"
						style="width: 63%" "height: 200px" required></textarea>
					<br>
				</div>
				<div class="smt">
					<input type="submit" value="Send Enquiry"><span id="message" style="color: red;" >${msg}</span>
				</div>
				
			</form>
		</section>
	</div>


	<!-- This div is for Footer -->
	<div class="footer">
		<b>@2021 Unique Venture All Rights Reserved.</b>
	</div>



</body>
</html>