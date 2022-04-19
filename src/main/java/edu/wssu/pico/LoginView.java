package edu.wssu.pico;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.wssu.pico.utils.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginView
 */
@WebServlet("/LoginView")
public class LoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginView() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        DatabaseManager dbm = new DatabaseManager();

        HttpSession session = request.getSession();
        String sessionKey = session.getId();
        
        boolean isLoggedIn = dbm.verifyAdminSession(sessionKey);
        
        if(isLoggedIn) {
        	response.sendRedirect("adminview");
        } else {
        	RequestDispatcher view = request.getRequestDispatcher("LoginView.jsp");
    		view.forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
        String passWord = request.getParameter("userPassword");
        
        HttpSession session = request.getSession();
        
        String sessionKey = session.getId();

        DatabaseManager dbm = new DatabaseManager();
        
        boolean isLoggedIn = dbm.verifyAdminSession(sessionKey);
        
        if(isLoggedIn) {
        	response.sendRedirect("adminview");
    		return;
        }
        
        String adminID = dbm.verifyAdminCredentials(userName, passWord);
        
        if(adminID != null) {
        	// Valid credentials
        	
        	// Submit session to DB
        	try {
				PreparedStatement pstmt = dbm.newSQL.conn.prepareStatement("INSERT INTO `sessions` VALUES (?, NOW(), ?) ON DUPLICATE KEY UPDATE session_start=NOW(),session_key=?");
				pstmt.setInt(1, Integer.parseInt(adminID));
				pstmt.setString(2, sessionKey);
				pstmt.setString(3, sessionKey);
				dbm.newSQL.executeQuery(pstmt);
			} catch (SQLException e) {
			}
    		doGet(request, response);
        } else {
        	// Invalid credentials
        	request.setAttribute("Error", "Invalid Credentials!");
    		doGet(request, response);
        }
	}

}
