/**
 * Created by: Madhuri on 02-Jul-2018
 */

function remove_error(id)
  {
	  if( id=="cnferror" || id=="pperror")
	  {
		  document.getElementById("pperror").innerHTML= "";
		  document.getElementById("cnferror").innerHTML= "";
	  }else
	   {
		  	document.getElementById(id).innerHTML= "";
	   }
  }
// Accept alphabates and numeric only
function IsAlphaNumeric(e,id) {
	document.getElementById(id).innerHTML=" ";

	if (e.charCode != 0) {
	    var regex = /^[a-zA-Z0-9]+$/
	    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (!regex.test(key)) {
	      e.preventDefault();
	      return false;
	    }
	  }
}
function IsAlphaNumericspecialwithoutspace(e,id) {
	document.getElementById(id).innerHTML=" ";

	if (e.charCode != 0) {
	    var regex = /^\S+$/g
	    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (!regex.test(key)) {
	      e.preventDefault();
	      return false;
	    }
	  }
}
//Accpet alpha,numeric,special char
function IsAlphaNumericwithspecialchar(e,id) {
	document.getElementById(id).innerHTML = "";
	if (e.charCode != 0) {
		 var regex = /^[a-zA-Z0-9\\\/\@\#\$\%\-\+\.\s]+$/;
	    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (!regex.test(key)) {
	      e.preventDefault();
	      return false;
	    }
	  }
}
//Accpet only number
function isNumberKey(evt,id){
	 document.getElementById(id).innerHTML= "";
	
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
         return false;
     return true;
 }
// Accept only alphabates with space
function isAlpha(e,id) {

	document.getElementById(id).innerHTML = "";
	if (e.charCode != 0) {
		 var regex = /^[a-zA-Z]+$/;
	    var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (!regex.test(key)) {
	      e.preventDefault();
	      return false;
	    }
	  }
}

// Check email already exist
function checkemail(value)
{
	xmlHttp=GetXmlHttpObject()
	var url="checkemail";
	url=url+"?email="+value;
	xmlHttp.onreadystatechange=stateChanged 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}

function stateChanged(){ 
	if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){ 
		var showdata = xmlHttp.responseText; 

		if(showdata=="null")
			{document.getElementById("isemail").value="";
			document.getElementById("mydiv").innerHTML= "";
			}
		else{
			document.getElementById("isemail").value="true";
			document.getElementById("mydiv").innerHTML= showdata;
		} 
}
}
// Check mobile number already exist	
function checkmobile(value)
{
	
	xmlHttp=GetXmlHttpObject()
	var url="checkmobile";
	url=url+"?mobile="+value;
	xmlHttp.onreadystatechange=stateChanged1 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}
function stateChanged1(){ 
		if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){ 
			var showdata1 = xmlHttp.responseText; 

			if(showdata1=="null")
				{
				document.getElementById("ismobile").value="";
				
				//document.getElementById("mydiv1").innerHTML= "";
				}
			else{
				document.getElementById("ismobile").value="true";
				//document.getElementById("mydiv1").innerHTML= showdata1;
				}
			} 
}
//Check username already exist	
function checkuname(value)
{

	xmlHttp=GetXmlHttpObject()
	var url="checkuname";
	url=url+"?uname="+value;
	xmlHttp.onreadystatechange=stateChanged2 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}
function stateChanged2(){ 
		if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){ 
			var showdata2 = xmlHttp.responseText; 

			if(showdata2=="null")
				{
				document.getElementById("isuname").value="";
				
				//document.getElementById("mydiv1").innerHTML= "";
				}
			else{
				document.getElementById("isuname").value="true";
				//document.getElementById("mydiv1").innerHTML= showdata1;
				}
			} 
}
// Ajax Call Global Function	
function GetXmlHttpObject(){
	var xmlHttp=null;
	try{
	xmlHttp=new XMLHttpRequest();
	}
	catch (e) {
	try {
	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	}
	catch (e){
	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	}
	return xmlHttp;
}