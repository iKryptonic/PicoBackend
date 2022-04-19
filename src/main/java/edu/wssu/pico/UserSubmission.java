package edu.wssu.pico;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserSubmission
 */
@WebServlet("/UserSubmission")
public class UserSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserSubmission() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
    	RequestDispatcher view = request.getRequestDispatcher("UserSubmission.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        DatabaseManager dbm = new DatabaseManager();
        
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String submissionMessage = "";
		int updatedID = -1;
		try {
			PreparedStatement pstmt = 
					dbm.newSQL.conn.prepareStatement("INSERT INTO `users` VALUES (NULL, ?, ?, ?) ON DUPLICATE KEY UPDATE firstname=?,lastname=?");
			pstmt.setString(1, request.getParameter("firstName"));
			pstmt.setString(2, request.getParameter("lastName"));
			pstmt.setString(3, request.getParameter("email"));
			pstmt.setString(4, request.getParameter("firstName"));
			pstmt.setString(5, request.getParameter("lastName"));
			dbm.newSQL.executeQuery(pstmt);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		try {
				ResultSet currentUser = dbm.newSQL.fetchResult("SELECT id FROM `users` WHERE firstname=\""+request.getParameter("firstName")+"\" and lastname=\""+request.getParameter("lastName")+"\"", false);
				while(currentUser.next()) {
					updatedID = Integer.parseInt(currentUser.getString(1));
				}; // should only be one entry..
				currentUser.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		if(updatedID != -1) {
			try {
				PreparedStatement pstmt = 
						dbm.newSQL.conn.prepareStatement("INSERT INTO `submission` (`userid`, `differentMethods`, `materialsUsed`,`experience`,`roadblocks`,`procedures`,`periods`,`improvements`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setInt(1, updatedID);
				pstmt.setString(2, request.getParameter("question_differentMethods"));
				pstmt.setString(3, request.getParameter("question_materialsUsed"));
				pstmt.setString(4, request.getParameter("question_experience"));
				pstmt.setString(5, request.getParameter("question_roadblocks"));
				pstmt.setString(6, request.getParameter("question_procedures"));
				pstmt.setString(7, request.getParameter("question_period"));
				pstmt.setString(8, request.getParameter("question_improvements"));
				dbm.newSQL.executeQuery(pstmt);
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		} else {
			submissionMessage = "Your submission could not be logged at this time.";
		}
		request.setAttribute("response", submissionMessage);
    	RequestDispatcher view = request.getRequestDispatcher("SubmissionSuccess.jsp");
		view.forward(request, response);
	}

}
