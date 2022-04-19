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
	</head>
	<body>
		<p style="text-align:center; color:white; font-size:300%;">
		<br/>
		Idea Submission Form
		</p>
		<br/><br/><br/><br/>
		<form style="text-align:center; color:white;" action="index" method="post">
			First Name: <input class="tab" type="text" name="firstName" required><br/><br/>
			Last Name: <input class="tab" type="text" name="lastName" required><br/><br/>
			Email: <input class="tab2" type="text" name="email" required><br/><br/><br/>
			
			Do you have any experience applying ceramic coatings by different method? <br/><br/>
			<textarea id="differentMethods" name="question_differentMethods" rows="4" cols="50" required>
			</textarea><br/><br/>
			What materials were used in your treatment? <br/><br/>
			<textarea id="materialsUsed" name="question_materialsUsed" rows="4" cols="50" required>
			</textarea><br/><br/>
			What is your experience with applying cold plasma jet surface treatments? <br/><br/>
			<textarea id="experience" name="question_experience" rows="4" cols="50" required>
			</textarea><br/><br/>
			What were some roadblocks encountered during the coating process? <br/><br/>
			<textarea id="roadblocks" name="question_roadblocks" rows="4" cols="50" required>
			</textarea><br/><br/>
			What was the procedure involved with the ceramic coating? <br/><br/>
			<textarea id="procedures" name="question_procedures" rows="4" cols="50" required>
			</textarea><br/><br/>
			How long was the period between maintenance on coating? <br/><br/>
			<textarea id="period" name="question_period" rows="4" cols="50" required>
			</textarea><br/><br/>
			What improvements may you recommend to the process, given your current experience? <br/><br/>
			<textarea id="improvements" name="question_improvements" rows="4" cols="50" required>
			</textarea><br/><br/>
			
			<input type="submit"><input type="reset">
		</form>
	</body> 
</html>