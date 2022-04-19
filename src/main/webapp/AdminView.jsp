<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>AdminPanel</title>
		
		<%!
			private void outNewLine(String Bits, jakarta.servlet.jsp.JspWriter myOut)
			{  
			  try{ myOut.println(Bits); } 
			  catch(Exception eek) { }
			}
		%>
		<style>
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
	</head>
	
	<body>
		    <div class="container white">
		        <h3 class="center header">All user submissions</h3>
				<div class="col s12">
					<!-- Data Retainer -->
					<div>
						<ul class="collection">
							<% /* Event[] allEvents = (Event[]) request.getAttribute("Events");
						        int pointer = 0;
					            outNewLine("<tr>", out);
					            if(allEvents != null)
					            {
							        for(Event e : allEvents)
							        {
							            if(e != null)
							           		outNewLine("<li class=\"collection-item\"><div>" + e.getName() + " - " + e.getDate() + " - " + e.getGenre() + "<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"/RamsHack2021-team-6/eventdetails?eventID=" + e.getID() + "\" class=\"secondary-content\"><i class=\"material-icons\">send</i></a></div></li>", out);
								  	}
					            }*/
						  	%>
						</ul>
					</div>
				</div>
		    </div>
	</body>
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="resources/js/materialize.js"></script>
  <script src="resources/js/init.js"></script>
</html>