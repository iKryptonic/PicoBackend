<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="edu.wssu.pico.AdminView.UserSubmission" %>
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
		<meta charset="ISO-8859-1">
		<title>Submission View</title>
	</head>
	
	<body>
		<div class="container white">
		        <h3 class="center header">User Submission</h3>
				<div class="col s12">
					<!-- Data Retainer -->
					<div>
						<form style="text-align:center; color:white;" action="adminview" method="post">
							First Name: <input value="${ FirstName }" class="tab" type="text" disabled><br/><br/>
							Last Name: <input value="${ LastName }" class="tab" type="text" disabled><br/><br/>
							Email: <input value="${ Email }" class="tab2" type="text"  disabled><br/><br/><br/>
							
							Do you have any experience applying ceramic coatings by different method? <br/><br/>
							<textarea id="differentMethods"  rows="4" cols="50" disabled>${q1}</textarea><br/><br/>
							What materials were used in your treatment? <br/><br/>
							<textarea id="materialsUsed"  rows="4" cols="50" disabled>${q2}</textarea><br/><br/>
							What is your experience with applying cold plasma jet surface treatments? <br/><br/>
							<textarea id="experience" rows="4" cols="50" disabled>${q3}</textarea><br/><br/>
							What were some roadblocks encountered during the coating process? <br/><br/>
							<textarea id="roadblocks" rows="4" cols="50" disabled>${q4}</textarea><br/><br/>
							What was the procedure involved with the ceramic coating? <br/><br/>
							<textarea id="procedures" rows="4" cols="50" disabled>${q5}</textarea><br/><br/>
							How long was the period between maintenance on coating? <br/><br/>
							<textarea id="period" rows="4" cols="50" disabled>${q6}</textarea><br/><br/>
							What improvements may you recommend to the process, given your current experience? <br/><br/>
							<textarea id="improvements" rows="4" cols="50" disabled>${q7}</textarea><br/><br/>
							
							<input name="submissionID" type="hidden" value="${submissionID}">
							<button name="decision" value="accept">Accept</button>
							<button name="decision" value=back>Go Back</button>
							<button name="decision" value="reject">Reject</button>
						</form>
					</div>
				</div>
		    </div>
	</body>
</html>