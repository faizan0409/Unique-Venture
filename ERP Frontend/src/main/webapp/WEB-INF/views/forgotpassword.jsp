<!-- Created By: Siddhi Suryawanshi on Mar 04, 2021-->
<%@ page isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/css/font-awesome.css">
<link href="resources/css/forgotpsd.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

<script type="text/javascript">
	
	(function() {
		$('form > input').keyup(function() {

			var empty = false;
			$('form > input').each(function() {
				if ($(this).val() == '') {
					empty = true;
				}
			});

			if (empty) {
				$('#s').attr('disabled', 'disabled');
			} else {
				$('#s').removeAttr('disabled');
			}
		});
	})()
	
</script>
</head>

<body>
	<div class="form-gap"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h5>
								<i class="fa fa-lock fa-4x"></i>
							</h5>
							<h3 class="text-center">Forgot Password?</h3>
							<p>You can reset your password here.</p>
							<div class="panel-body">

								<form id="register-form" role="form" autocomplete="off"
									data-toggle="validator" action="otp" class="form">

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope color-blue"></i></span> <input
												type="email" id="email" name="email"
												placeholder="Ex.admin@gmail.com" class="form-control"
												pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<button class="btn btn-info" id="sendOTPBtn" type="submit">Send
												OTP</button>
											&nbsp; <a href="companydashboard" class="btn btn-info">Cancel</a>
										</div>
									</div>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>