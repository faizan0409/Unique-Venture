<!-- Author: Faizan
Title: Team Management
Date:15 MARCH 2021  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
<script>
	$(function() {
		for (var i = 0; i < 100; i++) {
			$('.modal-body').append(i + '<br>');
		}
	});
</script>
<style>
/* .user-info li:hover
{
background-color:gray;
}
 */
body {
	font-family: 'Roboto';
}

.required {
	color: red;
}

.modal-dialog, .modal-content {
	height: 90%;
	width: 80%;
	margin-left: -15%
}

.modal-body {
	/* 100% = dialog height, 120px = header + footer */
	max-height: calc(100% - 120px);
	overflow-y: scroll;
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

				<li><a href="companydashboard"><span class="sidebar-icon"><i
							class="fa fa-th"></i></span> <span class="menu-title">Dashboard</span></a></li>
				<li><a href="departmentmanagement"><span
						class="sidebar-icon"><i class="fa fa-braille"></i></span> <span
						class="menu-title">Department Management</span></a></li>
				<li><a href="rolemanagement"><span class="sidebar-icon"><i
							class="fa fa-user-circle "></i></span> <span class="menu-title">Role
							Management</span></a></li>
				<li><a href="employeemanagement"><span class="sidebar-icon"><i
							class="fa fa-users "></i></span> <span class="menu-title">Employee
							Management</span></a></li>
				<li><a href="menumanagement"><span class="sidebar-icon"><i
							class="fa fa-bars "></i></span> <span class="menu-title">Menu
							Management</span></a></li>

				<li><a  href="teammanagement"><span
						class="sidebar-icon"><i class="fa fa-user-plus "></i></span> <span
						class="menu-title">Team Management</span></a></li>
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
				<li><a   class="active" href="announecement"><span class="sidebar-icon"><i
							class="fa fa-bullhorn"></i></span> <span class="menu-title">Announcement
					</span></a></li>

			</ul>
		</div>
	</div>


	<section id="main-content">
		<section class="wrapper main-wrapper row">
			<div class="col-md-7">
				<section class="box1" style="margin: -20px 0;">
					<h1 class="fa fa-bullhorn  fa-2x " style="height: 200%">&nbsp;  &nbsp;Announcement
						</h1>
				</section>
			</div>

		</section>
	</section>
	<div class="container">
		<div class="row col-md-10.9 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>

					<button data-toggle="modal" data-target="#myModal"
						class="btn btn-info btn-xl pull-right" style="margin-left: 11px">
						Add</button>
					<button class="btn btn-danger btn-xm pull-right"
						style="margin-left: 11px">Import</button>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Name</th>
						<th class="text-center">Gender</th>
						<th class="text-center">Contact Number</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>



				<tr>

					<td>1</td>
					<td>Faizan</td>
					<td>Male</td>
					<td>9735285895</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a> <a
						href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>

				<tr>
					<td>2</td>
					<td>ajit</td>
					<td>Male</td>
					<td>9876539856</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a> <a
						href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>siddhi</td>
					<td>Female</td>
					<td>8934765342</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>4</td>
					<td>Gawali</td>
					<td>Male</td>
					<td>9637574794</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>5</td>
					<td>Salman</td>
					<td>Male</td>
					<td>8256874563</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>7</td>
					<td>Aifaz</td>
					<td>Male</td>
					<td>9665873456</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>8</td>
					<td>Atif</td>
					<td>Male</td>
					<td>9534787298</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>9</td>
					<td>Rimsha</td>
					<td>Female</td>
					<td>9879829355</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>
				<tr>
					<td>10</td>
					<td>Aliya</td>
					<td>Female</td>
					<td>9476265688</td>
					<td class="text-center"><a class='btn btn-primary btn-xs'
						href="#"><span class="glyphicon glyphicon-eye-open"></span>
							view </a> &nbsp;<a data-toggle="modal"
						data-target="#squarespaceModal" class='btn btn-info btn-xs'
						href="#"><span class="glyphicon glyphicon-edit"></span> Edit </a>
						<a href="#" class="btn btn-danger btn-xs"><span
							class="glyphicon glyphicon-remove"></span> Del</a></td>
				</tr>

			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">«</span> <span
							class="sr-only">Previous</span>
					</a></li>
					<li class="page-item active"><a class="page-link"
						href="#Products">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">»</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>

	</div>
	<!-- line modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="align: center">
		<div class="modal-dialog modal-lg" style="float: left">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Announcement</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 well">
						<div class="row">
							<form>
								<div class="col-sm-12">
									<div class="form-group">
										<label for="title">Title<span class="required"
											style="color: red">*</span></label> <input type="text"
											class="form-control" id="title" placeholder="Title." required>
									</div>
									<div class="form-group">
										<label for="desc">Description </label>
										<textarea name="desc" placeholder="Description." rows="3"
											class="form-control"></textarea>

										<div class="form-group">
											<label for="send">Send To  </label><br> <select
												name="send" id="send" style="width: 100%" multiple>
												<option value="Security">Ozone</option>
												<option value="Software">Dzone</option>
												<option value="Hardware">Unique Venture</option>

											</select>


										</div>
										<button type="submit" class="btn btn-info">Send</button>
										<a href="announecement" class="btn btn-danger">Cancel</a>


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
