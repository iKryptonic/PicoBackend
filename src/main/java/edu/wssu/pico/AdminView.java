package edu.wssu.pico;

import java.io.IOException;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseManager dbm = new DatabaseManager();
        HttpSession session = request.getSession();
        String sessionKey = session.getId();
        boolean isAdmin = dbm.verifyAdminSession(sessionKey);
        
        
        if(isAdmin) {
        	RequestDispatcher view = request.getRequestDispatcher("AdminView.jsp");
    		view.forward(request, response);
        } else {
        	response.sendRedirect("loginview");
        }
	}

}
