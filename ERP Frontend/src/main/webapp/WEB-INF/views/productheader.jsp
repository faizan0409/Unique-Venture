<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<script type="text/javascript">
$(document).ready(function() {
	   $('#menu_icon').click(function() {
	      if ($('.page-sidebar').hasClass('expandit')){
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

</script> 
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
			<li><h3 style="color: white">Employee Management System</h3></li>

		</ul>  

        <ul class="pull-right info-menu user-info">
               
         <li class="dropdown">
         
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" style=" background-color: #eeeeee3d;" >Admin<!-- <b class="fa fa-angle-down"></b> --></a>
                <ul class="dropdown-menu">
                    <li><a href="forgotpassword"><i class="fa fa-fw fa-cog"></i> Change Password</a></li>
                   <!--  <li class="divider"></li> -->
                    <li><a href="login"><i class="fa fa-fw fa-power-off"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
	</div>
</div>

<div class="page-sidebar expandit">
	<div class="sidebar-inner" id="main-menu-wrapper">
		
		<br><br><br>

		<ul class="wraplist" style="height: auto;">
			
			<li ><a href="productdashboard"><span class="sidebar-icon"><i
						class="fa fa-dashboard"></i></span> <span class="menu-title">Dashboard</span></a></li>
			<li class="active"><a href="companytab" ><span class="sidebar-icon"><i
						class="fa fa-building"></i></span> <span class="menu-title">company</span></a></li>
			<li><a href="announcementtab"><span class="sidebar-icon"><i
						class="fa fa-bullhorn "></i></span> <span class="menu-title">Announcement</span></a></li>

		</ul>
	</div>
</div>




</body>
</html>