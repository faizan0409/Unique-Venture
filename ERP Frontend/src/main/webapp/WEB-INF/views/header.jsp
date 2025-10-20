<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management System</title>
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
				<li><h3 style="color: white;margin-top: 5%;">Employee Management System</h3></li>

			</ul>

			<ul class="pull-right info-menu user-info">

				<li class="dropdown"><a href="#"> <i class="fa fa-bell"></i><span
						class="label label-warning bg-warning">10</span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
					class="dropdown-toggle" data-toggle="dropdown">Admin<!-- <b
					class="fa fa-angle-down"></b> --></a>
					<ul class="dropdown-menu">
						<li class="text-left"><a href="companyprofile" class="btn btn-info btn-lg " ><i class="fa fa-user"></i>View
								Profile
								</a></li>
						<li><a href="forgotpassword" class="btn btn-info btn-lg"><i
								class="fa fa-cog"></i> Change Password</a></li>
						
						<li ><a href="logout" class="btn btn-info btn-lg"><i
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
				<li><a class="active" href="rolemanagement"><span class="sidebar-icon"><i
							class="fa fa-user-circle "></i></span> <span class="menu-title">Role
							Management</span></a></li>
				<li><a href="employeemanagement"><span class="sidebar-icon"><i
							class="fa fa-users "></i></span> <span class="menu-title">Employee
							Management</span></a></li>
				<li><a href="menumanagement"><span class="sidebar-icon"><i
							class="fa fa-bars "></i></span> <span class="menu-title">Menu
							Management</span></a></li>

				<li><a href="teammanagement"><span class="sidebar-icon"><i
							class="fa fa-user-plus "></i></span> <span class="menu-title">Team
							Management</span></a></li>
				<li><a href="clientmanagement"><span class="sidebar-icon"><i
							class="fa fa-handshake-o"></i></span> <span class="menu-title">Client
							Management</span></a></li>
				<li><a href="projectmanagement"><span class="sidebar-icon"><i
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
					</span></a></li>

			</ul>
		</div>
	</div>
</body>
</html>