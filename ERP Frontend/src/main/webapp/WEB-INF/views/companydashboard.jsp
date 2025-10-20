<!-- Author: Faizan
Title: Company Dashbord
Date:09 MARCH 2021  -->
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet'>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="resources/css/companydashboard.css" rel="stylesheet"
	type="text/css">
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$('#menu_icon').click(function() {
			if ($('.page-sidebar').hasClass('expandit')) {
				$('.page-sidebar').addClass('collapseit');
				$('.page-sidebar').removeClass('expandit');
				$('.profile-info').addClass('short-profile');
				$('.logo-area').addClass('logo-icon');
				$('#main-content').addClass('sidebar_shift');
				$('.menu-title').css("display", "none");
			} else {
				$('.page-sidebar').addClass('expandit');
				$('.page-sidebar').removeClass('collapseit');
				$('.profile-info').removeClass('short-profile');
				$('.logo-area').removeClass('logo-icon');
				$('#main-content').removeClass('sidebar_shift');
				$('.menu-title').css("display", "inline-block");
			}
		});
		$(function(){
		       setTimeout(function(){
		           $("#sleep").hide();
		           }, 5000);
		         });

	});

	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
		$(".side-nav .collapse").on(
				"hide.bs.collapse",
				function() {
					$(this).prev().find(".fa").eq(1).removeClass(
							"fa-angle-right").addClass("fa-angle-down");
				});
		$('.side-nav .collapse').on(
				"show.bs.collapse",
				function() {
					$(this).prev().find(".fa").eq(1).removeClass(
							"fa-angle-down").addClass("fa-angle-right");
				});
	})
</script>

<style>
/* .user-info li:hover
{
background-color: #717171ab;
}
  */
body {
	font-family: 'Roboto';
}
.btn-lg {
width:165px;
    padding: 300px 150px;
    size: 10px;
    border-radius: 15px;
}
</style>
</head>
<body>
	<div class="page-topbar">
		<div class="logo-area">
			<img src="resources/img/unique Venture logo.png"
				class="img-responsive" />
		</div>
		<div class="quick-area">

			<ul class="pull-left info-menu  user-notify">
				<button id="menu_icon">
					<i class="fa fa-bars" aria-hidden="true"></i>
				</button>
				<li><h3 style="color: white;">Employee Management System</h3></li>

			</ul>
	<jsp:include page="header.jsp"></jsp:include>

	<section id="main-content">
		<section class="wrapper main-wrapper row">
			<div class="col-md-7">
				<section class="box1" style="margin: -20px 0;">
					<h1 class="fa fa-th fa-2x " style="height: 200%">   &nbsp;Dashboard</h1>
				</section>
			</div>
			<span id="sleep" style="color: red;" >
							${msg}
						</span> 
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="departmentmanagement">Department
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="rolemanagement">Role
							Management</a>
					</h1>
				</section>
			</div>

			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="employeemanagement">Employee
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="menumanagement">Menu
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="teammanagement">Team
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="clientmanagement">Client
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="projectmanagement">Project
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="taskmanagement">Task
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="salarymanagement">Salary
							Management</a>
					</h1>
				</section>
			</div>
			<div class="col-md-6">
				<section class="box">
					<h1>
						<a style="text-decoration: none" href="announecement">Announcement</a>
					</h1>
				</section>
			</div>



		</section>
	</section>
</body>

</html>
