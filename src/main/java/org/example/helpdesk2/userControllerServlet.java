package org.example.helpdesk2;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/userControllerServlet")
public class userControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private userDbUtil theUserDbUtil;
	
	@Resource(name="jdbc/helpdeskDB") private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			theUserDbUtil = new userDbUtil(datasource);
			
		}
		catch(Exception e) {
			throw new ServletException();
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theCommand = request.getParameter("command");
		switch(theCommand) {
		case "CHECK":
			try {
				checkUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		}
	}


	private void checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		
		
        boolean isValidUser = theUserDbUtil.validateUser(email, pw);
        
        
        if (isValidUser) {
            // Create a session for the user
            HttpSession session = request.getSession();
            session.setAttribute("username", email);
            
            // Redirect to the welcome page (or dashboard)
            response.sendRedirect("index.jsp");
           
        } else {
            // If invalid, forward to login.jsp with an error message
            request.setAttribute("errorMessage", "Invalid username or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
		
		
		
		
		
		
		
	}

}
