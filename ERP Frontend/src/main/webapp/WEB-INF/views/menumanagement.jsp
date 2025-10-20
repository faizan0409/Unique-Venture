<!--  Author: Faizan
Title: Menu Management
Date:17 MARCH 202 -->

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

			<ul class="pull-right info-menu user-info">

				<li class="dropdown"><a href="#"> <i class="fa fa-bell"></i><span
						class="label label-warning bg-warning">10</span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
					class="dropdown-toggle" data-toggle="dropdown">Admin<!-- <b
					class="fa fa-angle-down"></b> --></a>
					<ul class="dropdown-menu">
						<li class="text-left"><a href="userprofile" class="btn btn-info btn-lg " ><i class="fa fa-user"></i>View
								Profile
								</a></li>
						<li><a href="forgotpassword" class="btn btn-info btn-lg"><i
								class="fa fa-cog"></i> Change Password</a></li>
						
						<li ><a href="login" class="btn btn-info btn-lg"><i
								class="fa fa-power-off"></i> Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>

	<div class="page-sidebar expandit">
		<div class="sidebar-inner" id="main-menu-wrapper">
			<div class="profile-info row ">

				<div class="profile-details">
					<h3>
						<a href="ui-profile.html"></a>
					</h3>


				</div>
			</div>
			<br>

			<ul class="wraplist" style="height: auto;">

				<li><a  href="companydashboard"><span
						class="sidebar-icon"><i class="fa fa-th"></i></span> <span
						class="menu-title">Dashboard</span></a></li>
				<li><a href="departmentmanagement"><span
						class="sidebar-icon"><i class="fa fa-braille"></i></span> <span
						class="menu-title">Department Management</span></a></li>
				<li><a href="rolemanagement"><span class="sidebar-icon"><i
							class="fa fa-user-circle "></i></span> <span class="menu-title">Role
							Management</span></a></li>
				<li><a href="employeemanagement"><span class="sidebar-icon"><i
							class="fa fa-users "></i></span> <span class="menu-title">Employee
							Management</span></a></li>
				<li><a  class="active" href="menumanagement"><span class="sidebar-icon"><i
							class="fa fa-bars "></i></span> <span class="menu-title">Menu
							Management</span></a></li>

				<li><a  href="teammanagement"><span class="sidebar-icon"><i
							class="fa fa-user-plus "></i></span> <span class="menu-title">Team
							Management</span></a></li>
				<li><a  href="clientmanagement"><span class="sidebar-icon"><i
							class="fa fa-handshake-o"></i></span> <span class="menu-title">Client
							Management</span></a></li>
				<li><a  href="projectmanagement"><span class="sidebar-icon"><i
							class="fa fa-bar-chart "></i></span> <span class="menu-title">Project
							Management</span></a></li>
				<li><a href="taskmanagement"><span class="sidebar-icon"><i
							class="fa fa-tasks"></i></span> <span class="menu-title">Task
							Management</span></a></li>
				<li><a href="salarymanagement"><span class="sidebar-icon"><i
							class="fa fa-money "></i></span> <span class="menu-title">Salary
							Management </span></a></li>
				<li><a href="announecement"><span class="sidebar-icon"><i
							class="fa fa-bullhorn"></i></span> <span class="menu-title">Announcement
					</span></a></li></ul>
		</div>
	</div>


	<section id="main-content">
		<section class="wrapper main-wrapper row">
<div class="col-md-12">
				<section class="box1" style=" margin:-20px 0;">
					<h1  class="fa fa-bars fa-2x"> Menu Management</h1>
				</section>
			
			<a href="menumanagement"class="btn btn-md btn-danger pull-right" style="margin-left: 11px">Cancel</a>
			<button class="btn btn-md btn-info  pull-right"	 id="submit"  type="submit" value="" style="margin-left: 11px">Save</button>&nbsp; 
						</div>
			<table class="table" style="margin-top: -30%">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Director</th>
      <th scope="col">Manager</th>
      <th scope="col">HR</th>
      <th scope="col">Accountant</th>
      <th scope="col">Employee	</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Department Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="dept_director"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="dept_manager"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="dept_hr"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="dept_accountant"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="dept_employee"></td>
      
    </tr>
     <tr>
      <th scope="row">Role Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="role_director"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="role_manager"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="role_hr"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="role_accountant"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="role_employee"></td>
      
    </tr>
     <tr>
      <th scope="row">Employee Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="emp_director"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="emp_manager"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="emp_hr"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Menu Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Team Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Client Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Project Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Task Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Salary Management</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Announcement</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
     <tr>
      <th scope="row">Leave Calendarr</th>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
      
    </tr>
       
  </tbody>
 
</table>
<div class="text-center"> 
		  <!-- <button class="btn btn-lg btn-info"	 id="submit"  type="submit" value="">Submit</button>&nbsp; 
					<a href="/MenuManagement"class="btn btn-lg btn-info">Cancel</a>	 -->
</div>					
		</section>
	</section>
</body>

</html>
