package edu.wssu.pico;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wssu.pico.utils.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminView
 */
@WebServlet("/AdminView")
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminView() {
        // TODO Auto-generated constructor stub
    }
    
    public class UserSubmission {
    	
    	protected int submissionID = -1;
    	protected String firstName = null;
    	protected String lastName = null;
    	protected String email = null;
    	protected String[] responses = new String[7];
    	
    	protected UserSubmission(int submissionID, String firstName, String lastName, String email, String differentMethods, String materialsUsed, String experience, String roadblocks, String procedures, String periods, String improvements) {
    		this.submissionID = submissionID;
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.email = email;
    		
    		responses[0] = differentMethods;
    		responses[1] = materialsUsed;
    		responses[2] = experience;
    		responses[3] = roadblocks;
    		responses[4] = procedures;
    		responses[5] = periods;
    		responses[6] = improvements;
    	}
    	
    	public String[] getResponses() {
    		return this.responses;
    	}
    	
    	public String getFirstName() {
    		return this.firstName;
    	}
    	
    	public String getLastName() {
    		return this.lastName;
    	}
    	
    	public String getEmail() {
    		return this.email;
    	}
    	
    	public int getID() {
    		return this.submissionID;
    	}
    }
    
    public UserSubmission[] getAllSubmissions() throws SQLException {
    	ArrayList<UserSubmission> allSubmissions = new ArrayList<UserSubmission>();
    	DatabaseManager dbm = new DatabaseManager();
    	
    	ResultSet allUsers = dbm.newSQL.fetchResult("SELECT U.id, U.firstName, U.lastName, U.email, S.differentMethods, S.materialsUsed, S.experience, S.roadblocks, S.procedures, S.periods, S.improvements\r\n"
    			+ "FROM users U, submission S \r\n"
    			+ "WHERE U.id=S.userid;", false);
    	while(allUsers.next()) {
			allSubmissions.add(new UserSubmission(allUsers.getInt(1), allUsers.getString(2),allUsers.getString(3),allUsers.getString(4),allUsers.getString(5),allUsers.getString(6),allUsers.getString(7),allUsers.getString(8),allUsers.getString(9),allUsers.getString(10),allUsers.getString(11)));
		}; // should only be one entry..
		allUsers.close();
		
    	return allSubmissions.toArray(new UserSubmission[0]);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseManager dbm = new DatabaseManager();
        HttpSession session = request.getSession();
        String sessionKey = session.getId();
        boolean isAdmin = dbm.verifyAdminSession(sessionKey);
        
        if(isAdmin) {
        	if(request.getParameter("submissionID") != null) {
        		ResultSet userSubmissionResult = null;
				try {
					userSubmissionResult = dbm.newSQL.fetchResult("SELECT U.id, U.firstName, U.lastName, U.email, S.differentMethods, S.materialsUsed, S.experience, S.roadblocks, S.procedures, S.periods, S.improvements\r\n"
							+ "FROM users U, submission S \r\n"
							+ "WHERE U.id=S.userid AND S.userid=\""+request.getParameter("submissionID")+"\";", false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		UserSubmission foundSubmission = null;
            	try {
					while(userSubmissionResult.next()) {
						foundSubmission = new UserSubmission(
								userSubmissionResult.getInt(1), 
								userSubmissionResult.getString(2),
								userSubmissionResult.getString(3),
								userSubmissionResult.getString(4),
								userSubmissionResult.getString(5),
								userSubmissionResult.getString(6),
								userSubmissionResult.getString(7),
								userSubmissionResult.getString(8),
								userSubmissionResult.getString(9),
								userSubmissionResult.getString(10),
								userSubmissionResult.getString(11)
								);
					}
					userSubmissionResult.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}; // should only be one entry..
				
				
        		if(foundSubmission != null) {
        			request.setAttribute("submission", foundSubmission);
        			request.setAttribute("submissionID", foundSubmission.getID());
        			request.setAttribute("FirstName", foundSubmission.getFirstName());
        			request.setAttribute("LastName", foundSubmission.getLastName());
        			request.setAttribute("Email", foundSubmission.getEmail());
        			request.setAttribute("q1", foundSubmission.getResponses()[0]);
        			request.setAttribute("q2", foundSubmission.getResponses()[1]);
        			request.setAttribute("q3", foundSubmission.getResponses()[2]);
        			request.setAttribute("q4", foundSubmission.getResponses()[3]);
        			request.setAttribute("q5", foundSubmission.getResponses()[4]);
        			request.setAttribute("q6", foundSubmission.getResponses()[5]);
        			request.setAttribute("q7", foundSubmission.getResponses()[6]);
            		RequestDispatcher view = request.getRequestDispatcher("SubmissionView.jsp");
            		view.forward(request, response);
        		}
        	} else {
                try {
        			request.setAttribute("Submissions", getAllSubmissions());
        		} catch (SQLException e) {
        			e.printStackTrace(System.out);
        		}
        		RequestDispatcher view = request.getRequestDispatcher("AdminView.jsp");
        		view.forward(request, response);
        	}
        } else {
        	response.sendRedirect("loginview");
        }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String decision = request.getParameter("decision");
        DatabaseManager dbm = new DatabaseManager();
		
		switch(decision) {
		case "accept":
			break;
		case "reject":
			try {
				PreparedStatement pstmt = dbm.newSQL.conn.prepareStatement("DELETE FROM submission S \r\n"
						+ "WHERE S.userid=?;");
				pstmt.setInt(1, Integer.parseInt(request.getParameter("submissionID")));
				dbm.newSQL.executeQuery(pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
			break;
		default:
			break;
		}
		request.removeAttribute("submissionID");
		doGet(request, response);
	}
}
