<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html> 
	<head>
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
			<p style="text-align:center; color:white; font-size:300%;">
			<br/>
			Idea Submission Form
			</p>
			<br/><br/><br/><br/>

			<p style="text-align:center; color:white; font-size:200%;"> Your submission was uploaded successfully!<br>Thank you!</p>
			
			<br><br><br><br>
			
			<% if((String)request.getAttribute("response") != null){
				%>
				<h4><%=(String)request.getAttribute("response")%></h4>
			<% 
				}
			%>
			
			<div class="video-container center">
				<iframe width="480" height="480" src="https://www.youtube.com/embed/ohWYEmbQBP0" frameborder="0"></iframe>
			</div>
		</div>
	</body> 
	<!-- create the container object for user login -->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="resources/js/materialize.js"></script>
  <script src="resources/js/init.js"></script>
</html>