<%-- 
    Document   : results
    Created on : Dec 6, 2013, 8:43:47 PM
    Author     : Johanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/general.css" rel="stylesheet" media="screen">
    </head>
    <body>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <div class="header container">
      
           <a class="btn btn-success pull-right"href="index.jsp">Search Again</a>
        
    </div>
    <div class="container">
        
        <h3 class="text-center">Perfect Matches for You</h3>
        <ul id="results">
        <c:forEach var="person" items="${persons}">
            <li>
                <div class="row">
                    <div class="span2">
                        <img class="img-polaroid" style="width:120px;height:170px;" src="${person.photo}"/>                        
                    </div>
                    <div class="span3 ">
                               <div class="formRow">
                                <b>User Number:</b><span>
                                   ${person.personid}
                                </span>
                               </div>
                               <div class="formRow">
                                   <b>First Name:</b><span>${person.firstname}</span>
                               </div>
                               <div class="formRow">
                                   <b>Middle Initial:</b><span>${person.middleinitial}</span>
                               </div>
                               <div class="formRow">
                                   <b>Last Name : </b><span>${person.lastname}</span>
                               </div>
                               <div class="formRow">
                                <b>Birth date:</b>
                                <span><fmt:formatDate type="both" value="${person.birthdate}" pattern="dd-MM-yyyy" />                                       
                                </span>
                               </div>   
                                <div class="formRow">
                                <b>Sex:</b>
                                   <span>${person.sex}
                                </span>
                               </div>
                                  
                       
                    </div>
                    <div class="span3 offset1">
                        <b>Interests</b><br/> 
                        <c:forEach var="interest" items="${person.interests}">
                            ${interest}<br/>
                        </c:forEach>
                                    
                    </div>
                </div>

            </li>
        </c:forEach>
        </ul>
    </div>
    </body>
</html>
