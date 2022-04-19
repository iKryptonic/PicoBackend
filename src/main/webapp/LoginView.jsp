<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Administrator Login</title>		
		<style type="text/css">
			.space {margin-left: 20px;}
			.tab0 {margin-left: 34px;}
			.tab { margin-left: 40px; }
			.tab1 { margin-left: 60px; }
			.tab2 { margin-left: 70px; }
			body {
				background-image: url('resources/img/formBanner.jpg');
				background-repeat: no-repeat;
				background-attachment: fixed;
				background-size: cover;
			}
		</style>
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="resources/css/materialize.min.css"  media="screen,projection"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		
		<meta charset="ISO-8859-1">
	</head>
		
	<body>
		<div class="container">
			<div class="col s6 offset-s3 card-panel center"><span class="flow-text">Administrator Login</span><br><hr><br>
				<% if((String)request.getAttribute("Error") != null){
					%>
					<h4><%=(String)request.getAttribute("Error")%></h4>
				<% 
					}
				%>
				<form action="loginview" method="post"> 
					<div class="col s4 offset-s1">
						User Name:<input type="text" name="userName"/><br/>
					</div>
					<div class="col s4 offset-s1">
						Password:<input type="password" name="userPassword"/><br/>
					</div>
					<div class="col s2 offset-s2">
						<input type="submit" value="submit"/>
					</div>
				</form>
			</div>
		</div>
	</body>
	<!-- create the container object for user login -->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="resources/js/materialize.js"></script>
  <script src="resources/js/init.js"></script>
</html>