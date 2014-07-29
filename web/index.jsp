<%-- 
    Document   : index
    Created on : Dec 6, 2013, 7:35:28 PM
    Author     : Johanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Match Maker</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/general.css" rel="stylesheet" media="screen">
</head>

<body onload="loadInfo()">
	<div class="container">
	<div class="jumbotron"> 
		<h1 id="logo" class="text-center">Match Maker</h1>
		<img src="img/banner.png"/>

        <form id="search-form" class="form-inline" method="GET" onsubmit="return searchFormValidation();" action="${pageContext.request.contextPath}/Application" >
		
		<select class="input-medium" name="gender">
			<option value="F">Female</option>
			<option value="M">Male</option>
		</select>
		<select class="input-medium" name="minage" id="minage">
			<option value="-1">Min Age</option>
			
		</select>
		<select class="input-medium" name="maxage" id="maxage">
			<option value="-1">Max Age</option>
			
                        
		</select>
		<select class="input-medium" name="interest" id="interestList">
			<option value="-1">Interest</option>
			<option value="-2">do not care</option>
			
		</select>
		<button type="submit" class="btn btn-success" style="margin-left: 10px">Submit</button>
                <input type="hidden" value="search-users" name="action"/>
		</form>
      
	</div>
		
	</div>

        <script>
           
            function searchFormValidation(){
                var select=document.getElementById("interestList").value;
                var min=document.getElementById("minage").value;
                var max=document.getElementById("maxage").value;
                if(select=="-1"){
                    alert("Please choose and option from the interest list");
                    return false;
                }
                if(min>max){
                    alert("Minimun Age must be smaller that Maximum Age");
                    return false;
                }
                return true;
            }
          
            function createXMLHttpRequest() {
                var xmlHttp;
                if (window.ActiveXObject) {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                else if (window.XMLHttpRequest) {
                    xmlHttp = new XMLHttpRequest();
                }
                return xmlHttp;
            }
            function loadInfo(){                
                var xhr=createXMLHttpRequest();
                xhr.open("GET","Application?action=getInfo",true);
                xhr.onreadystatechange=function(){
                    loadResponse(xhr);
                 };
                 xhr.send(null);
            }
            function  loadResponse(xhr){
                var XML;
                if(xhr.readyState==4){
                    if(xhr.status==200){
                      XML=xhr.responseXML;
                      var select =document.getElementById("interestList");
                      var interest = XML.getElementsByTagName("interest");
                      for (var i = 0; i < interest.length; i++) {
                        var int = interest[i].firstChild.nodeValue;
                        var option=document.createElement("option");
                        option.setAttribute("value",int);
                        option.appendChild(document.createTextNode(int));
                        select.appendChild(option);
                      }
                  }
                }
            }
            
                var select=document.getElementById("minage");
                for (var i=18;i<=100;i++){
                        var option=document.createElement("option");
                        option.setAttribute("value",i);
                        option.appendChild(document.createTextNode(i));
                        select.appendChild(option);
                }
                select=document.getElementById("maxage");
                for (var i=18;i<=100;i++){
                        var option=document.createElement("option");
                        option.setAttribute("value",i);
                        option.appendChild(document.createTextNode(i));
                        select.appendChild(option);
                }
        
         

     </script>
</body>
</html>