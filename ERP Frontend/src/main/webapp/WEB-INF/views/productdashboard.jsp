<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
 
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
<link href="resources/css/productdashboard.css" rel="stylesheet" type="text/css">
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
                 <li><a href="login"  ><i class="fa fa-fw fa-power-off"></i> Logout</a></li><!-- <b class="fa fa-angle-down"></b> --></a>
               
            </li>
        </ul>
	</div>
</div>

<div class="page-sidebar expandit">
	<div class="sidebar-inner" id="main-menu-wrapper">
		
		<br><br><br>

		<ul class="wraplist" style="height: auto;">
			
			<li class="active"><a href="productdashboard"><span class="sidebar-icon"><i
						class="fa fa-dashboard"></i></span> <span class="menu-title">Dashboard</span></a></li>
			<li><a href="companytab" ><span class="sidebar-icon"><i
						class="fa fa-building"></i></span> <span class="menu-title">company</span></a></li>
			<li><a href="announcementtab"><span class="sidebar-icon"><i
						class="fa fa-bullhorn "></i></span> <span class="menu-title">Announcement</span></a></li>

		</ul>
	</div>
</div>


<section id="main-content">
	<section class="wrapper main-wrapper row">
	<section class="box1" style="margin: -7px 0;">
		<h1 class="fa fa-dashboard fa-2x " style="height: 7%;margin-top: -1%;">&nbsp; &nbsp;Dashboard</h1>
	</section>
	 <div class="col-md-6" >
			<section class="box"style="cursor: pointer;" onclick="location.href='companytab';">
			                <div class="tf_card_head" >
			                 <h2 class="tf_title " style="margin-left: 10%; padding-top: 8%; "><span class="fa fa-building ">&nbsp; </span>Company
			                 <span style="float:right;padding-right: 10%;margin-left: 15%; padding-top: 1%; ">${dashboard.companyCount}10</span></h2>
			  			   </div>
			          
	       </section>
		</div> 
		<div class="col-md-6">
			<section class="box"style="cursor: pointer;" onclick="location.href='announcementtab';">
			                <div class="tf_card_head" >
			                  <h2 class="tf_title" style="margin-left: 10%; padding-top: 8%; "><span  class="fa fa-bullhorn ">&nbsp; </span>Announcement
			                 <span style="float:right;padding-right: 10%;margin-left: 15%; padding-top: 1%; ">${dashboard.companyCount}05</span></h2>
			                </div>          
	       </section>
		</div>

	</section>
</section>

<script>

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

$(function(){
    $('[data-toggle="tooltip"]').tooltip();
    $(".side-nav .collapse").on("hide.bs.collapse", function() {                   
        $(this).prev().find(".fa").eq(1).removeClass("fa-angle-right").addClass("fa-angle-down");
    });
    $('.side-nav .collapse').on("show.bs.collapse", function() {                        
        $(this).prev().find(".fa").eq(1).removeClass("fa-angle-down").addClass("fa-angle-right");        
    });
})  


</script>
</body>
</html>