//Created By Sumit For user profile page

/* validation for field must have digits only */
 var count=0;
 function callhome()
 {
	 count++;

 }
    function isNumberKey(evt,id){
    	document.getElementById(id).innerHTML = "";
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }
    function IsAlphaNumeric(e,id) {
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
    
    var marker = false;
    var markerposition=false;
    var lat,lng;
    var locchange=false;
    var loop=false;
    //var address="";
    function checklat(address)
    { 
    	
        var geocoder =  new google.maps.Geocoder();
        var zipcode=document.getElementById("zipCode").value;
        var country=document.getElementById("countryID").value;
        var state=document.getElementById("stateID").value;
        geocoder.geocode( { 'address': ''+address+','+state +','+country+','+zipcode+''},
        function(results, status) {
              if (status == google.maps.GeocoderStatus.OK) {
            	  
            	  if(results[0].geometry.location.lat()!=lat && results[0].geometry.location.lng()!=lng)
            		  {
            		  locchange=false;
            		  }
            	  
            	  if(loop==false)
            		  {
            		  lat=document.getElementById('registration_lat').value;
            		  lng=document.getElementById('registration_lng').value;
            		  loop=true;
            		  }else
            		  {
            	   			lat=results[0].geometry.location.lat();  lng=results[0].geometry.location.lng();
            		  }
            
               $('#dialog').css('overflow', 'hidden');
               var mapOptions = {
                   center: new google.maps.LatLng(lat,lng),
                   zoom: 16,
                   mapTypeId: google.maps.MapTypeId.ROADMAP
               }
              
               var map = new google.maps.Map($("#dvMap")[0], mapOptions);
               var latLng = new google.maps.LatLng(lat,lng); // Put lat long as desired.
              
             if(locchange==false)
            	 {
            	// alert("marker");
          	    marker = new google.maps.Marker({
          	    //position: {lat:lat,lng:lng},
          	    position:latLng,
          	    title: 'Marker',
          	    map: map,
          	    draggable: false
          	  });
          	    
          	  markerLocation();
            	 }
             else
            	 {
            	    marker = new google.maps.Marker({
               	    position:markerposition,
               	    title: 'Marker',
               	    map: map,
               	    draggable: false
               	    });
            	 }
             
               google.maps.event.addListener(map, 'click', function(event) { 
    	                var clickedLocation = event.latLng;
    	               
    	                markerposition=clickedLocation;
    	                if(marker === false){
    	                
    	                    //Create the marker.
    	                        marker = new google.maps.Marker({
    	                        position: clickedLocation,
    	                        map: map,
    	                        draggable: false //make it draggable
    	                    });
    	                    //Listen for drag events!
    	                    google.maps.event.addListener(marker, 'dragend', function(event){
    	                        markerLocation();
    	                    });
    	                } else{
    	                	// alert("clickedLocation"+clickedLocation);
    	                	 marker.setPosition(clickedLocation);  
    	                	 locchange=true;
    	                }
    	                markerLocation();
               }); 
                
              } else {
            	 var result = address.substr(address.indexOf(" ") + 1);
            	  checklat(result);
              }
        });
        
        function markerLocation(){
            //Get location.
            var currentLocation = marker.getPosition();
            //Add lat and lng values to a field that we can save.
            document.getElementById('registration_lat').value = currentLocation.lat(); //latitude
            document.getElementById('registration_lng').value = currentLocation.lng(); //longitude   	
            count=0;
        }
    }
    $(function () {
        $("#btnShow").click(function () {
        	//document.getElementById("btnShow").checked = true;
        	if(document.getElementById("address").value=="")
        		{
        		//alert("Please Enter Address First");
        		 document.getElementById("address").innerHTML = "Address should not be empty.";
        		  document.getElementById("address").focus();
        		}else
        			{
    			        $("#dialog").dialog({
    			            modal: true,
    			            title: "Click on map to set your home location",
    			            width: 600,
    			            height: 'auto',
    			            draggable: false,
    			            left: 245,
    			            open: function (event, ui) {
    			                $(".ui-widget-overlay").click(function () {
    			                    $('#dialog').dialog('close');
    			                }); 
    			               if(lat=="" || lat==undefined)
    			            	   {
    			            	   var address=document.getElementById("address").value;
    			            	   checklat(address);
    			            	  }
    			               else
    			            	   {
    			            	    var address=document.getElementById("address").value;
    			            	    checklat(address); 
    			            	    //locchange=false;
    			            	   }
    			               
    			             }        
    			        });
    			        $("#btnShow").click(function () {
    			            $('#dialog').dialog('open');
    			        });
        	 }  
        });
    });   
    var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
	
	/* Validation of all input fields */
 function validate()
  {  
	  if(document.getElementById("firstName").value=="")
	  {
	  
	  document.getElementById("ferror").innerHTML="First name should not be empty.";
	  	document.getElementById("firstName").focus();
	  }
	  else if(document.getElementById("lastName").value=="")
	  {
	  
	  	document.getElementById("lerror").innerHTML="Last name should not be empty.";  
	  	document.getElementById("lastName").focus();
	  }
	  else if(document.getElementById("mobileno").value=="")
	  {
	  
	  	document.getElementById("mnoerror").innerHTML="Mobile no should not be empty.";
	  	document.getElementById("mobileno").focus();
	  }
	  else if(document.getElementById("mobileno").value.length<10){
		
		  document.getElementById("mnoerror").innerHTML="Mobile no should not be less than 10 digits.";
		  document.getElementById("mobileno").focus();
	  }
	 
	  else if(document.getElementById("address").value=="")
	  {
	  	
	  	  document.getElementById("addrerror").innerHTML="Address should not be empty.";
	  	document.getElementById("address").focus();
	  }
	  else if(document.getElementById("city").value=="")
	  {
	  	
	  	document.getElementById("cityerror").innerHTML="Please add city.";
	  	document.getElementById("city").focus();
	  }
	  else if(document.getElementById("zipCode").value=="")
	  {
	  
	  	document.getElementById("zcodeerror").innerHTML="Please add zipcode.";
	  	document.getElementById("zipCode").focus();
	  }else if(document.getElementById("zipCode").value.length<6)
		  {
			document.getElementById("zcodeerror").innerHTML="Please add valid zipcode.";
		  	document.getElementById("zipCode").focus();
		  }
	  else if(document.getElementById("countryID").value=="")
	  {
	  
	  	document.getElementById("countryerror").innerHTML="Please select country.";
	  	document.getElementById("countryID").focus();
	  }
	  else if(document.getElementById("stateID").value=="")
	  {
	  	
	  	document.getElementById("stateerror").innerHTML="Please select state.";
	  	document.getElementById("stateID").focus();
	  }
	  else if(document.getElementById("registration_lat").value == null || document.getElementById("registration_lat").value == ""){
		  document.getElementById("hlocerror").innerHTML="Please set your home location.";
	  }
	  else if(document.getElementById("registration_lng").value == null || document.getElementById("registration_lng").value == ""){
		  document.getElementById("hlocerror").innerHTML="Please set your home location.";
	  }
	  else if(count>0)
		  {
		  
		  document.getElementById("hlocerror").innerHTML="Please set your home location.";
		  }
	  else
		  {
			document.getElementById("edit").value="editProfile";
			document.userform.submit(); 
		}
  }