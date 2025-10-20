<!-- Author: Ajit Bankar
Date: 26 Feb 2021 -->
<%@page isELIgnored="false" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/registration.css" rel="stylesheet" type="text/css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>	
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.css">
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
<script type="text/javascript">
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
(function() {
    $('form > input').keyup(function() {

        var empty = false;
        $('form > input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });

        if (empty) {
            $('#submit').attr('disabled', 'disabled');
        } else {
            $('#submit').removeAttr('disabled');
        }
    });
})()

</script>

  
 <script> 
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
    function myFunctionP() {
		var x = document.getElementById("pass");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
    function submitClicked(){
    	
    }
    $(function(){
        setTimeout(function(){
            $("#sleep").hide();
            }, 5000);
          });
</script> 
 
</head>
<body>
<div class="container">
	<h1 class="well" align="center"style="color: green">Company Registration Form</h1>
	<div  id="sleep" ><h3 align="center"style="color: red">${exist}</h3></div>

	<div class="col-lg-12 well">
		<div class="row">
			<form role="form" data-toggle="validator" action="registerotp">	
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="cname">Company Name <span class="required">*</span></label> 
							<input type="text" name="cname"  placeholder="Enter Company Name Here.." autocomplete="off" class="form-control" required>
						</div>
						<div class="col-sm-6 form-group">
							<label for="owner">Company Owner Name <span class="required">*</span></label> <input
								type="text" name="owner" placeholder="Enter Owner First Name &  Last Name Here.." autocomplete="off" pattern="^\S+(\s\S+)+$" 
							onkeypress="return (event.charCode > 64 &&
                          event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode > 31 && event.charCode < 33)" class="form-control" required >
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="text">Registration Date   <span class="required">*</span></label> <input type="date"
								name="date" id="txtDate"  autocomplete="off" onkeypress="return validation(event)"class="form-control" required>
						</div>
						<div class="col-sm-6 form-group">
							<label for="gst">GST Number</label> <input type="text" name="gst"
								placeholder="Enter GST Number Here.." minlength="15" maxlength="15"onkeypress="return (event.charCode > 47 &&
                          event.charCode < 58) || (event.charCode > 64 && event.charCode < 91) || (event.charCode > 96 && event.charCode < 123)" autocomplete="off" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="address">Company Address <span class="required">*</span></label>
						<textarea name="address" placeholder="Enter Address Here.." rows="3"
							class="form-control" required></textarea>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="email">Email <span class="required">* </span></label> <input type="email"
								name="email" placeholder="Enter Email Here.." autocomplete="off"class="form-control"
								pattern="[a-z0-9.-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
						</div> 
						<div class="col-sm-6 form-group">
							<label for="contact"> Contact Number <span class="required">*</span></label> <input type="text"
								name="contact" placeholder="Enter Contact Number Here.."autocomplete="off"  maxlength="10" onkeypress="return onlyNumberKey(event)"  pattern="^\d{10}$"
								class="form-control" required>
						</div>
					</div>
					<div class="row">
				       				
							<div class="col-sm-6 form-group">
							<label for="site">Website</label> <input type="text" name="website"
								placeholder="Enter Website here.." class="form-control" autocomplete="off" pattern="[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$">
						</div>
						<div class="col-sm-6 form-group">
							<label for="s_contact">Support Contact Number</label> <input
								type="text" name="s_contact" placeholder="Enter Support Contact Number Here.."autocomplete="off" onkeypress="return onlyNumberKey(event)"  pattern="^\d{10}$"
								class="form-control" maxlength="10">
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label for="s_email">Support Email Id</label> <input type="text" name="s_email"
								placeholder="Enter Support Email ID Here.." class="form-control" autocomplete="off"
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
						</div>
						<div class="col-sm-6 form-group">
							<label for="s_password">Support Email Password</label> <input
								type="password" placeholder="Enter Support Email Password Here.." name="s_email_password"  onkeypress="return (event.charCode > 32 &&
                          event.charCode < 127)" class="form-control" style="width:100%" autocomplete="off" id="pass"
								class="col-sm-6 form-group">
								<span class="col-sm-6 form-group" style=" margin-left: 85%;margin-top:-9%;">
												<a class="fa fa-eye" onclick="myFunctionP()"></a>
											</span>
						</div>
					</div>
					<div class="row">
						
						<div class="col-sm-6 form-group">
							<label for="company">Company <span class="required">*</span></label><br> <label><input
								type="radio" name="company"value="GOV"> &nbsp;GOV</label> &nbsp; &nbsp; <label><input
								type="radio" name="company" value="NON-GOV" checked> &nbsp;NON-GOV</label>
						</div>
						<div class="row">
						<div class="col-sm-6 form-group">
							<label for="company_type">Company Type <span class="required">*</span></label><br> <select
								name="companytype" id="Companytype" required>
								<option value="Select">Select</option>
								<option value="Small">Small(1-99)</option>
								<option value="Large">Large(100-999)</option>
								<option value="VeryLarge">Very Large(1000 Above)</option>
							</select>


						</div>
					</div>

					<div class="col-sm-6 form-group">
						<label for="logo">Company Logo</label> 
							<input type="file" name="myImage" id="file" accept="application/pdf, image/x-png,image/gif,image/jpeg" />
					</div>
					<div class="col-sm-6 form-group">
					<label for="doc">Company Document's</label> 
						<input type="file" id="file" accept="application/pdf, image/gif,image/jpeg" min="2" max="5" maxlength="2048"  multiple />
					</div>		
					</div>
					
					<div class="form-group">
						<input type="checkbox" required name="terms"> I accept the
						<button type="button" class="btn btn-link" data-toggle="modal"
							data-target="#myModal1">Term and Condition</button>
					</div>
	
			
				<button class="btn btn-lg btn-info" id="submit"  type="submit" value="">Submit</button>&nbsp; 
				
				<a href="/Employee_Management_System"class="btn btn-lg btn-info">Cancel</a>
	


					<div class="modal fade" id="myModal1" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="Submit" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title" style="border-color: black;">Term and Condition </h4>
								</div>
								<div class="modal-body">
									<p>This is Term and Condition </p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">OK</button>	
								</div>
							</div>

						</div>


					</div>
				</div>
				
			</form>
		</div>
	</div>
	
 </div>
</body>
</html> 