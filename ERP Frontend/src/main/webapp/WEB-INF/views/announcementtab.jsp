<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
<link href="resources/css/announcementtab.css" rel="stylesheet" type="text/css">

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

$(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    $('#txtDate').attr('max', maxDate);
});
var uploadField = document.getElementById("file");
uploadField.onchange = function() {
    if(this.files[0].size > 1024){
       alert("File size should less than 2mb!");
       this.value = "";
    };
};

function onlyNumberKey(evt) { 
    
    // Only ASCII charactar in that range allowed 
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) 
        return false; 
    return true; 
} 
function validation(evt) { 
    
    // Only ASCII charactar in that range allowed 
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
    if (ASCIICode > 31) 
        return false; 
    return true; 
} 



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
			<li ><a href="companytab" ><span class="sidebar-icon"><i
						class="fa fa-building"></i></span> <span class="menu-title">company</span></a></li>
			<li class="active"><a href="announcementtab"><span class="sidebar-icon"><i
						class="fa fa-bullhorn "></i></span> <span class="menu-title">Announcement</span></a></li>

		</ul>
	</div>
</div>


<section id="main-content">
	<section class="wrapper main-wrapper row" >
	<section class="box1" style="margin: -7px 0;">
		<h1 class="fa fa-bullhorn fa-2x " style="height: 7%;margin-top: -1%;">&nbsp; &nbsp;Announcement</h1>
	</section>
  <div class="col-md-6"  > 
  <div class="container" style="width:200%;" >
   
    <table class="table table-striped custab">
    <thead><br>
    <a href="#" class="btn btn-primary btn-xs pull-right" data-toggle="modal" data-target="#squarespaceModal"><b>+</b> Add<br> Announcement</a>
        <tr>
            <th>Sr.No</th>
            <th>Date</th>
            <th>Title</th>
            <th class="text-center">Action</th>
        </tr>
    </thead>
            <tr>
                <td>1</td>
                <td>01/01/2020</td>
                <td>Republic Day</td>
            <td class="text-center"><a class='btn btn-info btn-xs'data-toggle="modal" data-target="#squarespaceModal"  ><span class="glyphicon glyphicon-eye-open"></span>View</a>&nbsp;<a href="#" class="btn btn-danger btn-xs"> Resend</a> </td>
               
            </tr>
            <tr>
                  <td>2</td>
                <td>02/02/2020</td>
                <td>Holiday</td>
                <td class="text-center"><a class='btn btn-info btn-xs'  data-toggle="modal" data-target="#squarespaceModal"><span class="glyphicon glyphicon-eye-open"></span>View</a>&nbsp;<a href="#" class="btn btn-danger btn-xs"> Resend</a> </td>
                
            </tr>
            
              <tr>
                  <td>3</td>
                <td>03/03/2020</td>
                <td>Holiday</td>
                <td class="text-center"><a class='btn btn-info btn-xs' data-toggle="modal" data-target="#squarespaceModal"  ><span class="glyphicon glyphicon-eye-open"></span>View</a>&nbsp;<a href="#" class="btn btn-danger btn-xs"> Resend</a> </td>
              
            </tr>
            
            
    </table>     
    <ul class="pagination justify-content-end mt-3 mr-3"  style="margin-left: 70%;">
                        <li class="page-item disabled">
                            <span class="page-link">Previous</span>
                        </li>
                          <li class="page-item active">
                            <span class="page-link">1<span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </ul>
    </div> 
</div>
<div class="modal fade" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" style=" left: 5%;padding-top: 3%" >
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
			<h3 class="modal-title" id="lineModalLabel"> Announcement</h3>
		</div>
		<div class="modal-body">
			
            <!-- content goes here -->
			<form>
              <div class="form-group">
                <label for="s_contact">Sr. No<span class="required" style="color: red" >*</span></label> <input
								type="text" name="s_contact" placeholder="."autocomplete="off" onkeypress="return onlyNumberKey(event)"  
								class="form-control">
              </div>
             <div class=" form-group">
							<label for="text">Date   <span class="required" style="color: red">*</span></label> <input type="date"
								name="date" id="txtDate"  autocomplete="off" onkeypress="return validation(event)"class="form-control" required>
						</div>
            <div class="form-group">
							<label for="title">Title <span class="required" style="color: red">*</span></label> 
							<input type="text" name="title"  placeholder="Enter Company Name Here.." autocomplete="off" class="form-control" required>
						</div>
             <div class="form-group">
						<label for="description">Description <span class="required" style="color: red">*</span></label>
						<textarea name="description" placeholder="Enter Message Here.." rows="3"
							class="form-control" required></textarea>
					</div> 
					<div class=" form-group">
							<label for="company">Company <span class="required" style="color: red">*</span></label><br> <label><input
								type="radio" name="company"> &nbsp;All</label> &nbsp; &nbsp; <label><input
								type="radio" name="company" checked> &nbsp;Selected</label>
								
						</div>
				<div class="form-group">	
              <button type="button" class="btn btn-danger" data-dismiss="modal"  role="button"  style="width: auto">Close</button>
				<button type="submit" class="btn btn-info" style="width: auto" >Submit</button>
				</div>
            </form>

		</div>
		<!-- <div class="modal-footer">
			<div class="btn-group btn-group-justified" role="group" aria-label="group button">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-danger" data-dismiss="modal"  role="button"  style="width: auto">Close</button>
				<button type="submit" class="btn btn-info" style="width: auto" >Submit</button>
				</div>
				
			</div>
		</div> -->
	</div>
  </div>
</div>
  
</section>
</section>


</body>
</html>